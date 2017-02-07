

import java.awt.Graphics;
import java.awt.Point;

public class RLLine implements RLObject {
	
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	
	public RLLine(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public RLLine() {
		this(0, 0, 0, 0);
	}

	public void setEnd(int x, int y) {
		this.x2 = x;
		this.y2 = y;
	}
	
	public void setStart(int x, int y) {
		this.x1 = x;
		this.y1 = y;
	}

	public void draw(Graphics g) {
		g.drawLine(x1, y1, x2, y2);
	}

	@Override
	public void scalingDraw(Graphics g) {
		g.drawLine(x1/4, y1/4, x2/4, y2/4);
		
	}
}