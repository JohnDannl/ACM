
import java.awt.geom.Point2D;
import java.util.Scanner;

public class NearestNeighbour {

    static int[] getClosest(Point2D.Double[] points)
    {
        int[] result = new int[2];
        double mindis=Point2D.distance(points[0].x, points[0].y, points[1].x, points[1].y);
        result[0] = 0;
		result[1] = 1;
        for(int i=0;i<points.length;i++){
        	for (int j=i+1;j<points.length;j++){
        		double dis= Point2D.distance(points[i].x, points[i].y, points[j].x, points[j].y);
        		if(dis<mindis){
        			mindis=dis;
        			result[0] = i;
        			result[1] = j;
        		}
        	}
        }      
        return result;
    }
    
    public static void main(String[] args) {
        Point2D.Double[] points;
        Scanner input = new Scanner(System.in);
        {
            int n = input.nextInt();
            input.nextLine();
            points = new Point2D.Double[n];
            for (int i = 0; i < n; ++i) {
                double x = input.nextDouble();
                double y = input.nextDouble();
                input.nextLine();
                points[i] = new Point2D.Double(x, y);
            }
        }
        int[] result = getClosest(points);
        System.out.printf("Closest points: %d, %d\n", result[0], result[1]);
    }
}