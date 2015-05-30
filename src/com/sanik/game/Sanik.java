package com.sanik.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Sanik extends Canvas implements Runnable {
	
	//Fields
	private static final int HEIGHT = 600;
	private static final int WIDTH = 800;
	private static final String TITLE = "Sanik Speyd Advantur";
	private static Sanik game = new Sanik();
	
	private boolean running = false;
	private Thread t1;
	private Renderer gfx;
	
	//Main method
	public static void main(String[] args){
		
		game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		game.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		game.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		
		JFrame frame = new JFrame(TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}
	
	//Constructor
	public Sanik(){
		
		
	}
	
	public static Sanik getInstance(){
		
		return game;
	}
	
	private synchronized void start(){
		
		if(running)
			return;
		
		running = true;
		t1 = new Thread(this);
		t1.start();
	}
	
	private synchronized void stop(){
		
		if(!running)
			return;
		
		running = false;
		try{
			
			t1.join();
		}catch(InterruptedException e){
			
			e.printStackTrace();
		}
		
		System.exit(1);
	}
	
	private void init(){
		
		gfx = new Renderer();
	}
	
	private void tick(){
		
	}
	
	private void render(){
		
		BufferStrategy b = this.getBufferStrategy();
		
		if(b == null){
			
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = b.getDrawGraphics();
		g.setColor(new Color(10, 10, 240));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		gfx.renderBackground(g);
		gfx.renderForeground(g);
		
		g.dispose();
		b.show();
	}

	//Run method
	public void run() {
		
		init();
		
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0.0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		while(running){
			
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			if(delta >= 1){
				
				tick();
				updates++;
				delta--;
			}
			
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				
				timer += 1000;
				System.out.println(updates + " Ticks, FPS: " + frames);
				updates = 0;
				frames = 0;
			}
		}
		
		stop();
	}
}
