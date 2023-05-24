package application;

import java.awt.Color;
import java.awt.Graphics;

public class CisternView extends FieldView {
	private static final long serialVersionUID = 1L;
	private Cistern cistern;
	
	public CisternView(Cistern cistern, int x, int y) {
		this.cistern = cistern;
		Game.getFieldViewList().add(this);
		Game.getFieldMap().put(cistern, this);
		this.move(x, y);
	}
	
	public void display(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x - 10, y - 10, 20, 20);
		g.drawString(cistern.toString(), x - 20, y + 20);
	}


	public Cistern getCistern() {
		return cistern;
	}	

}
