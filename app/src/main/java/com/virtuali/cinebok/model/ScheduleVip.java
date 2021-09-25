package com.virtuali.cinebok.model;

public class ScheduleVip {
    String date;
    String time;
    int availableTickets;
    double tPrice;
    String mName, hName, hType, duration;

    public ScheduleVip() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAvailableTickets() {
        return String.valueOf(availableTickets);
    }

    public void setAvailableTickets(String availableTickets) {
        this.availableTickets = Integer.parseInt(availableTickets);
    }

    public String gettPrice() {
        return String.valueOf(tPrice);
    }

    public void settPrice(String tPrice) {
        this.tPrice = Double.parseDouble(tPrice);
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String gethName() {
        return hName;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }

    public String gethType() {
        return hType;
    }

    public void sethType(String hType) {
        this.hType = hType;
    }
}
