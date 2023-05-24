package application;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/*
 * A kirajzolasert felelos osztaly.
 */
public class Canvas extends JPanel {
	private static final long serialVersionUID = 1L;
	private static BufferedImage img;
	
	/*
	 * Ha a kostruktor nem talalja meg a hatteret akkor az nem okoz hibat kesobb.
	 */
	public Canvas() {
		try {
			img = ImageIO.read(new File("background.png"));
		} catch (IOException e) {
			img = null;
		}
	}
	
	/*
	 * Eloszor kirajzolja a hatteret, majd a jatekosokat, majd az elemeket, ahol mindig a lista elejen vannak a csovek,
	 * es a csovek ekkor mar az aktualis jatekos poziciokat veszik figyelembe. Ezt kovetoen a jatekosokat megint
	 * kirajzoljuk, hogy felulre keruljenek.
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (img != null) {
			g.drawImage(img, 0, 0, 800, 600, null);
		}
		
		for (PlayerView p : Game.getPlayerViewList()) {
			p.display(g);
		}
		
		for (FieldView f : Game.getFieldViewList()) {
			f.display(g);
		}
		
		for (PlayerView p : Game.getPlayerViewList()) {
			p.display(g);
		}
	}

}
