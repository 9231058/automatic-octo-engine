/**
 * 
 */
package test;

import java.util.Random;

/**
 * @author parham
 * 
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("\033[1;30m salam");
		new XMLHandler();
		Random rand = new Random();
		System.out.println("Hello ....");
		System.out.println(rand.nextInt(1));
		NestedClass nestedClass = new NestedClass();
		nestedClass.toString();
		nestedClass.new Nested();
	}
}
