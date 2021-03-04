package com.example.mareu;

import android.content.Context;
import android.widget.DatePicker;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.mareu.Controler.CreateMeetingActivity;
import com.example.mareu.Controler.MainActivity;
import com.example.mareu.Model.Meeting;
import com.example.mareu.Services.ApiService;
import com.example.mareu.Services.MeetingApiService;
import com.example.mareu.View.MeetingRecyclerViewAdapter;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.removeGlobalAssertion;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.hasSibling;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static androidx.test.espresso.matcher.ViewMatchers.withInputType;
import static androidx.test.espresso.matcher.ViewMatchers.withParentIndex;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MeetingInstrumentedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> rule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setUp() {
        ActivityScenario<MainActivity> scenario = rule.getScenario();
    }

    @Test
    public void  meetingList_shouldBeEmpty() {
        onView(ViewMatchers.withId(R.id.main_recycler_view)).check(matches(hasChildCount(0)));
    }

    @Test
    public void meetingList_createAction_shouldCreateAndDisplayItem() {
        onView(ViewMatchers.withId(R.id.main_recycler_view)).check(matches(hasChildCount(0)));
        onView(ViewMatchers.withId(R.id.activity_main));
        onView(ViewMatchers.withId(R.id.fab)).perform(click());
        onView(withId(R.id.meeting_name_input)).perform(typeText("meeting1"));
        onView(withId(R.id.meeting_participants_input)).perform(click());
        onView(withText("Peach@lamzone.com")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.meeting_date)).perform(click());
        onView(withText("OK")).perform(click());
        onView(withId(R.id.meeting_hour_input)).perform(click());
        onView(withText("5h45")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.scroll_view)).perform(swipeUp());
        onView(withId(R.id.meeting_room_input)).perform(click());
        onView(withText("102")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.create_btn)).perform(click());
        onView(ViewMatchers.withId(R.id.main_recycler_view)).check(matches(hasChildCount(1)));
        onView(ViewMatchers.withId(R.id.main_recycler_view)).check(matches(withText("meeting1")));
    }

    @Test
    public void meetingList_deleteAction_shouldDeleteItem() {
        onView(ViewMatchers.withId(R.id.main_recycler_view)).check(matches(hasChildCount(1)));
        pressBack();
        onView(withId(R.id.delete_btn)).perform(click());
        onView(ViewMatchers.withId(R.id.main_recycler_view)).check(matches(hasChildCount(0)));
    }

    @Test
    public void meetingList_filterMeetings_shouldReturnOnlyFilteredMeeting() {
        meetingList_createAction_shouldCreateAndDisplayItem();
        onView(ViewMatchers.withId(R.id.main_recycler_view)).check(matches(hasChildCount(1)));
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        onView(withText("Filtrer les Réunions par Date")).perform(click());
        onView(withText("OK")).perform(click());
        onView(ViewMatchers.withId(R.id.main_recycler_view)).check(matches(hasChildCount(1)));
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        onView(withText("Filtrer les Réunions par Salle")).perform(click());
        onView(withText("105")).perform(click());
        onView(withText("OK")).perform(click());
        onView(ViewMatchers.withId(R.id.main_recycler_view)).check(matches(hasChildCount(0)));
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        onView(withText("Afficher toutes les Réunions")).perform(click());
        onView(ViewMatchers.withId(R.id.main_recycler_view)).check(matches(hasChildCount(1)));
        onView(ViewMatchers.withId(R.id.delete_btn)).perform(click());
    }
}
