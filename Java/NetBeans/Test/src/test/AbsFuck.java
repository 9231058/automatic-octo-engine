/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

/**
 *
 * @author Parham Alvani
 */
public abstract class AbsFuck {
    protected AbsFuck(){
        
    }
    abstract public void fuckYouMan();
    private static void ahAh(){
        System.out.println("Akh Akh");
    }
    public static class RealFuck extends AbsFuck{

        public RealFuck() {
            System.out.println("New Inner Class Object is created");
        }
        @Override
        public void fuckYouMan() {
            System.out.println("Ah Ah .....");
            AbsFuck.ahAh();
        }
        
    }
}
