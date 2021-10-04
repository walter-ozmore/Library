package Examples;

import java.awt.Color;

import Events.SimpleWindowEvent;
import Misc.SimpleWindow;
import Rendering.Graphics;

public class SimpleWindowExampleTest implements SimpleWindowEvent {
	public static void main(String args[]) { new SimpleWindowExampleTest(); }
	public SimpleWindowExampleTest() {
		SimpleWindow gameWindow = new SimpleWindow();
		gameWindow.addSimpleWindowEvent(this);
		gameWindow.start();
	}
	
	public void tar(Graphics g) {
		System.out.println("tar");
		g.drawOutlinedString("127", 100, 100);
		g.setColor(new Color(100,0,244));
		g.fillRect(0, 0, 100, 100);
	}
}
