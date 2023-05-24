package application;

import java.awt.Color;
import java.awt.Graphics;

/*
 * A ciszterna grafikus osztalya.
 */
public class CisternView extends FieldView {
	private static final long serialVersionUID = 1L;
	private Cistern cistern;
	
	/*
	 * Konstruktor.
	 */
	public CisternView(Cistern cistern, int x, int y) {
		this.cistern = cistern;
		Game.getFieldViewList().add(this);
		Game.getFieldMap().put(cistern, this);
		this.move(x, y);
	}
	
	/*
	 * A kirajzolas feluldefinialasa.
	 */
	public void display(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x - 10, y - 10, 20, 20);
		g.drawString(cistern.toString(), x - 20, y + 20);
	}

	/*
	 * A logikai referencia lekerdezese.
	 */
	public Cistern getCistern() {
		return cistern;
	}	

}
