/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unkhownclass;

import java.lang.reflect.Constructor;

/**
 *
 * @author Parham Alvani
 */
public class ClassGuesser {

    private final Class<?> aClass;

    public ClassGuesser(String fullClassName) throws ClassNotFoundException {
        aClass = Class.forName(fullClassName);
    }

    public void getConstructors() {
        Constructor<?>[] constructors = aClass.getConstructors();
        int index = 0;
        for (Constructor<?> cons : constructors) {
            System.out.println(index + " " + cons.getName() + " From Class " + aClass.getName() + " and it's Arg is :");
            index++;
            Class<?>[] parameterTypes = cons.getParameterTypes();
            if (parameterTypes.length == 0) {
                System.out.println("No Args Needed");
            } else {
                for (Class<?> args : parameterTypes) {
                    System.out.println(args.getName());
                }
            }
        }
    }

}
