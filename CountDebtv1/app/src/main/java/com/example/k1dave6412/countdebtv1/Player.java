package com.example.k1dave6412.countdebtv1;

/**
 * Created by k1dave6412 on 2017/6/18.
 */

public class Player {
    private String name;
    private int score = 3;
    private int passTimes = 1;
    private int ReturnTimes = 1;

    public Player(String name) {
        this.name = name;
    }

    public int getScore() {
        return this.score;
    }

    public int setScore(int num) {
        return this.score += num;
    }

    public String getName() {
        return this.name;
    }

    public int getPassTimes() {
        return this.passTimes;
    }

    public void usePass() {
        this.passTimes -= 1;
    }

    public void useReturn() {
        this.ReturnTimes -= 1;
    }

    public int getReturnTimes() {
        return this.ReturnTimes;
    }

    public void initTimes() {
        this.passTimes = 1;
        this.ReturnTimes = 1;
    }

}
