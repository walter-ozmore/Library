package MiniWindows;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Misc.DataPoint;
import Misc.Graphics;

public class ScatterPlotSubWindow extends SubWindow {
	boolean drawLines = true;
	public ArrayList<DataPoint> points = new ArrayList<DataPoint>();
	
	public ScatterPlotSubWindow() {
		width = 1500;
		height = 800;
		//x=y;
		drawDefaultBackground = false;
		titleBar = true;
	}
	double maxX = 1, maxY = 1;
	double minX = Double.MAX_VALUE, minY = Double.MAX_VALUE;
	public void addPoint(DataPoint point) {
		points.add(point);
		//update();
	}
	public void update() {
		maxX = 1; maxY = 1;
		minX = Double.MAX_VALUE; minY = Double.MAX_VALUE;
		for(int z=0;z<points.size();z++) {
			if(maxX<points.get(z).x) maxX = points.get(z).x;
			if(maxY<points.get(z).y) maxY = points.get(z).y;
			if(minX>points.get(z).x) minX = points.get(z).x;
			if(minY>points.get(z).y) minY = points.get(z).y;
		}
		maxX++; maxY++;
		renderImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = new Graphics((Graphics2D)renderImage.getGraphics());
		g.setFont("Serif",Font.PLAIN,15);
		for(int z=0;z<points.size();z++) {
			DataPoint p = points.get(z);
			g.setColor( p.color );
			if(drawLines) 
				for(int zz=z-1;zz>0;zz--)
					if(p.color.getRGB()==points.get(zz).color.getRGB()) {
						g.drawLine((double)points.get(zz).x/maxX*width, height-(double)points.get(zz).y/maxY*height, (double)p.x/maxX*width, height-(double)p.y/maxY*height);
						break;
					}
			g.fillCenterCircle((double)p.x/maxX*width, height-(double)p.y/maxY*height, 2);
			
		}
		g.drawOutlinedString("MaxX:"+maxX+" MaxY:"+maxY, 0, 15);
		g.drawOutlinedString("MinX:"+minX+" MinY:"+minY, 0, 30);
		g.drawOutlinedString("Datapoints:"+points.size(), 0, 45);
	}
	public void render(Graphics g) {
		g.drawImage(renderImage, 0, 0, width, height);
	}
}
