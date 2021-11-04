package CustomCommandWindow;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.JFrame;

import Events.SimpleWindowEvent;
import Misc.KeyManager;
import Misc.MouseManager;
import static Misc.SDL.*;

public class CustomCommandWindow extends Thread {
	File fileLoc;
	ArrayList<String> q;
	public int width=900, height=475, devMode = 0, maxFPS = 120;
	public double fps=0;
	public ArrayList<String> debugMessages = new ArrayList<String>();
	
	public JFrame frame;
	private Canvas canvas;
	public KeyManager keyManager;
	public MouseManager mouseManager;
	public String name = "Custom Command Prompt";
	
	List<SimpleWindowEvent> listeners;
	
	public CustomCommandWindow(){
		fps = maxFPS;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		listeners = new ArrayList<>();
		fileLoc = new File(".");
		
		q = new ArrayList<String>();
		q.add("");
	}
	
	private void init(){
		frame = new JFrame( name );
		//frame.setUndecorated(true);
		frame.setSize(width, height);
		//This closes the program gracefully
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.addMouseListener(mouseManager);
		frame.addMouseMotionListener(mouseManager);
		frame.addMouseWheelListener(mouseManager);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		canvas.addMouseListener(mouseManager);
		canvas.addMouseMotionListener(mouseManager);
		canvas.addMouseWheelListener(mouseManager);
		frame.add(canvas);
		frame.pack();
		frame.addKeyListener(keyManager);
		
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		width=canvas.getWidth();
		height=canvas.getHeight();
	}
	private BufferStrategy bs;
	private Graphics g;
	
	void tar(){
		bs = canvas.getBufferStrategy();
		if(bs == null){
			canvas.createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		
		//Clear Screen
		g.clearRect(0, 0, width, height);
		g.setColor(new Color(0,0,0));
		g.fillRect(0, 0, width, height);
		//Draw Here!
		
		Rendering.Graphics gg = new Rendering.Graphics((Graphics2D) g);
		gg.setDim(width, height);
		
		int offSet = height - q.size()*15;
//		offSet = 0;
		for(int z=0;z<q.size();z++) {
			gg.drawOutlinedString(q.get(z), 5,((1+z)*gg.fontSize)+offSet);
		}
		
		width = canvas.getWidth();
		height = canvas.getHeight();
		keyManager.tick();
		
		//End Drawing!
		bs.show();
		g.dispose();
		debugMessages = new ArrayList<String>();
	}
	//Rendering
	public final static String cSetColor = "asdf";
	
	public void drawOutlinedString(String str, double x, double y, Color strColor, Color outlineColor) {
		int borderAmount = 1;
		g.setColor(outlineColor);
		drawString(str, x+borderAmount, y);
		drawString(str, x-borderAmount, y);
		drawString(str, x, y+borderAmount);
		drawString(str, x, y-borderAmount);
		g.setColor(strColor);
		drawString(str, x, y);
	}
	public void drawString(String str, double x, double y) { 
		if(str==null)
			g.drawString("null", (int)x, (int)y);
		else {
			g.drawString(str, (int)x, (int)y);
		}
	}
	
	public void run(){
		init();
		while(true) {
			tar();
			try { sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
	public <T> void print(T t) {
		q.set(q.size()-1, q.get(q.size()-1)+t+"");
	}
	public void println() {
		q.add("");
	}
	public <T> void println(T t) {
		q.set(q.size()-1, q.get(q.size()-1)+t+"");
		println();
	}

}
