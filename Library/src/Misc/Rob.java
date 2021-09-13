package Misc;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Rob extends Robot {

	public Rob() throws AWTException {
		super();
	}

	public void paste() {
		keyPress(KeyEvent.VK_CONTROL);
		keyPress(KeyEvent.VK_V);
		delay(200);
		keyRelease(KeyEvent.VK_CONTROL);
		keyRelease(KeyEvent.VK_V);
	}
	public void selectAll() {
		keyPress(KeyEvent.VK_CONTROL);
		keyPress(KeyEvent.VK_A);
		delay(200);
		keyRelease(KeyEvent.VK_CONTROL);
		keyRelease(KeyEvent.VK_A);
	}
	public void leftClick(int x) {
		for(int z=0;z<x;z++)
			leftClick();
	}
	public void leftClick() {
		mousePress(InputEvent.BUTTON1_DOWN_MASK);
		delay(200);
		mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		delay(200);
	}

	public void rightClick() {
		mousePress(InputEvent.BUTTON3_DOWN_MASK);
		delay(200);
		mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
		delay(200);
	}

	public void enter() {
		keyPress(KeyEvent.VK_ENTER);
		delay(200);
		keyRelease(KeyEvent.VK_ENTER);
		delay(200);
	}

	public static void clipboard(String str) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		StringSelection strSel = new StringSelection(str);
		clipboard.setContents(strSel, null);
	}

	public void type(int i) {
		delay(40);
		keyPress(i);
		keyRelease(i);
	}

	public void type(String s) {
//		byte[] bytes = s.getBytes();
//		for (byte b : bytes) {
//			int code = b;
//			// keycode only handles [A-Z] (which is ASCII decimal [65-90])
//			if (code > 96 && code < 123)
//				code = code - 32;
//			delay(40);
//			keyPress(code);
//			keyRelease(code);
//		}
		for(int z=0;z<s.length();z++) {
			type( s.charAt(z) );
		}
	}
	public void type(char c) {
		switch(c) {
			case 'a': keyPress( KeyEvent.VK_A ); keyRelease( KeyEvent.VK_A ); break;
			case 'b': keyPress( KeyEvent.VK_B ); keyRelease( KeyEvent.VK_B ); break;
			case 'c': keyPress( KeyEvent.VK_C ); keyRelease( KeyEvent.VK_C ); break;
			case 'd': keyPress( KeyEvent.VK_D ); keyRelease( KeyEvent.VK_D ); break;
			case 'e': keyPress( KeyEvent.VK_E ); keyRelease( KeyEvent.VK_E ); break;
			case 'f': keyPress( KeyEvent.VK_F ); keyRelease( KeyEvent.VK_F ); break;
			case 'g': keyPress( KeyEvent.VK_G ); keyRelease( KeyEvent.VK_G ); break;
			case 'h': keyPress( KeyEvent.VK_H ); keyRelease( KeyEvent.VK_H ); break;
			case 'i': keyPress( KeyEvent.VK_I ); keyRelease( KeyEvent.VK_I ); break;
			case 'j': keyPress( KeyEvent.VK_J ); keyRelease( KeyEvent.VK_J ); break;
			case 'k': keyPress( KeyEvent.VK_K ); keyRelease( KeyEvent.VK_K ); break;
			case 'l': keyPress( KeyEvent.VK_L ); keyRelease( KeyEvent.VK_L ); break;
			case 'm': keyPress( KeyEvent.VK_M ); keyRelease( KeyEvent.VK_M ); break;
			case 'n': keyPress( KeyEvent.VK_N ); keyRelease( KeyEvent.VK_N ); break;
			case 'o': keyPress( KeyEvent.VK_O ); keyRelease( KeyEvent.VK_O ); break;
			case 'p': keyPress( KeyEvent.VK_P ); keyRelease( KeyEvent.VK_P ); break;
			case 'q': keyPress( KeyEvent.VK_Q ); keyRelease( KeyEvent.VK_Q ); break;
			case 'r': keyPress( KeyEvent.VK_R ); keyRelease( KeyEvent.VK_R ); break;
			case 's': keyPress( KeyEvent.VK_S ); keyRelease( KeyEvent.VK_S ); break;
			case 't': keyPress( KeyEvent.VK_T ); keyRelease( KeyEvent.VK_T ); break;
			case 'u': keyPress( KeyEvent.VK_U ); keyRelease( KeyEvent.VK_U ); break;
			case 'v': keyPress( KeyEvent.VK_V ); keyRelease( KeyEvent.VK_V ); break;
			case 'w': keyPress( KeyEvent.VK_W ); keyRelease( KeyEvent.VK_W ); break;
			case 'x': keyPress( KeyEvent.VK_X ); keyRelease( KeyEvent.VK_X ); break;
			case 'y': keyPress( KeyEvent.VK_Y ); keyRelease( KeyEvent.VK_Y ); break;
			case 'z': keyPress( KeyEvent.VK_Z ); keyRelease( KeyEvent.VK_Z ); break;
			case 'A': keyPress(KeyEvent.VK_SHIFT); keyPress( KeyEvent.VK_A ); keyRelease( KeyEvent.VK_A ); keyRelease(KeyEvent.VK_SHIFT); break;
			case 'B': keyPress(KeyEvent.VK_SHIFT); keyPress( KeyEvent.VK_B ); keyRelease( KeyEvent.VK_B ); keyRelease(KeyEvent.VK_SHIFT); break;
			case 'C': keyPress(KeyEvent.VK_SHIFT); keyPress( KeyEvent.VK_C ); keyRelease( KeyEvent.VK_C ); keyRelease(KeyEvent.VK_SHIFT); break;
			case 'D': keyPress(KeyEvent.VK_SHIFT); keyPress( KeyEvent.VK_D ); keyRelease( KeyEvent.VK_D ); keyRelease(KeyEvent.VK_SHIFT); break;
			case 'E': keyPress(KeyEvent.VK_SHIFT); keyPress( KeyEvent.VK_E ); keyRelease( KeyEvent.VK_E ); keyRelease(KeyEvent.VK_SHIFT); break;
			case 'F': keyPress(KeyEvent.VK_SHIFT); keyPress( KeyEvent.VK_F ); keyRelease( KeyEvent.VK_F ); keyRelease(KeyEvent.VK_SHIFT); break;
			case 'G': keyPress(KeyEvent.VK_SHIFT); keyPress( KeyEvent.VK_G ); keyRelease( KeyEvent.VK_G ); keyRelease(KeyEvent.VK_SHIFT); break;
			case 'H': keyPress(KeyEvent.VK_SHIFT); keyPress( KeyEvent.VK_H ); keyRelease( KeyEvent.VK_H ); keyRelease(KeyEvent.VK_SHIFT); break;
			case 'I': keyPress(KeyEvent.VK_SHIFT); keyPress( KeyEvent.VK_I ); keyRelease( KeyEvent.VK_I ); keyRelease(KeyEvent.VK_SHIFT); break;
			case 'J': keyPress(KeyEvent.VK_SHIFT); keyPress( KeyEvent.VK_J ); keyRelease( KeyEvent.VK_J ); keyRelease(KeyEvent.VK_SHIFT); break;
			case 'K': keyPress(KeyEvent.VK_SHIFT); keyPress( KeyEvent.VK_K ); keyRelease( KeyEvent.VK_K ); keyRelease(KeyEvent.VK_SHIFT); break;
			case 'L': keyPress(KeyEvent.VK_SHIFT); keyPress( KeyEvent.VK_L ); keyRelease( KeyEvent.VK_L ); keyRelease(KeyEvent.VK_SHIFT); break;
			case 'M': keyPress(KeyEvent.VK_SHIFT); keyPress( KeyEvent.VK_M ); keyRelease( KeyEvent.VK_M ); keyRelease(KeyEvent.VK_SHIFT); break;
			case 'N': keyPress(KeyEvent.VK_SHIFT); keyPress( KeyEvent.VK_N ); keyRelease( KeyEvent.VK_N ); keyRelease(KeyEvent.VK_SHIFT); break;
			case 'O': keyPress(KeyEvent.VK_SHIFT); keyPress( KeyEvent.VK_O ); keyRelease( KeyEvent.VK_O ); keyRelease(KeyEvent.VK_SHIFT); break;
			case 'P': keyPress(KeyEvent.VK_SHIFT); keyPress( KeyEvent.VK_P ); keyRelease( KeyEvent.VK_P ); keyRelease(KeyEvent.VK_SHIFT); break;
			case 'Q': keyPress(KeyEvent.VK_SHIFT); keyPress( KeyEvent.VK_Q ); keyRelease( KeyEvent.VK_Q ); keyRelease(KeyEvent.VK_SHIFT); break;
			case 'R': keyPress(KeyEvent.VK_SHIFT); keyPress( KeyEvent.VK_R ); keyRelease( KeyEvent.VK_R ); keyRelease(KeyEvent.VK_SHIFT); break;
			case 'S': keyPress(KeyEvent.VK_SHIFT); keyPress( KeyEvent.VK_S ); keyRelease( KeyEvent.VK_S ); keyRelease(KeyEvent.VK_SHIFT); break;
			case 'T': keyPress(KeyEvent.VK_SHIFT); keyPress( KeyEvent.VK_T ); keyRelease( KeyEvent.VK_T ); keyRelease(KeyEvent.VK_SHIFT); break;
			case 'U': keyPress(KeyEvent.VK_SHIFT); keyPress( KeyEvent.VK_U ); keyRelease( KeyEvent.VK_U ); keyRelease(KeyEvent.VK_SHIFT); break;
			case 'V': keyPress(KeyEvent.VK_SHIFT); keyPress( KeyEvent.VK_V ); keyRelease( KeyEvent.VK_V ); keyRelease(KeyEvent.VK_SHIFT); break;
			case 'W': keyPress(KeyEvent.VK_SHIFT); keyPress( KeyEvent.VK_W ); keyRelease( KeyEvent.VK_W ); keyRelease(KeyEvent.VK_SHIFT); break;
			case 'X': keyPress(KeyEvent.VK_SHIFT); keyPress( KeyEvent.VK_X ); keyRelease( KeyEvent.VK_X ); keyRelease(KeyEvent.VK_SHIFT); break;
			case 'Y': keyPress(KeyEvent.VK_SHIFT); keyPress( KeyEvent.VK_Y ); keyRelease( KeyEvent.VK_Y ); keyRelease(KeyEvent.VK_SHIFT); break;
			case 'Z': keyPress(KeyEvent.VK_SHIFT); keyPress( KeyEvent.VK_Z ); keyRelease( KeyEvent.VK_Z ); keyRelease(KeyEvent.VK_SHIFT); break;
			case '0': keyPress( KeyEvent.VK_0 ); keyRelease( KeyEvent.VK_0 ); break;
			case '1': keyPress( KeyEvent.VK_1 ); keyRelease( KeyEvent.VK_1 ); break;
			case '2': keyPress( KeyEvent.VK_2 ); keyRelease( KeyEvent.VK_2 ); break;
			case '3': keyPress( KeyEvent.VK_3 ); keyRelease( KeyEvent.VK_3 ); break;
			case '4': keyPress( KeyEvent.VK_4 ); keyRelease( KeyEvent.VK_4 ); break;
			case '5': keyPress( KeyEvent.VK_5 ); keyRelease( KeyEvent.VK_5 ); break;
			case '6': keyPress( KeyEvent.VK_6 ); keyRelease( KeyEvent.VK_6 ); break;
			case '7': keyPress( KeyEvent.VK_7 ); keyRelease( KeyEvent.VK_7 ); break;
			case '8': keyPress( KeyEvent.VK_8 ); keyRelease( KeyEvent.VK_8 ); break;
			case '9': keyPress( KeyEvent.VK_9 ); keyRelease( KeyEvent.VK_9 ); break;
			case '.': keyPress( KeyEvent.VK_PERIOD ); keyRelease( KeyEvent.VK_PERIOD ); break;
		}
	}
}
