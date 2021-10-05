package com.virtuali.cinebok;

public class Schedule {

     String Movie, Hall, Time, Duration;

    Schedule() {
    }

    public Schedule(String movie, String hall, String time, String duration) {
        Movie = movie;
        Hall = hall;
        Time = time;
        Duration = duration;
    }

    public String getMovie() {
        return Movie;
    }

    public void setMovie(String movie) {
        Movie = movie;
    }

    public String getHall() {
        return Hall;
    }

    public void setHall(String hall) {
        Hall = hall;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }
}