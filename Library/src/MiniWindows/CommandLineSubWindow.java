package MiniWindows;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Events.KeyTypedEvent;
import Misc.Graphics;
import Misc.KeyManager;

public class CommandLineSubWindow extends SubWindow implements KeyTypedEvent{
	String type = "";
	ArrayList<String> typed = new ArrayList<String>();
	
	public CommandLineSubWindow() {
		width = 500;
		height = 300;
		drawDefaultBackground = true;
		KeyManager.addKeyTypedEvent(this);
	}
	public void tick() {}
	int flash = 0;
	public void render(Graphics g) {
		g.setFont("Serif",Font.PLAIN,15);
		
		g.setColor(Color.white);
		String ex = ""; if(flash<30) ex="_"; flash++; if(flash>60) flash = 0; //Flash the line
		g.drawString(type+ex, 5, height-5);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		if(selected) {
			if( e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
				if(type.length()>0)
					type = type.substring(0,type.length()-1);
				return;
			}
			if( e.getKeyChar() == KeyEvent.VK_ENTER) {
				if(type.length()>0) {
					typed.add(type);
					type = "";
				}
				return;
			}
			type += e.getKeyChar();
		}
	}
}

class Line {
	
}