package application;

import java.awt.Graphics;
import java.io.Serializable;

public abstract class PlayerView implements Serializable {
	private static final long serialVersionUID = 1L;
	protected int x = 0;
	protected int y = 0;

	public abstract Player returnPlayer();
	
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public abstract void display(Graphics g);
	
	public abstract void megkerdezik();
	
	public abstract void cselekszik(int number);
	
	public void pickupPipe(int validIndex) {
		
	}
}
