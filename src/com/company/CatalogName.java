package com.company;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class CatalogName {

    private String[] alphabet = new String[]{"ALPHA", "BETA", "GAMMA", "DELTA", "EPSILON", "EPSILON", "ZETA", "ETA", "THETA", "IOTA", "KAPPA", "LAMBDA", "MU", "NU", "XI", "OMICRON", "PI", "RHO", "SIGMA", "TAU", "UPSILON", "PHI", "CHI", "PSI", "OMEGA"};
    List<String> constellationList;

    public CatalogName(){

    }

    /*

    public String setCatalogName(Star temp){
        int counter = 0;
        ObjectInputStream ois;
        try{
            ois = new ObjectInputStream(new FileInputStream("stars.obj"));
            Object obj;
            while((obj = ois.readObject()) != null){
                if(obj instanceof Star){
                    if(((Star) obj).getConstellation().equals((temp).getConstellation())){
                        counter++;
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
        return temp.getConstellation() + alphabet[counter];
    }

     */
}
