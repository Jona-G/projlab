package application;

import java.awt.Graphics;
import java.io.Serializable;

public abstract class FieldView implements Serializable {
	private static final long serialVersionUID = 1L;
	protected int x = 0;
	protected int y = 0;
	
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getXRef() {
		return x;
	}
	
	public int getYRef() {
		return y;
	}
	
	public abstract void display(Graphics g);

}
