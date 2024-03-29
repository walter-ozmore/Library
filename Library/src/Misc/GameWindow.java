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

import Events.GameWindowEvent;
import Events.KeyTypedEvent;
import Misc.Assets;
import Misc.KeyManager;
import Misc.MouseManager;

public class GameWindow extends Thread {
	
	boolean running = true;
	public int width=500, height=500, devMode = 0, maxFPS = 120;
	public double fps=0;
	public ArrayList<String> debugMessages = new ArrayList<String>();
	
	public JFrame frame;
	private Canvas canvas;
	public KeyManager keyManager;
	public MouseManager mouseManager;
	public String name = "MISSING WINDOW NAME";
	
	List<GameWindowEvent> listeners;
	
	public GameWindow(){
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
	
	public void addGameWindowEvent(GameWindowEvent listener) { listeners.add(listener); }
	
	void tick(){
		long startTime = System.nanoTime();
		width = canvas.getWidth();
		height = canvas.getHeight();
		keyManager.tick();
		
		if(KeyManager.keyRelease(KeyEvent.VK_EQUALS) & devMode<1) devMode++;
		if(KeyManager.keyRelease(KeyEvent.VK_MINUS) & devMode>0) devMode--;
		//stateManager.tick();
		for(GameWindowEvent listener:listeners) listener.tick();
		tickTime = System.nanoTime() - startTime;
	}
	
	private BufferStrategy bs;
	private Graphics g;
	
	void render(){
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
		
		//Drawing fps
		
		for(GameWindowEvent listener:listeners) listener.render(gg);
		
		if(devMode>0) {
			g.setColor(Color.green);
			g.setFont( new Font("Serif",Font.PLAIN,15) );
			debugMessages.add("FPS "+fps);
			debugMessages.add("Tick Time: "+tickTime/1_000_000.0+"ms");
			debugMessages.add("Render Time: "+renderTime/1_000_000.0+"ms");
			for(int x=0;x<debugMessages.size();x++)
				g.drawString(debugMessages.get(x),0, (x+1)*15);
//			g.drawString("Dev Mode "+devMode,0, height-20);
		}
		//End Drawing!
		bs.show();
		g.dispose();
		debugMessages = new ArrayList<String>();
		renderTime = System.nanoTime() - startTime;
	}
	static long tickTime = 0, renderTime = 0;
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
				tick();
				render();
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