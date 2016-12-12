import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Panel extends java.awt.Panel implements MouseListener, MouseMotionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Rectangle r;
	int state;
	private static final int State_rect = 0;
	private static final int State_line = 1;
	private static final int State_Comm = 2;
	Line curlLine;
	
	private java.util.List<Rectangle> rectangles;
	private java.util.List<Line> lines;
	
	
	Panel(){
		state = State_rect;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		rectangles = new java.util.ArrayList<Rectangle>();
		lines = new java.util.ArrayList<Line>();
		r = new Rectangle(0,0);
		
	}

	public void mouseClicked(MouseEvent e) {
		if (state  == State_rect){
			Rectangle r = new Rectangle(e.getX(), e.getY());
			System.out.println(e.getX()+" rect "+e.getY());
			rectangles.add(r);
			this.repaint();
		}else if (curlLine == null && state == State_line){
			curlLine = new Line();
			if (rectangles != null){
				for (Rectangle r: rectangles){
					if( e.getX() >= r.getX() && e.getX() <= (r.getX()+100) ){
						if(e.getY() >= r.getY() && e.getY() <= (r.getY()+100)){
							int x = (r.getX()+(r.getX()+100))/2;
							int y = (r.getY()+(r.getY()+100))/2;
							curlLine.setOrigin(x, y);
						}
					}
				}
			}
		}else if ( curlLine != null && state == State_line){
			lines.add(curlLine);
			curlLine = null;
		}else if( state == State_Comm){
			if (rectangles != null){
				for (Rectangle r: rectangles){
					if( e.getX() >= r.getX() && e.getX() <= (r.getX()+100) ){
						if(e.getY() >= r.getY() && e.getY() <= (r.getY()+100)){
							System.out.println(e.getXOnScreen()+" getting "+ e.getYOnScreen());
						}
					}
				}
			}
		}
		
	}
	
	public void setRectangleMode(){
		state = State_rect;
	}
	
	public void setLineMode(){
		state = State_line;
	}
	
	public void setCommentMode(){
		state = State_Comm;
	}
	
	public void paint(Graphics g){
		for (Rectangle r: rectangles){
			g.drawRect(r.getX(),r.getY(),100, 100);
		}
		
		for (Line l: lines){
			g.drawLine(l.getX1(), l.getY1(), l.getX2(),l.getY2());
		}
		
		if( curlLine != null && state == State_line){
			g.drawLine(curlLine.getX1(), curlLine.getY1(), curlLine.getX2(), curlLine.getY2());
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
//		System.out.println(e.getX()+" "+ e.getY());
////		if (curlLine != null && )
//		for( Rectangle r: rectangles){
//			if(e.getX() == r.getX() && e.getY() == r.getY()){
//				
//			}
//		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (curlLine != null && state == State_line){
			if (rectangles != null){
				for (Rectangle r: rectangles){
					if( e.getX() >= r.getX() && e.getX() <= (r.getX()+100) ){
						if(e.getY() >= r.getY() && e.getY() <= (r.getY()+100)){
							int x = (r.getX()+(r.getX()+100))/2;
							int y = (r.getY()+(r.getY()+100))/2;
							curlLine.setDest(x, y);
							this.repaint();
						}
					}
				}
			}
			
		}
		
	}
	
}
