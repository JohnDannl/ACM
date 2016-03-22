package p1010;
import java.util.Scanner;

/**
 * HDOJ problem 1007<br>
 * Still memory limit exceeded
 * @author dannl
 *
 */
public class P1007 {
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();		
		while(n!=0){
			double[][] a=new double[n][2];
			for(int i=0;i<n;i++){
				a[i][0]=in.nextDouble();
				a[i][1]=in.nextDouble();
			}
			double min=solveMinDis2(a);
			System.out.println(String.format("%.2f", min/2));
			n=in.nextInt();
		}			
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
		return Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2));
	}
	public static double solveMinDis2(double[][] a){
		int n=a.length;
		PointEx[] pointX=new PointEx[n];
		PointEx[] pointY=new PointEx[n];
		for(int i=0;i<n;i++)pointY[i]=new PointEx(a[i][0],a[i][1]);
		PointEx.quickSortY(pointY, 0, n-1);
		System.arraycopy(pointY, 0, pointX, 0, n);
		// initial the index array to record the x to y index
		int[] x2y=new int[n];
		for(int i=0;i<n;i++)x2y[i]=i;
		PointEx.quickSortXE(x2y,pointX, 0, n-1);		
		return _solveMinDis2(pointX,pointY,x2y,0,n-1);
	}
	public static double _solveMinDis2(PointEx[] x,PointEx[] y,int[] x2y,int p,int r){
		// for two points
		if(r-p==1)return distance(x[0].x,x[0].y,x[1].x,x[1].y);
		// for three points
		if(r-p==2){
			double d1=distance(x[0].x,x[0].y,x[1].x,x[1].y);
			double d2=distance(x[0].x,x[0].y,x[2].x,x[2].y);
			double d3=distance(x[1].x,x[1].y,x[2].x,x[2].y);
			double d4=d1<d2?d1:d2;
			return d3<d4?d3:d4;
		}
		// if more than 3 points,use divide-and-conquer
		int m=p+(p+r)/2;		
		double d1=_solveMinDis2(x,y,x2y,p,m);
		double d2=_solveMinDis2(x,y,x2y,m+1,r);
		double d3=d1<d2?d1:d2;
		double xmin=x[m].x-d3;
		double xmax=x[m].x+d3;
		double tmpd;
		// for each x[i],no more than 6 y[yind] is qualified
		for(int i=m;i>=p;i--){
			if(x[i].x<xmin)break;
			int yind=x2y[i];
			double ymin=y[yind].y-d3;
			double ymax=y[yind].y+d3;
			while(--yind>0){
				if(y[yind].y<ymin)break;
				if(y[yind].x>xmax||y[yind].x<x[m].x)continue;
				tmpd=distance(x[i].x,x[i].y,y[yind].x,y[yind].y);
				if(tmpd<d3)d3=tmpd;
			}
			yind=x2y[i];
			while(++yind<y.length){
				if(y[yind].y>ymax)break;
				if(y[yind].x>xmax||y[yind].x<x[m].x)continue;
				tmpd=distance(x[i].x,x[i].y,y[yind].x,y[yind].y);
				if(tmpd<d3)d3=tmpd;
			}
		}
		return d3;
	}
}
class PointEx {
	public double x,y;
	
	public PointEx(double x){
		this.x=x;
		this.y=0;
	}
	public PointEx(double x,double y){
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
	public static PointEx selectKthX(PointEx[] points,int p,int r,int k){
		if(r-p<75){
			quickSortX(points,p,r);
			return points[p+k-1];
		}
		PointEx tmp;
		for(int i=0;i<=(r-p-4)/5;i++){
			quickSortX(points,p+5*i,p+5*i+4);
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
	}
	/**
	 * split a[p:r] into 2 parts:elements in a[p:j-1] is no bigger than a[j],<br>
	 * elements in a[j+1:r] is no less than a[j]
	 * @param points : note that elements' order will be changed
	 * @param p : the start index
	 * @param r : the end index
	 */
	public static void quickSortX(PointEx[] points,int p,int r){
		if(p>=r)return;	// end the iteration
		PointEx head=points[p],tmp;
		int i=p,j=r+1;
		while(true){
			while(points[++i].x<head.x&&i<r);
			while(points[--j].x>head.x);
			if(i>=j)break;
			tmp=points[i];
			points[i]=points[j];
			points[j]=tmp;
		}
		points[p]=points[j];
		points[j]=head;
		quickSortX(points,p,j-1);// the left part
		quickSortX(points,j+1,r);// the right part
	}
	/**
	 * for minimal distance point pair,record the exchange track
	 * @param index
	 * @param points
	 * @param p
	 * @param r
	 */
	public static void quickSortXE(int[] index,PointEx[] points,int p,int r){
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
	}
	public static void quickSortY(PointEx[] points,int p,int r){
		if(p>=r)return;	// end the iteration
		PointEx head=points[p],tmp;
		int i=p,j=r+1;
		while(true){
			while(points[++i].y<head.y&&i<r);
			while(points[--j].y>head.y);
			if(i>=j)break;
			tmp=points[i];
			points[i]=points[j];
			points[j]=tmp;
		}
		points[p]=points[j];
		points[j]=head;
		quickSortX(points,p,j-1);// the left part
		quickSortX(points,j+1,r);// the right part
	}
}
