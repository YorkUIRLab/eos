package org.factory;

import weka.core.Instances;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * PackALunch
 * Created by Sadra on 12/29/15.
 */

public class InstanceFactory {

    public static BufferedReader readDataFile(String filename) {
        BufferedReader inputReader = null;

        try {
            inputReader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException ex) {
            System.err.println("File not found: " + filename);
        }

        return inputReader;
    }

    public static Instances getInstance(String fileName)  {
        BufferedReader datafile = readDataFile(fileName);
        Instances instances = null;
        try {
            instances = new Instances(datafile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return instances;
    }
}
