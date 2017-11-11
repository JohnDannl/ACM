package p101x;

import java.util.Scanner;

public class P1017 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int block = 0;
		while (block < N) {
			block ++;
			processBlock(in);
			if (block < N)
			System.out.println();
		}
	}
	public static void processBlock(Scanner in) {
		int i = 0;
		while(true) {
			i++;
			int n = in.nextInt();
			int m = in.nextInt();
			if (n == 0 && m == 0) break;
			int ret = processPair(n, m);
			System.out.println(String.format("Case %d: %d", i, ret));
		}
	}
	
	public static int processPair(int n, int m) {
		if (n < 1) return 0;
		int count = 0;
		for (int a = 1; a < n - 1; a++) {
			for (int b = a + 1; b < n; b++) {
				if ((a*a + b*b + m) % (a*b) == 0) count++;
			}
		}
		return count;
	}
	
}
