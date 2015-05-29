package com.game.window;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class LevelOne {

	private int fontSize = 48;
	
	
	public LevelOne() {
		
		
		
	}
	
	
	public void createLevel(Graphics g){
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman", Font.BOLD, fontSize));
		g.drawString("LEVEL 1", 295, 125);
		
		
		
	}


	

}
