package Misc;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import Events.KeyTypedEvent;

public class KeyManager implements KeyListener {
	
	public static boolean[] keys;
	static List<KeyTypedEvent> listeners = new ArrayList<>();
	
	public KeyManager(){
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

	public static String keyToString(int z) {
		if(KeyEvent.VK_0 == z) return "0";
		if(KeyEvent.VK_1 == z) return "1";
		if(KeyEvent.VK_2 == z) return "2";
		if(KeyEvent.VK_3 == z) return "3";
		if(KeyEvent.VK_4 == z) return "4";
		if(KeyEvent.VK_5 == z) return "5";
		if(KeyEvent.VK_6 == z) return "6";
		if(KeyEvent.VK_7 == z) return "7";
		if(KeyEvent.VK_8 == z) return "8";
		if(KeyEvent.VK_9 == z) return "9";
		return "NAN";
	}
	public static String checkType() {
		if(KeyManager.getKey(KeyEvent.VK_SHIFT)) {
			if(KeyManager.keyRelease(KeyEvent.VK_A)) return "A";
			if(KeyManager.keyRelease(KeyEvent.VK_B)) return "B";
			if(KeyManager.keyRelease(KeyEvent.VK_C)) return "C";
			if(KeyManager.keyRelease(KeyEvent.VK_D)) return "D";
			if(KeyManager.keyRelease(KeyEvent.VK_E)) return "E";
			if(KeyManager.keyRelease(KeyEvent.VK_F)) return "F";
			if(KeyManager.keyRelease(KeyEvent.VK_G)) return "G";
			if(KeyManager.keyRelease(KeyEvent.VK_H)) return "H";
			if(KeyManager.keyRelease(KeyEvent.VK_I)) return "I";
			if(KeyManager.keyRelease(KeyEvent.VK_J)) return "J";
			if(KeyManager.keyRelease(KeyEvent.VK_K)) return "K";
			if(KeyManager.keyRelease(KeyEvent.VK_L)) return "L";
			if(KeyManager.keyRelease(KeyEvent.VK_M)) return "M";
			if(KeyManager.keyRelease(KeyEvent.VK_N)) return "N";
			if(KeyManager.keyRelease(KeyEvent.VK_O)) return "O";
			if(KeyManager.keyRelease(KeyEvent.VK_P)) return "P";
			if(KeyManager.keyRelease(KeyEvent.VK_Q)) return "Q";
			if(KeyManager.keyRelease(KeyEvent.VK_R)) return "R";
			if(KeyManager.keyRelease(KeyEvent.VK_S)) return "S";
			if(KeyManager.keyRelease(KeyEvent.VK_T)) return "T";
			if(KeyManager.keyRelease(KeyEvent.VK_U)) return "U";
			if(KeyManager.keyRelease(KeyEvent.VK_V)) return "V";
			if(KeyManager.keyRelease(KeyEvent.VK_W)) return "W";
			if(KeyManager.keyRelease(KeyEvent.VK_X)) return "X";
			if(KeyManager.keyRelease(KeyEvent.VK_Y)) return "Y";
			if(KeyManager.keyRelease(KeyEvent.VK_Z)) return "Z";
		} else {
			if(KeyManager.keyRelease(KeyEvent.VK_A)) return "a";
			if(KeyManager.keyRelease(KeyEvent.VK_B)) return "b";
			if(KeyManager.keyRelease(KeyEvent.VK_C)) return "c";
			if(KeyManager.keyRelease(KeyEvent.VK_D)) return "d";
			if(KeyManager.keyRelease(KeyEvent.VK_E)) return "e";
			if(KeyManager.keyRelease(KeyEvent.VK_F)) return "f";
			if(KeyManager.keyRelease(KeyEvent.VK_G)) return "g";
			if(KeyManager.keyRelease(KeyEvent.VK_H)) return "h";
			if(KeyManager.keyRelease(KeyEvent.VK_I)) return "i";
			if(KeyManager.keyRelease(KeyEvent.VK_J)) return "j";
			if(KeyManager.keyRelease(KeyEvent.VK_K)) return "k";
			if(KeyManager.keyRelease(KeyEvent.VK_L)) return "l";
			if(KeyManager.keyRelease(KeyEvent.VK_M)) return "m";
			if(KeyManager.keyRelease(KeyEvent.VK_N)) return "n";
			if(KeyManager.keyRelease(KeyEvent.VK_O)) return "o";
			if(KeyManager.keyRelease(KeyEvent.VK_P)) return "p";
			if(KeyManager.keyRelease(KeyEvent.VK_Q)) return "q";
			if(KeyManager.keyRelease(KeyEvent.VK_R)) return "r";
			if(KeyManager.keyRelease(KeyEvent.VK_S)) return "s";
			if(KeyManager.keyRelease(KeyEvent.VK_T)) return "t";
			if(KeyManager.keyRelease(KeyEvent.VK_U)) return "u";
			if(KeyManager.keyRelease(KeyEvent.VK_V)) return "v";
			if(KeyManager.keyRelease(KeyEvent.VK_W)) return "w";
			if(KeyManager.keyRelease(KeyEvent.VK_X)) return "x";
			if(KeyManager.keyRelease(KeyEvent.VK_Y)) return "y";
			if(KeyManager.keyRelease(KeyEvent.VK_Z)) return "z";
			if(KeyManager.keyRelease(KeyEvent.VK_1)) return "1";
			if(KeyManager.keyRelease(KeyEvent.VK_2)) return "2";
			if(KeyManager.keyRelease(KeyEvent.VK_3)) return "3";
			if(KeyManager.keyRelease(KeyEvent.VK_4)) return "4";
			if(KeyManager.keyRelease(KeyEvent.VK_5)) return "5";
			if(KeyManager.keyRelease(KeyEvent.VK_6)) return "6";
			if(KeyManager.keyRelease(KeyEvent.VK_7)) return "7";
			if(KeyManager.keyRelease(KeyEvent.VK_8)) return "8";
			if(KeyManager.keyRelease(KeyEvent.VK_9)) return "9";
			if(KeyManager.keyRelease(KeyEvent.VK_0)) return "0";
		}
		if(KeyManager.keyRelease(KeyEvent.VK_SPACE)) return " ";
		if(KeyManager.keyRelease(KeyEvent.VK_ENTER)) return "ENTER";
		if(KeyManager.keyRelease(KeyEvent.VK_BACK_SPACE)) return "BACKSPACE";
		return "";
	}
}
