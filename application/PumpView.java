package application;

import java.awt.Color;
import java.awt.Graphics;

/*
 * A pumpa grafikus osztalya.
 */
public class PumpView extends FieldView {
	private static final long serialVersionUID = 1L;
	private Field pump;
	
	/*
	 * Konstruktor.
	 */
	public PumpView(Field pump, int x, int y) {
		this.pump = pump;
		Game.getFieldViewList().add(this);
		Game.getFieldMap().put(pump, this);
		this.move(x, y);
	}

	/*
	 * A logikai pumpa getterje.
	 */
	public Field getPump() {
		return pump;
	}

	/*
	 * Kirajzolas.
	 */
	@Override
	public void display(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x - 10, y - 10, 20, 20);
		
		if (pump.isDamaged()) {
			g.setColor(Color.RED);
		}

		g.drawString(pump.toString(), x - 20, y + 20);
	}	
}
