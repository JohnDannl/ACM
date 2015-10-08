import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;


public class Main {
	public static void main(String[] args) throws IOException{
		StreamTokenizer in=new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		PrintWriter out=new PrintWriter(new OutputStreamWriter(System.out));		
		while(in.nextToken()!=StreamTokenizer.TT_EOF){
			double thres_deg=in.nval;
			if(thres_deg==-1)break;
			double percent=happyTime2(thres_deg);
			out.println(String.format("%.3f", percent));
		}
		out.flush();
	}
	public static double happyTime2(double thres_deg){
		double sum=0;
		for(int h=0;h<12;h++)
			for(int m=0;m<60;m++){
				// dsh=719/120*s-30h-0.5m
				double[] a=solveInequation(719/120.0,-30*h-0.5*m,thres_deg);
				// dsm=5.9s-6m
				double[] b=solveInequation(5.9,-6*m,thres_deg);
				// dhm=11/120*s-30h+5.5m
				double[] c=solveInequation(11/120.0,-30*h+5.5*m,thres_deg);
				double[] e=intersection(a,b,c);
				BoundPoint[] bps=new BoundPoint[e.length];
				for(int i=0;i<e.length/2;i++){
					bps[2*i]=new BoundPoint(e[2*i],false);
					bps[2*i+1]=new BoundPoint(e[2*i+1],true);
				}
				/*for(int i=0;i<bps.length;i++)
					System.out.print(bps[i].isEnd+" "+bps[i].value);
				System.out.println();	*/			
				BoundPoint.bubbleSort(bps);
				/*for(int i=0;i<bps.length;i++)
					System.out.print(bps[i].isEnd+" "+bps[i].value);
				System.out.println();*/
				int start=0,deep=0;
				// the union set
				for(int i=0;i<bps.length;i++){					
					if(bps[i].isEnd)deep--;
					else deep++;
					if(deep==0){
						sum+=(bps[i].value-bps[start].value);						
						start=i+1;
					}
				}
			}
		return sum*100/(3600.0*12);
	}
	static class BoundPoint{
		double value;
		boolean isEnd=false;
		public BoundPoint(double value,boolean isEnd){
			this.value=value;
			this.isEnd=isEnd;
		}
		public static void bubbleSort(BoundPoint[] array){
			for(int j=0;j<array.length;j++){
				for(int k=1;k<array.length-j;k++){
					if(array[k-1].value>array[k].value){
						BoundPoint tmp=array[k];
						array[k]=array[k-1];
						array[k-1]=tmp;
					}
				}
			}
		}
	}
	public static double[] intersection(double[] a,double[] b,double[] c){
		double[] d=new double[8];
		for(int i=0;i<d.length;i++)
			d[i]=0;
		for(int i=0;i<a.length/2;i++)
			for(int j=0;j<b.length/2;j++)
			{
				if(a[2*i]<a[2*i+1]&&b[2*j]<b[2*j+1]){
					d[4*i+2*j]=a[2*i]>b[2*j]?a[2*i]:b[2*j];
					d[4*i+2*j+1]=a[2*i+1]<b[2*j+1]?a[2*i+1]:b[2*j+1];
				}
			}		
		double[] e=new double[16];
		for(int i=0;i<e.length;i++)e[i]=0;
		for(int i=0;i<d.length/2;i++)
			for(int j=0;j<c.length/2;j++)
				if(d[2*i]<d[2*i+1]&&c[2*j]<c[2*j+1]){
					e[4*i+2*j]=d[2*i]>c[2*j]?d[2*i]:c[2*j];
					e[4*i+2*j+1]=d[2*i+1]>c[2*j+1]?d[2*i+1]:c[2*j+1];
				}
		return e;
	}
	public static double[] solveInequation(double a,double b,double D){
		double[] result=new double[4];
		result[0]=(D-b)/a;
		result[1]=(360-D-b)/a;
		result[2]=(D-360-b)/a;
		result[3]=(-b-D)/a;
		// The seconds should be between 0 and 60
		for(int i=0;i<result.length;i++){
			if(result[i]<0)result[i]=0;
			if(result[i]>60)result[i]=60;
		}
		return result;
	}
	public static double happyTime(double thres_deg){
		int count=0;
		double[] degree=new double[3];
		for(int i=0;i<12*3600;i++){
			degree[0]=(i%60)*360/(double)60;
			degree[1]=(i%3600)*360/(double)3600;
			degree[2]=(i%(12*3600))*360/(double)(12*3600);
			/*degree[0]=(i*6)%360;
			degree[1]=(0.1*i)%360;
			degree[2]=(360.0/(12*3600)*i)%360;*/
			bubbleSort(degree);
			if((degree[2]-degree[1])>=thres_deg
					&&(degree[1]-degree[0])>=thres_deg
					&&(degree[0]+360-degree[2])>=thres_deg)count++;
			
		}
		double percent=(double)count*100/(3600*12);
		return percent;
	}
	public static void bubbleSort(double[] array){
		for(int j=0;j<array.length;j++){
			for(int k=1;k<array.length-j;k++){
				if(array[k-1]>array[k]){
					double tmp=array[k];
					array[k]=array[k-1];
					array[k-1]=tmp;
				}
			}
		}
	}
}
