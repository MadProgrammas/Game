package Main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import GameState.GameStateManager;

public class GamePanel extends JPanel implements Runnable, KeyListener {

	//Fields
	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	public static final int SCALE = 2;
	
	private Thread t1;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;
	
	private BufferedImage image;
	private Graphics2D g;
	
	private GameStateManager gsm;
	
	//Constructor
	public GamePanel(){
		
		super();
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify(){
		
		super.addNotify();
		
		if(t1 == null){
			
			t1 = new Thread(this);
			addKeyListener(this);
			t1.start();
		}
	}
	
	private void init(){
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		
		running = true;
		
		gsm = new GameStateManager();
	}
	
	public void run(){
		
		init();
		
		long start;
		long elapsed;
		long wait;
		
		while(running){
			
			start = System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime();
			wait = targetTime - elapsed / 1000000;
			
			try{
				t1.sleep(wait);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	private void update(){
		
		gsm.update();
	}
	
	private void draw(){
		
		gsm.draw(g);
	}
	
	private void drawToScreen(){
		
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}
	
	public void keyTyped(KeyEvent e){}
	public void keyPressed(KeyEvent e){
		
		gsm.keyPressed(e.getKeyCode());
	}
	public void keyReleased(KeyEvent e){
		
		gsm.keyReleased(e.getKeyCode());
	}
	
	
	
}
