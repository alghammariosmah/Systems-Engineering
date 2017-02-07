

import java.awt.Graphics;

public class RLRectangle implements RLObject {
	
	private java.awt.Rectangle rect;
	int x1,y1;
	
	public RLRectangle(int x, int y, int width, int height) {
		rect = new java.awt.Rectangle(x, y, width, height);
	}
	
	public boolean containsPoint(int x, int y) {
		return rect.contains(x, y);
	}
	
	public int getConnectionPointX() {
		return (int)rect.getCenterX();
	}
	
	public int getConnectionPointY() {
		return (int)rect.getCenterY();
	}

	public void draw(Graphics g) {
		g.drawRect(rect.x, rect.y, rect.width, rect.height);
	}
	
	public void scalingDraw(Graphics g){
		g.drawRect(rect.x/4, rect.y/4,  10,  10);
	}

}
