package pao.de.queijo.omdbmtc.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pao.de.queijo.omdbmtc.R;
import pao.de.queijo.omdbmtc.data.model.Movie;

/**
 * @author Lucas Campos
 * @since 1.0.0
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private final List<Movie> movies;
    private final List<Movie> favorites;

    public MovieAdapter(List<Movie> movies, List<Movie> favorites) {
        this.movies = movies;
        this.favorites = favorites;
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieAdapter.ViewHolder holder, int position) {
        Movie movie = movies.get(position);

        holder.title.setText(movie.getTitle());
        holder.year.setText(movie.getYear());

        if (favorites.contains(movie)) {
            holder.favorite.setImageResource(R.drawable.ic_favorite);
        } else {
            holder.favorite.setImageResource(R.drawable.ic_favorite_border);
        }

        holder.favorite.setOnClickListener(view -> {
            if (favorites.contains(movie)) {
                favorites.remove(movie);
                holder.favorite.setImageResource(R.drawable.ic_favorite_border);
            } else {
                favorites.add(movie);
                holder.favorite.setImageResource(R.drawable.ic_favorite);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies != null ? movies.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.year)
        TextView year;

        @BindView(R.id.favorite)
        ImageView favorite;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
