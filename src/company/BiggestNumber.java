package company;

import java.util.Arrays;
import java.util.Scanner;

public class BiggestNumber {
	static int[][] dir = {
			{-1, 0},
			{1, 0},
			{0, -1},
			{0, 1}};
	static class Ans implements Comparable<Ans>{
		int len = 0;
		int[] number = new int[30];
		
		public int compareTo(Ans other) {
			// TODO Auto-generated method stub
			if (len > other.len) return 1;
			else if(len < other.len) return -1;
			else {
				for (int i = 0; i < len; i++) {
					if (number[i] > other.number[i]) return 1;
					else if (number[i] < other.number[i]) return -1;
				}
				return 0;
			}
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < len; i++) {
				sb.append(number[i]);
			}
			return sb.toString();			
		}
	}
	private static Ans maxNum = new Ans();
	private static Ans current = new Ans();
	private static int R, C;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			R = in.nextInt();
			C = in.nextInt();
			if (R == 0 && C == 0) {
				break;
			}
			String line = in.nextLine();
			int[][] a = new int[R][C];
			int count = 0;
			for (int i = 0; i < R; i++) {
				line = in.nextLine();
				for (int j = 0; j < C; j++) {
					char ch = line.charAt(j);
					if (ch == '#') {
						a[i][j] = -1;
					} else {
						a[i][j] = ch - '0';
						count++;
					}
					//System.out.print(a[i][j]);
				}
				//System.out.println();
			}
			maxNum = new Ans();
			current = new Ans();
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					getMaxNumDFS(a, i, j, 0);
				}
			}
			System.out.println(maxNum);
		}
	}
	public static void getMaxNumDFS(int[][] a, int r, int c, int level) {
		if (r >= R || c >= C || r < 0 || c < 0 || a[r][c] == -1) {
			return;			
		}		
		if (!judgePotential(a, r, c, level)) return;
		int tmp = a[r][c];
		current.number[level] = tmp;	
		current.len = level + 1;
		if (current.compareTo(maxNum) > 0) {
			maxNum.len = current.len;
			System.arraycopy(current.number, 0, maxNum.number, 0, current.len);
		}
		a[r][c] = -1;
		for (int i = 0; i < dir.length; i++) {
			getMaxNumDFS(a, r + dir[i][0], c + dir[i][1], level + 1);
		}
		a[r][c] = tmp; // restore data
	} 
	
	/**
	 * 判断是否有潜力成为最大值
	 * @param a
	 * @param r
	 * @param c
	 * @param stack
	 * @param R
	 * @param C
	 * @param level
	 * @return
	 */
	public static boolean judgePotential(int[][] a, int r, int c, int level) {
		Point[] stack = new Point[R*C];
		int[][] used = new int[R][C];
		int len = getStackNumBFS(a, r, c, stack, used);	
		if (level + len < maxNum.len) return false;
		else if (level + len > maxNum.len ) return true;
		else {
			for (int i = 0; i < level; i ++) {
				if (current.number[i] < maxNum.number[i]) return false;
				else if (current.number[i] > maxNum.number[i]) return true;
			}
			if (a[r][c] < maxNum.number[level]) return false;
			else return true;
		}
		
	}
	
	public static int getStackNumBFS(int[][] a, int r, int c, Point[] stack, int[][] used) {
		int top = 0, bottom = 1;
		stack[top] = new Point(r, c, a[r][c]);
		used[r][c] = 1;
		while (top < bottom) {	
			for (int i = 0; i < dir.length; i++) {
				int row = stack[top].row + dir[i][0];
				int column = stack[top].column + dir[i][1];
				if (row >= 0 && row < R && column >= 0 && column < C 
						&& a[row][column] != -1 && used[row][column] != 1) {
					stack[bottom] = new Point(row, column, a[row][column]);
					used[row][column] = 1;
					bottom ++;
				}
			}
			top++;
		}		
		return bottom;
	}
	
	static class Point {
		public Point(int row, int column, int value) {
			this.row = row;
			this.column = column;
			this.value = value;
		}
		int row;
		int column;
		int value;
	}
}
