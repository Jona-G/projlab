package application;

import java.awt.Graphics;
import java.io.Serializable;

/*
 * A jatekos absztrakt grafikus megfeleloje.
 */
public abstract class PlayerView implements Serializable {
	private static final long serialVersionUID = 1L;
	protected int x = 0;
	protected int y = 0;

	/*
	 * Visszaadja a logikai jatekos referenciat.
	 */
	public abstract Player returnPlayer();
	
	/*
	 * Atallitja az x es y tagvaltozot, ezek alapjan tortenik a kirajzolas.
	 */
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/*
	 * Kirajzolas.
	 */
	public abstract void display(Graphics g);
	
	/*
	 * A megkerdezes szoveget irja ki.
	 */
	public abstract void megkerdezik();
	
	/*
	 * Szam alapjan cselekszik.
	 */
	public abstract void cselekszik(int number);
	
	/*
	 * Felvesz egy csovet.
	 */
	public void pickupPipe(int validIndex) {
		
	}
}
