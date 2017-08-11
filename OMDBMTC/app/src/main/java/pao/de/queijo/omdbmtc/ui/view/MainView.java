package pao.de.queijo.omdbmtc.ui.view;

import java.util.List;

import io.reactivex.disposables.Disposable;
import pao.de.queijo.omdbmtc.data.model.Movie;

/**
 * @author Lucas Campos
 * @since 1.0.0
 */

public interface MainView {
    void showMovieNotFound();
    void showInvalidNumberFormat();
    void bindResult(List<Movie> movie);

    void flipLoader();
    void flipContent();
    void flipResults();

    void showWireNotFound();
    void showSomethingWrongHappen();
}
