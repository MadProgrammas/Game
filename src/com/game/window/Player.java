package com.game.window;


import java.awt.Color;
import java.awt.Graphics;

public class Player {
	public int charX;
	public int charY;
	public int xdirec;
	public int ydirec;
	public int plX;
	public int plY;
	
	

	public Player(int charXdir, int charYdir) {
		charX = charXdir;
		charY = charYdir;
		plX = 400;
		plY = 300;
	}

	public void paintPlayer(Graphics g) {
		g.setColor(Color.BLUE);
		//g.fillRect(charX, charY, 30, 30);

	}

	public void setXDirection(int xdir) {

		xdirec = xdir;
	}

	public void setYDirection(int ydir) {

		ydirec = ydir;
	}

	public void move() {

		charX += xdirec;
		charY += ydirec;

		if (charX <= 0) {

			charX = 0;
		}
		if (charX >= 775) {

			charX = 775;
		}
		if (charY <= 22) {

			charY = 22;
		}
		if (charY >= 570) {

			charY = 570;
		}
	}

}
