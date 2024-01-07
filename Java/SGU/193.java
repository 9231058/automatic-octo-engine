import java.math.BigInteger;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) 
		Scanner cin = new Scanner(System.in);
		BigInteger bigInteger = cin.nextBigInteger();
		if (bigInteger.mod(new BigInteger("2")).equals(BigInteger.ZERO)) {
			bigInteger = bigInteger.divide(new BigInteger("2"));
			if (bigInteger.mod(new BigInteger("2")).equals(BigInteger.ZERO)) {
				System.out.println(bigInteger.add(new BigInteger("-1")));
			} else {
				System.out.println(bigInteger.add(new BigInteger("-2")));
			}
		} else {
			System.out.println((bigInteger.add(new BigInteger("-1"))).divide(new BigInteger("2")));
		}
		cin.close();
	}
}
