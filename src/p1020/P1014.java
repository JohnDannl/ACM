package p1020;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
/**
 * HDOJ problem 1014
 * @author dannl
 *
 */
public class P1014 {
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		Set<Integer> set=new HashSet<Integer>();
		while(in.hasNext()){
			int step=in.nextInt();
			int mod=in.nextInt();
			set.clear();
			int sed=0;boolean isPoor=false;
			for(int i=0;i<mod;i++){
				if(set.contains(sed)){isPoor=true;break;}
				else {
					set.add(sed);	
					sed=(sed+step)%mod;
				}
			}
			if(isPoor)System.out.println(String.format("%10d%10d    %s",step,mod,"Bad Choice"));
			else System.out.println(String.format("%10d%10d    %s",step,mod,"Good Choice"));
			System.out.println();
		}
	}
}
