package MiniWindows;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Misc.Graphics;
import Misc.MouseManager;
import Threading.ThreadManager;

public class SubWindowManager {
	boolean threaded = false;
	ArrayList<SubWindow> subWindows;
	
	//Holding window vars
	boolean mouseHeld;
	int mouseHeldX = 0,mouseHeldY = 0;
	SubWindow heldWindow = null;
	
	//Accent Color
	public Color accentColor = Color.red;
	
	//Selected Window
	SubWindow selectedWindow = null;
	
	public SubWindowManager() {
		init();
	}
	void init() {
		subWindows = new ArrayList<SubWindow>();
	}
	public void addSubWindow(SubWindow subWindow) { subWindows.add(subWindow); }
	public void addSubWindow(SubWindow subWindow,boolean visable) { subWindow.visable = visable; subWindows.add(subWindow); }
	public void tick() {
		for(SubWindow sub:subWindows) sub.tick();
	}
	public void render(Graphics g) {
		int mx = MouseManager.mouseX;
		int my = MouseManager.mouseY;
		
		if(threaded) {
			ThreadManager threadManager = new ThreadManager(5);
			for(int z=0;z<subWindows.size();) 
				if(threadManager.addThread( new ThreadRender( subWindows.get(z), g ) )) z++;
		} else {
			for(SubWindow sub:subWindows)
				if(sub.visable) {
					BufferedImage image = new BufferedImage(sub.width, sub.height, BufferedImage.TYPE_INT_ARGB);
					Graphics i = new Graphics((Graphics2D)image.getGraphics());
					sub.render(i);
					if(sub.titleBar) {
						if(MouseManager.leftPressed)
							if(mx>sub.x & mx<sub.x+sub.width & my>sub.y & my<sub.y+15 ) {//Mouse on title bar
								if(mx<sub.x+sub.width-20){//Check for X button
									if(heldWindow==sub | heldWindow == null) {
										if(!mouseHeld) {
											mouseHeldX = (sub.x - mx);
											mouseHeldY = (sub.y - my);
										}
										sub.x = mx+mouseHeldX;
										sub.y = my+mouseHeldY;
										
										mouseHeld = true;
										heldWindow = sub;
										moveSubToTop(sub);
										selectedWindow = sub;
									}
								}
							}
						if(heldWindow==null) {}else{
							heldWindow.x = mx+mouseHeldX;
							heldWindow.y = my+mouseHeldY;
						}
						if(!MouseManager.leftPressed) {
							mouseHeld = false;
							heldWindow = null;
						}
						//Check if windown click
						if(MouseManager.leftPressed & !mouseHeld)
							if(mx>sub.x & mx<sub.x+sub.width & my>sub.y & my<sub.y+sub.height+15) {
								selectedWindow = sub;
								moveSubToTop(sub);
							}
						//Actually draw stuff
						if(sub.drawDefaultBackground) { g.setColor(new Color(45,45,45)); g.fillRect(sub.x, sub.y, sub.width, sub.height+15);}//DrawBackground
						g.drawImage(image, sub.x, sub.y+15, sub.width, sub.height);
						if(selectedWindow == sub) {
							g.setColor(accentColor); 
							sub.selected = true;
						} else {
							g.setColor(Color.gray);
							sub.selected = false;
						}
						g.drawRect(sub.x, sub.y, sub.width, sub.height+15);
						g.fillRect(sub.x, sub.y, sub.width, 15);
					} else {
						g.drawImage(image, sub.x, sub.y, sub.width, sub.height);
					}
				}
		}
		if(heldWindow!=null) {
			heldWindow.x = mx + mouseHeldX;
			heldWindow.y = my + mouseHeldY;
		}
	}
	public void moveSubToTop(SubWindow sub) {
		ArrayList<SubWindow> newList = new ArrayList<SubWindow>();
		for(SubWindow s:subWindows)
			if(s!=sub)
				newList.add(s);
		newList.add(sub);
		subWindows = newList;
	}
}

class ThreadRender extends Thread {
	public ThreadRender(SubWindow subWindow,Graphics g) {
		
	}
	
	public void run() {
		
	}
}