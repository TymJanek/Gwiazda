package com.company;

import java.io.Serializable;

public class Declination implements Serializable {

    private int degrees;
    private int minutes;
    private double seconds;

    public int getDegrees() {
        return degrees;
    }

    public int getMinutes() {
        return minutes;
    }

    public double getSeconds() {
        return seconds;
    }

    private int checkDegrees(int degrees){
        if(degrees < 0 || degrees > 90){
            throw new IllegalArgumentException("Value of given degrees is incorrect. Give value in range of (0;90)");
        }
        return degrees;
    }

    private int checkMinutes(int minutes){
        if(minutes < 0 || minutes > 60){
            throw new IllegalArgumentException("Value of given minutes is incorrect. Give value in range of (0;60)");
        }
        return minutes;
    }

    private double checkSeconds(double seconds){
        if(seconds < 0 || seconds > 59.00){
            throw new IllegalArgumentException("Value of given seconds is incorrect. Give value in range of (0;59.99)");
        }
        return seconds;
    }

    public Declination(int degrees, int minutes, double seconds) {
        this.degrees = checkDegrees(degrees);
        this.minutes = checkMinutes(minutes);
        this.seconds = checkSeconds(seconds);
    }

    @Override
    public String toString(){
        return  getDegrees() + "°" + getMinutes() + "'" + getSeconds() + "\"";
    }

}
