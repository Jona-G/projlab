package application;

import java.awt.Color;
import java.awt.Graphics;

public class SourceView extends FieldView {
	private static final long serialVersionUID = 1L;
	private Source source;
	
	public SourceView(Source source, int x, int y) {
		this.source = source;
		Game.getFieldViewList().add(this);
		Game.getFieldMap().put(source, this);
		this.move(x, y);
	}

	public void display(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x - 10, y - 10, 20, 20);
		g.drawString(source.toString(), x - 20, y + 20);
	}

	public Source getSource() {
		return source;
	}	
}
