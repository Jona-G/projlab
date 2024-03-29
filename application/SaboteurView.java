package application;

import java.awt.Color;
import java.awt.Graphics;

/*
 * A szabotor grafikus osztalya.
 */
public class SaboteurView extends PlayerView {
	private static final long serialVersionUID = 1L;
	private Saboteur saboteur;
	
	/*
	 * Konstruktor.
	 */
	public SaboteurView(Saboteur saboteur) {
		this.saboteur = saboteur;
		Game.getPlayerViewList().add(this);
		Game.getPlayerMap().put(saboteur, this);
	}

	/*
	 * Getter.
	 */
	public Saboteur getSaboteur() {
		return saboteur;
	}
	
	/*
	 * Getter.
	 */
	@Override
	public Player returnPlayer() {
		return saboteur;
	}
	
	/*
	 * Kirajzolas.
	 */
	@Override
	public void display(Graphics g) {
		g.setColor(Color.BLACK);
		
		if (saboteur.getParalyzedTime() > 0) {
			g.setColor(Color.MAGENTA);
		}
		
		x = saboteur.getLocation().getView().getXRef();
		y = saboteur.getLocation().getView().getYRef();
		g.drawString(Integer.toString(Game.getSaboteurScore()), 460, 20);
		g.fillRect(x - 5, y - 30, 10, 30);
		g.setColor(Color.BLACK);
		g.drawString(saboteur.toString(), x - 20, y - 40);
	}
	
	/*
	 * Kiirja a valasztasi lehetosegeket.
	 */
	@Override
	public void megkerdezik() {
		saboteur.step();
		String elso = "=====> Mit szeretne csinalni a " + saboteur.toString() + "?";
		Game.print(elso);
		Game.println("1 Lepni valahova.");
		Game.println("2 Kilyukasztani a mezot, amin all. (Csak csonel lesz ennek eredmenye.)");
		Game.println("3 Szivofejet allitani. (Csak pumpanal lesz ennek eredmenye.)");
		Game.println("4 Kimenofejet allitani. (Csak pumpanal lesz ennek eredmenye.)");
		Game.println("5 Ragadossa teszi a mezot, amin all. (Csak csonel lesz ennek eredmenye.)");
		Game.println("6 Csuszossa teszi a mezot, amin all. (Csak csonel lesz ennek eredmenye.)");
		Game.println("egyeb Ne csinaljon semmit.");
	}
	
	/*
	 * A szam alapjan cselekszik.
	 */
	@Override
	public void cselekszik(int answer) {
		if (answer == 1) {
			if (!Game.printNeighbors(saboteur, "=====> Hova szeretne lepni?", "Nincs hova lepni...")) {
				return;
			}
			Game.state = 31;
		} else if (answer == 2) {
			saboteur.puncture();
			Game.rollToNext();
		} else if (answer == 3) {
			if (!Game.printNeighbors(saboteur, "=====> Mihez csatlakozzon a szivofej?", "Nincs mihez csatlakoznia...")) {
				return;
			}
			Game.state = 32;
		} else if (answer == 4) {
			if (!Game.printNeighbors(saboteur, "=====> Mihez csatlakozzon a kimenofej?", "Nincs mihez csatlakoznia...")) {
				return;
			}
			Game.state = 33;
		} else if (answer == 5) {
			saboteur.tryToMakeSticky();
			Game.rollToNext();
		} else if (answer == 6) {
			saboteur.tryToMakeSlippery();
			Game.rollToNext();
		} else {
			// Do nothing...
			Game.rollToNext();
		}
	}
}
