import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;


public class SumN {
	public static void main(String[] args) throws IOException{
		StreamTokenizer in=new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while(in.nextToken()!=StreamTokenizer.TT_EOF){
			int n=(int) in.nval;
			int sum=0;
			for(int i=1;i<=n;i++){
				sum+=i;
			}
			out.println(sum);
			out.println();
		}
		out.flush();
	}
}
