package pao.de.queijo.omdbmtc.ui.view;

import pao.de.queijo.omdbmtc.data.model.MovieDetail;

/**
 * @author Lucas Campos
 * @since 1.0.0
 */

public interface MovieDetailsView {

    void bind(MovieDetail detail);

    void flipLoader();

    void flipContent();

    void showWireNotFound();

    void showSomethingWrongHappen();
}
