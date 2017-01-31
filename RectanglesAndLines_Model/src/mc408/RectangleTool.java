package mc408;

import java.awt.event.MouseEvent;

public class RectangleTool implements Tool {
	
	private Model model;
	
	private volatile int screenX = 0;
	  private volatile int screenY = 0;
	  private volatile int myX = 0;
	  private volatile int myY = 0;

	public RectangleTool(Model model) {
		this.model = model;
	}

	public void mouseClicked(MouseEvent e) {
		RLRectangle r = new RLRectangle(e.getX(), e.getY(), 40, 40);
		model.addRectangle(r);
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
        RLRectangle r;
        
//        while( e.MOUSE_DRAGGED == 506){
//        	System.out.println("True");
//        	if ((r = model.getRectangle(e.getX(), e.getY())) != null) {
//            	model.removeRectange(r);
//
//            }
//        	if(e.MOUSE_RELEASED == 502){
//        		break;
//        	}
//        }
//        r = new RLRectangle(myX +deltaX, myY + deltaY, 40, 40);
//        model.addRectangle(r);
//        model.removeRectange(r);
        
        
        //setLocation(myX + deltaX, myY + deltaY);
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
