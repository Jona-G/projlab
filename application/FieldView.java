package application;

import java.awt.Graphics;
import java.io.Serializable;

/*
 * A mezo absztrakt grafikai megfeleloje.
 */
public abstract class FieldView implements Serializable {
	private static final long serialVersionUID = 1L;
	protected int x = 0;
	protected int y = 0;
	
	/*
	 * A mezo tarolt x, y koordinatajanak atirasa,
	 * ez meg nem helyezi at a mezot, de a kirajzolas ez alapjan tortenik.
	 */
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/*
	 * Polimorfikus foggveny, visszaadja az x koordinatat, de van olyan override, ahol ez egy szamitott ertek, mondjuk az alakzat kozepe.
	 */
	public int getXRef() {
		return x;
	}
	
	/*
	 * Polimorfikus foggveny, visszaadja az y koordinatat, de van olyan override, ahol ez egy szamitott ertek, mondjuk az alakzat kozepe.
	 */
	public int getYRef() {
		return y;
	}
	
	/*
	 * Kirajzolas.
	 */
	public abstract void display(Graphics g);

}
