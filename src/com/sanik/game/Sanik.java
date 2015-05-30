package com.sanik.game;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Sanik extends Canvas implements Runnable {

	//Fields
	private static final int HEIGHT = 800;
	private static final int WIDTH = 600;
	private static final int SCALE = 2;
	private static final String TITLE = "Sanik Speyd Advantur";
	
	//Main method
	public static void main(String[] args){
		
		Sanik game = new Sanik();
		
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		JFrame frame = new JFrame();
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	//Constructor
	public Sanik(){
		
		
	}

	//Run method
	public void run() {
		
		
	}
}
