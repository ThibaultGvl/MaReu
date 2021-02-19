package com.example.mareu;

import com.example.mareu.Model.Meeting;
import com.example.mareu.Services.ApiService;
import com.example.mareu.Services.DI;
import com.example.mareu.Services.MeetingGenerator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(JUnit4.class)
public class ExampleUnitTest {

    private ApiService mService;

    @Before
    public void setUp() {mService = DI.getNewInstanceApiService();}

    @Test
    public void createMeetingWithSuccess() {
        List<Meeting> meetings = mService.getMeetings();
        //assertTrue(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetings.toArray()));
    }

}