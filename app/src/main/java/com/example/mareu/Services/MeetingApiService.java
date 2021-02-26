package com.example.mareu.Services;

import com.example.mareu.Controler.DatePicker;
import com.example.mareu.Controler.MainActivity;
import com.example.mareu.Model.Meeting;

import java.util.ArrayList;
import java.util.List;

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
    public void getMeetingsByDate(List<Meeting> meetings) {
        for (Meeting meeting : mMeetings){
            if (meeting.getDate().equals(DatePicker.getDate())) {
                meetings.add(meeting);
            }
        }
        mActivity.returnResult(meetings);
    }

    @Override
    public void getMeetingsByRoom(List<Meeting> meetings) {
       /*for (Meeting meeting : mMeetings) {
            if (meeting.getRoom().equals(RoomPosition)) {
                meetings.add(meeting);
            }
        }
        mActivity.returnResult(meetings);*/
    }
}
