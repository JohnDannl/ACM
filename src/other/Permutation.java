package other;

public class Permutation {
	
	public static void main(String[] args){
		String a="decafb";
		byte[] ba=a.getBytes();
		quickSorted(ba,0,ba.length-1);
//		for(int i=0;i<ba.length;i++)System.out.print((char)ba[i]);
		CharNode[] nodes=new CharNode[ba.length];
		CharNode[] x=new CharNode[ba.length];
		for(int i=0;i<ba.length;i++)nodes[i]=new CharNode(ba[i],false);
		permutation(nodes,x,0);
	}
	/**
	 * Deep-first search algorithm
	 * @param nodes
	 * @param x
	 * @param deep
	 */
	public static void permutation(CharNode[] nodes,CharNode[] x,int deep){	
		if(deep>=nodes.length){
			StringBuilder sb=new StringBuilder();
			for(int i=0;i<x.length;i++)
				sb.append((char)x[i].v);
			System.out.println(sb.toString());
			return;
		}
		for(int i=0;i<nodes.length;i++){
			if(!nodes[i].added){
				x[deep]=nodes[i];
				nodes[i].added=true;
				deep++;
				permutation(nodes,x,deep);
				deep--;
				nodes[i].added=false;
			}					
		}			
	}
	public static void quickSorted(byte[] array,int q,int r){
		if(q>=r)return;
		int i=q,j=r+1;
		byte tmp,mid=array[q];	
		while(true){
			while(array[++i]<mid&&i<r);
			while(array[--j]>mid);
			if(i>=j)break;
			tmp=array[i];
			array[i]=array[j];
			array[j]=tmp;
		}
		array[q]=array[j];
		array[j]=mid;
		quickSorted(array,q,j-1);
		quickSorted(array,j+1,r);
}
}
class CharNode{
	public byte v;
	public boolean added;
	public CharNode(byte v,boolean added){
		this.v=v;
		this.added=added;
	}
}