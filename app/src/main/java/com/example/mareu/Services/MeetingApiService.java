package com.example.mareu.Services;

import com.example.mareu.Controler.DatePicker;
import com.example.mareu.Controler.MainActivity;
import com.example.mareu.Model.Meeting;
import com.example.mareu.Services.ApiService;
import com.example.mareu.Services.MeetingGenerator;

import java.nio.file.DirectoryStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;

public class MeetingApiService implements ApiService {

    private final List<Meeting> mMeetings = new ArrayList<>();

    private DatePicker mDatePicker;

    private MainActivity mActivity;

    @Override
    public List<Meeting> getMeetings() {
        return mMeetings;
    }

    @Override
    public void createMeeting(Meeting meeting) {
        mMeetings.add(meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {mMeetings.remove(meeting);}

    @Override
    public List<Meeting> getMeetingsByDate(List<Meeting> meetings) {
        for (Meeting meeting : mMeetings){
            if (meeting.getDate().equals(mDatePicker.getDate())) {
                meetings.add(meeting);
            }
        }
        return meetings;
    }

    @Override
    public List<Meeting> getMeetingsByRoom(List<Meeting> meetings) {
        for (Meeting meeting : mMeetings) {
            if (meeting.getRoom().equals(mActivity.getRoomPosition())) {
                meetings.add(meeting);
            }
        }
        return meetings;
    }
}
