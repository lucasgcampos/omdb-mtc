package pao.de.queijo.omdbmtc.ui.activity;


import android.content.Intent;
import android.support.test.espresso.accessibility.AccessibilityChecks;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import pao.de.queijo.omdbmtc.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public IntentsTestRule<MainActivity> activityRule = new IntentsTestRule<>(MainActivity.class, true, false);

    @BeforeClass
    public static void enableAccessibilityChecks() {
        AccessibilityChecks.enable().setRunChecksFromRootView(true);
    }

    @Test
    public void shouldOpenTheCorrectlyMovie() {
        activityRule.launchActivity(new Intent());

        searchMovie("Batman");
        selectMovie("Batman Begins");

        intended(hasComponent(MovieDetailsActivity.class.getName()));
    }

    @Test
    public void verifyFavoriteComponents() {
        activityRule.launchActivity(new Intent());

        searchMovie("Hey");
        selectMovie("Hey Arnold!");
        verifyMovieDetails();
    }

    @Test
    public void shouldSeeErrorMessageWhenPerformSearchWithoutTitle() {
        activityRule.launchActivity(new Intent());

        onView(withId(R.id.year)).perform(typeText("1099"));
        onView(withId(R.id.error)).check(matches(isDisplayed()));
    }

    @Test
    public void searchButtonShouldStillDisableWithoutTitle() {
        activityRule.launchActivity(new Intent());

        onView(withId(R.id.year)).perform(typeText("1099"));
        onView(withId(R.id.search)).check(matches(not(isEnabled())));
    }

    private void verifyMovieDetails() {
        onView(withId(R.id.title)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.subtitle)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.poster)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.description)).check(matches(isCompletelyDisplayed()));
    }

    private void searchMovie(String movie) {
        onView(withId(R.id.search_title)).perform(typeText(movie));
        onView(withId(R.id.search)).perform(click());
    }

    private void selectMovie(String movie) {
        onView(withText(movie)).check(matches(isCompletelyDisplayed()))
                .perform(click());

    }

    private void tapBack() {
        onView(withContentDescription("voltar"))
                .check(matches(isDisplayed()))
                .perform(click());
    }
}