/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package generic.test;

/**
 *
 * @author Parham Alvani
 * @param <T>
 */
public class GenClass <T extends Object> {
    private final T refT;
    public GenClass(T refT) {
        this.refT = refT;
    }
    public T getRegT(){
        return refT;
    }
}
