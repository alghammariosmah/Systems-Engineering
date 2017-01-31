package mc408;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

public class CommentTool implements Tool {
	private volatile int screenX = 0;
	  private volatile int screenY = 0;
	  private volatile int myX = 0;
	  private volatile int myY = 0;
	
	private Model model;
	RLRectangle selectedRect;

	public CommentTool(Model model) {
		this.model = model;
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		RLRectangle r;
		if ((r = model.getRectangle(e.getX(), e.getY())) != null) {
			screenX = e.getXOnScreen();
		    screenY = e.getYOnScreen();
		    
		    myX = r.getConnectionPointX();
		    myY = r.getConnectionPointY();
		}
		 
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int deltaX = e.getXOnScreen() - screenX;
        int deltaY = e.getYOnScreen() - screenY;

        System.out.println("It is draggged");
        //setLocation(myX + deltaX, myY + deltaY);
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	

}
