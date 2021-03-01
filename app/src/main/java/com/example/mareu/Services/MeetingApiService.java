package com.example.mareu.Services;

import com.example.mareu.Model.Meeting;
import com.example.mareu.View.MeetingRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MeetingApiService implements ApiService {

    private final List<Meeting> mMeetings = new ArrayList<>();

    private final List<Meeting> allMeetings = new ArrayList<>();

    private MeetingRecyclerViewAdapter adapter;

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
    public void getMeetingsByDate(List<Meeting> meetings, String DatePosition) {
        for (Meeting meeting : mMeetings){
            if (meeting.getDate().equals(DatePosition)) {
                meetings.add(meeting);
            }
        }
    }

    @Override
    public void getMeetingsByRoom(List<Meeting> meetings, String RoomPosition) {
       for (Meeting meeting : mMeetings) {
            if (meeting.getRoom().equals(RoomPosition)) {
                meetings.add(meeting);
            }
        }
    }

    @Override
    public List<Meeting> getAllMeetings() {
        return allMeetings;
    }

    @Override
    public void returnResult (List<Meeting> meetings) {
        mMeetings.clear();
        mMeetings.addAll(meetings);
        adapter.notifyDataSetChanged();
    }
}
