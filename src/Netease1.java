
import java.util.Scanner;


public class Netease1 {
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		int[] a=new int[9];		
		for(int i=0;i<9;i++){
			a[i]=in.nextInt();
		}
		int r2=a[0]*a[0];
		int hurt=0;
		for(int i=1;i<=3;i++){
			if(distance(a[2*i-1],a[2*i],a[7],a[8])<=r2)hurt++;
		}
		System.out.println(hurt+"X");
	}
	public static int distance(int x1,int y1,int x2,int y2){
		return (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2);
	}
}
