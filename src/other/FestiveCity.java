package other;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * We use a graph to store all the info,each node represents a city.<br>
 * A city contains its nearest festival city's distance as an integer<br>
 * and its neighbor cities as a list.<br>
 * At the first all nearest distances is assigned n (the city count),when<br>
 * a festive city (parent city) is announced,update its nearest distance to 0,<br>
 * and all of its neighbors (child city) whose nearest distance is bigger<br>
 * than 1 (the parent's nearest distance plus 1) to 1.Do the same update for <br>
 * its neighbors (who then become the parent city and whose neighbors become <br>
 * child city) until the child's nearest distance is no bigger than its parent<br>
 * city's nearest distance plus 1.
 * For every query,each node store its nearest festive city's distance.
 * @author dannl
 *
 */
public class FestiveCity {
	public static void main(String[] args){
		// deal with the input
		Scanner in =new Scanner(System.in);
		PrintWriter out=new PrintWriter(new OutputStreamWriter(System.out));
		int n=in.nextInt();
		int m=in.nextInt();
		// initialize n cities
		City[] cities=new City[n+1];
		// use n as the primitive nearest distance
		for(int i=1;i<n+1;i++)cities[i]=new City(n); 
		// construct the graph (highways between cities)
		for(int i=0;i<n-1;i++){
			int a=in.nextInt();
			int b=in.nextInt();
			cities[a].neighbours.add(cities[b]);
			cities[b].neighbours.add(cities[a]);
		}
		cities[1].nearestFest=0;
		cities[1].update();// At the beginning,only one festive city
		// m queries
		for(int i=0;i<m;i++){
			int q=in.nextInt();
			int c=in.nextInt();
			if(q==1){
				cities[c].nearestFest=0;
				cities[c].update();
			}else{
				out.println(cities[c].nearestFest);
			}			
		}
		out.flush();
		in.close();
	}
}

class City{	
	public int nearestFest;	// the nearest festive city's distance
	public ArrayList<City> neighbours=new ArrayList<City>();// neighbor cities
	public City(int n){
		this.nearestFest=n;
	}
	public void update(){
		for(City node:neighbours){
			// update until the neighbors' nearest distance is no bigger than
			// its parent's nearest distance
			if(node.nearestFest>this.nearestFest+1){
				node.nearestFest=this.nearestFest+1;
				node.update();
			}
		}
	}
}