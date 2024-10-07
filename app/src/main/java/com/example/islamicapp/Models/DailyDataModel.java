package com.example.islamicapp.Models;

public class DailyDataModel {
    private String date;
    private String counter;

    public DailyDataModel(String date, String counter) {
        this.date = date;
        this.counter = counter;
    }

    public String getDate() {
        return date;
    }

    public String getCounter() {
        return counter;
    }
}
