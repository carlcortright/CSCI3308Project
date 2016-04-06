import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.slack.csci3308project.dailyfortune.MainActivity;
import com.slack.csci3308project.dailyfortune.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> myActivityRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void generalButtonShouldGetQuote(){
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.quote)).check(matches(isDisplayed()));
    }

    @Test
    public void sportsButtonShouldGetQuote() {
        onView(withId(R.id.button3)).perform(click());
        onView(withId(R.id.quote)).check(matches(isDisplayed()));
    }

    @Test
        public void educationalButtonShouldGetQuote() {
            onView(withId(R.id.button2)).perform(click());
            onView(withId(R.id.quote)).check(matches(isDisplayed()));
        }

    @Test
    public void generalClickHidesButtons(){
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.button)).check(matches(not(isDisplayed())));
        onView(withId(R.id.button2)).check(matches(not(isDisplayed())));
        onView(withId(R.id.button3)).check(matches(not(isDisplayed())));
    }

    @Test
    public void educationalClickHidesButtons(){
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.button)).check(matches(not(isDisplayed())));
        onView(withId(R.id.button2)).check(matches(not(isDisplayed())));
        onView(withId(R.id.button3)).check(matches(not(isDisplayed())));
    }

    @Test
    public void sportsClickHidesButtons(){
        onView(withId(R.id.button3)).perform(click());
        onView(withId(R.id.button)).check(matches(not(isDisplayed())));
        onView(withId(R.id.button2)).check(matches(not(isDisplayed())));
        onView(withId(R.id.button3)).check(matches(not(isDisplayed())));
    }

    @Test
    public void moreQuotesTest(){
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.button4)).perform(click());
        onView(withId(R.id.button)).check(matches(isDisplayed()));
        onView(withId(R.id.button2)).check(matches(isDisplayed()));
        onView(withId(R.id.button3)).check(matches(isDisplayed()));
        onView(withId(R.id.quote)).check(matches(not(isDisplayed())));
    }
}