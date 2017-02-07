

import java.awt.event.MouseEvent;

public class ConnectionTool implements Tool {

	Model model;
	RLRectangle firstSelectedRect;
	RLRectangle secondSelectedRect;
	
	public ConnectionTool(Model model) {
		this.model = model;
		
	}
	
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		RLRectangle r;
		if ((r = model.getRectangle(e.getX(), e.getY())) != null) {
			if (firstSelectedRect == null) {
				firstSelectedRect = r;
				secondSelectedRect = null;
				
				model.addCurrentLine(firstSelectedRect.getConnectionPointX(), firstSelectedRect.getConnectionPointY());
				model.updateCurrentLine(e.getX(), e.getY());
			
			} else {
				secondSelectedRect = r;
				if (firstSelectedRect != secondSelectedRect) {
					RLLine line = new RLLine(
							firstSelectedRect.getConnectionPointX(),
							firstSelectedRect.getConnectionPointY(),
							secondSelectedRect.getConnectionPointX(),
							secondSelectedRect.getConnectionPointY()
					);
					
					model.removeCurrentLine();
					model.addLine(line);
					firstSelectedRect = null;
				}
			}
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (firstSelectedRect != null) {
			model.updateCurrentLine(e.getX(), e.getY());
		}
	}

}
