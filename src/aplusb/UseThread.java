package aplusb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class UseThread implements Runnable{
	void solve() throws IOException
	   {
	      int a = nextInt();
	      int b = nextInt();
	      out.println(a + b);
	   }

	   StreamTokenizer in;
	   PrintWriter out;

	   int nextInt() throws IOException
	   {
	      in.nextToken();
	      return (int)in.nval;
	   }

	   public void run()
	   {
	      try
	      {
	         in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	         out = new PrintWriter(new OutputStreamWriter(System.out));
	         solve();
	         out.flush();
	      }
	      catch (IOException e)
	      {
	         throw new IllegalStateException(e);
	      }
	   }

	   public static void main(String[] args)
	   {
	      new Thread(new UseThread()).start();
	   }
}
