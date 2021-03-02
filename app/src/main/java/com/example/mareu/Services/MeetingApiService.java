package com.example.mareu.Services;

import com.example.mareu.Controler.MainActivity;
import com.example.mareu.Model.Meeting;
import com.example.mareu.View.MeetingRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MeetingApiService implements ApiService {

    private final List<Meeting> mMeetings = new ArrayList<>();

    private final List<Meeting> allMeetings = new ArrayList<>();

    private MainActivity mActivity;

    @Override
    public List<Meeting> getMeetings() {
        return mMeetings;
    }

    @Override
    public void createMeeting(Meeting meeting) {
        mMeetings.add(meeting);
        allMeetings.add(meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        mMeetings.remove(meeting);
        allMeetings.remove(meeting);
    }

    @Override
    public List<Meeting> getMeetingsByDate(List<Meeting> meetings, String DatePosition) {
        for (Meeting meeting : mMeetings){
            if (meeting.getDate().equals(DatePosition)) {
                meetings.add(meeting);
            }
        }
        return meetings;
    }

    @Override
    public List<Meeting> getMeetingsByRoom(List<Meeting> meetings, String RoomPosition) {
       for (Meeting meeting : mMeetings) {
            if (meeting.getRoom().equals(RoomPosition)) {
                meetings.add(meeting);
            }
        }
       return meetings;
    }

    @Override
    public List<Meeting> getAllMeetings() {
        return allMeetings;
    }
}
