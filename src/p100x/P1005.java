package p100x;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * HDOJ problem 1005
 * @author dannl
 *
 */
public class P1005 {
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		while(in.hasNext()){
			int a=in.nextInt();
			int b=in.nextInt();
			int n=in.nextInt();			
			if(a==0&&b==0&&n==0)break;
			int[] fn=new int[52];
			fn[0]=1;
			fn[1]=1;
			int start=0,end=0;
			for(int i=2;i<n;i++){
				fn[i]=(a*fn[i-1]+b*fn[i-2])%7;
				for(int j=i-1;j>0;j--){
					if(fn[i]==fn[j]&&fn[i-1]==fn[j-1]){
						start=j-1;
						end=i-2;
						break;
					}
				}
				if(end!=0)break;
			}			
			if(end==0)System.out.println(fn[n-1]);
			else{
				if(n>start)
					System.out.println(fn[((n-1-start)%(end-start+1))+start]);
				else
					System.out.println(fn[n-1]);
			}			
		}
	}
}
