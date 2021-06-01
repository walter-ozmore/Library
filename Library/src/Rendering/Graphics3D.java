package Rendering;

import java.awt.Color;

public class Graphics3D {
	public Graphics g;
	int screenWidth = 0, screenHeight = 0;
	Point3D camera = new Point3D(0,0,0);
	
	public Graphics3D (Graphics g) {
		this.g = g;
	}
	public void updateScreen(int screenWidth, int screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}
	public void drawPoint(Point3D point3D) {
		int defaultSize = 100;
		g.drawCenterCircle(point3D.x, point3D.z, defaultSize-point3D.x);
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