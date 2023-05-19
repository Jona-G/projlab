package application;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Canvas extends JPanel {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		for (FieldView f : Game.getFieldViewList()) {
			f.display(g);
		}
		
		for (PlayerView p : Game.getPlayerViewList()) {
			p.display(g);
		}
	}

}
