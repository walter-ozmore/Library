package Misc;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class GameWindow {
	
	public static int width=500, height=500, devMode = 0, maxFPS = 120;
	public static double fps=0;
	public static ArrayList<String> debugMessages = new ArrayList<String>();
	
	private JFrame frame;
	private Canvas canvas;
	public static KeyManager keyManager;
	public static MouseManager mouseManager;
	
	//Starts the program
	public static void main(String args[]) throws FileNotFoundException, IOException {
		//Commands commands = new Commands(); commands.start();
		main = new Main2();
		main.start();
	}
	
	public Main2(){
		fps = maxFPS;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		//mouseManager.addMouseWheelEvent(new StateManager());
		//keyManager.addKeyTypedEvent(new StateManager());
		Assets.init();
	}
	
	private void init(){
		frame = new JFrame("Dickson's Game");
		frame.setSize(width, height);
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
		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		width=canvas.getWidth();
		height=canvas.getHeight();
		
		stateManager=new StateManager();
	}
	
	private void tick(){
		Main.width = width = canvas.getWidth();
		Main.height = height = canvas.getHeight();
		keyManager.tick();
		
		if(KeyManager.keyRelease(KeyEvent.VK_EQUALS) & devMode<1) devMode++;
		if(KeyManager.keyRelease(KeyEvent.VK_MINUS) & devMode>0) devMode--;
		stateManager.tick();
	}
	
	private BufferStrategy bs;
	private Graphics g;
	
	private void render(){
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
		
		Graphics2D g2d=(Graphics2D) g;
		
		stateManager.render( new Misc.Graphics(g2d) );
		
		//Drawing fps
		if(devMode>0) {
			g.setColor(Color.green);
			g.setFont( new Font("Serif",Font.PLAIN,15) );
			debugMessages.add("FPS "+fps);
			debugMessages.add("Tick Time: "+tickTime/1_000_000.0+"ms");
			debugMessages.add("Render Time: "+renderTime/1_000_000.0+"ms");
			for(int x=0;x<debugMessages.size();x++) {
				g.drawString(debugMessages.get(x),0, (x+1)*15);
			}
			g.drawString("Dev Mode "+devMode,0, height-20);
		}
		//End Drawing!
		bs.show();
		g.dispose();
		debugMessages = new ArrayList<String>();
	}
	long tickTime = 0, renderTime = 0;
	public void run(){
		init();
		//Tick
		boolean tickThread = true;
		if(tickThread) {
			new Thread( new Runnable() {
				public void run() {
					int ticks = 0;
					double timePerTick = 1000000000 / maxFPS;
					double delta = 0;
					long now;
					long lastTime = System.nanoTime();
					long timer = 0;
					while(true){
						now = System.nanoTime();
						delta += (now - lastTime) / timePerTick;
						timer += now - lastTime;
						lastTime = now;
						if(delta >= 1){
							tick();
							ticks++;
							delta--;
						}
						if(timer >= 1000000000){
							//fps=ticks;
							ticks = 0;
							timer = 0;
						}
					}
				}
			}).start();
		}else {
			new Thread( new Runnable() {
				public void run() {
					while(true) {
						long startTime = System.nanoTime();
						tick();
						tickTime = System.nanoTime() - startTime;
					}
				}
			}).start();
		}
		new Thread( new Runnable() {
			public void run() {
				long frames = 0;
				long lastTime = System.nanoTime();
				while(true) {
					long startTime = System.nanoTime();
					render();
					frames++;
					if((System.nanoTime()-lastTime)/1_000_000_000 >= 1) {
						fps = frames;
						frames = 0;
						lastTime = System.nanoTime();
					}
					renderTime = System.nanoTime() - startTime;
				}
			}
		}).start();
		
	}
}
