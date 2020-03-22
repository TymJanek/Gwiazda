package com.company;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
    private static DecimalFormat df = new DecimalFormat("#.##",
            DecimalFormatSymbols.getInstance(Locale.US));

    //Validating methods
    //method to check whether the hemisphere is correct (PN/PD)
    private String checkHemisphere(String hemisphere){
        if(!hemisphere.toUpperCase().equals("PD") || !hemisphere.toUpperCase().equals("PN")){
            return hemisphere;
        }
        throw new IllegalArgumentException("Value og given hemisphere is incorrect. Give value 'PN' or 'PD'");
    }

    //method to check whether the observable magnitude is correct (-26.74;15.00)
    private double checkObservedMagnitude(double observedMagnitude){
        if(observedMagnitude < -26.74 || observedMagnitude > 15.00){
            throw new IllegalArgumentException("Value of given observed magnitude is incorrect. Give value in range of (-26.74;15.00)");
        }
        return observedMagnitude;
    }

    //method to check whether the temperature is correct (more than 2000 degrees Celsius)
    private double checkTemperature(double temperature){
        if(temperature < 2000){
            throw new IllegalArgumentException("Value of given temperature is incorrect. Give value bigger than 2000 Celsius");
        }
        return temperature;
    }

    //method to check whether the mass is correct (0.1;50.0)
    private double checkMass(double mass){
        if(mass < 0.1 || mass > 50.0){
            throw new IllegalArgumentException("Value of given mass is incorrect. Give value in range of (0.1;50.0)");
        }
        return mass;
    }

    //method to check whether the degrees in Declination is correct (0;90) for PN and (-90;0) for PD
    private Declination checkDeclination(String hemisphere, Declination declination){
        if(hemisphere.equals("PN")){
            if(declination.getDegrees() < 0 || declination.getDegrees() > 90){
                throw new IllegalArgumentException("Value of given declination is incorrect. Give value in range 0f (0;90)");
            }
            else{
                return declination;
            }
        }
        else if(hemisphere.equals("PD")){
            if(declination.getDegrees() > 0 || declination.getDegrees() < (-90)){
                throw new IllegalArgumentException("Value of given declination is incorrect. Give value in range 0f (-90;0)");
            }
            else{
                return declination;
            }
        }
        return declination;
    }

    //method to set absolute magnitude based on observed magnitude and distance in light years
    private double setAbsoluteMagnitude(double lightYearsDistance, double observedMagnitude){
        return (observedMagnitude - 5 * Math.log10(lightYearsDistance/ 3.26) + 5);
    }


    public void setCatName(String constellation){
        this.catalogName = constellation;
    }

    //creating catalog name based on following letter from Greek alphabet (if star with the same constellation already exists in list, then it's the next one) and constellation name
    //and add the star to the list
    public void createCatalogName(Star star, List<Star> stars){
        int counter = 0;
        String catalogName = greekAlphabet[counter] + " " +  star.getConstellation();
        for (Star value : stars) {
            if (value.getCatalogName().equals(catalogName)) {
                counter++;
                catalogName = greekAlphabet[counter] + " " + star.getConstellation();
            }
        }
        star.catalogName = catalogName;
        stars.add(star);
    }

    //method for updating catalog name
    public static void updateCatalogName(String constellation, List<Star> stars){
        int counter = 0;
        for (Star star : stars) {
            if (star.getConstellation().equals(constellation)) {
                String catalogName = greekAlphabet[counter] + " " + constellation;
                star.setCatName(catalogName);
                counter++;
            }
        }
    }

    //method to remove star from the list and return the list without the removed one
    public static List<Star> removeStar(List<Star> stars, String catalogName){
        List<Star> listOfStars = new ArrayList<>();
        for (Star star : stars) {
            if (!star.getCatalogName().equals(catalogName)) {
                listOfStars.add(star);
            }
        }
        return listOfStars;
    }

    //getters
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


    //constructor
    public Star(String name, Declination declination, RightAscension rightAscension, double observedMagnitude, double lightYearsDistance, String constellation, String hemisphere, double temperature, double mass) {
        this.name = name;
        this.declination = checkDeclination(hemisphere, declination);
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

    //override toString fot testing and displaying
    @Override
    public String toString(){
        return "Name: " + getName() + " declination: " + getDeclination() + " right ascension: " + getRightAscension() + " observed magnitude: " + getObservedMagnitude() + " absolute magnitude: " + df.format(getAbsoluteMagnitude()) + " distance in light years: " + getLightYearsDistance()
                 + " constellation: " + getConstellation() + " hemisphere: " + getHemisphere() + " temperature: " + getTemperature() + " mass: " + getMass() + " catalog name: " + getCatalogName();
    }
}

