import java.util.Scanner;


public class Main {
	public static void main(String[] args){
		Scanner in= new Scanner(System.in);
		int row=in.nextInt();
		int column=in.nextInt();
		int[][] a=new int[row][column];
		for(int i=0;i<row;i++)
			for(int j=0;j<column;j++)
				a[i][j]=in.nextInt();
		int[][] sum=new int[row][column];
		for(int i=0;i<row;i++)	// initialize the sum
			for(int j=0;j<column;j++)
				sum[i][j]=-1;		
		
		// deal with the first column	
		int top=0,bottom=0,left=0;		
		for(int i=0;i<row;i++){// top down
			if(a[i][0]==-1){
				sum[i][0]=-1;
				top=0;
			}
			else{
				top+=a[i][0];
				sum[i][0]=top;
			}
		}
		for(int i=row-1;i>=0;i--){ // bottom up
			if(a[i][0]==-1)bottom=0;// no need to update sum[i][0],because sum[i][0]>=-1
			else{
				bottom+=a[i][0];
				if(bottom>sum[i][0])sum[i][0]=bottom; // the maximum score				
			}
		}
		// deal with the left columns
		int[] topsum=new int[row]; // not through the top
		int[] bottomsum=new int[row]; // not through the bottom
		int[] topsumt=new int[row];	// through the top
		int[] bottomsumt=new int[row]; // through the bottom
		int k=0;boolean pass=false,reached=false;
		for(int j=1;j<column;j++){
			k=0;pass=false;reached=false;			
			for(int i=0;i<row;i++){// top to bottom
				if(a[i][j]==-1){
					topsum[i]=-1;
					topsumt[i]=-1;
					continue;
				}			
				if(i==0){
					// detect if the first row can be reached directly 
					if(sum[i][j-1]!=-1){
						pass=true;	
						left=sum[i][j-1]+a[i][j];
					}
					else {
						pass=false;
						left=-1;
					}
					// for topsum not through the top
					topsum[i]=left;
					
					if(!pass){
						// for topsumt,the first row,need to detect whether it can reach through the top
						k=row-1;					
						while(k>0){ // a linear detection
							topsumt[k]=-1;
							if(a[k][j]!=-1&&sum[k][j-1]!=-1){reached=true;break;}
							if(a[k][j]==-1){reached=false;break;}						
							k--;
						}
						if(reached)topsumt[i]=a[i][j];
						else topsumt[i]=-1;
					}									
				}
				else{ // the other row excluding the first row
					// for topsum
					top=topsum[i-1];
					if(top!=-1)top+=a[i][j];	
					if(sum[i][j-1]!=-1)left=sum[i][j-1]+a[i][j];
					else left=-1;
					if(top>left)topsum[i]=top;
					else topsum[i]=left;
					// for topsumt
					if(reached&&i<k){
						top=topsumt[i-1];
						if(top!=-1)top+=a[i][j];	
						if(top>left)topsumt[i]=top;
						else topsumt[i]=left;
					}
				}				
			}
			if(reached)
			for(int i=0;i<row;i++){
				if(topsumt[i]>topsum[i])topsum[i]=topsumt[i];
			}
			k=0;pass=false;reached=false;
			for(int i=row-1;i>=0;i--){ // bottom up
				if(a[i][j]==-1){
					bottomsum[i]=-1;
					bottomsumt[i]=-1;
					continue;
				}
				if(i==row-1){ 				
					if(sum[i][j-1]!=-1){
						pass=true;
						left=sum[i][j-1]+a[i][j];
						}
					else{
						pass=false;
						left=-1;
					}
					bottomsum[i]=left;
					
					// the last row,need to detect if it can reach through the bottom	
					if(!pass){
						k=0;
						while(k<row-1){
							bottomsumt[k]=-1;
							if(a[k][j]!=-1&&sum[k][j-1]!=-1){reached=true;break;}
							if(a[k][j]==-1){reached=false;break;}
							k++;
						}
						if(reached)bottomsumt[i]=a[i][j];
						else bottomsumt[i]=-1;
					}					
				}
				else{ // the other row excluding the last row
					// for bottomsum
					bottom=bottomsum[i+1];
					if(bottom!=-1)bottom+=a[i][j];
					left=sum[i][j-1];
					if(left!=-1)left+=a[i][j];
					if(bottom>left)bottomsum[i]=bottom;
					else bottomsum[i]=left;
					// for bottomsumt
					if(reached&&i>k){
						bottom=bottomsumt[i+1];
						if(bottom!=-1)bottom+=a[i][j];
						if(bottom>left)bottomsumt[i]=bottom;
						else bottomsumt[i]=left;
					}
				}				
			}
			for(int i=0;i<row;i++){
				if(reached&&bottomsumt[i]>bottomsum[i])bottomsum[i]=bottomsumt[i];
				// the sum[i][j] assigned the maximum one
				if(bottomsum[i]>topsum[i])sum[i][j]=bottomsum[i];
				else sum[i][j]=topsum[i];
			}
		}
		int max=-1;
		for(int i=0;i<row;i++){
			if(sum[i][column-1]>max)max=sum[i][column-1];
		}
		System.out.println(max);
	}
}
