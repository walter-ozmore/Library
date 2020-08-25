package Misc;

import java.awt.Color;

public class Graphics3D {
	public Graphics g;
	
	public Graphics3D (Graphics g) {
		this.g = g;
	}
	
	public void drawCube(double angle) {
		double x = 150, y = 150, width = 100;
		
		double temp = width * Math.cos(angle);
		System.out.println( Math.toDegrees(angle) + " " + temp);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x, y, width, 100);
		g.setColor(Color.WHITE);
		g.drawRect(x, y, width, 100);
	}
}
