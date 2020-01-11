package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	    Star star1 = new Star("ABC1234",new Declination(40,50,10),new RightAscension(20,30,10) , 6.1, 1.0, "Andromeda", "PN", 50000, 25);
	    Star star2 = new Star("XYZ9876",new Declination(25,10,30),new RightAscension(10,30,15),  9.0, 1.5, "Orion", "PD", 40000, 1.43);
        Star star3 = new Star("MNO5678",new Declination(10,20,20),new RightAscension(15,20,25),  5.0, 0.9, "Andromeda", "PD", 60000, 16);
        Star star4 = new Star("GUQ2107",new Declination(40,50,10),new RightAscension(20,30,10) , 4.1, 0.7, "Andromeda", "PN", 39000, 40);
        Star star5 = new Star("HAV1241",new Declination(20,30,50),new RightAscension(10,20,30) , 4.1, 0.6, "Orion", "PD", 44000, 11);


        List<Star> listOfStars = new ArrayList<>();
        listOfStars.add(star1);
        listOfStars.add(star2);
        listOfStars.add(star3);
        listOfStars.add(star4);
        listOfStars.add(star5);

        new Main().saveStars(listOfStars);
        //new Main().searchForStarsInRangeOfTemperatures(30000, 65000); //for testing

        List<Star> listOfStars2 = searchForStars();
        new Main().searchForStarsInRangeOfTemperatures(30000, 65000);

        //deleting star from file and saving to file again
        //List<Star> listaTemp = removeStar("ABC1234");
        //new Main().saveStars(listaTemp);
        //new Main().searchForStarsInRangeOfTemperatures(30000, 65000); //for testing




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

    //method to return list with all stars except the deleted one
    public static List<Star> removeStar(String name){
        List<Star> listOfStars = new ArrayList<>(); //list for all stars except deleted one
        ObjectInputStream ois;
        try{
            ois = new ObjectInputStream(new FileInputStream("stars.obj"));
            Object obj;
            while((obj = ois.readObject()) != null){
                if(obj instanceof Star){
                    if(((!((Star) obj).getName().equals(name)))){
                        listOfStars.add((Star) obj);
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

        return listOfStars;
    }

    //save to object file
    public void saveStars(List<Star> listOfStars){
        ObjectOutputStream oos;
        try{
            oos = new ObjectOutputStream(new FileOutputStream("stars.obj"));
            for(Star values : listOfStars){
                oos.writeObject(values);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static List<Star> searchForStars(){
        List<Star> list = new ArrayList<>();
        ObjectInputStream ois;
        try{
            ois = new ObjectInputStream(new FileInputStream("stars.obj"));
            Object obj;
            while((obj = ois.readObject()) != null){
                if(obj instanceof Star){
                    list.add((Star) obj);
                }
            }
        }
        catch(EOFException eof){
            System.out.println("End of file.");
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }


    //METHODS to search stars with given criteria

    //searching and displaying all stars in given constellation
    public void searchForStarsInConstellation(String constellation){
        ObjectInputStream ois;
        try{
            ois = new ObjectInputStream(new FileInputStream("stars.obj"));
            Object obj;
            while((obj = ois.readObject()) != null){
                if(obj instanceof Star){
                    if(((Star) obj).getConstellation().equals(constellation)){
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
                if(obj instanceof Star){
                    if(((Star) obj).getLightYearsDistance() > (parsecs/3.26)){
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
                if(obj instanceof Star){
                    if(((Star) obj).getTemperature() >= rangeA && ((Star) obj).getTemperature() <= rangeB){
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
                if(obj instanceof Star){
                    if(((Star) obj).getObservedMagnitude() >= rangeA && ((Star) obj).getObservedMagnitude() <= rangeB){
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
                if(obj instanceof Star){
                    if(((Star) obj).getHemisphere().equals(hemisphere.toUpperCase())){
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
                if(obj instanceof Star){
                    if(((Star) obj).getMass() >= 1.44){
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
