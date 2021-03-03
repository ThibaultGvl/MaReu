package com.example.mareu;

import com.example.mareu.Controler.MainActivity;
import com.example.mareu.Model.Meeting;
import com.example.mareu.Services.ApiService;
import com.example.mareu.Services.DI;
import com.example.mareu.View.MeetingRecyclerViewAdapter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.rules.ExternalResource;


import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(JUnit4.class)
public class MeetingUnitTest {

    private ApiService mService;

    private Meeting mMeeting0;

    private Meeting mMeeting1;

    private String DatePosition;

    @Before
    public void setUp() {
        mService = DI.getNewInstanceApiService();
        mService.createMeeting(mMeeting0);
    }

    @Test
    public void getMeetingsWithSuccess() {
        List<Meeting> meetings = mService.getMeetings();
        assertEquals(meetings.size(), 1);
    }

    @Test
    public void createMeetingWithSuccess() {
        Meeting meetingToCreate = mService.getMeetings().get(0);
        mService.createMeeting(meetingToCreate);
        assertTrue(mService.getMeetings().contains(meetingToCreate));
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = mService.getMeetings().get(0);
        mService.deleteMeeting(meetingToDelete);
        assertFalse(mService.getMeetings().contains(meetingToDelete));
    }

    @Test
    public void filterMeetingsByDateWithSuccess() {
        mMeeting0 = mService.getMeetings().get(0);
        Meeting meetingNoReturn = mService.getMeetings().get(1);
        mService.getMeetingsByDate(DatePosition);
        assertTrue(mService.getMeetings().contains(mMeeting0));
    }
}