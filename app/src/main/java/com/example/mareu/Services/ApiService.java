package com.example.mareu.Services;

import com.example.mareu.Model.Meeting;

import java.util.List;

public interface ApiService {

    List<Meeting> getMeetings();

    void createMeeting(Meeting meeting);

    void deleteMeeting(Meeting meeting);

    void getMeetingsByDate(List<Meeting> meetings);

    void getMeetingsByRoom(List<Meeting> meetings);
}
