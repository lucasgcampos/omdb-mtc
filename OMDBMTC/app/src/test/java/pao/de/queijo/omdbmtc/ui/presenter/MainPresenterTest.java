package pao.de.queijo.omdbmtc.ui.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import pao.de.queijo.omdbmtc.data.OmdbApi;
import pao.de.queijo.omdbmtc.ui.view.MainView;

import static org.mockito.Mockito.verify;
import static pao.de.queijo.omdbmtc.TestHelper.jUnitScheduler;

/**
 * @author Lucas Campos
 * @since 1.0.0
 */
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

        // when
        presenter.fetch(someTitle, someYear);

        // then
        verify(api).getMovieByNameAndYear(someTitle, Integer.valueOf(someYear));
    }

    @Test
    public void shouldShowMovieNotFoundWhenTitleDoesNotMatch() throws Exception {

    }

    @Test
    public void shouldBindResultWhenFindResults() throws Exception {
        

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
}