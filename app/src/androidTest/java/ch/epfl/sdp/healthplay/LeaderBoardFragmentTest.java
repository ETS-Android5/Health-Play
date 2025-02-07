package ch.epfl.sdp.healthplay;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.Matchers.allOf;

import android.app.Activity;
import android.view.View;

import androidx.navigation.Navigation;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import ch.epfl.sdp.healthplay.database.DataCache;
import ch.epfl.sdp.healthplay.database.Database;

public class LeaderBoardFragmentTest {

    ActivityScenario activity;

    @Before
    public void init(){
        FirebaseAuth.getInstance().signInWithEmailAndPassword("a.b@admin.ch", "123456");
        WelcomeScreenActivity.cache = new DataCache(ApplicationProvider.getApplicationContext());
        new Database().addHealthPoint(FirebaseAuth.getInstance().getCurrentUser().getUid(), 10);
        activity = ActivityScenario.launch(HomeScreenActivity.class);
        activity.onActivity(new ActivityScenario.ActivityAction() {
            @Override
            public void perform(Activity activity) {
                BottomNavigationView b = activity.findViewById(R.id.bottomNavigationView);
                Navigation.findNavController(activity.findViewById(R.id.fragmentContainerView)).navigate(R.id.gamesMenu);
            }
        });
        onView(withId(R.id.button3)).perform(click());

        Espresso.onView(withId(R.id.todayButton)).check(matches(allOf(isEnabled(), isClickable()))).perform(
                new ViewAction() {
                    @Override
                    public Matcher<View> getConstraints() {
                        return isEnabled(); // no constraints, they are checked above
                    }

                    @Override
                    public String getDescription() {
                        return "click plus button";
                    }

                    @Override
                    public void perform(UiController uiController, View view) {
                        view.performClick();
                    }


                }
        );
    }

    @Test
    public void initiate(){
        onView(withId(R.id.top1)).check(matches(isDisplayed()));
        onView(withId(R.id.top2)).check(matches(isDisplayed()));
        onView(withId(R.id.top3)).check(matches(isDisplayed()));
        onView(withId(R.id.top4)).check(matches(isDisplayed()));
        onView(withId(R.id.top5)).check(matches(isDisplayed()));
        onView(withId(R.id.topText1)).check(matches(isDisplayed()));
        onView(withId(R.id.topText2)).check(matches(isDisplayed()));
        onView(withId(R.id.topText3)).check(matches(isDisplayed()));
        onView(withId(R.id.topText4)).check(matches(isDisplayed()));
        onView(withId(R.id.topText5)).check(matches(isDisplayed()));
        onView(withId(R.id.profile_picture1)).check(matches(isDisplayed()));
        onView(withId(R.id.profile_picture2)).check(matches(isDisplayed()));
        onView(withId(R.id.profile_picture3)).check(matches(isDisplayed()));
        onView(withId(R.id.profile_picture4)).check(matches(isDisplayed()));
        onView(withId(R.id.profile_picture5)).check(matches(isDisplayed()));
        onView(withId(R.id.todayBackButton)).check(matches(isDisplayed()));

    }
    @Test
    public void viewMyProfileButtonTest(){
        onView(withId(R.id.top1)).check(matches(isDisplayed()));
        onView(withId(R.id.top1)).perform(click());
        // onView(withId(R.id.viewProfileNoFriend)).perform(click());
        onData(Matchers.anything()).atPosition(0).perform(click());
        onView(withId(R.id.profile_picture)).check(matches(isDisplayed()));
    }
    @Test
    public void viewProfileButtonTest(){
        onView(withId(R.id.top2)).check(matches(isDisplayed()));
        onView(withId(R.id.top2)).perform(click());
        // onView(withId(R.id.viewProfileNoFriend)).perform(click());
        onData(Matchers.anything()).atPosition(0).perform(click());
        onView(withId(R.id.profile_picture)).check(matches(isDisplayed()));
    }

    @Test
    public void addFriendMonthlyLeaderBoardTest(){
        onView(withId(R.id.top2)).check(matches(isDisplayed()));
        onView(withId(R.id.top2)).perform(click());
        //openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        //onView(withId(R.id.addFriendLeaderBoard)).perform(click());
        //onView(withContentDescription(R.string.add_to_friendlist)).perform(click());

    }

    @Test
    public void returnTest() {
        onView(withId(R.id.top1)).check(matches(isDisplayed()));
        onView(withId(R.id.todayBackButton)).perform(click());
        onView(withId(R.id.todayButton)).check(matches(isDisplayed()));

    }



}
