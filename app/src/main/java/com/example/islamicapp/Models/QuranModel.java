package com.example.islamicapp.Models;

public class QuranModel {

    private String txtSurahArbicName;
    private  String txtSurahEngName;
    private String txtSurahEng2Name;

    private String number;
    public QuranModel(String txtSurahArbicName, String txtSurahEngName, String txtSurahEng2Name, String number) {
        this.txtSurahArbicName = txtSurahArbicName;
        this.txtSurahEngName = txtSurahEngName;
        this.txtSurahEng2Name = txtSurahEng2Name;
        this.number = number;
    }

    public String getTxtSurahArbicName() {
        return txtSurahArbicName;
    }

    public void setTxtSurahArbicName(String txtSurahArbicName) {
        this.txtSurahArbicName = txtSurahArbicName;
    }

    public String getTxtSurahEngName() {
        return txtSurahEngName;
    }

    public void setTxtSurahEngName(String txtSurahEngName) {
        this.txtSurahEngName = txtSurahEngName;
    }

    public String getTxtSurahEng2Name() {
        return txtSurahEng2Name;
    }

    public void setTxtSurahEng2Name(String txtSurahEng2Name) {
        this.txtSurahEng2Name = txtSurahEng2Name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
