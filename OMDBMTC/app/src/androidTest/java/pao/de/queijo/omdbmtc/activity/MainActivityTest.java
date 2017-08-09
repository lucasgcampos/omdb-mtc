package pao.de.queijo.omdbmtc.activity;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pao.de.queijo.omdbmtc.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    ActivityTestRule<MainActivity> a = new ActivityTestRule<>(MainActivity.class, true, false);


    @Test
    public void useAppContext() throws Exception {
        onView(withId(R.id.title)).perform(ViewActions.typeText("Superman"));
        onView(withId(R.id.search)).perform(click());


    }
}
