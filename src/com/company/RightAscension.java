package com.company;

import java.io.Serializable;

public class RightAscension implements Serializable {

    private int hours;
    private int minutes;
    private int seconds;

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    private int checkHours(int hours){
        if(hours < 0 || hours > 24){
            throw new IllegalArgumentException("Value of given hours is incorrect. Give value in range of (0;24)");
        }
        return hours;
    }

    private int checkMinutes(int hours){
        if(minutes < 0 || minutes > 24){
            throw new IllegalArgumentException("Value of given minutes is incorrect. Give value in range of (0;60)");
        }
        return hours;
    }

    private int checkSeconds(int seconds){
        if(seconds < 0 || seconds > 60){
            throw new IllegalArgumentException("Value of given seconds is incorrect. Give value in range of (0;60)");
        }
        return seconds;
    }

    public RightAscension(int hours, int minutes, int seconds) {
        this.hours = checkHours(hours);
        this.minutes = checkMinutes(minutes);
        this.seconds = checkSeconds(seconds);
    }

    @Override
    public String toString(){
        return getHours() + ":" + getMinutes() + "." + getSeconds();
    }
}
