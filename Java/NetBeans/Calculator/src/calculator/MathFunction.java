/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

/**
 *
 * @author Parham Alvani
 */
public final class MathFunction {

    public static int GCD(int number1, int number2) {
        if (number2 == 0) {
            return number1;
        }
        return GCD(number2, number1 % number2);
    }
}
