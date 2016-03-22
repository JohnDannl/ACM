package p100x;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 * HDOJ problem 1003
 * @author dannl
 *
 */
public class P1003 {	
	public static void main(String[] args) throws IOException{
		StreamTokenizer in =new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		PrintWriter out =new PrintWriter(new OutputStreamWriter(System.out));
		in.nextToken();
		int CaseTotal=(int)in.nval;
		int lineno=0;
		while(lineno<CaseTotal){
			lineno++;
			// read the N and N numbers
			in.nextToken();
			int N=(int)in.nval;
			int[] a=new int[N];
			for(int j=0;j<N;j++){
				in.nextToken();
				a[j]=(int)in.nval;
			}
			// calculate the maximum sub-sequence using some optimization
			/*int[] s=new int[N];
			int maxSum=0,row=0,column=0;
			for(int j=0;j<N;j++)s[j]=0;// The first line
			for(int i=1;i<N+1;i++){
				for(int j=0;j<N+1-i;j++){
					s[j]+=a[j+i-1];
					if(s[j]>maxSum){
						maxSum=s[j];
						row=i;
						column=j;
					}
				}				
			}*/
			//using dynamic programming
			int maxSum=-1001,max_start=0,max_end=0,start=0,sum=0;
			for(int j=0;j<N;j++){
				if(sum>=0){
					sum+=a[j];
				}else {
					sum=a[j];
					start=j;						
				}
				if(sum>maxSum){
					maxSum=sum;	
					max_start=start;
					max_end=j;
				}
				/*sum+=a[j];
				if(sum>maxSum){
					maxSum=sum;
					max_start=start;
					max_end=j;
				}
				if(sum<0){
					sum=0;
					start=j+1;
				}*/
			}
			out.println(String.format("Case %d:", lineno));
			out.println(String.format("%d %d %d",maxSum,max_start+1,max_end+1));
			if(lineno<CaseTotal)out.println();
//			out.flush();
		}
		out.flush();
	}
}
