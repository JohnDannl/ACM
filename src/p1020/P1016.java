package p1020;
import java.io.PrintWriter;
import java.util.Scanner;
/**
 * HDOJ problem 1016
 * The first element should be 1,and from 2 to n,using deep-first-search<br>
 * to find all the solutions which satisfy the condition.<br>
 * Note the space at the end of the row counts.<br>
 * @author dannl
 *
 */
public class P1016 {
	private static int n=0;
	private static int nMax=20;
	private static int[] result=new int[nMax];
	private static boolean[] visited=new boolean[nMax];
	private static PrintWriter out;
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		int count=0;
		while(in.hasNext()){
			n=Integer.parseInt(in.nextLine());
			for(int i=0;i<nMax;i++){	//initialization
				result[i]=0;
				visited[i]=false;
			}
			count++;
			System.out.println(String.format("Case %d:", count));
			dfs(0);
			System.out.println();
		}		
	}
	public static void dfs(int deep){
		if(deep==n){
			if(isPrime(result[deep-1]+result[0])){
				for(int i=0;i<n-1;i++)System.out.print(result[i]+" ");
				System.out.println(result[n-1]);	// this space is contributing
			}
			return;
		}
		if(deep==0){	// the first element should be 1
			result[deep]=1;
			visited[0]=true;
			dfs(deep+1);
			visited[0]=false;
		}else{
			for(int i=1;i<n;i++){		
				result[deep]=i+1;
				if(!visited[i]&&isPrime(result[deep]+result[deep-1])){
					visited[i]=true;
					dfs(deep+1);
					visited[i]=false;
				}				
			}
		}		
	}
	public static boolean isPrime(int n){
		if(n==2)return true;
		for(int i=2;i*i<=n;i++){
			if(n%i==0)return false;
		}
		return true;
	}
}
