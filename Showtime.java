package com.example.popcorntest1.models;

public class Showtime {
    private String time;
    private String theater;

    // Constructor
    public Showtime(String time, String theater) {
        this.time = time;
        this.theater = theater;
    }

    // Getters
    public String getTime() {
        return time;
    }

    public String getTheater() {
        return theater;
    }

    // Setters
    public void setTime(String time) {
        this.time = time;
    }

    public void setTheater(String theater) {
        this.theater = theater;
    }

    // Optional: Override toString for better object representation
    @Override
    public String toString() {
        return "Showtime{time='" + time + "', theater='" + theater + "'}";
    }
}
