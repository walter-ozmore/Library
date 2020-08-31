package MiniWindows;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Misc.Graphics;
import Misc.MouseManager;
import Threading.ThreadManager;

public class SubWindowManager {
	boolean threaded = true;
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
	int mx = MouseManager.mouseX;
	int my = MouseManager.mouseY;
	public void render(Graphics g) {
		mx = MouseManager.mouseX;
		my = MouseManager.mouseY;
		
		if(threaded) {
			for(SubWindow sub:subWindows) {
				new ThreadRender(this,sub,g).start();
				
			}
			/*
			ThreadManager threadManager = new ThreadManager(5);
			for(int z=0;z<subWindows.size();) 
				if(threadManager.addThread( new ThreadRender(this, subWindows.get(z), g ) )) z++;
			*/
		} else {
			defaultRender(g,mx,my);
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
	public void defaultRender(Graphics g, int mx, int my) {
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
}

class ThreadRender extends Thread {
	Graphics g;
	SubWindow sub;
	SubWindowManager manager;
	public ThreadRender(SubWindowManager manager, SubWindow subWindow,Graphics g) {
		this.g = g;
		sub = subWindow;
		this.manager = manager;
	}
	
	public void run() {
		g.setColor(Color.white); g.fillRect(0, 0, 100, 100);
		if(sub.visable) {
			BufferedImage image = new BufferedImage(sub.width, sub.height, BufferedImage.TYPE_INT_ARGB);
			Graphics i = new Graphics((Graphics2D)image.getGraphics());
			sub.render(i);
			if(sub.titleBar) {
				if(MouseManager.leftPressed)
					if(manager.mx>sub.x & manager.mx<sub.x+sub.width & manager.my>sub.y & manager.my<sub.y+15 ) {//Mouse on title bar
						if(manager.mx<sub.x+sub.width-20){//Check for X button
							if(manager.heldWindow==sub | manager.heldWindow == null) {
								if(!manager.mouseHeld) {
									manager.mouseHeldX = (sub.x - manager.mx);
									manager.mouseHeldY = (sub.y - manager.my);
								}
								sub.x = manager.mx+manager.mouseHeldX;
								sub.y = manager.my+manager.mouseHeldY;
								
								manager.mouseHeld = true;
								manager.heldWindow = sub;
								manager.moveSubToTop(sub);
								manager.selectedWindow = sub;
							}
						}
					}
				if(manager.heldWindow==null) {}else{
					manager.heldWindow.x = manager.mx+manager.mouseHeldX;
					manager.heldWindow.y = manager.my+manager.mouseHeldY;
				}
				if(!MouseManager.leftPressed) {
					manager.mouseHeld = false;
					manager.heldWindow = null;
				}
				//Check if windown click
				if(MouseManager.leftPressed & !manager.mouseHeld)
					if(manager.mx>sub.x & manager.mx<sub.x+sub.width & manager.my>sub.y & manager.my<sub.y+sub.height+15) {
						manager.selectedWindow = sub;
						manager.moveSubToTop(sub);
					}
				//Actually draw stuff
				if(sub.drawDefaultBackground) { g.setColor(new Color(45,45,45)); g.fillRect(sub.x, sub.y, sub.width, sub.height+15);}//DrawBackground
				g.drawImage(image, sub.x, sub.y+15, sub.width, sub.height);
				if(manager.selectedWindow == sub) {
					g.setColor(manager.accentColor); 
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
}