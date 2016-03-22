package p1010;
import java.util.Scanner;

/**
 * HDOJ problem 1009
 * @author dannl
 *
 */
public class P1009 {
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		while(true){
			int m=in.nextInt();
			int n=in.nextInt();
			if(m==-1&&n==-1)break;
			FatRoom[] a=new FatRoom[n];
			for(int i=0;i<n;i++){
				a[i]=new FatRoom(in.nextInt(),in.nextInt());
			}
			FatRoom.quickSorted(a, 0, a.length-1);
			double max=0,left=m;
			for(int i=a.length-1;i>=0;i--){
				if(left>=a[i].f){
					max+=a[i].j;
					left-=a[i].f;
				}else{
					max+=a[i].j*left/a[i].f;
					break;
				}
			}
			System.out.println(String.format("%.3f", max));
		}
	}
}
class FatRoom{	
	double j;
	double f;
	double ratio;
	public FatRoom(double j,double f){
		this.j=j;
		this.f=f;
		if(f!=0)this.ratio=j/f;
		else this.ratio=Double.MAX_VALUE;
	}
	public static void quickSorted(FatRoom[] a,int p,int r){
		if(p>=r)return;
		int i=p,j=r+1;
		FatRoom head=a[p],tmp;
		while(true){
			while(a[++i].ratio<head.ratio&&i<r);
			while(a[--j].ratio>head.ratio);
			if(i>=j)break;
			tmp=a[i];
			a[i]=a[j];
			a[j]=tmp;
		}
		a[p]=a[j];
		a[j]=head;
		quickSorted(a,p,j-1);
		quickSorted(a,j+1,r);
	}
}
