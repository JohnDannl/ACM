package p101x;
import java.util.Scanner;

/**
 * HDOJ problem 1015<br>
 * Sort the input uppercase alphabets/letters in descent order to keep<br>
 * the solution is lexicographically greatest<br>
 * then using deep-first-search to find the letter combination that <br>
 * satisfies the equation<br>
 * @author dannl
 *
 */
public class P1015 {
	private static int nMax=12;
	private static int[] result=new int[5];
	private static int[] seqs=new int[nMax];
	private static boolean[] visited=new boolean[nMax];
	private static int target;
	private static boolean success;
	private static int alphnum=0;
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		while(true){
			String line=in.nextLine();
			String[] inputs=line.split("\\s+");
			target=Integer.parseInt(inputs[0]);
			if(target==0)break;
			byte[] alphs=inputs[1].getBytes();
			alphnum=alphs.length;
			for(int i=0;i<nMax;i++){	// initialization
				seqs[i]=0;
				visited[i]=false;
			}			
			for(int i=0;i<alphs.length;i++){
				seqs[i]=alphs[i]-'A'+1;
			}				
			success=false;
			crackKlein(seqs);
			if(success){
				for(int i=0;i<result.length;i++){				
					System.out.print((char)(result[i]+'A'-1));
				}					
				System.out.println();
			}else{
				System.out.println("no solution");
			}
		}		
	}
	public static void crackKlein(int[] seqs){
		quickSortedDesc(seqs,0,seqs.length-1);
		dfs(0);
	}
	public static void dfs(int deep){
		if(success)return;
		if(deep>=5){	//v - w^2 + x^3 - y^4 + z^5 = target 
			if(result[0]-Math.pow(result[1], 2)+Math.pow(result[2], 3)
					-Math.pow(result[3], 4)+Math.pow(result[4], 5)==target){
				success=true;
			}
			return;
		}
		for(int i=0;i<alphnum;i++){
			if(success)return;
			if(!visited[i]){
				result[deep]=seqs[i];
				visited[i]=true;
				dfs(deep+1);
				visited[i]=false;
			}						
		}
	}
	public static void quickSortedDesc(int[] a,int p,int r){
		if(p>=r)return;
		int i=p,j=r+1,head=a[p],tmp;
		while(true){
			while(a[++i]>head&&i<r);
			while(a[--j]<head);
			if(i>=j)break;
			tmp=a[i];
			a[i]=a[j];
			a[j]=tmp;
		}
		a[p]=a[j];
		a[j]=head;
		quickSortedDesc(a,p,j-1);
		quickSortedDesc(a,j+1,r);
	}
}
