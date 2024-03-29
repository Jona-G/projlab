package application;

import java.awt.Color;
import java.awt.Graphics;

/*
 * A forras grafikus osztalya.
 */
public class SourceView extends FieldView {
	private static final long serialVersionUID = 1L;
	private Source source;
	
	/*
	 * Konstruktor.
	 */
	public SourceView(Source source, int x, int y) {
		this.source = source;
		Game.getFieldViewList().add(this);
		Game.getFieldMap().put(source, this);
		this.move(x, y);
	}

	/*
	 * Kirajzolas.
	 */
	public void display(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x - 10, y - 10, 20, 20);
		g.drawString(source.toString(), x - 20, y + 20);
	}

	/*
	 * Getter.
	 */
	public Source getSource() {
		return source;
	}	
}
