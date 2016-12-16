package company;

import java.util.Scanner;

public class AverageNum {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		float[] array = new float[10];
		int sum = 0;
		for (int i = 0; i < 10; i++) {
			array[i] = in.nextFloat();
			sum += array[i];
		}
		float average = sum / array.length;
		int num = 0;
		for (int i = 0; i < 10; i++) {
			if (array[i] > average) {
				num++;
			}
		}
		System.out.println(num);
	}
}
