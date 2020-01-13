package com.company;

import java.io.Serializable;

public class Declination implements Serializable {

    private int degrees;
    private int minutes;
    private double seconds;

    //getters
    public int getDegrees() {
        return degrees;
    }

    public int getMinutes() {
        return minutes;
    }

    public double getSeconds() {
        return seconds;
    }

    //Validating methods
    //method for validating degrees is in Star.java since the hemisphere value is needed

    //method to check whether the minutes are correct (0;60)
    private int checkMinutes(int minutes){
        if(minutes < 0 || minutes > 60){
            throw new IllegalArgumentException("Value of given minutes is incorrect. Give value in range of (0;60)");
        }
        return minutes;
    }

    //method to check whether the seconds are correct (0.00; 59.99)
    private double checkSeconds(double seconds){
        if(seconds < 0 || seconds > 59.99){
            throw new IllegalArgumentException("Value of given seconds is incorrect. Give value in range of (0;59.99)");
        }
        return seconds;
    }

    //constructor
    public Declination(int degrees, int minutes, double seconds) {
        this.degrees = degrees;
        this.minutes = checkMinutes(minutes);
        this.seconds = checkSeconds(seconds);
    }

    //override toString for testing and displaying
    @Override
    public String toString(){
        return  getDegrees() + "°" + getMinutes() + "'" + getSeconds() + "\"";
    }

}
