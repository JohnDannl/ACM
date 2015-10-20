import java.util.Scanner;


public class Elevator {
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		while(true){
			if(n==0)break;
			int[] intarr=new int[n];
			for(int i=0;i<n;i++)intarr[i]=in.nextInt();
			int sum=6*intarr[0],tmp;
			for(int i=0;i<intarr.length-1;i++){
				tmp=intarr[i+1]-intarr[i];
				if(tmp>0)sum+=tmp*6;
				else if(tmp<0)sum+=-tmp*4;
			}
			sum+=intarr.length*5;
			System.out.println(sum);
			n=in.nextInt();
		}	
		
	}	
}
