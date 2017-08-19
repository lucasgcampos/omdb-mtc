package pao.de.queijo.omdbmtc.ui.activity;


import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pao.de.queijo.omdbmtc.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public IntentsTestRule<MainActivity> activityRule = new IntentsTestRule<>(MainActivity.class, true, false);

    @Test
    public void mainActivityTest() throws InterruptedException {
        activityRule.launchActivity(new Intent());

        onView(withId(R.id.search_title)).perform(typeText("spider man"));

        onView(withId(R.id.search)).perform(click());

        onView(withText("Spider-Man: Homecoming")).perform(click());

        intended(hasComponent(MovieDetailsActivity.class.getName()));
    }

}
