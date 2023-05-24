package application;

import java.awt.Color;
import java.awt.Graphics;

/*
 * A cso grafikus megjelenitese.
 */
public class PipeView extends FieldView {
	private static final long serialVersionUID = 1L;
	private Field pipe;
	private int x2 = 0;
	private int y2 = 0;
	
	/*
	 * Konstruktor, ami a listaba mindig elore teszi az uj elemet
	 * a helyes kirajzolasi sorrend miatt.
	 */
	public PipeView(Field pipe) {
		this.pipe = pipe;
		Game.getFieldViewList().add(0, this);
		Game.getFieldMap().put(pipe, this);
	}
	
	/*
	 * A cso kirajzolasa.
	 */
	@Override
	public void display(Graphics g) {
		g.setColor(Color.GREEN);
		
		if (pipe.fieldIsSlippery()) {
			g.setColor(Color.BLUE);
		} else if (pipe.fieldIsSticky()) {
			g.setColor(Color.MAGENTA);
		}
		
		if (pipe.getNeighbors().size() == 2) {
			x = pipe.getNeighbors().get(0).getView().x;
			y = pipe.getNeighbors().get(0).getView().y;
			
			x2 = pipe.getNeighbors().get(1).getView().x;
			y2 = pipe.getNeighbors().get(1).getView().y;
		} else if (pipe.getNeighbors().size() == 1) {
			g.setColor(Color.LIGHT_GRAY);
			x = pipe.getNeighbors().get(0).getView().x;
			y = pipe.getNeighbors().get(0).getView().y;
			
			x2 = x + 30;
			y2 = y - 30;
			
			for (Player p : Game.getPlayerList()) {
				if (p.showPipeInHand() == pipe) {
					x2 = p.getView().x;
					y2 = p.getView().y - 15;
					break;
				}
			}
		} else if (pipe.getNeighbors().size() == 0) {
			g.setColor(Color.LIGHT_GRAY);
			boolean firstHasFound = false;
			for (Player p : Game.getPlayerList()) {
				if (!firstHasFound && p.showPipeInHand() == pipe) {
					x = p.getView().x;
					y = p.getView().y - 15;
					firstHasFound = true;
					continue;
				}
				
				if (firstHasFound && p.showPipeInHand() == pipe) {
					x2 = p.getView().x;
					y2 = p.getView().y - 15;
					break;
				}
			}
		}
		
		
		g.drawLine(x, y, x2, y2);
		
		if (pipe.isDamaged()) {
			g.setColor(Color.RED);
		}
		g.drawString(pipe.toString(), (x + x2) / 2, (y + y2) / 2);
	}

	/*
	 * A logikai cso getterje.
	 */
	public Field getPipe() {
		return pipe;
	}
	
	/*
	 * Itt a szamitott kozeppontot adja vissza, azaz annak az x koordinatajat.
	 */
	@Override
	public int getXRef() {
		return (x + x2) / 2;
	}
	
	/*
	 * Itt a szamitott kozeppontot adja vissza, azaz annak az y koordinatajat.
	 */
	@Override
	public int getYRef() {
		return (y + y2) / 2;
	}
}
