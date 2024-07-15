package com.example.islamicapp.Models;

import android.widget.Button;

public class AthkarModel {
    private int image;
    private String btnText;
    private String text;
    private int counter;
    private int goal;

//    public AthkarModel(int image, String  btnText) {
//        this.image = image;
//        this.btnText = btnText;
//    }
    public AthkarModel(String  btnText, String text) {
        this.btnText = btnText;
        this.text = text;
    }

    public AthkarModel() {
    }

    public AthkarModel(int goal, String text) {
        this.counter = 0;
        this.goal = goal;
        this.text = text;
    }

    public AthkarModel(String text) {
        this.counter = 0;
        this.text = text;
    }

    public AthkarModel(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }


    public String getBtnText() {
        return btnText;
    }

    public String getText() {
        return text;
    }

    public int getCounter() {
        return counter;
    }

    public void incrementCounter() {
        this.counter++;
    }

    public int getGoal() {
        return goal;
    }
}
