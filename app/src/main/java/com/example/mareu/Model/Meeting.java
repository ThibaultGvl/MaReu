package com.example.mareu.Model;

public class Meeting {

    private String Hour;

    private String Name;

    private String Participants;

    private String Room;

    private int Date;

    public Meeting(String Hour, String Name, String Participants, String Room, int Date) {
        this.Hour = Hour;
        this.Name = Name;
        this.Participants = Participants;
        this.Room = Room;
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

    public int getDate() {return Date;}

    public void setDate() {this.Date = Date;}
}
