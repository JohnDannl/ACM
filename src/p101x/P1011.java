package p101x;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * HDOJ problem 1011<br>
 * confused,wrong answer
 * @author dannl
 *
 */
public class P1011 {
	static int nMax = 100;
	static BugRoom[] rooms=new BugRoom[nMax];	
	static int n,m, maxBrain;
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		
		while(true){
			n = in.nextInt();
			m = in.nextInt();
			if(n == -1 && m == -1)break;
			for(int i = 0; i < n; i++){
				int bugs = (in.nextInt() + 19) / 20;
				int brains = in.nextInt();
				rooms[i] = new BugRoom(bugs, brains, i + 1);
			}
			for(int i = 0; i < n - 1;i++){
				int room1 = in.nextInt() - 1;
				int room2 = in.nextInt() - 1;
				rooms[room1].children.add(rooms[room2]);
				rooms[room2].parent = rooms[room1];
			}
			if(m==0) {
				System.out.println(0);
				continue;
				}
			maxBrain = 0;
			dfs(rooms[0]);
			System.out.println(maxBrain);			
		}
	}
	public static void dfs(BugRoom room){
		if (room.children.isEmpty()) {
			List<BugRoom> rooms = new ArrayList<BugRoom>();
			do {
				rooms.add(room);
				room = room.parent;
			} while(room != null); 
			int tmpBrain = getMaxBrain(rooms);
			if (tmpBrain > maxBrain) maxBrain = tmpBrain;
		} else {
			for (int i = 0; i < room.children.size(); i ++) {
				dfs(room.children.get(i));
			}
		}
	}
	public static int getMaxBrain(List<BugRoom> list) {
		int row = list.size() + 1;
		int col = m + 1;
		int[][] a = new int[row][col];
		for (int i = 0; i < row; i++) {
			a[i][0] = 0;
		}
		for (int j = 0; j < col; j++) {
			a[0][j] = 0;
		}
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				int wi = list.get(i - 1).bugs;
				int vi = list.get(i - 1).brains;
				if (wi > j) {
					a[i][j] = a[i - 1][j];
				} else {
					a[i][j] = max(a[i - 1][j], a[i - 1][j - wi] + vi);
				}				
			}
		}
		return a[row - 1][col - 1];
	}
	
	public static int max(int a, int b) {
		return a > b ? a : b;
	}
}

class BugRoom{
	int bugs;
	int brains;
	int num = 0; 
	BugRoom parent = null;
	List<BugRoom> children = new ArrayList<BugRoom>();
	public BugRoom(int bugs,int brains,int num){
		this.bugs=bugs;
		this.brains=brains;
		this.num = num;
	}
}
