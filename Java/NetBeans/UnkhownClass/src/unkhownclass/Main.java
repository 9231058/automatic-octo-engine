/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unkhownclass;

import java.lang.reflect.Constructor;
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
        Scanner cin = new Scanner(System.in);
        System.out.println("Please enter your class name :");
        try {
            ClassGuesser myClassGuesser = new ClassGuesser(cin.next());
            myClassGuesser.getConstructors();
        } catch (ClassNotFoundException exception) {
            System.out.println("Your class name is not found ......");
        }
    }
    
}
