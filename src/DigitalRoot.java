import java.util.Scanner;

/**
 * <b>Hdoj problem 1013</b><br>
 * Big integer should be considered,<br>
 * so dealing the input in string format first using {@link #getRoot(String) getRoot(String)}.
 * @author dannl
 *
 */
public class DigitalRoot {
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		String str;
		while(true){
			str=in.nextLine();			
			if(str.length()==1&&str.charAt(0)=='0')break;
			int num=getRoot(str);
			while(true){
				num=getRoot(num);
				if(num/10==0){
					System.out.println(num);
					break;
				}
			}			
		}
	}
	/**
	 * See also {@link #getRoot(int) getRoot(int)}
	 * @param num
	 * @return
	 */
	public static int getRoot(String num){
		int sum=0;
		byte[] bn=num.getBytes();
		for(int i=0;i<bn.length;i++)
			sum+=bn[i]-'0';
		return sum;
	}
	public static int getRoot(int num){
		int sum=0;
		while(true){
			sum+=num%10;
			if(num/10==0)break;
			num=num/10;
		}
		return sum;
	}
	public static String addStr(String a,String b){
		byte[] ba=new StringBuilder(a).reverse().toString().getBytes();
		byte[] bb=new StringBuilder(b).reverse().toString().getBytes();
		int len=ba.length>bb.length?ba.length:bb.length;
		StringBuilder sb=new StringBuilder();
		int ai=0,bi=0,count=0,sum;
		for(int i=0;i<len;i++){
			if(i<ba.length)ai=ba[i]-'0';else ai=0;
			if(i<bb.length)bi=bb[i]-'0';else bi=0;
			sum=ai+bi+count;
			count=sum/10;
			sb.append(sum%10);
		}
		return sb.reverse().toString();
	}
}
