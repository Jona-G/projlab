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
		g.fillRect(x, y, 20, 20);
		g.drawString(source.toString(), x, y + 30);
	}

	public Source getSource() {
		return source;
	}	
}
