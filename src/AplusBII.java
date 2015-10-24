import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 * HDOJ problem 1002
 * @author dannl
 *
 */
public class AplusBII {
	public static void main(String[] args) throws IOException{
		BufferedReader in =new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out =new PrintWriter(new OutputStreamWriter(System.out));
		String line;
		line=in.readLine();
//		while((line=in.readLine())!=null)
		{
			int num=Integer.parseInt(line);
			int lineno=0;
			while(lineno<num){
				lineno++;
				line=in.readLine();
				String[] ab=line.split("\\s+");
				char[] la,sb;
				// Assign long char array to la,short to sb
				if(ab[0].length()>ab[1].length()){
					la=ab[0].toCharArray();
					sb=ab[1].toCharArray();	
				}else{
					la=ab[1].toCharArray();
					sb=ab[0].toCharArray();
				}				
				StringBuilder rev_result=new StringBuilder();
				int sum_c=0,sum_a=0;
//				System.out.println(String.format("%d,%d",la.length,sb.length));
				for(int i=1;i<=la.length;i++){	
					if(i<=sb.length){
						sum_c=Integer.parseInt(String.valueOf(la[la.length-i]))
								+Integer.parseInt(String.valueOf(sb[sb.length-i]))+sum_a;					
					}else{
						sum_c=Integer.parseInt(String.valueOf(la[la.length-i]))+sum_a;					
					}				
					sum_a=sum_c/10;
					sum_c=sum_c%10;
					rev_result.append(sum_c);
				}
				if(sum_a>0)rev_result.append(sum_a);
				String result=rev_result.reverse().toString();
				out.println(String.format("Case %d:", lineno));
				out.println(String.format("%s + %s = %s",ab[0],ab[1],result));
				if(lineno<num)out.println();
//				out.flush();
			}
			out.flush();
		}		
	}
}
