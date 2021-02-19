package com.example.mareu.Model;

import android.text.Editable;

import java.util.Date;

public class Meeting {

    private String Hour;

    private String Name;

    private String Participants;

    private String Room;

    /*private int Day;

    private int Month;

    private int Year;*/

    private String Date;

    public Meeting(String Hour, String Name, String Participants, String Room, String Date/*int Day, int Month, int Year*/) {
        this.Hour = Hour;
        this.Name = Name;
        this.Participants = Participants;
        this.Room = Room;
        /*this.Day = Day;
        this.Month = Month;
        this.Year = Year;*/
        this.Date = Date;
    }

    public String getHour() {return Hour;}

    public void setHour(String Hour) {this.Hour = Hour;}

    public String getName() {return Name;}

    public void setName(String Name){this.Name = Name;}

    public String getParticipants() {return Participants;}

    public void setParticipants(String Participants) {this.Participants = Participants;}

    public String getRoom() {return Room;}

    public void setRoom(String Room) {this.Room = Room;}

    /*public int getDay() { return Day;}

    public void setDay() {this.Day = Day;}

    public int getMonth() {return  Month;}

    public void setMonth() {this.Month = Month;}

    public int getYear() {return Year;}

    public void setYear() {this.Year = Year;}*/

    public String getDate() {return Date;}

    public void setDate() {this.Date = Date;}
}
