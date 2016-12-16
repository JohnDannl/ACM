package company;

import java.util.Scanner;

public class PrimeSum {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int a = in.nextInt();
		int b = in.nextInt();
		int sum = 0;
		for (int i = a; i <= b; i++) {
			if (isPrime(i)) {
				sum += i;
			}
		}
		System.out.println(sum);
	}
	
	private static boolean isPrime(int a) {
		if (a < 2) return false;
		if (a == 2) return true;
		if (a % 2 == 0) return false;
		for (int i = 3; i * i <= a; i += 2) {
			if (a % i == 0) {
				return false;
			}
		}
		return true;
	}
}
