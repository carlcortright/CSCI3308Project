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


    //Test that a quote is displayed when the general button is clicked.
    @Test
    public void generalButtonShouldGetQuote(){
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.quote)).check(matches(isDisplayed()));
    }

    //Test that a quote is displayed when the sports button is clicked.
    @Test
    public void sportsButtonShouldGetQuote() {
        onView(withId(R.id.button3)).perform(click());
        onView(withId(R.id.quote)).check(matches(isDisplayed()));
    }

    //Test that a quote is displayed when the educational button is clicked.
    @Test
        public void educationalButtonShouldGetQuote() {
            onView(withId(R.id.button2)).perform(click());
            onView(withId(R.id.quote)).check(matches(isDisplayed()));
        }

    //Test that when the "General" button is clicked, all the quotes buttons disappear and the
    //"More Quotes" button is displayed.
    @Test
    public void generalClickHidesButtons(){
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.button)).check(matches(not(isDisplayed())));
        onView(withId(R.id.button2)).check(matches(not(isDisplayed())));
        onView(withId(R.id.button3)).check(matches(not(isDisplayed())));
        onView(withId(R.id.button4)).check(matches(isDisplayed()));
    }

    //Test that when the "Educational" button is clicked, all the quotes buttons disappear and the
    //"More Quotes" button is displayed.
    @Test
    public void educationalClickHidesButtons(){
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.button)).check(matches(not(isDisplayed())));
        onView(withId(R.id.button2)).check(matches(not(isDisplayed())));
        onView(withId(R.id.button3)).check(matches(not(isDisplayed())));
        onView(withId(R.id.button4)).check(matches(isDisplayed()));
    }

    //Test that when the "Sports" button is clicked, all the quotes buttons disappear and the
    //"More Quotes" button is displayed.
    @Test
    public void sportsClickHidesButtons(){
        onView(withId(R.id.button3)).perform(click());
        onView(withId(R.id.button)).check(matches(not(isDisplayed())));
        onView(withId(R.id.button2)).check(matches(not(isDisplayed())));
        onView(withId(R.id.button3)).check(matches(not(isDisplayed())));
        onView(withId(R.id.button4)).check(matches(isDisplayed()));
    }

    //Test that clicking the "More Quotes" button displayed the three quotes buttons and hides the old quote
    @Test
    public void moreQuotesTest() {
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.button4)).perform(click());
        onView(withId(R.id.button)).check(matches(isDisplayed()));
        onView(withId(R.id.button2)).check(matches(isDisplayed()));
        onView(withId(R.id.button3)).check(matches(isDisplayed()));
        onView(withId(R.id.quote)).check(matches(not(isDisplayed())));
    }
}
