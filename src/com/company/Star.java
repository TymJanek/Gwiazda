package com.company;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Star implements Serializable {
    public static String[] greekAlphabet = new String[]{"ALPHA", "BETA", "GAMMA", "DELTA", "EPSILON", "EPSILON", "ZETA", "ETA", "THETA", "IOTA", "KAPPA", "LAMBDA", "MU", "NU", "XI", "OMICRON", "PI", "RHO", "SIGMA", "TAU", "UPSILON", "PHI", "CHI", "PSI", "OMEGA"};
    private String name;
    public String catalogName;
    private Declination declination;
    private RightAscension rightAscension;
    private double observedMagnitude;
    private double absoluteMagnitude;
    public double lightYearsDistance;
    private String constellation;
    private String hemisphere;
    private double temperature;
    private double mass;
    private static DecimalFormat df = new DecimalFormat("##.##");

    private String checkHemisphere(String hemisphere){
        if(!hemisphere.toUpperCase().equals("PD") || !hemisphere.toUpperCase().equals("PN")){
            return hemisphere;
        }
        throw new IllegalArgumentException("Value og given hemisphere is incorrect. Give value 'PN' or 'PD'");
    }

    private double checkObservedMagnitude(double observedMagnitude){
        if(observedMagnitude < -26.74 || observedMagnitude > 15.00){
            throw new IllegalArgumentException("Value of given observed magnitude is incorrect. Give value in range of (-26.74;15.00)");
        }
        return observedMagnitude;
    }

    private double setAbsoluteMagnitude(double lightYearsDistance, double observedMagnitude){
        return (observedMagnitude - 5 * Math.log10(lightYearsDistance/ 3.26) + 5);
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


    public void setCatName(String constellation){
        this.catalogName = constellation;
    }

    public void createCatalogName(Star star, List<Star> stars){
        int counter = 0;
        String catalogName = greekAlphabet[counter] + " " +  star.getConstellation();
        for(int i=0; i < stars.size(); i++){
            if(stars.get(i).getCatalogName().equals(catalogName)){
                counter++;
                catalogName = greekAlphabet[counter] + " " + star.getConstellation();
            }
        }
        star.catalogName = catalogName;
        stars.add(star);
    }

    public static void updateCatalogName(String constellation, List<Star> stars){
        //String[] alphabet = new String[]{"ALPHA", "BETA", "GAMMA", "DELTA", "EPSILON", "EPSILON", "ZETA", "ETA", "THETA", "IOTA", "KAPPA", "LAMBDA", "MU", "NU", "XI", "OMICRON", "PI", "RHO", "SIGMA", "TAU", "UPSILON", "PHI", "CHI", "PSI", "OMEGA"};
        int counter = 0;
        for (Star star : stars) {
            if (star.getConstellation().equals(constellation)) {
                String catalogName = greekAlphabet[counter] + " " + constellation;
                star.setCatName(catalogName);
                counter++;
            }
        }
    }

    public static List<Star> removeStar(List<Star> stars, String catalogName){
        List<Star> listOfStars = new ArrayList<>();
        for (Star star : stars) {
            if (!star.getCatalogName().equals(catalogName)) {
                listOfStars.add(star);
            }
        }
        return listOfStars;

    }

    public void setCatalogueName(String catalogueName) {
        this.catalogName = catalogName;
    }

    public String getName() {
        return name;
    }

    public String getCatalogName() {
        return catalogName;
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


    public Star(String name, Declination declination, RightAscension rightAscension, double observedMagnitude, double lightYearsDistance, String constellation, String hemisphere, double temperature, double mass) {
        this.name = name;
        this.declination = declination;
        this.rightAscension = rightAscension;
        this.observedMagnitude = checkObservedMagnitude(observedMagnitude);
        this.absoluteMagnitude = setAbsoluteMagnitude(lightYearsDistance, observedMagnitude);
        this.lightYearsDistance = lightYearsDistance;
        this.constellation = constellation;
        this.hemisphere = checkHemisphere(hemisphere);
        this.temperature = checkTemperature(temperature);
        this.mass = checkMass(mass);
        this.catalogName = constellation;
    }

    @Override
    public String toString(){

        return "Name: " + getName() + ", declination: " + getDeclination() + ", right ascension: " + getRightAscension() + ", observed magnitude: " + getObservedMagnitude() + ", absolute magnitude: " + df.format(getAbsoluteMagnitude()) + ", distance in light years: " + getLightYearsDistance()
                 + ", constellation: " + getConstellation() + ", hemisphere: " + getHemisphere() + ", temperature: " + getTemperature() + ", mass: " + getMass() + ", catalog name: " + getCatalogName();
    }
}

