package company;

import java.util.Scanner;

public class BrotherHw {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int sum = 0;
		while(in.hasNext()) {
			String line = in.nextLine();
			if (line.equals("0")) {
				break;
			}
			String[] array1 = line.split("=");
			if (!array1[1].equals("?")) {
				if (array1[0].contains("+")) {
					String[] array2 = array1[0].split("\\+");
					int c = Integer.valueOf(array1[1]);
					int a = Integer.valueOf(array2[0]);
					int b = Integer.valueOf(array2[1]);
					if (a + b == c) {
						sum += 1;
					}
				} else {
					String[] array2 = array1[0].split("-");
					int c = Integer.valueOf(array1[1]);
					int a = Integer.valueOf(array2[0]);
					int b = Integer.valueOf(array2[1]);
					if (a - b == c) {
						sum += 1;
					}
				}
			}
		}
		System.out.println(sum);
	}
}
