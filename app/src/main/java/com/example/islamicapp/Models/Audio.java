package com.example.islamicapp.Models;

public class Audio {
    private String title;
    private int audioResId;

    public Audio(String title, int audioResId) {
        this.title = title;
        this.audioResId = audioResId;
    }

    public String getTitle() {
        return title;
    }

    public int getAudioResId() {
        return audioResId;
    }
}
