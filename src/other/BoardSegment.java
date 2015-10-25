package other;

import java.util.Scanner;


public class BoardSegment {
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		String line;
		while(in.hasNext()){
			line=in.nextLine();
			String[] params=line.split("\\s+");
			int w=Integer.parseInt(params[0]);
			int h=Integer.parseInt(params[1]);
			int n=Integer.parseInt(params[2]);
			
			int[] x=new int[w+1];
			int[] y=new int[h+1];
			for(int i=0;i<w+1;i++)x[i]=0;
			for(int i=0;i<h+1;i++)y[i]=0;
			x[0]=1;x[w]=1;y[0]=1;y[h]=1;			
			for(int i=0;i<n;i++){
				line=in.nextLine();
				String[] linea=line.split("\\s+");
				if(linea[0].equals("H"))y[Integer.parseInt(linea[1])]=1;
				if(linea[0].equals("V"))x[Integer.parseInt(linea[1])]=1;
				int xmax=0,ymax=0,start=0,tmp;
				for(int j=0;j<w+1;j++){
					if(x[j]==1){
						tmp=j-start;
						if(tmp>xmax)xmax=tmp;
						start=j;
					}
				}
				start=0;
				for(int j=0;j<h+1;j++){
					if(y[j]==1){
						tmp=j-start;
						if(tmp>ymax)ymax=tmp;
						start=j;
					}
				}
				System.out.println(xmax*ymax);
			}
		}
	}
}
