package pao.de.queijo.omdbmtc.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pao.de.queijo.omdbmtc.R;
import pao.de.queijo.omdbmtc.data.model.Movie;
import pao.de.queijo.omdbmtc.ui.activity.MainActivity;
import pao.de.queijo.omdbmtc.ui.activity.MovieDetailsActivity;
import pao.de.queijo.omdbmtc.ui.adapter.FavoriteAdapter;

import static pao.de.queijo.omdbmtc.ui.activity.MainActivity.EXTRAS_IS_FAVORITE;
import static pao.de.queijo.omdbmtc.ui.activity.MainActivity.EXTRAS_MOVIE;
import static pao.de.queijo.omdbmtc.ui.activity.MainActivity.IDENTIFIER;

/**
 * @author Lucas Campos
 * @since 1.0.0
 */

public class FavoriteFragment extends Fragment implements MainActivity.OnSelectItem {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.view_flipper)
    ViewFlipper viewFlipper;

    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        bind = ButterKnife.bind(this, view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Movie> favorites = ((MainActivity) getActivity()).getFavorites();
        fetchData(favorites);

        return view;
    }
    public void fetchData(List<Movie> favorites) {
        if (favorites.isEmpty()) {
            viewFlipper.setDisplayedChild(0);
        } else {
            viewFlipper.setDisplayedChild(1);

            recyclerView.setAdapter(new FavoriteAdapter(((MainActivity) getActivity()).getFavorites(), this));
        }
    }

    @Override
    public void onItemSelected(Movie movie) {
        Intent intent = new Intent(getActivity(), MovieDetailsActivity.class);
        intent.putExtra(EXTRAS_MOVIE, movie);
        intent.putExtra(EXTRAS_IS_FAVORITE, true);
        startActivityForResult(intent, IDENTIFIER);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }
}
