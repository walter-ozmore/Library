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
	public static double inRange(double value, double lower, double upper) {
		if(value >= upper) return upper;
		if(value <= lower) return lower;
		return value;
	}
	public static boolean isInRange(double value, double lower, double upper) {
		if(value >= lower & value <= upper) return true;
		return false;
	}
	public static double reduce(double value, double location, double delta) {
		if(isInRange(value,location-delta, location+delta)) {
			value = location;
		} else {
			if(value > location) value -= delta;
			if(value < location) value += delta;
		}
		
		return value;
	}
	public static double max(double[] inputArray) {
		double max = Double.MIN_VALUE;
		for(double value:inputArray)
			if(value > max)
				max = value;
		return max;
	}
	
	public static Boolean isNum(String num) {
		if(num.length()==0) return false;
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