package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //start of GUI
        GUI();


//        //saving list to object file
//        saveStarsToFile(listOfStars, "stars.obj");
//        searchForAllStars("stars.obj");
//
//        System.out.println();
//        //removing star1 and updating catalog name of the rest of the stars
//        listOfStars = Star.removeStar(listOfStars, star1.getCatalogName());
//        Star.updateCatalogName(star1.getConstellation(), listOfStars);
//
//        //removing star2 and updating catalog name of the rest of the stars
//        listOfStars = Star.removeStar(listOfStars, star2.getCatalogName());
//        Star.updateCatalogName(star2.getConstellation(), listOfStars);
//
//        //saving updated list to the file and displaying file's content
//        saveStarsToFile(listOfStars, "stars.obj");
//        searchForAllStars("stars.obj");


        //METHODS TO SEARCH STARS WITH GIVEN CRITERIA
        //search for stars in given constellation
        //searchForStarsInConstellation("Cassiopeia");

        //search for stars in given distance from Earth(in parsecs)
        //searchForStarsInDistance(4);

        //search for stars in given range of temperatures
        //searchForStarsInRangeOfTemperatures(40000, 55000);

        //search for stars in given range of observed magnitude
        //searchForStarsInRangeOfObservableMagnitude(2.0, 11.0);

        //search for stars in given hemisphere(PN/PD)
        //searchForStarsInGivenHemisphere("PN");

        //search for stars that could be supernovas(their mass is bigger than 1.44 of Sun mass)
        //searchForPotentialSupernovas();

    }

    public static void GUI(){
        Color frameColor = new Color(84,106,123);
        Color labelResultColor = new Color(158,163,176);
        Color borderResultFrameColor = new Color(13,31,45);

        JFrame frame = new JFrame("Projekt Gwiazda");
        frame.getContentPane().setBackground(frameColor);


        JButton button1 = new JButton("Add");
        button1.setBounds(100,30,200,40);
        frame.add(button1);

        JButton button2 = new JButton("Remove");
        button2.setBounds(400,30,200,40);
        frame.add(button2);

        JButton button3 = new JButton("Display");
        button3.setBounds(700,30,200,40);
        frame.add(button3);




        JLabel labelResult = new JLabel("Results will be shown here", SwingConstants.CENTER);
        labelResult.setOpaque(true);
        labelResult.setBackground(labelResultColor);
        labelResult.setBounds(100, 100, 1700, 800);
        labelResult.setFont(new Font("Arial", Font.PLAIN, 14));
        Border borderResult = BorderFactory.createLineBorder(borderResultFrameColor, 3, false);
        labelResult.setBorder(borderResult);
        frame.add(labelResult);

        frame.setSize(1900,1000);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //adding
        button1.addActionListener(e -> {
            //labelResult.setText(display());
        });

        //removing
        button2.addActionListener(e -> {
            //labelResult.setText(display());
        });

        //displaying
        button3.addActionListener(e -> {
            labelResult.setText(display());
        });
    }

    public static String display(){
        //creating 5 stars
        Star star1 = new Star("ABC1234",new Declination(40,50,10),new RightAscension(20,50,10), 6.1, 9.0, "Andromeda", "PN", 50000, 25);
        Star star2 = new Star("XYZ9876",new Declination(-25,10,30),new RightAscension(10,30,15), 9.0, 1.5, "Cassiopeia", "PD", 40000, 1.43);
        Star star3 = new Star("MNO5678",new Declination(-10,20,20),new RightAscension(15,20,25), 5.0, 0.9, "Andromeda", "PD", 60000, 16);
        Star star4 = new Star("GUQ2107",new Declination(30,40,20),new RightAscension(20,30,10), 4.1, 0.7, "Andromeda", "PN", 39000, 40);
        Star star5 = new Star("HAV1241",new Declination(-20,30,50),new RightAscension(10,20,30), 3.7, 0.6, "Cassiopeia", "PD", 44000, 11);

        List<Star> listOfStars = new ArrayList<>();

        //adding stars to list and creating their catalog name
        star1.createCatalogName(star1, listOfStars);
        star2.createCatalogName(star2, listOfStars);
        star3.createCatalogName(star3, listOfStars);
        star4.createCatalogName(star4, listOfStars);
        star5.createCatalogName(star5, listOfStars);

        String[] tab = new String[listOfStars.size()];

        for(int i=0; i<tab.length; i++){
            tab[i] = listOfStars.get(i).toString();
        }

        return "<html><table>" + Arrays.toString(tab).replace(",", "<tr>").replace("[", "").replace("]", "") + "</table></html>";
    }



    //method to display all stars in file (for testing)
    public static void searchForAllStars(String filePath) {
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(new FileInputStream(filePath));
            Object obj;
            while ((obj = ois.readObject()) != null) {
                if (obj instanceof Star) {
                    {
                        System.out.println(obj);
                    }
                }
            }

        } catch (EOFException eof) {
            //
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //METHODS to search stars with given criteria

    //searching and displaying all stars in given constellation
    public static void searchForStarsInConstellation(String constellation){
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
            //
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //searching and displaying all stars in given distance(in parsecs) from Earth
    public static void searchForStarsInDistance(double parsecs){
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
            //
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //searching and displaying all stars in given range of temperatures
    public static void searchForStarsInRangeOfTemperatures(double rangeA, double rangeB){
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
            //
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //searching and displaying all stars in given range of observable magnitude
    public static void searchForStarsInRangeOfObservableMagnitude(double rangeA, double rangeB){
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
            //
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //searching and displaying all stars from either Northern(PN) or Southern(PD) hemisphere
    public static void searchForStarsInGivenHemisphere(String hemisphere){
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
            //
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //searching and displaying all potential supernovas
    public static void searchForPotentialSupernovas(){
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

    public static void saveStarsToFile(List<Star> list, String filepath){
        ObjectOutputStream oos;
        try{
            oos = new ObjectOutputStream(new FileOutputStream(filepath));
            for(Star values : list){
                oos.writeObject(values);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


}


