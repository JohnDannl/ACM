package p100x;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * HDOJ problem 1010<br>
 * Breath-First-Search,but time limit exceeded 
 * @author dannl
 *
 */
public class P1010BFS {
	public static boolean success;
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
			ArrayList<MazeNode> nodes=new ArrayList<MazeNode>();
			nodes.add(new MazeNode(sx,sy,b,-1));	// the start point's parent journey should be -1
			while(!nodes.isEmpty()){				
				if(success)break;
				MazeNode start=nodes.remove(0);
				goOut(nodes,start,t,dx,dy);
			}
			if(success)System.out.println("YES");
			else System.out.println("NO");					
		}
		
		
	}
	public static void goOut(ArrayList<MazeNode> nodes,MazeNode start,int t,int dx,int dy){
		int sx=start.x,sy=start.y;
		char[][] b=start.a;		
		/*for(int i=0;i<b.length;i++){
			for(int j=0;j<b[0].length;j++)
				System.out.print(b[i][j]+" ");
			System.out.println();
		}*/
		if(success)return;
		int left=t-start.journey-Math.abs(sx-dx)-Math.abs(sy-dy);
		if(left<0||left%2==1)return;	// minimum distance and odd/even branches cut
		if(start.journey==t&&start.x==dx&&start.y==dy){success=true;return;}
		if(sx-1>=0&&b[sx-1][sy]!='X'){	// add upper node
			MazeNode node=new MazeNode(sx-1,sy,b,start.journey);				
			nodes.add(node);
		}
		if(sx+1<b.length&&b[sx+1][sy]!='X'){ // add node below
			MazeNode node=new MazeNode(sx+1,sy,b,start.journey);				
			nodes.add(node);
		}
		if(sy-1>=0&&b[sx][sy-1]!='X'){	// left			
			MazeNode node=new MazeNode(sx,sy-1,b,start.journey);				
			nodes.add(node);
		}
		if(sy+1<b[0].length&&b[sx][sy+1]!='X'){ // right			
			MazeNode node=new MazeNode(sx,sy+1,b,start.journey);				
			nodes.add(node);
		}			
	}
}
class MazeNode{
	int x,y;
	int journey=0;
	char [][] a;
	public MazeNode(int x,int y,char[][] b,int pjourney){
		this.x=x;
		this.y=y;
		this.journey=pjourney+1;
		a=new char[b.length][b[0].length];
		//System.arraycopy(b, 0, a, 0, b.length);
		for(int i=0;i<a.length;i++)
			System.arraycopy(b[i], 0, a[i], 0, b[0].length);
		a[x][y]='X';
	}
}
