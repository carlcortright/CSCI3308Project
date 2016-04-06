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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> myActivityRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void generalButtonShouldGetQuote(){
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.quote)).check(matches(notNullValue()));
    }

}
