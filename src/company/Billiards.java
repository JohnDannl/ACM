package company;

import java.util.Scanner;

public class Billiards {
	public static void main(String[] args) {
		int L,W,x,y,R,a,v,s;
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			L = in.nextInt();
			W = in.nextInt();
			x = in.nextInt();
			y = in.nextInt();
			R = in.nextInt();
			a = in.nextInt();
			v = in.nextInt();
			s = in.nextInt();
			if (L == 0 && W == 0 && x == 0 && y ==0 && R == 0 && a == 0 && v == 0 && s == 0) {
				break;
			}
			double radians = a * Math.PI / 180;
			double vx = v * Math.cos(radians);
			double vy = v * Math.sin(radians);
			double fx = calculateX(x, vx, s, L, R);
			double fy = calculateX(y, vy, s, W, R);
			System.out.println(String.format("%.2f %.2f", fx, fy));
		}
	}
	
	public static double calculateX(double x, double vx, double s, double L, double R) {
		double fx = 0;
		if (vx > 0) {
			double ds = vx * s + x - R;
			double rx = ds % (2*(L - 2 * R));
			if (rx <= L - 2 * R) {
				fx = rx + R;
			} else {
				fx = 2 * L - rx - 3 * R;
			}
		} else {
			vx = - vx;
			double ds = vx * s + L - R - x;
			double rx = ds % (2*(L - 2 * R));
			if (rx <= L - 2 * R){
				fx = L - R - rx;
			} else {
				fx = rx - L + 3 * R;
			}
		}
		return fx;
	}
}
