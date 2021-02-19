package com.example.mareu.Services;

import com.example.mareu.Model.Meeting;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class MeetingGenerator {
    public static List<Meeting> sMeetings = Arrays.asList(
    );
    static  List <Meeting> generateMeetings() {return new ArrayList<>(sMeetings);}
}
