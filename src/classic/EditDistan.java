package classic;
import java.util.Scanner;


public class EditDistan {
	public static int edit_distance (String src, String dst) {   
		byte[] lhs=src.getBytes();
		byte[] rhs=dst.getBytes();
	    int len0 = lhs.length + 1;                                                     
	    int len1 = rhs.length + 1;                                                     
	                                                                                    
	    // the array of distances                                                       
	    int[] cost = new int[len0];                                                     
	    int[] newcost = new int[len0];                                                  
	                                                                                    
	    // initial cost of skipping prefix in String s0                                 
	    for (int i = 0; i < len0; i++) cost[i] = i;                                     
	                                                                                    
	    // dynamically computing the array of distances                                  
	                                                                                    
	    // transformation cost for each letter in s1                                    
	    for (int j = 1; j < len1; j++) {                                                
	        // initial cost of skipping prefix in String s1                             
	        newcost[0] = j;                                                             
	                                                                                    
	        // transformation cost for each letter in s0                                
	        for(int i = 1; i < len0; i++) {                                             
	            // matching current letters in both strings                             
	            int match = (lhs[i - 1] == rhs[j - 1]) ? 0 : 1;             
	                                                                                    
	            // computing cost for each transformation                               
	            int cost_replace = cost[i - 1] + match;                                 
	            int cost_insert  = cost[i] + 1;                                         
	            int cost_delete  = newcost[i - 1] + 1;                                  
	                                                                                    
	            // keep minimum cost                                                    
	            newcost[i] = Math.min(Math.min(cost_insert, cost_delete), cost_replace);
	        }                                                                           
	                                                                                    
	        // swap cost/newcost arrays                                                 
	        int[] swap = cost; cost = newcost; newcost = swap;                          
	    }                                                                               
	                                                                                    
	    // the distance is the cost for transforming all letters in both strings        
	    return cost[len0 - 1];                                                          
	}
	public static int edit_distance2(String src,String dst){
		byte[] bs=src.getBytes();
		byte[] bd=dst.getBytes();
		int slen=bs.length;
		int dlen=bd.length;
		int[] cost=new int[dlen];
		int[] newcost=new int[dlen];
		int cost_delete=0,cost_insert=0,cost_replace=0;
		for(int j=0;j<dlen;j++){
			if(j==0){
				if(bd[j]==bs[0])cost[j]=0;
				else cost[j]=1;
			}else{
				if(bd[j]==bs[0])cost[j]=j;
				else cost[j]=cost[j-1]+1;
			}			
		}
		for(int i=1;i<slen;i++){			
			for(int j=0;j<dlen;j++){
				if(j==0){
					if(bs[i]==bd[j])newcost[j]=i;
					else newcost[j]=cost[j]+1;
				}else{
					if(bs[i]==bd[j])newcost[j]=cost[j-1];
					else{
						cost_insert=newcost[j-1]+1;
						cost_delete=cost[j]+1;
						cost_replace=cost[j-1]+1;
						newcost[j]=Math.min(cost_replace,Math.min(cost_delete, cost_insert));
					}					
				}				
			}
			System.arraycopy(newcost, 0, cost, 0, cost.length);
		}
		return newcost[newcost.length-1];
	}
	public static void main(String[] args){
		String src="string";
		String dst="strim";
		System.out.println(edit_distance(src,dst));
		System.out.println(edit_distance2(src,dst));
	}
}
