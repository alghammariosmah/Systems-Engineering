package mc408;

import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class OverViewPanel extends JPanel implements Observer{
	private Vector<RLObject> rlObjects;
	private RLLine oldLine;
	private RLLine currentLine;
	
	public OverViewPanel(MouseListener mouseListener, MouseMotionListener mouseMotionListener){
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseMotionListener);
		
	}
	
	public void update(Observable o, Object arg) {
		rlObjects = ((Model)o).getRLObjects();
		this.repaint();	
	} 
	
	public void paint(Graphics g)  {
//		if (rlObjects != null) {
//			for (RLObject rlo : rlObjects) {
//				rlo.scalingDraw(g);
//				
//			}
//		}
		
		g.drawRect(10,  10,  10,  10);
	} 	

}
