package com.example.mareu.Services;

import com.example.mareu.Model.Meeting;

import java.util.ArrayList;
import java.util.List;

public class MeetingApiService implements ApiService {

    private List<Meeting> mMeetings = new ArrayList<>();

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

    @Override
    public List<Meeting> getMeetingsByDate(String DatePosition) {
        List<Meeting> meetingsFilteredDate = new ArrayList<>();
        for (Meeting meeting : mMeetings){
            if (meeting.getDate().equals(DatePosition)) {
                meetingsFilteredDate.add(meeting);
            }
        }
        return meetingsFilteredDate;
    }

    @Override
    public List<Meeting> getMeetingsByRoom(String RoomPosition) {
        List<Meeting> meetingsFilteredRoom = new ArrayList<>();
        for (Meeting meeting : mMeetings) {
            if (meeting.getRoom().equals(RoomPosition)) {
                meetingsFilteredRoom.add(meeting);
            }
        }
       return meetingsFilteredRoom;
    }
}
