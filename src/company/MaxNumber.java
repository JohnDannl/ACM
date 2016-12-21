package company;

import java.util.Scanner;

public class MaxNumber {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			int R = in.nextInt();
			int C = in.nextInt();
			if (R == 0 && C == 0) {
				break;
			}
			String line = in.nextLine();
			int[][] a = new int[R][C];
			for (int i = 0; i < R; i++) {
				line = in.nextLine();
				for (int j = 0; j < C; j++) {
					char ch = line.charAt(j);
					if (ch == '#') {
						a[i][j] = -1;
					} else {
						a[i][j] = ch - '0';
					}
					//System.out.print(a[i][j]);
				}
				//System.out.println();
			}
			long maxNum = -1, numij = -1;
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					String numStr = getMaxNum(a, i, j);
					System.out.println("" + i + "," + j + ":" + numStr);
					numij = Long.valueOf(numStr);
					if (numij > maxNum) {
						maxNum = numij;
					}
				}
			}
			System.out.println(maxNum);
		}
	}
	public static String getMaxNum(int[][] a, int r, int c) {
		StringBuilder sb = new StringBuilder();
		if (r >= a.length || c >= a[0].length || r < 0 || c < 0 || a[r][c] == -1) {
			return "-1";			
		} else {
			sb.append(String.valueOf(a[r][c]));
		}
		int tmp = a[r][c];
		a[r][c] = -1;
		String left1 = "-1", right1 = "-1", up1 = "-1", down1 = "-1";
		String left = getMaxNum(a, r, c - 1);		
		if (!left.equals("-1")) {
			left1 = left;		
			System.out.println("left:" + left);
			left = "1" + left; 
		}
		String right = getMaxNum(a, r, c + 1);
		if (!right.equals("-1")) {
			right1 = right;
			System.out.println("right:" + right);
			right = "1" + right; 
		}
		String up = getMaxNum(a, r - 1, c);
		if (!up.equals("-1")) {
			up1 = up;
			System.out.println("up:" + up);
			up = "1" + up; 
		}
		String down = getMaxNum(a, r + 1, c);
		if (!down.equals("-1")) {
			down1 = down;
			System.out.println("down:" + down);
			down = "1" + down; 
		}
		a[r][c] = tmp; // restore data
		long leftNum = Long.valueOf(left);
		long rightNum = Long.valueOf(right);
		long upNum = Long.valueOf(up);
		long downNum = Long.valueOf(down);
		long maxNum = -1;
		String maxStr = "-1";
		if (leftNum > maxNum) {
			maxNum = leftNum;
			maxStr = left1;
		}
		if (rightNum > maxNum) {
			maxNum = rightNum;
			maxStr = right1;
		}
		if (upNum > maxNum) {
			maxNum = upNum;
			maxStr = up1;
		}
		if (downNum > maxNum) {
			maxNum = downNum;
			maxStr = down1;
		}
		if (maxNum != -1) {
			return sb.append(maxStr).toString();
		} else {
			return sb.toString();
		}
	} 
}
