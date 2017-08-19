package pao.de.queijo.omdbmtc.ui.presenter;

import java.net.UnknownHostException;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import pao.de.queijo.omdbmtc.data.OmdbApi;
import pao.de.queijo.omdbmtc.data.model.MovieResponse;
import pao.de.queijo.omdbmtc.ui.view.MainView;

/**
 * @author Lucas Campos
 * @since 1.0.0
 */

public class MainPresenter {

    private final MainView view;
    private final Scheduler scheduler;
    private final OmdbApi api;

    public MainPresenter(MainView view, OmdbApi api, Scheduler scheduler) {
        this.api = api;
        this.view = view;
        this.scheduler = scheduler;
    }

    public void fetch(String title, String year) {
        if (year.isEmpty()) {
            execute(api.getMovieByName(title));
        } else {
            try {
                execute(api.getMovieByNameAndYear(title, Integer.valueOf(year)));
            } catch (NumberFormatException e) {
                view.showInvalidNumberFormat();
            }
        }
    }

    private void execute(Observable<MovieResponse> response) {
        response
                .observeOn(scheduler)
                .doOnSubscribe(Void -> view.flipLoader())
                .doOnError(Void -> view.flipContent())
                .subscribe(result -> {
                    if (result.isResponse()) {
                        view.bindResult(result.getSearch());
                        view.flipResults();
                    } else {
                        view.flipContent();
                        view.showMovieNotFound();
                    }}, error -> {
                            if (error instanceof UnknownHostException) {
                                view.showWireNotFound();
                            } else {
                                view.showSomethingWrongHappen();
                            }});
    }

}
