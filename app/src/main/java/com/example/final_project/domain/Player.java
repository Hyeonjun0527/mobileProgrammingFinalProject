package com.example.final_project.domain;

public class Player {
    private String name;
    private Score score;
    private boolean isTired;
    private int tiredCount;

    public Player() {
        this.name = "익명";
        this.score = new Score();
        this.isTired = false;
        this.tiredCount = 0;
    }

    public Player(String name) {
        this.name = name;
        this.score = new Score();
        this.isTired = false;
        this.tiredCount = 0;
    }

    public String getName() {
        return name;
    }

    public Score getScore() {
        return score;
    }

    public boolean isTired() {
        return isTired;
    }

    public void setTired(boolean isTired) {
        this.isTired = isTired;
    }

    public int getTiredCount() {
        return tiredCount;
    }

    public void setTiredCount(int tiredCount) {
        this.tiredCount = tiredCount;
    }

}
