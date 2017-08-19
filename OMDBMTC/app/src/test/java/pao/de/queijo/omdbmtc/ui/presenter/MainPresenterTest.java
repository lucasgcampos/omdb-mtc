package pao.de.queijo.omdbmtc.ui.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.UnknownHostException;

import io.reactivex.Observable;
import pao.de.queijo.omdbmtc.data.OmdbApi;
import pao.de.queijo.omdbmtc.data.model.Movie;
import pao.de.queijo.omdbmtc.data.model.MovieResponse;
import pao.de.queijo.omdbmtc.ui.view.MainView;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pao.de.queijo.omdbmtc.TestHelper.jUnitScheduler;

/**
 * @author Lucas Campos
 * @since 1.0.0
 */
@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock private MainView view;
    @Mock private OmdbApi api;

    private MainPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new MainPresenter(view, api, jUnitScheduler);
    }

    @Test
    public void shouldFetchOnlyByNameWhenYearIsEmpty() throws Exception {
        // given
        String someTitle = "title";
        String emptyYear = "";

        when(api.getMovieByName("title")).thenReturn(Observable.just(new MovieResponse()));

        // when
        presenter.fetch(someTitle, emptyYear);

        // then
        verify(api).getMovieByName(someTitle);
    }

    @Test
    public void shouldFetchByNameAndYearWhenNameAndYearIsValid() throws Exception {
        // given
        String someTitle = "title";
        String someYear = "2007";

        when(api.getMovieByNameAndYear("title", 2007)).thenReturn(Observable.just(new MovieResponse()));

        // when
        presenter.fetch(someTitle, someYear);

        // then
        verify(api).getMovieByNameAndYear(someTitle, Integer.valueOf(someYear));
    }

    @Test
    public void shouldBindResultsWhenFoundListOfMovies() throws Exception {
        // given
        String someTitle = "spiderman";
        MovieResponse response = new MovieResponse(singletonList(new Movie()), true);

        when(api.getMovieByName(someTitle)).thenReturn(Observable.just(response));

        // when
        presenter.fetch(someTitle, "");

        // then
        verify(view).flipLoader();
        verify(view).bindResult(response.getSearch());
        verify(view).flipResults();
    }

    @Test
    public void shouldShowMovieNotFoundWhenMovieNotFound() throws Exception {
        // given
        String someTitle = "movie not exist";

        when(api.getMovieByName("movie not exist")).thenReturn(Observable.just(new MovieResponse()));

        // when
        presenter.fetch(someTitle, "");

        // then
        verify(view).showMovieNotFound();
    }

    @Test
    public void shouldShowInvalidNumberFormatWhenValueOfYearIsInvalid() throws Exception {
        // given
        String someTitle = "title";
        String invalidYear = "abc";

        // when
        presenter.fetch(someTitle, invalidYear);

        // then
        verify(view).showInvalidNumberFormat();
    }

    @Test
    public void shouldShowNotConnectedWhenUserNotBeConnected() throws Exception {
        // given
        String someTitle = "some movie";

        when(api.getMovieByName(someTitle)).thenReturn(Observable.error(new UnknownHostException()));

        // when
        presenter.fetch(someTitle, "");

        // then
        verify(view).showWireNotFound();
    }

    @Test
    public void shouldShowSomethingWrongHappenWhenSomeNotExpectedErrorHappen() throws Exception {
        // given
        String someTitle = "some movie";

        when(api.getMovieByName(someTitle)).thenReturn(Observable.error(new Throwable()));

        // when
        presenter.fetch(someTitle, "");

        // then
        verify(view).showSomethingWrongHappen();
    }
}