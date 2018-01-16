package company;
import java.math.BigInteger;
import java.util.Scanner;

public class FourOperations {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		in.nextLine();
		int count = 0;
		while (count < T) {
			count ++;
			String numbers = in.nextLine();
			int len = numbers.length();
			long max = Long.MIN_VALUE;
			for (int i = 1; i <= len - 4; i ++) {
				for (int j = 1; j <= len - i - 3; j ++) {
					for (int k = 1; k <= len - i - j -2; k ++) {
						for (int m = 1; m <= len - i -j - k -1; m ++) {
							long a = Long.parseLong(numbers.substring(0, i));
							long b = Long.parseLong(numbers.substring(i, i + j));
							long c = Long.parseLong(numbers.substring(i + j, i + j + k));
							long d = Long.parseLong(numbers.substring(i + j + k, i + j + k + m));
							long e = Long.parseLong(numbers.substring(i + j + k + m, len));
							
							long tmp = a + b - c * d / e;
							if (tmp > max) max = tmp;
						}
					}
				}
			}
			
			System.out.println(String.format("Case #%d: %s", count, max));
			
		}
	}
}
