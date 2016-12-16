package company;

import java.util.Scanner;

public class Bottle {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int a = in.nextInt();
			if (a == 0){
				break;
			}
			System.out.println(process(a));
		}
	}
	
	public static int process(int a) {
		int sum = 0, quotient = 0, remainder = 0, divident;
		divident = a;
			while(divident >= 3) {
				quotient = divident / 3;
				remainder = divident % 3;
				divident = quotient + remainder;
				sum += quotient;
			}
			switch(divident) {
				case 1:
					break;
				case 2:
					sum += 1;
					break;
			}
		return sum;
	}
}
