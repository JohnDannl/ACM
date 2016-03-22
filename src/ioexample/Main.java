package ioexample;
import java.util.Scanner;
/**
 * <b>HDOJ problem 1000</b> <br>
 * <1 The answer should contain a Main class name,<br>
 * <2 should not contain package info
 */
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
