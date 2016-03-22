

import java.util.Scanner;

/**
 *   We have a sample as below,a[4][4] stores the elements: <br> 
 *   														<br> 
 *   -1    4    5   1										<br> 
 *    2   -1    2   4										<br> 
 *    3 -->3   -1   3										<br> 
 *    4    2 -->1   2										<br> 
 *    
 *    The snake can just go up/down/right,so if we reached any one <br> 
 *    element such as a[2][1]=3,we will totally have three ways to go to:<br> 
 *    (1) through the top of a[2][1],i.e. a[1][1]				<br> 
 *    (2) through the left of a[2][1],i.e. a[2][0]				<br> 
 *    (3) through the bottom of a[2][1],i.e. a[3][1]			<br> 
 *    If we have gotten the highest points way to reach a[1][1],<br>
 *    a[2][0] and a[3][1],assume they are respectively sum[1][1],<br>
 *    sum[2][0] and sum[3][1],then the highest points way to reach <br>
 *    a[2][1] will be the highest one of the three ways:		<br>
 *    (1) sum[1][1]+a[2][1]										<br>
 *    (2) sum[2][0]+a[2][1]										<br>
 *    (3) sum[3][1]+a[2][1]										<br>
 *    It means this problem has optimal substructure property,so <br>
 *    <b>dynamic programming </b> is used,whose time complexity is O(n^2).<br>	
 *    Otherwise,we have to consider <b>some exceptions</b>:	  		<br>
 *    (1) when the a[i][j]=-1,we always have sum[i][j]=-1		<br>
 *    (2) if sum[i][j]=-1,it means any way to a[i][j] is block <br>
 *    (3) if it is from the top,it may be tele-transported from the <br>
 *    bottom or not,so two cases should be considered.The same is<br>
 *    for from bottom. <br>
 *    The result will be the highest score of sum[:][column-1] <br>
 *    						
 * @author dannl
 *
 */
public class Snake {
	public static void main(String[] args){
		// deal with the input
		Scanner in= new Scanner(System.in);
		int row=in.nextInt();
		int column=in.nextInt();
		int[][] a=new int[row][column];
		for(int i=0;i<row;i++)
			for(int j=0;j<column;j++)
				a[i][j]=in.nextInt();
		// initialize the sum array,which stores the highest score of position a[i][j]
		int[][] sum=new int[row][column];
		for(int i=0;i<row;i++)	
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
		// it may be tele-transported from bottom/top or not,two cases are considered
		int[] topsum=new int[row]; // not through the top (not tele-transported from bottom)
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
		in.close();
	}
}
