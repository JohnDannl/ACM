package company;

import java.util.Scanner;

public class CatAndMouse {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int M, N;
		M = in.nextInt();
		N = in.nextInt();
		while (M != -1 && N != -1) {
			int result = process(M, N, in);
			System.out.println(result);
			M = in.nextInt();
			N = in.nextInt();
		}
	}

	public static int process(int M, int N, Scanner in) {
		int[] value = new int[N + 1];
		int[] weight = new int[N + 1];
		int[] result = new int[M + 1];
		for (int i = 1; i < N + 1; i++) {
			value[i] = in.nextInt();
			weight[i] = in.nextInt();
		}
		for (int i = 0; i < M + 1; i++) {
			result[i] = 0;
		}
		for (int i = 1; i < N + 1; i++) {
			for (int j = M; j >= 0; j--) {
				if (j >= weight[i])
					result[j] = max(result[j], result[j - weight[i]] + value[i]);
			}
		}
		return result[M];
	}

	public static int process2(int M, int N, Scanner in) {
		int[] value = new int[N + 1];
		int[] weight = new int[N + 1];
		int[][] result = new int[N + 1][M + 1];
		for (int i = 1; i < N + 1; i++) {
			value[i] = in.nextInt();
			weight[i] = in.nextInt();
		}

		int init = 0;
		for (int i = 0; i < N + 1; i++) {
			if (weight[i] == 0)
				init += value[i];
			result[i][0] = init;
		}
		for (int j = 0; j < M + 1; j++) {
			result[0][j] = 0;
		}
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < M + 1; j++) {
				if (j < weight[i]) {
					result[i][j] = result[i - 1][j];
				} else {
					result[i][j] = max(result[i - 1][j], result[i - 1][j - weight[i]] + value[i]);
				}
			}
		}
		return result[N][M];
	}

	public static int max(int i, int j) {
		return i > j ? i : j;
	}
}
