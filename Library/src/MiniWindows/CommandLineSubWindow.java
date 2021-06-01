package MiniWindows;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Events.CommandEvent;
import Events.KeyTypedEvent;
import Misc.KeyManager;
import Rendering.Graphics;

public class CommandLineSubWindow extends SubWindow implements KeyTypedEvent{
	String type = "";
	static ArrayList<String> typed = new ArrayList<String>();
	static List<CommandEvent> events;
	
	public CommandLineSubWindow() {
		width = 500;
		height = 300;
		drawDefaultBackground = true;
		KeyManager.addKeyTypedEvent(this);
		events = new ArrayList<>();
	}
	public static void addCommandEvent(CommandEvent listener) { events.add(listener); }
	public void tick() {
		flash++; 
		if(flash>60) flash = 0; //Flash the line
	}
	int flash = 0;
	public void render(Graphics g) {
		g.setFont("Serif",Font.PLAIN,15);
		
		g.setColor(Color.white);
		String ex = ""; 
		if(flash<30) ex="_"; 
		for(int z=0;z<typed.size();z++) {
			g.drawString(typed.get(z), 5, height - (g.fontSize*(z+1)));
		}
		
		g.drawString(type+ex, 5, height-5);
	}
	public static void write(String message) {
		System.out.println(message);
		typed.add(0,message);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		if(selected & visable) {
			if( e.getKeyChar() == KeyEvent.VK_BACK_QUOTE) return;
			if( e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
				if(type.length()>0)
					type = type.substring(0,type.length()-1);
				return;
			}
			if( e.getKeyChar() == KeyEvent.VK_ENTER) {
				if(type.length()>0) {
					typed.add(0,type);
					Scanner scan = new Scanner(type);
					String command = "";
					ArrayList<String> list = new ArrayList<String>();
					while(scan.hasNext())
						if(command.length()==0)
							command = scan.next();
						else
							list.add(scan.next());
					scan.close();
					String[] args = new String[list.size()];
					for(int x=0;x<list.size();x++)
						args[x] = list.get(x);
					
					for(CommandEvent listener:events)
						listener.command(command, args);
					type = "";
				}
				return;
			}
			type += e.getKeyChar();
		}
	}
}