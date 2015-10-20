package other;
import java.util.Scanner;


public class Atack {
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		int hp1=in.nextInt();
		int hp2=in.nextInt();
		int a1=in.nextInt();
		int a2=in.nextInt();
		int t=in.nextInt();
		int tb=0;
		byte t1,t2;
		while(t>0){
			t--;
			t1=(byte)in.next().charAt(0);
			t2=(byte)in.next().charAt(0);
			if(t1=='B')tb+=2;
			if(t2=='B')tb-=2;
			if(tb==0&&t2=='A')hp1-=a2;
			if(t2!='B'&&t1=='A')hp2-=a1;				
			if(tb>0)tb--;
			if(tb<=0)tb=0;
		}
		if(hp1==0&&hp2>=0)System.out.println("YES");
		else System.out.println("NO");
	}
}
