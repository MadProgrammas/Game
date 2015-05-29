package com.game.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
 

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;





public class Main extends JFrame implements Runnable {
	Image dbi;
	Graphics dbg;
	private int fontSize = 76;
	static private String TITLE = "sanik speyd advantur";
	private int letX = 45;
	private int letY = 420;
	private boolean startGame = false;
	Player p = new Player(400, 300);
	LevelOne lo = new LevelOne();
	private boolean test = false;
	
	//Here
	private boolean startlevel = true;
	private boolean mainMenu = false;
	
	
	ImageIcon icon = new ImageIcon(getClass().getResource("/bg.png"));
	ImageIcon per = new ImageIcon(getClass().getResource("/char.png"));
	public static void main(String args[]) {
		Main start = new Main();
		Thread t1 = new Thread(start);
		t1.start();
		
	}

	public Main() {
		setTitle(TITLE);
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		addKeyListener(new KB());

		
	}

	public void paint(Graphics g) {
		dbi = createImage(getWidth(), getHeight());
		dbg = dbi.getGraphics();
		paintComp(dbg);
		g.drawImage(dbi, 0, 0, this);

	}

	public void paintComp(Graphics g) {
		Image toast = icon.getImage();
		Image guy = per.getImage();
		// Background
		if(mainMenu){
			g.drawImage(toast, 0, 0, this);
			// sanik speyd
			g.setFont(new Font("TimesRoman", Font.BOLD, fontSize));
			g.setColor(Color.BLACK);
			g.drawString("sanik speyd advantur", letX, letY);
			// Test
			
			// Level Start
			// Player
			p.paintPlayer(g);
			g.drawImage(guy, p.charX, p.charY, this);
			
		}
			if (startlevel == true && mainMenu == false) {
				lo.createLevel(g);
			}
		repaint();
	}

	public class KB extends KeyAdapter {

		public void keyPressed(KeyEvent e) {

			int kc = e.getKeyCode();

			// Controls for Player 1
			if(kc == e.VK_P){
				musik();
				
			}
			if (kc == e.VK_W) {

				p.setYDirection(-8);
			}
			if (kc == e.VK_S) {

				p.setYDirection(+8);
			}
			if (kc == e.VK_A) {

				p.setXDirection(-8);
			}
			if (kc == e.VK_D) {

				p.setXDirection(+8);
			}
			if (kc == e.VK_SPACE && startlevel == false && mainMenu == true) {
				startlevel = true;
				mainMenu = false;
				p.charX = 400;
				p.charY = 300;

			}
		}

		public void keyReleased(KeyEvent e) {

			int kc = e.getKeyCode();

			// Controls for Player 1
			if (kc == e.VK_W) {

				p.setYDirection(0);
			}
			if (kc == e.VK_S) {

				p.setYDirection(0);
			}
			if (kc == e.VK_A) {

				p.setXDirection(0);
			}
			if (kc == e.VK_D) {

				p.setXDirection(0);
			}

		}
	}
	
	
	public void musik(){
		try{
			InputStream blacks = getClass().getResourceAsStream("/song.wav");
			AudioStream whites = new AudioStream(blacks);
			AudioPlayer.player.start(whites);
		}catch(Exception e){
			
			
		}
		
	}

	@Override
	public void run() {
		try {
			while (true) {
				p.move();

				if (startlevel == true) {

				}
				Thread.sleep(10);
			}
		} catch (InterruptedException e) {
			System.out.println("Sux");
			e.printStackTrace();
		}
	}

}
