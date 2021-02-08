package com.example.mareu.Services;

public class DI {

    private static ApiService sApiService = new MeetingApiService();

    public static ApiService getMeetingApiService() {return sApiService;}

    public static ApiService getNewInstanceApiService() {
        return new MeetingApiService();
    }
}
