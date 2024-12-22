package com.example.final_project.domain;

public class Score {
    private int math;
    private int korean;
    private int english;
    private int science1;
    private int science2;

    public Score() {
        this.math = 0;
        this.korean = 0;
        this.english = 0;
        this.science1 = 0;
        this.science2 = 0;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public void setKorean(int korean) {
        this.korean = korean;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public void setScience1(int science1) {
        this.science1 = science1;
    }

    public void setScience2(int science2) {
        this.science2 = science2;
    }

    public int getMath() {
        return math;
    }

    public void addMath(int value) {
        math += value;
    }

    public int getKorean() {
        return korean;
    }

    public void addKorean(int value) {
        korean += value;
    }

    public int getEnglish() {
        return english;
    }

    public void addEnglish(int value) {
        english += value;
    }

    public int getScience1() {
        return science1;
    }

    public void addScience1(int value) {
        science1 += value;
    }

    public int getScience2() {
        return science2;
    }

    public void addScience2(int value) {
        science2 += value;
    }
}
