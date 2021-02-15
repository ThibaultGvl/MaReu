package com.example.mareu.Services;

import com.example.mareu.Model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class MeetingGenerator {
    public static List<Meeting> sMeetings = Arrays.asList(
            new Meeting("11h", "MaRéu", "Damien, Lucas, Martin", "102", 14 / 11 /21),
            new Meeting("15h30", "Rencontre des Nouveaux", "Rupert, Misha, Javier", "110", 15/ 10 /21)
    );
    static  List <Meeting> generateMeetings() {return new ArrayList<>(sMeetings);}
}
