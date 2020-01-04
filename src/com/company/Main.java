package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	    Gwiazda star1 = new Gwiazda("ABC1234",new Declination(40,50,10),new RightAscension(20,30,10) , 6.1, 1, "Andromeda", "PN", 50000, 25);
	    Gwiazda star2 = new Gwiazda("XYZ9876",new Declination(25,10,30),new RightAscension(10, 30, 15),  10.0, 1.5, "Delfin", "PD", 40000, 1.43);
        //System.out.println(star1);
        //System.out.println(star2);

        Gwiazda[] listOfStars = new Gwiazda[]{star1, star2};
        new Main().zapiszGwiazde(listOfStars);

        //METHODS TO SEARCH STARS WITH GIVEN CRITERIA
        //search for stars in given constellation
        //new Main().searchForStarsInConstellation("Andromeda");

        //search for stars in given distance from Earth(in parsecs)
        //new Main().searchForStarsInDistance(4);

        //search for stars in given range of temperatures
        //new Main().searchForStarsInRangeOfTemperatures(40000, 55000);

        //search for stars in given range of observed magnitude
        //new Main().searchForStarsInRangeOfObservableMagnitude(2.0, 11.0);

        //search for stars in given hemisphere(PN/PD)
        //new Main().searchForStarsInGivenHemisphere("PN");

        //search for stars that could be supernovas(their mass is bigger than 1.44 of Sun mass)
        //new Main().searchForPotentialSupernovas();

    }

    //save to object file
    public void zapiszGwiazde(Gwiazda[] listOfStars){
        ObjectOutputStream oos;
        try{
            oos = new ObjectOutputStream(new FileOutputStream("stars.obj"));
            for(Gwiazda values : listOfStars){
                oos.writeObject(values);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //METHODS to search stars with given criteria

    //searching and displaying all stars in given constellation
    public void searchForStarsInConstellation(String constellation){
        ObjectInputStream ois;
        try{
            ois = new ObjectInputStream(new FileInputStream("stars.obj"));
            Object obj;
            while((obj = ois.readObject()) != null){
                if(obj instanceof Gwiazda){
                    if(((Gwiazda) obj).getConstellation().equals(constellation)){
                        System.out.println(obj);
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
    }

    //searching and displaying all stars in given distance(in parsecs) from Earth
    public void searchForStarsInDistance(double parsecs){
        ObjectInputStream ois;
        try{
            ois = new ObjectInputStream(new FileInputStream("stars.obj"));
            Object obj;
            while((obj = ois.readObject()) != null){
                if(obj instanceof Gwiazda){
                    if(((Gwiazda) obj).getLightYearsDistance() > (parsecs/3.26)){
                        System.out.println(obj);
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
    }

    //searching and displaying all stars in given range of temperatures
    public void searchForStarsInRangeOfTemperatures(double rangeA, double rangeB){
        ObjectInputStream ois;
        try{
            ois = new ObjectInputStream(new FileInputStream("stars.obj"));
            Object obj;
            while((obj = ois.readObject()) != null){
                if(obj instanceof Gwiazda){
                    if(((Gwiazda) obj).getTemperature() >= rangeA && ((Gwiazda) obj).getTemperature() <= rangeB){
                        System.out.println(obj);
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
    }

    //searching and displaying all stars in given range of observable magnitude
    public void searchForStarsInRangeOfObservableMagnitude(double rangeA, double rangeB){
        ObjectInputStream ois;
        try{
            ois = new ObjectInputStream(new FileInputStream("stars.obj"));
            Object obj;
            while((obj = ois.readObject()) != null){
                if(obj instanceof Gwiazda){
                    if(((Gwiazda) obj).getObservedMagnitude() >= rangeA && ((Gwiazda) obj).getObservedMagnitude() <= rangeB){
                        System.out.println(obj);
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
    }

    //searching and displaying all stars from either Northern(PN) or Southern(PD) hemisphere
    public void searchForStarsInGivenHemisphere(String hemisphere){
        ObjectInputStream ois;
        try{
            ois = new ObjectInputStream(new FileInputStream("stars.obj"));
            Object obj;
            while((obj = ois.readObject()) != null){
                if(obj instanceof Gwiazda){
                    if(((Gwiazda) obj).getHemisphere().equals(hemisphere.toUpperCase())){
                        System.out.println(obj);
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
    }

    //searching and displaying all potential supernovas
    public void searchForPotentialSupernovas(){
        ObjectInputStream ois;
        try{
            ois = new ObjectInputStream(new FileInputStream("stars.obj"));
            Object obj;
            while((obj = ois.readObject()) != null){
                if(obj instanceof Gwiazda){
                    if(((Gwiazda) obj).getMass() >= 1.44){
                        System.out.println(obj);
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
    }
}
