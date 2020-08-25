package Misc;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import Events.KeyTypedEvent;

public class KeyManager implements KeyListener {
	
	public static boolean[] keys;
	static List<KeyTypedEvent> listeners;
	
	public KeyManager(){
		KeyManager.listeners = new ArrayList<>();
		keys = new boolean[256];
	}
	public static void addKeyTypedEvent(KeyTypedEvent listener) { listeners.add(listener); }
	
	public void tick(){}
	public static boolean keyLog[]=new boolean[256];
	
	public static boolean keyRelease(int key){
		if(KeyManager.keys[key]){
			if(!keyLog[key]){
				keyLog[key]=true;
				return true;
			}
		}else if(!KeyManager.keys[key]){
			keyLog[key]=false;
		}
		return false;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		try {
			keys[e.getKeyCode()] = true;
		}catch(Exception ee) {}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		try {
			keys[e.getKeyCode()] = false;
		}catch(Exception ee) {}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		for(KeyTypedEvent listener:listeners)
			listener.keyTyped(e);
		//Main.keyTyped(e);
	}
	public static boolean getKey(int key) {return keys[key];}

}
