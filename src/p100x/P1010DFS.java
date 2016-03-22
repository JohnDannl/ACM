package p100x;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * HDOJ problem 1010<br>
 * Notice the odd and even branch-cut
 * @author dannl
 *
 */
public class P1010DFS {
	public static boolean success=false;
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		while(true){
			int n=in.nextInt();
			int m=in.nextInt();
			int t=in.nextInt();
			in.nextLine();	// skip the carriage return/line feed character
			if(n==0)break;
			success=false;
			char[][] b=new char[n][m];
			int sx = 0,sy=0,dx = 0,dy = 0;
			String line;
			for(int i=0;i<n;i++){
				line=in.nextLine();
				for(int j=0;j<m;j++){
					b[i][j]=line.charAt(j);
					if(b[i][j]=='S'){sx=i;sy=j;}
					if(b[i][j]=='D'){dx=i;dy=j;}
				}				
			}
			
			if(n*m<t){
				System.out.println("NO");
				continue;
			}
			visit(b,sx,sy,-1,t,dx,dy);	
			if(success)System.out.println("YES");
			else System.out.println("NO");	
		}		
	}
	public static void visit(char[][] a,int x,int y,int pdeep,int t,int dx,int dy){
		/*System.out.println(x+" "+y+" "+pdeep+" "+t);
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a[0].length;j++)
				System.out.print(a[i][j]+" ");
			System.out.println();
			}*/
		if(success)return;
		int left=t-(pdeep+1+Math.abs(dx-x)+Math.abs(dy-y));
		if(left<0||left%2==1)return;	// odd and even cut branch
		if(pdeep+1==t){
			if(a[x][y]=='D'){
			success=true;return;
			}else return;}	
		char tmp=a[x][y];
		a[x][y]='X';
		if(x-1>=0&&a[x-1][y]!='X')visit(a,x-1,y,pdeep+1,t,dx,dy);
		if(x+1<a.length&&a[x+1][y]!='X')visit(a,x+1,y,pdeep+1,t,dx,dy);
		if(y-1>=0&&a[x][y-1]!='X')visit(a,x,y-1,pdeep+1,t,dx,dy);
		if(y+1<a[0].length&&a[x][y+1]!='X')visit(a,x,y+1,pdeep+1,t,dx,dy);
		a[x][y]=tmp;
	}
}
