package MiniWindows;

import Misc.Graphics;

public abstract class SubWindow {
	public boolean 
		visable = false,
		titleBar = true,
		selected = false,
		drawDefaultBackground = false;
	protected int x=0, y=0, width=100, height=100;
	protected String name = "";
	
	public void tick() {}
	public void render(Graphics g) {}
	public void debugRender(Graphics g) {}
	public void setLocation(int x, int y) { this.x = x; this.y = y;}
}
