
public class CalculateE {
	public static void main(String[] args){
		System.out.println("n e");
		System.out.println("- -----------");
		double sum=0,factorial=1;
		for(int i=0;i<10;i++){
			if(i==0)factorial=1;
			else factorial/=i;
			sum+=factorial;
			if(i<2)System.out.println(String.format("%d %.0f",i,sum));
			else if(i==2)System.out.println(String.format("%d %.1f",i,sum));
			else System.out.println(String.format("%d %.9f",i,sum));
		}
	}
}
