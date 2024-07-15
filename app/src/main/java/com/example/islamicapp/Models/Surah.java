package com.example.islamicapp.Models;

import android.media.Image;

import java.util.ArrayList;

public class Surah {
    private String SurahName;
    private ArrayList<String> surahImages;


    public Surah(String SurahName) {
        this.SurahName = SurahName;
        this.surahImages = new ArrayList<>();
    }

    public String getName() {
        return SurahName;
    }

    public ArrayList<String> getImagePaths() {
        return surahImages;
    }

    public void addImagePath(String path) {
        this.surahImages.add(path);
    }

}
