package pao.de.queijo.omdbmtc.ui.view;

import pao.de.queijo.omdbmtc.data.model.Movie;

/**
 * @author Lucas Campos
 * @since 1.0.0
 */

public interface MainView {
    void showMovieNotFound();

    void bindResult(Movie movie);
}
