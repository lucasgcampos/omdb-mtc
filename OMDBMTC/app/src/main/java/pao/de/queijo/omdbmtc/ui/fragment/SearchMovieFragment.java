package pao.de.queijo.omdbmtc.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import pao.de.queijo.omdbmtc.R;
import pao.de.queijo.omdbmtc.config.RetrofitConfig;
import pao.de.queijo.omdbmtc.config.ViewUtils;
import pao.de.queijo.omdbmtc.data.model.Movie;
import pao.de.queijo.omdbmtc.ui.activity.MainActivity;
import pao.de.queijo.omdbmtc.ui.activity.MovieDetailsActivity;
import pao.de.queijo.omdbmtc.ui.adapter.MovieAdapter;
import pao.de.queijo.omdbmtc.ui.presenter.MainPresenter;
import pao.de.queijo.omdbmtc.ui.view.MainView;

import static pao.de.queijo.omdbmtc.ui.activity.MainActivity.EXTRAS_IS_FAVORITE;
import static pao.de.queijo.omdbmtc.ui.activity.MainActivity.EXTRAS_MOVIE;
import static pao.de.queijo.omdbmtc.ui.activity.MainActivity.IDENTIFIER;

/**
 * @author Lucas Campos
 * @since 1.0.0
 */

public class SearchMovieFragment extends Fragment implements MainView, MainActivity.OnSelectItem {

    private static final int SHOW_LOADER = 2;
    private static final int SHOW_RESULTS = 1;
    private static final int SHOW_EMPTY_STATE = 0;

    @BindView(R.id.search_title)
    EditText title;

    @BindView(R.id.year)
    EditText year;

    @BindView(R.id.search)
    Button search;

    @BindView(R.id.error)
    TextView errorMessage;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.view_flipper)
    ViewFlipper viewFlipper;

    private Unbinder bind;
    private MainPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_movies, container, false);
        bind = ButterKnife.bind(this, view);

        presenter = new MainPresenter(this, RetrofitConfig.create("http://www.omdbapi.com/").createService(), AndroidSchedulers.mainThread());

        title.setOnFocusChangeListener((view1, isFocused) -> {
            if (isFocused) {
                errorMessage.setVisibility(View.INVISIBLE);
            } else {
                if (title != null && title.getText().toString().isEmpty()) {
                    errorMessage.setVisibility(View.VISIBLE);
                    search.setEnabled(false);
                }
            }
        });

        RxTextView.textChanges(title)
                .map(CharSequence::toString)
                .subscribe(result -> {
                    if (result.isEmpty()) {
                        search.setEnabled(false);
                    } else {
                        search.setEnabled(true);
                    }
                });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }

    @Override
    public void bindResult(List<Movie> movies) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new MovieAdapter(movies, getFavorites(), this));
    }

    private List<Movie> getFavorites() {
        return ((MainActivity) getActivity()).getFavorites();
    }

    @Override
    public void showInvalidNumberFormat() {
        new AlertDialog.Builder(getContext())
                .setMessage("Valor do ano inválido")
                .setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.dismiss()).create()
                .show();
    }

    @Override
    public void showMovieNotFound() {
        new AlertDialog.Builder(getContext())
                .setMessage("Filme não encontrado")
                .setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.dismiss()).create()
                .show();
    }

    @Override
    public void flipLoader() {
        viewFlipper.setDisplayedChild(SHOW_LOADER);
    }

    @Override
    public void flipContent() {
        if (recyclerView.getAdapter() == null) {
            viewFlipper.setDisplayedChild(SHOW_EMPTY_STATE);
        } else {
            viewFlipper.setDisplayedChild(SHOW_RESULTS);
        }
    }

    @Override
    public void flipResults() {
        viewFlipper.setDisplayedChild(SHOW_RESULTS);
    }

    @Override
    public void showWireNotFound() {
        new AlertDialog.Builder(getContext())
                .setMessage("Conecte-se a uma rede (:")
                .setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.dismiss()).create()
                .show();
    }

    @Override
    public void showSomethingWrongHappen() {
        new AlertDialog.Builder(getContext())
                .setMessage("Alguma coisa errada não está certa. Whaaaat?")
                .setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.dismiss()).create()
                .show();
    }

    @OnClick(R.id.search)
    void onClickSearch() {
        if (search.isEnabled()) {
            hideKeyboard();
            presenter.fetch(title.getText().toString().trim(), year.getText().toString());
        }
    }

    @Override
    public void onItemSelected(Movie movie) {
        Intent intent = new Intent(getActivity(), MovieDetailsActivity.class);
        intent.putExtra(EXTRAS_MOVIE, movie);
        intent.putExtra(EXTRAS_IS_FAVORITE, getFavorites().contains(movie));
        startActivityForResult(intent, IDENTIFIER);
    }

    private void hideKeyboard() {
        title.clearFocus();
        year.clearFocus();
        search.requestFocus();
        ViewUtils.hideKeyboard(title);
    }

    public void notifyChange() {
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}
