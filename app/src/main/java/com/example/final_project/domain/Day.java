package com.example.final_project.domain;

public class Day {
    private int currentDay;

    public Day() {
        this.currentDay = 30;
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public void nextDay() {
        if (currentDay > 0) {
            currentDay--;
        }
    }

    public boolean isGameOver() {
        return currentDay == 0;
    }
}
