package com.sanik.game;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Sanik extends Canvas implements Runnable {

	//Fields
	private static final int HEIGHT = 600;
	private static final int WIDTH = 800;
	private static final String TITLE = "Sanik Speyd Advantur";
	
	private boolean running = false;
	private Thread t1;
	
	//Main method
	public static void main(String[] args){
		
		Sanik game = new Sanik();
		
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
		
	}
	
	private void tick(){
		
	}
	
	private void render(){
		
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
