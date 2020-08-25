package Misc;

import java.util.ArrayList;
import java.util.Scanner;

public class Mat {
	public static double distance(double x1, double y1, double x2, double y2) {
//		return Math.sqrt( Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2) );
		return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
	}
	public static double getAngle(double x1,double y1, double x2, double y2) {
		double 
			px = x2-x1,
			py = y2-y1,
			angle = Math.atan2( py,px);
		return angle;
	}
	public static int inRange(double value, int lower, int upper) {
		if(value >= upper) return upper;
		if(value <= lower) return lower;
		return (int)Math.round(value);
	}
	public static void cal(String form) {
		System.out.print(form+" = ");
		ArrayList<String> list = new ArrayList<String>();
		Scanner scan = new Scanner(form);
		while(scan.hasNext()) list.add(scan.next());
		scan.close();
		//Addition
		for(int z=0;z<list.size();z++) {
			String str = list.get(z);
			if( !isNum(str) ) {
				if(str.contains("+")) {
					
				}
			}
		}
		System.out.println();
	}
	public static Boolean isNum(String num) {
		for(int z=0;z<num.length();z++)
			switch(num.charAt(z)) {
				case '0':break;
				case '1':break;
				case '2':break;
				case '3':break;
				case '4':break;
				case '5':break;
				case '6':break;
				case '7':break;
				case '8':break;
				case '9':break;
				default: return false;
			}
		return true;
	}
}