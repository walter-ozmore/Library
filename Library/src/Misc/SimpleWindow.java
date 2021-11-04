package Misc;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Events.KeyTypedEvent;
import Events.SimpleWindowEvent;
import Misc.Assets;
import Misc.KeyManager;
import Misc.MouseManager;

public class SimpleWindow extends Thread {
	
	boolean running = true;
	public int width=500, height=500, devMode = 0, maxFPS = 120;
	public double fps=0;
	public ArrayList<String> debugMessages = new ArrayList<String>();
	
	public JFrame frame;
	private Canvas canvas;
	public KeyManager keyManager;
	public MouseManager mouseManager;
	public String name = "MISSING WINDOW NAME";
	
	List<SimpleWindowEvent> listeners;
	
	public SimpleWindow(){
		fps = maxFPS;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		listeners = new ArrayList<>();
	}
	
	private void init(){
		frame = new JFrame( name );
		//frame.setUndecorated(true);
		frame.setSize(width, height);
		//This closes the program gracefully
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				running = false;
			}
		});
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
	
	public void addSimpleWindowEvent(SimpleWindowEvent listener) { listeners.add(listener); }
	
	private BufferStrategy bs;
	private Graphics g;
	
	void tar(){
		long startTime = System.nanoTime();
		bs = canvas.getBufferStrategy();
		if(bs == null){
			canvas.createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		
		//Clear Screen
		g.clearRect(0, 0, width, height);
		g.setColor(new Color(18,20,21));
		g.fillRect(0, 0, width, height);
		//Draw Here!
		
		Rendering.Graphics gg = new Rendering.Graphics((Graphics2D) g);
		gg.setDim(width, height);
		
		width = canvas.getWidth();
		height = canvas.getHeight();
		keyManager.tick();
		
		if(KeyManager.keyRelease(KeyEvent.VK_EQUALS) & devMode<1) devMode++;
		if(KeyManager.keyRelease(KeyEvent.VK_MINUS) & devMode>0) devMode--;
		for(SimpleWindowEvent listener:listeners) listener.tar(gg);
		tickTime = System.nanoTime() - startTime;
		
		//Drawing debug
		
		if(devMode>0) {
			g.setColor(Color.green);
			gg.setFont( "Serif",Font.PLAIN,10 );
			gg.fontSize = 10;
			debugMessages.add("FPS "+fps);
			debugMessages.add("TAR Time: "+tarTime/1_000_000.0+"ms");
			for(int x=0;x<debugMessages.size();x++)
				g.drawString(debugMessages.get(x),0, (x+1)*gg.fontSize);
		}
		//End Drawing!
		bs.show();
		g.dispose();
		debugMessages = new ArrayList<String>();
		tarTime = System.nanoTime() - startTime;
	}
	static long tickTime = 0, tarTime = 0;
	public void run(){
		init();
		int ticks = 0;
		double timePerTick = 1000000000 / maxFPS, delta = 0;
		long now, lastTime = System.nanoTime(), timer = 0;
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			if(delta >= 1){
				tar();
				ticks++;
				delta--;
			}
			if(timer >= 1000000000){
				fps=ticks;
				ticks = 0;
				timer = 0;
			}
		}
		System.exit(0);
	}
}