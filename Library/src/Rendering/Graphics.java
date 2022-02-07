package Rendering;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import Misc.Mat;

public class Graphics {
	public int fontSize = 15, width = 0, height = 0;
	public Graphics2D g;
	final public boolean defaultScaleable = false;
	public boolean scalable = defaultScaleable;
	public BufferedImage[] layers;
	public Graphics[] gl;
	
	public Graphics(java.awt.Graphics g) { this.g = (Graphics2D) g; }
	public Graphics(Graphics2D g) { this.g = g; }
	
	// Colors
	public void setColor(Color color) { g.setColor(color); }
	public void setColor(int r, int g, int b) {
		Color color = new Color( (int)Mat.inRange(r, 0, 255), (int)Mat.inRange(g, 0, 255), (int)Mat.inRange(b, 0, 255) );
		setColor(color);
	}
	
	public void setDim(int width, int height) { this.width = width; this.height = height; }
	public void initLayers(int num) { initLayers(num, width, height); }
	public void initLayers(int num, int width, int height) {
		layers = new BufferedImage[num];
		gl = new Graphics[num];
		for(int z=0;z<layers.length;z++) {
			layers[z] = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			gl[z] = new Graphics((Graphics2D)layers[z].getGraphics());
		}
	}
	public void renderLayers() {
		for(BufferedImage image:layers)
			drawImage(image);
	}
	public void drawRect(double x, double y, double width, double height) {
		g.drawRect((int)x, (int)y, (int)width, (int)height);
		g.drawRect((int)x, (int)y, (int)width, (int)height);
	}
	public void fillRect(double x, double y, double width, double height) {
		g.fillRect((int)x, (int)y, (int)width, (int)height);
	}
	public void outlineRect(double x, double y, double width, double height) {
		g.fillRect((int)x, (int)y, (int)width, (int)height);
		setColor(new Color(0,0,0));
		g.drawRect((int)x, (int)y, (int)width, (int)height);
	}
	public void drawOval(double x, double y, double width, double height) {
		g.drawOval((int)x, (int)y, (int)width, (int)height);
	}
	public void fillOval(double x, double y, double width, double height) {
		g.fillOval((int)x, (int)y, (int)width, (int)height); }
	public void drawCircle(double x, double y, double size) {
		g.drawOval((int)(x), (int)(y), (int)size, (int)size);
	}
	public void drawCircle(Point p, double size) {
		g.drawOval((int)(p.getX()), (int)(p.getY()), (int)size, (int)size);
	}
	public void drawCenterCircle(double x, double y, double size) {
		g.drawOval((int)(x-size/2), (int)(y-size/2), (int)size, (int)size); 
	}
	public void fillCircle(double x, double y, double size) {
		g.fillOval((int)x, (int)y, (int)size, (int)size);
	}
	public void fillCenterCircle(double x, double y, double size) {
		g.fillOval((int)(x-size/2), (int)(y-size/2), (int)size, (int)size);
	}
	public void fillCircle(Point p, double size) {
		g.fillOval((int)(p.getX()), (int)(p.getY()), (int)size, (int)size);
	}
	public void drawImage(BufferedImage img,double x, double y, double width, double height) {
		if(img!=null)
			g.drawImage(img, (int)x, (int)y, (int)width, (int)height, null);
	}
	public void drawImage(BufferedImage img) {
		if(img!=null)
			g.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null);
	}
	public void drawImage(BufferedImage img,int x,int y) {
		if(img!=null)
			g.drawImage(img, x, y, img.getWidth(), img.getHeight(), null);
	}
	public void drawRotatedImage(BufferedImage image,double x, double y, double width, double height, double angle) {
		if(image!=null && angle%360!=0) {
			BufferedImage rotated = rotateImageByDegrees(image,angle);
			double tempx = (double)rotated.getWidth()/image.getWidth();
			double tempy = (double)rotated.getHeight()/image.getHeight();
			drawImage(rotated,x-(width*(tempx-1))/2,y-(height*(tempy-1))/2,width*tempx,height*tempy);
		}else
			drawImage(image,x,y,width,height);
	}
	public static BufferedImage rotateImageByDegrees(BufferedImage img, double angle) {
		double rads = Math.toRadians(angle);
		double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
		int w = img.getWidth();
		int h = img.getHeight();
		int newWidth = (int) Math.floor(w * cos + h * sin);
		int newHeight = (int) Math.floor(h * cos + w * sin);
		
		BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = rotated.createGraphics();
		AffineTransform at = new AffineTransform();
		at.translate((newWidth - w) / 2, (newHeight - h) / 2);
		
		int x = w / 2;
		int y = h / 2;
		
		at.rotate(rads, x, y);
		g2d.setTransform(at);
		g2d.drawImage(img, 0, 0, null);
		g2d.dispose();
		
		return rotated;
	}
	public void drawString(String str, double x, double y) { 
		if(str==null)
			g.drawString("null", (int)x, (int)y);
		else
			g.drawString(str, (int)x, (int)y);
	}
	public void drawColoredString(String str, double x, double y) {
		String[] list = str.split("#");
		int loc = 0;
		for(int z=0;z<list.length;z++) {
			if(z!=0)
				if(list[z].charAt(0) == 'H') {
					
				} else {
					switch(list[z].charAt(0)) {
						case '0':setColor( new Color(  0,  0,  0) );break; //Black
						case '1':setColor( new Color(  0,  0,170) );break; //Dark Blue
						case '2':setColor( new Color(  0,170,  0) );break; //Dark Green
						case '3':setColor( new Color(  0,170,170) );break; //Dark Aqua
						case '4':setColor( new Color(170,  0,  0) );break; //Dark Red
						case '5':setColor( new Color(170,  0,170) );break; //Dark Purple
						case '6':setColor( new Color(255,170,  0) );break; //Gold
						case '7':setColor( new Color(170,170,170) );break; //Gray
						case '8':setColor( new Color( 85, 85, 85) );break; //Dark Gray
						case '9':setColor( new Color( 85, 85,255) );break; //Blue
						case 'a':setColor( new Color( 85,255, 85) );break; //Green
						case 'b':setColor( new Color( 85,255,255) );break; //Aqua
						case 'c':setColor( new Color(255, 80, 80) );break; //Red
						case 'd':setColor( new Color(255, 85,255) );break; //Light Purple
						case 'e':setColor( new Color(255,255, 85) );break; //Yellow
						case 'f':setColor( new Color(255,255,255) );break; //White
					}
					list[z] = list[z].substring(1);
//					player.sendMessage("#l "+ChatColor.BOLD+"Bold");
//					player.sendMessage("#m "+ChatColor.STRIKETHROUGH+"Strikethrough");
//					player.sendMessage("#n "+ChatColor.UNDERLINE+"Underline");
//					player.sendMessage("#o "+ChatColor.ITALIC+"Italic");
//					player.sendMessage("#r "+ChatColor.RESET+"Reset");
				}
			g.drawString(list[z], (int)x+loc, (int)(y));
			loc += getStringLength(list[z]);
		}
		//System.out.println();
	}
	public void drawOutlinedString(String str, double x, double y) {
		drawOutlinedString(str, x, y, Color.white, Color.black);
	}
	public void drawOutlinedString(String str, double x, double y, Color strColor, Color outlineColor) {
		int borderAmount = 1;
		g.setColor(outlineColor);
		drawString(str, x+borderAmount, y);
		drawString(str, x-borderAmount, y);
		drawString(str, x, y+borderAmount);
		drawString(str, x, y-borderAmount);
		g.setColor(strColor);
		drawString(str, x, y);
		
	}
	public void drawLine(double x1, double y1, double x2, double y2) { g.drawLine((int)x1, (int)y1, (int)x2, (int)y2); }
	public void drawLine(Point point,Point point2) { g.drawLine((int)point.getX(), (int)point.getY(), (int)point2.getX(), (int)point2.getY()); }
	//public void drawLine(DataPoint point,DataPoint point2) { g.drawLine((int)point.x, (int)point.y, (int)point2.x, (int)point2.y); }
	public void drawRLine(double x1, double y1, double x2, double y2) {
		x2 = x2+x1;
		y2 = y2+y1;
		g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
	}
	public void drawAngledLine(double x, double y, double angle, double length) {
		drawLine(x,y,x+length*Math.cos(Math.toRadians(angle)),y+length*Math.sin(Math.toRadians(angle)));
	}
	public void drawArc(double x, double y, double width, double height, double startAngle, double arcAngle) {
		g.drawArc((int)x, (int)y, (int)width, (int)height, (int)startAngle, (int)arcAngle);
	}
	public int getStringLength(String s) {
		if(s == null || s.length() == 0) return 0;
		return g.getFontMetrics().stringWidth(s);
	}
	public int getFontHeight() { return g.getFontMetrics().getHeight(); }
	public FontMetrics getFontMetrics() { return g.getFontMetrics(); }
	public void drawShape(Shape shape) { g.draw(shape); }
	public void drawPoly(Polygon shape) {
		g.draw(shape);
	}
	public Polygon rotatePolygon(Polygon p1,double angle,Point center) {
		Polygon newPolygon = new Polygon();
		for(int z=0;z<p1.npoints;z++) {
			Point p=new Point(p1.xpoints[z],p1.ypoints[z]);
			p = rotatePoint(p,center,angle);
			newPolygon.addPoint(p.x,p.y);
		}
		return newPolygon;
	}
	public Polygon rotatePolygon(Polygon p1,double angle) {
		Polygon newPolygon = new Polygon();
		Point center=new Point(0,0);
		double tempSumX = 0,tempSumY=0;
		
		for(int z=0;z<p1.npoints;z++) {
			tempSumX +=p1.xpoints[z];
			tempSumY +=p1.ypoints[z];
		}
		center.setLocation(tempSumX/p1.npoints, tempSumY/p1.npoints);
		for(int z=0;z<p1.npoints;z++) {
			Point p=new Point(p1.xpoints[z],p1.ypoints[z]);
			p = rotatePoint(p,center,angle);
			newPolygon.addPoint(p.x,p.y);
		}
		return newPolygon;
	}
	public Point rotatePoint(Point pt, Point center, double angleDeg) {
		double angleRad = Math.toRadians(angleDeg);
		double cosThetha = Math.cos(angleRad); //The angle COS
		double sinThetha = Math.sin(angleRad); //The angle SIN
		double dx = (pt.x - center.x); //Difference (Point in transformed to origo)
		double dy = (pt.y - center.y); //Difference -- || --

		int ptX = center.x + (int) (dx * cosThetha - dy * sinThetha);
		int ptY = center.y + (int) (dx * sinThetha + dy * cosThetha);

		return new Point(ptX, ptY);
	}
	public void setFont(String font, int style, double size) {
		fontSize = (int)size;
		g.setFont(new Font(font, style, (int)size));
	}
	public void setFont(Font font) { g.setFont( font ); }
}