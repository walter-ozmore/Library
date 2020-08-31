package Misc;

import java.awt.Color;

public class DataPoint {
	public double x,y;
	public Color color;
	public DataPoint(double x, double y) {
		this.x = x;
		this.y = y;
		color = Color.white;
		
	}
	public DataPoint(double x, double y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
}
