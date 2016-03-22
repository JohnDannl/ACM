package p101x;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * HDOJ problem 1011<br>
 * confused,wrong answer
 * @author dannl
 *
 */
public class P1011 {
	static int nMax=101;
	static BugRoom[] rooms=new BugRoom[nMax];	
	static boolean[][] tunnels=new boolean[nMax][nMax];
	static int[][] prob=new int[nMax][nMax];
	static int n,m;
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		
		while(true){
			n=in.nextInt();
			m=in.nextInt();
			if(n==-1&&m==-1)break;
			for(int i=1;i<=n;i++){
				int bugs=in.nextInt();
				int brains=in.nextInt();
				rooms[i]=new BugRoom(bugs,brains,false);
			}
			for(int i=1;i<n;i++){
				int room1=in.nextInt();
				int room2=in.nextInt();
				tunnels[room1][room2]=true;
			}
			for(int i=0;i<nMax;i++)
				for(int j=0;j<nMax;j++)
					prob[i][j]=0;
			if(m==0){System.out.println(0);continue;}
			dfs(1);
			System.out.println(prob[1][m]);			
		}
	}
	public static void dfs(int start){
		int r=(rooms[start].bugs+19)/20;
		rooms[start].visited=true;
		for(int i=m;i>=r;i--){
			prob[start][i]=rooms[start].brains;
		}
		for(int j=1;j<=n;j++){
			if(tunnels[start][j]&&!rooms[j].visited){
				dfs(j);
				for(int i=m;i>=r;i--){
					for(int k=1;k<=i-r;k++)
					prob[start][i]=Math.max(prob[start][i], prob[start][i-k]+prob[j][k]);
				}
			}
		}
	}
}

class BugRoom{
	int bugs;
	int brains;
	boolean visited;
	public BugRoom(int bugs,int brains,boolean visited){
		this.bugs=bugs;
		this.brains=brains;
		this.visited=visited;
	}
}
