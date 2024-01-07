package menchclient;

import java.util.Random;

public class Dice { // DONE

	int x;
	Random r = new Random();

	public int newDice() {
		
		int n = r.nextInt(8) + 1;
		
		if (n < 6) 
			return n;
		else 
			return 6;
	}
	
	public int getDice() {
		
		return x;
	}
}