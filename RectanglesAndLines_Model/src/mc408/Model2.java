package mc408;

import java.util.Observable;
import java.util.Vector;

public class Model2 extends Observable {
	
	private Vector<RLRectangle> rectangles = new Vector<RLRectangle>();
	private Vector<RLLine> lines = new Vector<RLLine>();
	private Vector<RLObject> rlObjects = new Vector<RLObject>();
	
	private RLLine currentLine;
	

	public void addRectangle(RLRectangle r) {
		rectangles.add(r);
		rlObjects.add(r);
		this.setChanged();
		this.notifyObservers();
	}
	
	public void addLine(RLLine l) {
		lines.add(l);
		rlObjects.add(l);
		this.setChanged();
		this.notifyObservers();
	}
	
	public Vector<RLObject> getRLObjects() {
		return rlObjects;
	}
	
	public RLRectangle getRectangle(int x, int y) {
		for (RLRectangle r: rectangles) {
			if (r.containsPoint(x, y)) {
				return r;
			}
		}
		return null;
	}
	
	public void removeRectange(RLRectangle r){
		rectangles.remove(r);
		rlObjects.remove(r);
		this.setChanged();
		this.notifyObservers();
	}
	
	public void addCurrentLine(int x, int y) {
		currentLine = new RLLine(x, y, x, y);
	}
	
	public void updateCurrentLine(int x, int y) {
		currentLine.setEnd(x, y);
		this.setChanged();
		this.notifyObservers();
	}
	
	public RLLine getCurrentLine() {
		return currentLine;
	}
	
	public void removeCurrentLine() {
		currentLine = null;
		this.setChanged();
		this.notifyObservers();
	}
}
