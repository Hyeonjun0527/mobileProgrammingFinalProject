package com.example.final_project.domain;

public class NextPoint {
    int mathPoint;
    int koreanPoint;
    int englishPoint;
    int science1Point;
    int science2Point;

    public NextPoint() {
        this.mathPoint = (int)(Math.random() * 10) + 1;
        this.koreanPoint = (int)(Math.random() * 10) + 1;
        this.englishPoint = (int)(Math.random() * 10) + 1;
        this.science1Point = (int)(Math.random() * 10) + 1;
        this.science2Point = (int)(Math.random() * 10) + 1;
    }

    public void refreshNextPoint() {
        this.mathPoint = (int)(Math.random() * 10) + 1;
        this.koreanPoint = (int)(Math.random() * 10) + 1;
        this.englishPoint = (int)(Math.random() * 10) + 1;
        this.science1Point = (int)(Math.random() * 10) + 1;
        this.science2Point = (int)(Math.random() * 10) + 1;
    }

    public int getEnglishPoint() {
        return englishPoint;
    }

    public void setEnglishPoint(int englishPoint) {
        this.englishPoint = englishPoint;
    }

    public int getKoreanPoint() {
        return koreanPoint;
    }

    public void setKoreanPoint(int koreanPoint) {
        this.koreanPoint = koreanPoint;
    }

    public int getMathPoint() {
        return mathPoint;
    }

    public void setMathPoint(int mathPoint) {
        this.mathPoint = mathPoint;
    }

    public int getScience1Point() {
        return science1Point;
    }

    public void setScience1Point(int science1Point) {
        this.science1Point = science1Point;
    }

    public int getScience2Point() {
        return science2Point;
    }

    public void setScience2Point(int science2Point) {
        this.science2Point = science2Point;
    }
}
