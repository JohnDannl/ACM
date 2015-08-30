package aplusb;
/**
 * <1 The answer should contain a Main class name,
 * <2 should not contain package info
 */

import java.util.Scanner;
public class Main {
	public static void main(String[] args){
		Scanner scan=new Scanner(System.in);
		while(scan.hasNextInt()){
			int a=scan.nextInt();
			int b=scan.nextInt();
			System.out.println(a+b);
		}		
	}
}
