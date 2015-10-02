import java.util.Scanner;


public class MaxDiff {
	public static int findMaxDifference(int[] arr,int len){
		int maxDif=0;
		for(int i=0;i<len;i++){
			for (int j=i;j<len;j++){
				int tmp=arr[j]-arr[i];
				if(tmp>maxDif)maxDif=tmp;
			}
		}
		return maxDif;
	}
	public static void main(String[] args){
		int[] arr={10,5};
		int len=2;
		System.out.println(findMaxDifference(arr,2));
	}
}