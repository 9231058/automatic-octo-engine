/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Parham Alvani
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        AbsFuck myAbsFuck = new AbsFuck.RealFuck();
        myAbsFuck.fuckYouMan();
        Runtime run = Runtime.getRuntime();
        /*        Scanner fscanf = new Scanner("Supernatrual S01E04");
        //fscanf.useDelimiter("\\s*");
        String nameString = "";
        while (fscanf.hasNext()) {
        String temp = fscanf.next();
        if (temp.matches("S[0-9]{2}E[0-9]{2}")) {
        System.out.println();
        } else {
        nameString = temp+nameString;
        }
        }
        System.out.println(nameString);*/
        /*try {
        Process myProcess = run.exec("mkdir.exe FUCK");
        InputStream inputStream = myProcess.getInputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(myProcess.getOutputStream());
        Scanner cin = new Scanner(inputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        bufferedWriter.append("help\n");
        bufferedWriter.flush();
        } catch (IOException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        System.out.println("Process Running : " + run.availableProcessors());
        Fuck result = F();
        Class<? extends Fuck> aClass = result.getClass();
        Constructor<?>[] constructors = aClass.getConstructors();
        System.out.println("The Constructor number is : " + constructors.length);
        System.out.println("First Constructor Name is : " + constructors[0].getName());
        try {
            Object newInstance = constructors[0].newInstance(10, new AbsFuck.RealFuck());
            System.out.println(newInstance.toString());

        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(Main.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        Random rand = new Random();
        System.out.println("Result Hash Code is : " + result.hashCode());
        System.out.println("copyResult Hash Code is : " + (new Fuck(result)).hashCode());
        fuckWithList(1, 1, 1, 1, 1, 1, 1, 1, 2, 2f);
    }

    public static Fuck F() {
        Fuck<AbsFuck.RealFuck> fuck = new Fuck<>(10, new AbsFuck.RealFuck());
        System.out.println(fuck.hashCode());
        System.out.println(fuck.getAsghar());
        return fuck;
    }

    public static void fuckWithList(Number... array) {
        List<Number> myList = new ArrayList<>(Arrays.asList(array));
        for (Number myInt : myList) {
            System.out.println("you enter number : " + myInt.toString());
        }
    }
}
