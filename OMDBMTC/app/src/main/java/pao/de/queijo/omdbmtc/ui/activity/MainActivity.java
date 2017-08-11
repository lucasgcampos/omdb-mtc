package pao.de.queijo.omdbmtc.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import pao.de.queijo.omdbmtc.R;
import pao.de.queijo.omdbmtc.config.RetrofitConfig;
import pao.de.queijo.omdbmtc.data.model.Movie;
import pao.de.queijo.omdbmtc.ui.adapter.MovieAdapter;
import pao.de.queijo.omdbmtc.ui.presenter.MainPresenter;
import pao.de.queijo.omdbmtc.ui.view.MainView;

/**
 * @author Lucas Campos
 * @since 1.0.0
 */
public class MainActivity extends AppCompatActivity implements MainView {

    private static final int SHOW_LOADER = 2;
    private static final int SHOW_RESULTS = 1;

    private MainPresenter presenter;

    @BindView(R.id.title)
    EditText title;

    @BindView(R.id.year)
    EditText year;

    @BindView(R.id.search)
    Button search;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.view_flipper)
    ViewFlipper viewFlipper;

    private List<Movie> favorites = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        presenter = new MainPresenter(this, RetrofitConfig.create("http://www.omdbapi.com/").createService(), AndroidSchedulers.mainThread());
    }

    @Override
    public void showMovieNotFound() {
        new AlertDialog.Builder(this)
                .setMessage("Filme não encontrado")
                .setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.dismiss()).create()
                .show();
    }

    @Override
    public void bindResult(List<Movie> movies) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MovieAdapter(movies, favorites));
    }

    @Override
    public void showInvalidNumberFormat() {
        new AlertDialog.Builder(this)
                .setMessage("Valor do ano inválido")
                .setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.dismiss()).create()
                .show();
    }

    @Override
    public void flipLoader() {
        viewFlipper.setDisplayedChild(SHOW_LOADER);
    }

    @Override
    public void flipContent() {
        viewFlipper.showPrevious();
    }

    @Override
    public void flipResults() {
        viewFlipper.setDisplayedChild(SHOW_RESULTS);
    }

    @Override
    public void showWireNotFound() {
        new AlertDialog.Builder(this)
                .setMessage("Conecte-se a uma rede (:")
                .setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.dismiss()).create()
                .show();
    }

    @Override
    public void showSomethingWrongHappen() {
        new AlertDialog.Builder(this)
                .setMessage("Alguma coisa errada não está certa. Whaaaat?")
                .setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.dismiss()).create()
                .show();
    }

    @OnClick(R.id.search)
    void onClickSearch() {
        if (search.isEnabled()) {
            presenter.fetch(title.getText().toString(), year.getText().toString());
        }
    }

}
