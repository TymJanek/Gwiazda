package com.company;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Gwiazda implements Serializable {
    private String[] alphabet = new String[]{"ALPHA", "BETA", "GAMMA", "DELTA", "EPSILON", "EPSILON", "ZETA", "ETA", "THETA", "IOTA", "KAPPA", "LAMBDA", "MU", "NU", "XI", "OMICRON", "PI", "RHO", "SIGMA", "TAU", "UPSILON", "PHI", "CHI", "PSI", "OMEGA"};
    private String name;
    public String catalogName;
    private Declination declination;
    private RightAscension rightAscension;
    private double observedMagnitude;
    private double absoluteMagnitude;
    private double lightYearsDistance;
    private String constellation;
    private String hemisphere;
    private double temperature;
    private double mass;
    public List<Double> lista = new ArrayList<Double>();

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

    public String SetCatalogName(){
        int counter = 0;
        ObjectInputStream ois;
        try{
            ois = new ObjectInputStream(new FileInputStream("stars.obj"));
            Object obj;
            while((obj = ois.readObject()) != null){
                if(obj instanceof Gwiazda){
                    Gwiazda temp = (Gwiazda) obj;
                    if(constellation.equals((temp).constellation)){
                        lista.add(temp.getObservedMagnitude());
                        //counter++;
                    }
                }
            }
        }
        catch(EOFException eof){
            System.out.println("End of file.");
        }
        catch(Exception e){
            e.printStackTrace();
        }

        if(lista.size() > 0){
            Double temp = lista.get(0);
            for(int i=0; i< lista.size(); i++){
                temp = lista.get(i);
                if(temp.equals(observedMagnitude)){
                    counter = i;
                }
            }
        }

        String catalogName = constellation + alphabet[counter];
        return catalogName;
    }

    public void setCatalogueName(String catalogueName) {
        this.catalogName = catalogueName;
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
        this.catalogName = SetCatalogName();
    }

    @Override
    public String toString(){
        return "Name: " + catalogName + ", declination: " + declination + ", right ascension: " + rightAscension + ", observed magnitude: " + getObservedMagnitude() + ", absolute magnitude: " + getAbsoluteMagnitude() + ", distance in light years: " + getLightYearsDistance()
                 + ", constellation: " + getConstellation() + ", hemisphere: " + getHemisphere() + ", temperature: " + getTemperature() + ", mass: " + getMass() + ", catalog name: " + catalogName;
    }
}

