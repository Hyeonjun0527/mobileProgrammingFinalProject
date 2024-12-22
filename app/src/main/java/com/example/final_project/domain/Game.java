package com.example.final_project.domain;

import com.example.final_project.util.FileManager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Game {
    private NextPoint nextPoint;
    private Player player;
    private Day day;

    public Game(String name) {
        this.player = new Player(name);
        this.day = new Day();
        this.nextPoint = new NextPoint();
    }

    public Player getPlayer() {
        return player;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public NextPoint getNextPoint() {
        return nextPoint;
    }

    public void setNextPoint(NextPoint nextPoint) {
        this.nextPoint = nextPoint;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Day getDay() {
        return day;
    }

    public void start() {
        System.out.println("게임이 시작되었습니다!");
    }

    public void refreshNextPoint(){
        nextPoint.refreshNextPoint();
    }

    public void performAction(String action, NextPoint nextPoint, Map<String, Boolean> activeItems) {
        boolean isDoublePointsActive = Boolean.TRUE.equals(activeItems.getOrDefault("doublePoints", false));

        int pointMultiplier = isDoublePointsActive ? 2 : 1;
        switch (action) {
            case "math":
                player.getScore().addMath(nextPoint.getMathPoint() * pointMultiplier);
                break;
            case "korean":
                player.getScore().addKorean(nextPoint.getKoreanPoint() * pointMultiplier);
                break;
            case "english":
                player.getScore().addEnglish(nextPoint.getEnglishPoint() * pointMultiplier);
                break;
            case "science1":
                player.getScore().addScience1(nextPoint.getScience1Point() * pointMultiplier);
                break;
            case "science2":
                player.getScore().addScience2(nextPoint.getScience2Point() * pointMultiplier);
                break;
            default:
                System.out.println("알 수 없는 액션: " + action);
        }

        day.nextDay();
    }

    public String getUniversity() {
        System.out.println("게임 종료! 각 과목별 상태:");

        int[] scores = {
                player.getScore().getMath(),
                player.getScore().getKorean(),
                player.getScore().getEnglish(),
                player.getScore().getScience1(),
                player.getScore().getScience2()
        };

        int minScore = Arrays.stream(scores).min().orElse(0);

        if (minScore >= 100) {
            return "샤울대학교";
        } else if (minScore >= 80) {
            return "성큔콴대학교";
        } else if (minScore >= 60) {
            return "곰두리대학교";
        } else {
            return "재수학원";
        }


    }

}
