package Misc;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import Events.KeyTypedEvent;
import Events.MouseClickedEvent;
import Events.MouseListenerEvent;
import Events.MouseWheelMovedEvent;

public class MouseManager implements MouseListener, MouseMotionListener, MouseWheelListener {

	public static boolean leftPressed, rightPressed, middlePressed;
	public static int mouseX, mouseY, mouseScroll;
	static List<MouseWheelMovedEvent> listeners;
	static List<MouseClickedEvent> clickListeners;
	static List<MouseListenerEvent> mouseListeners;
	
	public MouseManager(){
		MouseManager.listeners = new ArrayList<>();
		MouseManager.clickListeners = new ArrayList<>();
		MouseManager.mouseListeners = new ArrayList<>();
	}
	public static void addMouseWheelEvent(MouseWheelMovedEvent listener) { listeners.add(listener); }
	public static void addMouseClickEvent(MouseClickedEvent listener) { clickListeners.add(listener); }
	public static void addMouseListener(MouseListenerEvent listener) { mouseListeners.add(listener); }
	
	// Implemented methods
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = true;
		else if(e.getButton() == MouseEvent.BUTTON2)
			middlePressed = true;
		else if(e.getButton() == MouseEvent.BUTTON3)
			rightPressed = true;
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) 
			leftPressed = false;
		else if(e.getButton() == MouseEvent.BUTTON2)
			middlePressed = false;
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
	public void mouseClicked(MouseEvent e){
		for(MouseClickedEvent listener:clickListeners)
			listener.mouseClicked(e);
	}
	@Override
	public void mouseEntered(MouseEvent e){}
	@Override
	public void mouseExited(MouseEvent e){}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		for(MouseWheelMovedEvent listener:listeners) listener.mouseWheelMoved(e);
		for(MouseListenerEvent listener:mouseListeners) listener.mouseWheelMoved(e);
	}
	
	public static void print() {
		System.out.println("MX:"+mouseX+" MY:"+mouseY);
	}
}