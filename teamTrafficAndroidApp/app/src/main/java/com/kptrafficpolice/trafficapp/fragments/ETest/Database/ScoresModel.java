package com.kptrafficpolice.trafficapp.fragments.ETest.Database;

/**
 * Created by DzonE on 11-Jul-18.
 */

public class ScoresModel {

    String score, date;

    public ScoresModel(String score, String date) {
        this.score = score;
        this.date = date;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
