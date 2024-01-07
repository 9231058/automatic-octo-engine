import java.math.BigInteger;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		BigInteger n = cin.nextBigInteger();
		cin.close();
		BigInteger r = BigInteger.ONE;
		BigInteger lower = BigInteger.ZERO;
		BigInteger upper = new BigInteger(n.toString());
		while ((upper.add(lower.negate())).compareTo(new BigInteger("1")) > 0) {
			BigInteger mider = lower.add(upper).divide(new BigInteger("2"));
			r = mider;
			if (n.compareTo(r.pow(2)) < 0) {
				upper = mider;
			} else if (n.compareTo(r.pow(2)) == 0) {
				break;
			} else {
				lower = mider;
			}
		}
		if (n.compareTo(r.pow(2)) < 0) {
			r = r.add(new BigInteger("-1"));
		}
		System.out.println(r);
	}
}
