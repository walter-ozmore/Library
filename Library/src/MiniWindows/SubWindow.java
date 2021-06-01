package MiniWindows;

import java.awt.image.BufferedImage;

import Rendering.Graphics;

public abstract class SubWindow {
	public boolean 
		visable = false,
		titleBar = true,
		selected = false,
		drawDefaultBackground = false;
	protected int x=0, y=0, width=100, height=100;
	static int sid = 0;
	int id = 0;
	protected String name = "";
	protected BufferedImage renderImage;
	BufferedImage icon = null;
	
	public void tick() {}
	public void render(Graphics g) {}
	public void debugRender(Graphics g) {}
	public void setLocation(int x, int y) { this.x = x; this.y = y;}
}
