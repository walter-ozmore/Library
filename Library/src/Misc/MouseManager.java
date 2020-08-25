package Misc;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import Events.MouseWheelMovedEvent;

public class MouseManager implements MouseListener, MouseMotionListener, MouseWheelListener {

	public static boolean leftPressed, rightPressed;
	public static boolean leftRelease, rightRelease;
	public static int mouseX, mouseY, mouseScroll;
	List<MouseWheelMovedEvent> listeners;
	
	public MouseManager(){
		this.listeners = new ArrayList<>();
	}
	public void addMouseWheelEvent(MouseWheelMovedEvent listener) { listeners.add(listener); }
	
	// Implemented methods
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = true;
		else if(e.getButton() == MouseEvent.BUTTON3)
			rightPressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) 
			leftPressed = false;
		else if(e.getButton() == MouseEvent.BUTTON3) 
			rightPressed = false;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
	@Override
	public void mouseDragged(MouseEvent e){
		mouseX = e.getX();
		mouseY = e.getY();
	}
	@Override
	public void mouseClicked(MouseEvent e){}
	@Override
	public void mouseEntered(MouseEvent e){}
	@Override
	public void mouseExited(MouseEvent e){}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		for(MouseWheelMovedEvent listener:listeners)
			listener.mouseWheelMoved(e);
	}
	
	public static void print() {
		System.out.println("MX:"+mouseX+" MY:"+mouseY);
	}
}