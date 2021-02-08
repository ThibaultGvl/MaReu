package com.example.mareu.Services;

import com.example.mareu.Model.Meeting;
import com.example.mareu.Services.ApiService;
import com.example.mareu.Services.MeetingGenerator;

import java.util.List;

public class MeetingApiService implements ApiService {

    private final List<Meeting> mMeetings = MeetingGenerator.generateMeetings();

    @Override
    public List<Meeting> getMeetings() {
        return mMeetings;
    }

    @Override
    public void createMeeting(Meeting meeting) {
        mMeetings.add(meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        mMeetings.remove(meeting);
    }
}
