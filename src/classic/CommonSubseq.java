package classic;

import java.util.Scanner;


public class CommonSubseq {
	public static void main(String[] args){				
		Scanner in =new Scanner(System.in);
		while(in.hasNext()){
			String line=in.nextLine();
			String[] a=line.split("\\s+");
			int clen=longestCommonSubsequence2(a[0],a[1]);
			System.out.println(clen);
		}
	}
	/**
	 * The standard Dynamic Programming solution
	 * @param a
	 * @param b
	 * @return
	 */
	public static int longestCommonSubsequence(String a,String b){
		byte[] ba=a.getBytes();
		byte[] bb=b.getBytes();
		int[][] c=new int[ba.length][bb.length];		
		// the first column
		for(int i=0;i<ba.length;i++){
			int clen=0;			
			if(i==0){
				if(ba[i]==bb[0])clen=1;
				c[i][0]=clen;
			}else{
				if(ba[i]==bb[0])clen=1;
				if(clen>c[i-1][0])c[i][0]=clen;
				else c[i][0]=c[i-1][0];
			}
			
		}
		// the first row
		for(int j=0;j<bb.length;j++){			
			int clen=0;
			if(j==0){
				if(bb[j]==ba[0])clen=1;
				c[0][j]=clen;
			}else{
				if(bb[j]==ba[0])clen=1;
				if(clen>c[0][j-1])c[0][j]=clen;
				else c[0][j]=c[0][j-1];
			}			
		}
		// the other row and column
		for(int i=1;i<ba.length;i++){
			for(int j=1;j<bb.length;j++){
				if(ba[i]==bb[j])c[i][j]=c[i-1][j-1]+1;
				else{
					if(c[i][j-1]>c[i-1][j])c[i][j]=c[i][j-1];
					else c[i][j]=c[i-1][j];
				}
			}
		}
		return c[ba.length-1][bb.length-1];
	}
	/**
	 * Some memory optimization for standard dynamic programming
	 * @param a
	 * @param b
	 * @return
	 */
	public static int longestCommonSubsequence2(String a,String b){
		byte[] ba=a.getBytes();
		byte[] bb=b.getBytes();
		int[] c_pre=new int[bb.length];
		int[] c_cur=new int[bb.length];	
		// the first row
		for(int j=0;j<bb.length;j++){	
			int clen=0;	
			if(j==0){
				if(bb[j]==ba[0])clen=1;
				c_pre[j]=clen;
			}else{
				if(bb[j]==ba[0])clen=1;
				if(clen>c_pre[j-1])c_pre[j]=clen;
				else c_pre[j]=c_pre[j-1];
			}
					
			
		}				
		// the other row and column
		for(int i=1;i<ba.length;i++){
			for(int j=0;j<bb.length;j++){
				int clen=0;
				if(j==0){	// the first column
					if(ba[i]==bb[j])clen=1;
					if(clen>c_pre[j])c_cur[j]=clen;
					else c_cur[j]=c_pre[j];
				}else{
					if(ba[i]==bb[j])c_cur[j]=c_pre[j-1]+1;
					else{
						if(c_cur[j-1]>c_pre[j])c_cur[j]=c_cur[j-1];
						else c_cur[j]=c_pre[j];
					}
				}				
			}
			System.arraycopy(c_cur, 0, c_pre, 0, c_pre.length);
		}
		return c_cur[bb.length-1];
	}
}
