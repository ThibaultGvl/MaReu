package com.example.mareu.Model;


public class Meeting {

    private int Color;

    private String Hour;

    private String Name;

    private String Participants;

    private String Room;

    private String Date;

    public Meeting(int Color,String Hour, String Name, String Participants, String Room, String Date) {
        this.Color = Color;
        this.Hour = Hour;
        this.Name = Name;
        this.Participants = Participants;
        this.Room = Room;
        this.Date = Date;
    }

    public int getColor() {return Color;}

    public void setColor(int Color) {this.Color = Color;}

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
}
