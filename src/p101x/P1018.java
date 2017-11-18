package p101x;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class P1018 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int count = 0;
		int[] origin = new int[n];
		int[] sort = new int[n];
		Map<Integer, Integer> results = new HashMap<Integer, Integer>();
		while(count < n) {
			int a = in.nextInt();
			origin[count] = a;
			sort[count] = a;
			System.out.println(process4(a));
			count++;
		}
		/*quickSort(sort, 0, n - 1);
		process3(sort, results);
		count = 0;
		while (count < n) {
			System.out.println(results.get(origin[count]));
			count++;
		}*/
	}
	public static void printA(int[] a){
		for (int i : a) {
			System.out.print(i + ",");
		}
		System.out.println();
	}
	public static int process4(int a) {
		double sum = 1;
		for (int i = 1; i <= a; i++) {
			sum += Math.log10(i);
		}
		return (int) sum;
	}
	public static void process3(int[] sort, Map<Integer, Integer> results) {
		double len = 1.0;
		int j = 0;
		for (int i = 1; i <= sort[sort.length - 1]; i++) {
			len += Math.log10(i);
			if (i == sort[j]) {
				results.put(i, (int)len);
				j++;
			}
		}
	}
	
	public static void process2(int[] sort, Map<Integer, Integer> results) {
		BigInteger result = new BigInteger("1");
		int j = 0;
		for (int i = 1; i <= sort[sort.length - 1]; i++) {
			result = result.multiply(new BigInteger(String.valueOf(i)));
			if (i == sort[j]) {
				results.put(i, String.valueOf(result).length());
				j++;
			}
		}
	}
	
	public static void process(int[] sort, Map<Integer, Integer> results) {
		int j = 0;
		String result = "1";
		for (int i = 1; i <= sort[sort.length - 1]; i++) {
			result = strMultiply(result, String.valueOf(i));
			if(i == sort[j]) {
				results.put(i, String.valueOf(result).length());
				j++;
			}
		}
		//System.out.println(result);
	}
	
	public static String strMultiply(String a, String b) {
		if (a.length() < 2 && b.length() < 2) {
			int xa = Integer.valueOf(a);
			int xb = Integer.valueOf(b);
			return String.valueOf(xa * xb);
		}
		int[] xa = new int[a.length()];
		int[] xb = new int[b.length()];
		for (int i = 0; i < a.length(); i++) {
			xa[i] = a.charAt(i) - '0';
		}
		for (int i = 0; i < b.length(); i++) {
			xb[i] = b.charAt(i) - '0';
		}
		
		int[] result = new int[a.length() + b.length()];
		for (int j = b.length() - 1; j >= 0; j--) {
			for (int i = a.length() - 1; i >= 0; i --) {
				int high = 0;
				int low = 0;
				int ret = xa[i] * xb[j];
				if (ret > 9) {
					high = ret / 10;
					low = ret % 10;
				} else {
					low = ret;
				}
				 
				if (result[i + j + 1] + low > 9) {
					high += 1; 
				}
				result[i + j + 1] = (result[i + j + 1] + low) % 10; 
				result[i + j] += high;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		if (result[0] > 0) sb.append(result[0]);
		for (int i = 1; i < result.length; i++) {
			sb.append(result[i]);
		}
		return sb.toString();
	}
	
	public static void quickSort(int[] a, int p, int q) {
		if (p >= q)return;
		int i = p;
		int j = q - 1;
		int r = q;
		while (true) {
			while(a[i] <= a[r] && i < q) i++;
			while(a[j] >= a[r] && j > p) j--;
			if (i < j) {
				int tmp = a[i];
				a[i] = a[j];
				a[j] = tmp;
			} else {
				int tmp = a[i];
				a[i] = a[r];
				a[r] = tmp;
				break;
			}
		}
		quickSort(a, p, i - 1);
		quickSort(a, i + 1, q);
	}
}
