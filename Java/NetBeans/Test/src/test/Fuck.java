/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author Parham Alvani
 * @param <T>
 */
public class Fuck <T extends AbsFuck>{

    private final int asghar;
    private final T asgharT;

    //public Fuck FUCK_ME;
    public Fuck(int a,T refT) {
        this.asgharT = refT;
        this.asghar = a;
        System.out.println("This is Generic Class");
        asgharT.fuckYouMan();
        //this.FUCK_ME=new Fuck(10);
    }
    
    public Fuck(Fuck copyFuck){
        this.asgharT = null;
        this.asghar = copyFuck.asghar;
    }

    public int getAsghar() {
        return this.asghar;
    }

    @Override
    public String toString() {
        return String.format("Hey Every Body Iam a Fuck with Number %d", this.asghar);
    }
}
