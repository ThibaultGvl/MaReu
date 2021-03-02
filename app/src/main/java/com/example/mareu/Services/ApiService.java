package com.example.mareu.Services;

import com.example.mareu.Model.Meeting;

import java.util.List;

public interface ApiService {

    List<Meeting> getMeetings();

    void createMeeting(Meeting meeting);

    void deleteMeeting(Meeting meeting);

    List<Meeting> getMeetingsByDate(List<Meeting> meetings, String DatePosition);

    List<Meeting> getMeetingsByRoom(List<Meeting> meetings, String RoomPosition);

    List<Meeting> getAllMeetings();
}
