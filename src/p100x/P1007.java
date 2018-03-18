package p100x;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * HDOJ problem 1007<br>
 * Still Time limit exceeded
 * @author dannl
 *
 */
public class P1007 {
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();		
		while(n!=0){
			PointEx[] points = new PointEx[n];
			double x, y;
			for(int i = 0; i < n; i++){
				x = in.nextDouble();
				y = in.nextDouble();
				points[i] = new PointEx(x, y);
			}
			double min = solveMinDis2(points, n);
			System.out.println(String.format("%.2f", min / 2));
			n=in.nextInt();
		}			
	}
	
	public static void printA(int[] a) {
		for (int i : a) {
			System.out.print(i + ",");
		}
		System.out.println();
	}
	private static Comparator<PointEx> compX = new Comparator<PointEx>() {

		
		public int compare(PointEx o1, PointEx o2) {
			// TODO Auto-generated method stub
			if (o1.x == o2.x) {
				return o1.y > o2.y ? 1 : o1.y < o2.y ? -1 : 0;
			}
			return o1.x > o2.x ? 1 : -1;
		}
	};
	
	private static Comparator<PointEx> compY = new Comparator<PointEx>() {

		
		public int compare(PointEx o1, PointEx o2) {
			// TODO Auto-generated method stub
			if (o1.y== o2.y) {
				return o1.x > o2.x ? 1 : o1.x < o2.x ? -1 : 0;
			}
			return o1.y > o2.y ? 1 : -1;
		}		
	};
	
	public static double solveMinDis3(PointEx[] points, int n) {
		Arrays.sort(points, 0, n, compX);
		return _solveMinDis3(points, 0, n - 1);
	}
	public static double _solveMinDis3(PointEx[] points, int p, int r) {
		if (p == r) return Double.MAX_VALUE;
		if (r - p == 1) return distance(points[p].x, points[p].y, points[r].x, points[r].y);
		int m = (p + r) / 2;
		double left = _solveMinDis3(points, p, m);
		double right = _solveMinDis3(points, m + 1, r);
		double min = left < right ? left : right;
		double leftX = points[m].x - min;
		double rightX = points[m].x + min;
		int start = m;
		for (int i = m; i > p; i--) {
			if (points[i].x < leftX) break;
			start--;
		}
		
		int end = m;
		for (int i = m; i < r; i++) {
			if (points[i].x > rightX) break;
			end++;
		}
		PointEx[] pointY = new PointEx[end - start + 1];
		System.arraycopy(points, start, pointY, 0, pointY.length);
		Arrays.sort(pointY, 0,  pointY.length, compY);
		for(int i = 0; i < pointY.length; i++) {
			for (int j = i + 1; j <= i + 7 && j < pointY.length; j++) {
				if (pointY[j].y - pointY[i].y > min) break;
				double tmpd = distance(points[i].x, points[i].y, points[j].x, points[j].y);
				if (tmpd < min) min = tmpd;
			}
		}
		return min;
	}
	/**
	 * A solution exhausting all distances : O(n^2)
	 * @param a
	 * @return
	 */
	public static double solveMinDis(double[][] a){
		double min,dis;
		int n=a.length;
		if(n>1)min=distance(a[0][0],a[0][1],a[1][0],a[1][1]);
		else min=0;
		for(int i=0;i<n;i++)
			for(int j=i+1;j<n;j++){
				dis=distance(a[i][0],a[i][1],a[j][0],a[j][1]);
				if(dis<min)min=dis;
			}
		return min;
	}
	public static double distance(double x1,double y1,double x2,double y2){
		return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
	}
	public static double solveMinDis2(PointEx[] points, int n){
		quickSortX(points, 0, n-1);		
		return _solveMinDis2(points, 0, n-1);
	}
	public static double _solveMinDis2(PointEx[] points, int p, int r) {
		if (r <= p) return Double.MAX_VALUE;
		// for two points
		if(r-p == 1) return distance(points[p].x,points[p].y,points[r].x,points[r].y);
		// for three points
		if(r-p == 2) {
			double d1 = distance(points[p].x, points[p].y, points[p + 1].x, points[p + 1].y);
			double d2 = distance(points[p].x, points[p].y, points[p + 2].x, points[p + 2].y);
			double d3 = distance(points[p + 1].x, points[p + 1].y, points[p + 2].x, points[p + 2].y);
			double d4 = d1 < d2 ? d1 : d2;
			return d3 < d4 ? d3 : d4;
		}
		// if more than 3 points,use divide-and-conquer
		int m = (p + r) / 2;		
		double d1 = _solveMinDis2(points, p, m);
		double d2 = _solveMinDis2(points, m + 1, r);
		double d3 = d1 < d2 ? d1 : d2;
		double xmin = points[m].x - d3;
		double xmax = points[m].x + d3;
		
		int left = m;
		for(int i = m; i > p; i--) {
			if(points[i].x < xmin) break;
			left--;			
		}
		int right = m;
		for(int i = m; i < r; i++) {
			if(points[i].x > xmax) break;
			right++;
		}
		PointEx[] pointY = new PointEx[right - left + 1];
		System.arraycopy(points, left, pointY, 0, pointY.length);
		quickSortY(pointY, 0, pointY.length - 1);
		// for each x[i],no more than 6 y[yind] is qualified
		for (int i = 0; i < pointY.length; i++) {
			for (int j = i + 1; j <= i + 7 && j < pointY.length; j++) {
				if(pointY[j].y - pointY[i].y > d3) break;
				double tmpd = distance(pointY[i].x, pointY[i].y, pointY[j].x, pointY[j].y);
				if (tmpd < d3) d3 = tmpd;
			}
		}
		return d3;
	}
	static class PointEx {
		public double x,y;
		
		public PointEx(double x, double y){
			this.x=x;
			this.y=y;
		}
		/**
		 * an algorithm to select the kth element from points with linear time complexity
		 * @param points : note that elements' order will be changed
		 * @param p : the start index
		 * @param r : the end index
		 * @param k : the kth element,k is in [1,points.length]
		 * @return
		 */
		/*public static PointEx selectKthX(PointEx[] points,int p,int r,int k){
			if(r-p<75){
				quickSortX(p,r);
				return points[p+k-1];
			}
			PointEx tmp;
			for(int i=0;i<=(r-p-4)/5;i++){
				quickSortX(p+5*i,p+5*i+4);
				tmp=points[p+i];
				points[p+i]=points[p+5*i+2];
				points[p+5*i+2]=tmp;
			}
			PointEx median=selectKthX(points,p,p+(r-p-4)/5,(r-p-4)/10); // the median of medians
			// split into two parts
			int i=p-1,j=r+1;
			while(true){
				while(points[++i].x<median.x&&i<r);
				while(points[--j].x>median.x);
				if(i>=j)break;
				tmp=points[i];
				points[i]=points[j];
				points[j]=tmp;
			}
			int sk=j-p+1;
			if(k<=sk)return selectKthX(points,p,sk,k);
			else return selectKthX(points,sk+1,r,k-sk);
		}*/
		
		/**
		 * for minimal distance point pair,record the exchange track
		 * @param index
		 * @param points
		 * @param p
		 * @param r
		 */
		/*public static void quickSortXE(int[] index,PointEx[] points,int p,int r){
			if(p>=r)return;	// end the iteration
			PointEx head=points[p],tmp;
			int tmp_ind;
			int i=p,j=r+1;
			while(true){
				while(points[++i].x<head.x&&i<r);
				while(points[--j].x>head.x);
				if(i>=j)break;
				tmp=points[i];
				points[i]=points[j];
				points[j]=tmp;
				// exchange position index
				tmp_ind=index[i];
				index[i]=index[j];
				index[j]=tmp_ind;
			}
			points[p]=points[j];
			points[j]=head;
			// exchage position index
			tmp_ind=index[p];
			index[p]=index[j];
			index[j]=tmp_ind;
			quickSortXE(index,points,p,j-1);// the left part
			quickSortXE(index,points,j+1,r);// the right part
		}*/
		
	}
	/**
	 * split a[p:r] into 2 parts:elements in a[p:j-1] is no bigger than a[j],<br>
	 * elements in a[j+1:r] is no less than a[j]
	 * @param points : note that elements' order will be changed
	 * @param p : the start index
	 * @param r : the end index
	 */
	public static void quickSortX(PointEx[] points, int p, int r){
		if (p >= r) return;	// end the iteration
		PointEx head = points[p],tmp;
		int i = p,j = r + 1;
		while(true){
			while(points[++i].x < head.x && i < r);
			while(points[--j].x > head.x);
			if(i >= j)break;
			tmp = points[i];
			points[i] = points[j];
			points[j] = tmp;
		}
		points[p] = points[j];
		points[j] = head;
		quickSortX(points, p, j - 1);// the left part
		quickSortX(points, j + 1, r);// the right part
	}
	public static void quickSortY(PointEx[] points, int p, int r){
		if(p >= r)return;	// end the iteration
		PointEx head = points[p],tmp;
		int i = p, j = r+1;
		while(true){
			while(points[++i].y < head.y && i < r);
			while(points[--j].y > head.y);
			if(i >= j)break;
			tmp = points[i];
			points[i] = points[j];
			points[j] = tmp;
		}
		points[p] = points[j];
		points[j] = head;
		quickSortY(points, p, j - 1);// the left part
		quickSortY(points, j + 1, r);// the right part
	}
}
