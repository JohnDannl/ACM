package company;

import java.math.BigInteger;
import java.util.Scanner;

public class BigDivision {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			BigInteger a = in.nextBigInteger();
			if (a.equals(BigInteger.valueOf(0))) {
				break;
			}
			if (a.mod(BigInteger.valueOf(17)).equals(BigInteger.valueOf(0))) {
				System.out.println(1);
			} else {
				System.out.println(0);
			}
		}
	}
}
