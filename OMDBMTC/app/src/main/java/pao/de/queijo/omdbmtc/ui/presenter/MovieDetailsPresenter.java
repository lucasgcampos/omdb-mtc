package pao.de.queijo.omdbmtc.ui.presenter;

import java.net.UnknownHostException;

import io.reactivex.Scheduler;
import pao.de.queijo.omdbmtc.data.OmdbApi;
import pao.de.queijo.omdbmtc.data.model.MovieDetail;
import pao.de.queijo.omdbmtc.ui.view.MovieDetailsView;

/**
 * @author Lucas Campos
 * @since 1.0.0
 */

public class MovieDetailsPresenter {

    private final OmdbApi api;
    private final Scheduler scheduler;
    private final MovieDetailsView view;

    public MovieDetailsPresenter(MovieDetailsView view, OmdbApi api, Scheduler scheduler) {
        this.api = api;
        this.view = view;
        this.scheduler = scheduler;
    }

    public void fetchData(String imdbID) {
        api.getMovieDetails(imdbID)
                .observeOn(scheduler)
                .doOnSubscribe(Void -> view.flipLoader())
                .doOnError(Void -> view.flipContent())
                .doOnComplete(view::flipContent)
                .filter(MovieDetail::getResponse)
                .subscribe(view::bind, error -> {
                    if (error instanceof UnknownHostException) {
                        view.showWireNotFound();
                    } else {
                        view.showSomethingWrongHappen();
                    }
                });
    }
}
