package com.company;

import java.io.Serializable;

public class Gwiazda implements Serializable {
    private String name;
    private String catalogueName;
    private Declination declination;
    private RightAscension rightAscension;
    private double observedMagnitude;
    private double absoluteMagnitude;
    private double lightYearsDistance;
    private String constellation;
    private String hemisphere;
    private double temperature;
    private double mass;

    private String checkHemisphere(String hemisphere){
        if(!hemisphere.toUpperCase().equals("PD") || !hemisphere.toUpperCase().equals("PN")){
            throw new IllegalArgumentException("Value og given hemisphere is incorrect. Give value 'PN' or 'PD'");
        }
        return hemisphere;
    }

    private double checkObservedMagnitude(double observedMagnitude){
        if(observedMagnitude < -26.74 || observedMagnitude > 15.00){
            throw new IllegalArgumentException("Value of given observed magnitude is incorrect. Give value in range of (-26.74;15.00)");
        }
        return observedMagnitude;
    }

    private long setAbsoluteMagnitude(){
        double parsec = 3.26 * lightYearsDistance;
        return (long)(observedMagnitude - (5*Math.log10(parsec)) + 5);
    }

    private double checkTemperature(double temperature){
        if(temperature < 2000){
            throw new IllegalArgumentException("Value of given temperature is incorrect. Give value bigger than 2000 Celsius");
        }
        return temperature;
    }

    private double checkMass(double mass){
        if(mass < 0.1 || mass > 50.0){
            throw new IllegalArgumentException("Value of given mass is incorrect. Give value in range of (0.1;50.0)");
        }
        return mass;
    }

    public void setCatalogueName(String catalogueName) {
        this.catalogueName = catalogueName;
    }

    public String getName() {
        return name;
    }

    public String getCatalogueName() {
        return catalogueName;
    }

    public Declination getDeclination() {
        return declination;
    }

    public RightAscension getRightAscension() {
        return rightAscension;
    }

    public double getObservedMagnitude() {
        return observedMagnitude;
    }

    public double getAbsoluteMagnitude() {
        return absoluteMagnitude;
    }

    public double getLightYearsDistance() {
        return lightYearsDistance;
    }

    public String getConstellation() {
        return constellation;
    }

    public String getHemisphere() {
        return hemisphere;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getMass() {
        return mass;
    }

    public Gwiazda(String name, Declination declination, RightAscension rightAscension, double observedMagnitude, double lightYearsDistance, String constellation, String hemisphere, double temperature, double mass) {
        this.name = name;
        this.declination = declination;
        this.rightAscension = rightAscension;
        this.observedMagnitude = checkObservedMagnitude(observedMagnitude);
        this.absoluteMagnitude = setAbsoluteMagnitude();
        this.lightYearsDistance = lightYearsDistance;
        this.constellation = constellation;
        this.hemisphere = hemisphere;
        this.temperature = checkTemperature(temperature);
        this.mass = checkMass(mass);
    }

    @Override
    public String toString(){
        return "Name: " + getName() + ", observed magnitude: " + getObservedMagnitude() + ", absolute magnitude: " + getAbsoluteMagnitude() + ", distance in light years: " + getLightYearsDistance()
                 + ", constellation: " + getConstellation() + ", hemisphere: " + getHemisphere() + ", temperature: " + getTemperature() + ", mass: " + getMass();
    }
}

