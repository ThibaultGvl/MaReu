package com.example.mareu.Model;

import android.text.Editable;

import com.example.mareu.Controler.DatePicker;

import java.util.Date;

public class Meeting {

    private String Hour;

    private String Name;

    private String Participants;

    private String Room;

    private String Date;

    //private boolean Filter;

    private DatePicker mDatePicker;

    public Meeting(String Hour, String Name, String Participants, String Room, String Date/*, boolean Filter*/) {
        this.Hour = Hour;
        this.Name = Name;
        this.Participants = Participants;
        this.Room = Room;
        this.Date = Date;
        //this.Filter = Filter;
    }

    public String getHour() {return Hour;}

    public void setHour(String Hour) {this.Hour = Hour;}

    public String getName() {return Name;}

    public void setName(String Name){this.Name = Name;}

    public String getParticipants() {return Participants;}

    public void setParticipants(String Participants) {this.Participants = Participants;}

    public String getRoom() {return Room;}

    public void setRoom(String Room) {this.Room = Room;}

    public String getDate() {return Date;}

    public void setDate(String Date) {this.Date = Date;}

    /*public boolean getFilter() {return Filter;}

    public void setFilter(boolean Filter) {this.Filter = Filter;}

    public boolean isFilter(Meeting meeting) {
        if(meeting.getDate().equals(mDatePicker.getDate())) {
            setFilter(true);
        }
        else {
            setFilter(false);
        }
        return Filter;
    }*/
}
