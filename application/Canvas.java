package application;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Canvas extends JPanel {
	private static final long serialVersionUID = 1L;
	private static BufferedImage img;
	
	public Canvas() {
		try {
			img = ImageIO.read(new File("background.png"));
		} catch (IOException e) {
			img = null;
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (img != null) {
			g.drawImage(img, 0, 0, 800, 600, null);
		}
		
		for (FieldView f : Game.getFieldViewList()) {
			f.display(g);
		}
		
		for (PlayerView p : Game.getPlayerViewList()) {
			p.display(g);
		}
	}

}
