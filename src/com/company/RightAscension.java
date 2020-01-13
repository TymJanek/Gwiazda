package com.company;

import java.io.Serializable;

public class RightAscension implements Serializable {

    private int hours;
    private int minutes;
    private int seconds;

    //getters
    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    //Validating methods
    //method to check whether the hours are correct (0;24)
    private int checkHours(int hours){
        if(hours < 0 || hours > 24){
            throw new IllegalArgumentException("Value of given hours is incorrect. Give value in range of (0;24)");
        }
        return hours;
    }

    //method to check whether the minutes are correct (
    private int checkMinutes(int minutes){
        if(minutes < 0 || minutes > 60){
            throw new IllegalArgumentException("Value of given minutes is incorrect. Give value in range of (0;60)");
        }
        return minutes;
    }

    //method to check whether the seconds are correct (0;60)
    private int checkSeconds(int seconds){
        if(seconds < 0 || seconds > 60){
            throw new IllegalArgumentException("Value of given seconds is incorrect. Give value in range of (0;60)");
        }
        return seconds;
    }

    //constructor
    public RightAscension(int hours, int minutes, int seconds) {
        this.hours = checkHours(hours);
        this.minutes = checkMinutes(minutes);
        this.seconds = checkSeconds(seconds);
    }

    //override toString for testing and displaying
    @Override
    public String toString(){
        return getHours() + ":" + getMinutes() + "." + getSeconds();
    }
}
