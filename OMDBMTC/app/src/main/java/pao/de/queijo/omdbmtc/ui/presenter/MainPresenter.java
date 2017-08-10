package pao.de.queijo.omdbmtc.ui.presenter;

import pao.de.queijo.omdbmtc.data.OmdbApi;
import pao.de.queijo.omdbmtc.ui.view.MainView;

/**
 * @author Lucas Campos
 * @since 1.0.0
 */

public class MainPresenter {

    private final MainView view;
    private final OmdbApi api;

    public MainPresenter(MainView view, OmdbApi api) {
        this.api = api;
        this.view = view;
    }

    public void fetch(String title, String year) {
        api.getMovieByName(title)
                .subscribe(response -> {
                    if (response.isResponse()) {
                        view.bindResult(response);
                    } else {
                        view.showMovieNotFound();
                    }
                });
    }
}
