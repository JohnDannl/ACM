import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class HumbleNum {
	public static long[] generateHumbleNumber(){
		int N=5842;
		long[] humbleArray=new long[N];
		int[] primeFactor={2,3,5,7};
		humbleArray[0]=1;	// The first number is 1
		TreeSet<Long> humbleSet=new TreeSet<Long>();
		for(int i=1;i<N;i++){							
			for(int k:primeFactor){
				long nextNum=humbleArray[i-1]*k;
				if(nextNum>humbleArray[i-1]){
					humbleSet.add(nextNum);
				}						
			}
			humbleArray[i]=humbleSet.first();
			humbleSet.remove(humbleArray[i]);			
		}
		return humbleArray;
	}
	public static void main(String args[]) throws IOException{
		StreamTokenizer in=new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		PrintWriter out=new PrintWriter(new OutputStreamWriter(System.out));
		
		long[] array=generateHumbleNumber();	
		/*for(long i:array){
			out.println(i);
		}*/
		while(true){
			in.nextToken();
			int numth=(int)in.nval;
			if (numth==0)break;
			if(numth%100>10&&numth%100<20){
				out.println(String.format("The %dth humble number is %d.", numth,array[numth-1]));
			}else{
				switch(numth%10){
				case 1:
					out.println(String.format("The %dst humble number is %d.", numth,array[numth-1]));
					break;
				case 2:
					out.println(String.format("The %dnd humble number is %d.", numth,array[numth-1]));
					break;
				case 3:
					out.println(String.format("The %drd humble number is %d.", numth,array[numth-1]));
					break;
				default:
					out.println(String.format("The %dth humble number is %d.", numth,array[numth-1]));
					break;						
				}
			}			
		}
		out.flush();
	}
}
