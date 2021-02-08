package com.example.mareu.Model;

public class Meeting {

    private String Hour;

    private String Name;

    private String Participants;

    private String Room;

    public Meeting(String Hour, String Name, String Participants, String Room) {
        this.Hour = Hour;
        this.Name = Name;
        this.Participants = Participants;
        this.Room = Room;
    }

    public String getHour() {return Hour;}

    public void setHour(String Hour) {this.Hour = Hour;}

    public String getName() {return Name;}

    public void setName(String Name){this.Name = Name;}

    public String getParticipants() {return Participants;}

    public void setParticipants(String Participants) {this.Participants = Participants;}

    public String getRoom() {return Room;}

    public void setRoom(String Room) {this.Room = Room;}
}
