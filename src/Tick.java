import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;


public class Tick {
	public static void main(String[] args){
		StreamTokenizer in=new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		PrintWriter out=new PrintWriter(new OutputStreamWriter(System.out));
		System.out.println(fun1(120));
	}
	public static double fun1(double degree){
		double percent=(25.0/2*Math.pow(degree, 2)-360*7*degree+360*360)/360;
		return percent;
	}
}
