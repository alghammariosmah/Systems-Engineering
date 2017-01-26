import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;
import java.util.List;

import javax.swing.JTextArea;



public class Panel extends java.awt.Panel implements MouseListener, MouseMotionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Rectangle r;
	private Rectangle dashedRect;
	
	int state;
	private static final int State_rect = 0;
	private static final int State_line = 1;
	private static final int State_Comm = 2;
	private static final int State_DashedLine = 3;
	private static final int State_DashedRect = 4;
	Line curlLine;
	Line dashedLine;
	
	/**
	 * 
	 */
	
	private java.util.List<Rectangle> rectangles;
	private java.util.List<Rectangle> dashedRectangles;
	
	private java.util.List<Line> lines;
	private java.util.List<Line> dashedLines;
	
	
	Panel(){
		state = State_rect;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		rectangles = new java.util.ArrayList<Rectangle>();
		dashedRectangles = new java.util.ArrayList<Rectangle>();
		
		lines = new java.util.ArrayList<Line>();
		dashedLines = new java.util.ArrayList<Line>();
		
		r = new Rectangle(0,0);
		dashedRect = new Rectangle(0,0);
		
	
		
		
	}

	public void mouseClicked(MouseEvent e) {
		if (state  == State_rect){
			Rectangle r = new Rectangle(e.getX(), e.getY());
			rectangles.add(r);
			this.repaint();
		}else if(state  == State_DashedRect){
			Rectangle dashedRect = new Rectangle(e.getX(), e.getY());
			dashedRectangles.add(dashedRect);
			this.repaint();
		}
		
		else if (curlLine == null && state == State_line){
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
		
		// Part of dashed lines
		else if (dashedLine == null && state == State_DashedLine){
			dashedLine = new Line();
			if (dashedRectangles != null){
				for (Rectangle r: dashedRectangles){
					if( e.getX() >= r.getX() && e.getX() <= (r.getX()+100) ){
						if(e.getY() >= r.getY() && e.getY() <= (r.getY()+100)){
							int x = (r.getX()+(r.getX()+100))/2;
							int y = (r.getY()+(r.getY()+100))/2;
							dashedLine.setOrigin(x, y);
														
						}
					}
				}
			}
		}else if ( dashedLine != null && state == State_DashedLine){
			dashedLines.add(dashedLine);
			dashedLine = null;	
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
	
	public void setDashedLineMode(){
		state = State_DashedLine;
	}
	
	public void setDashedRectMode(){
		state = State_DashedRect;
	}
	
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
       
        
		for (Rectangle r: rectangles){
			g.drawRect(r.getX(),r.getY(),100, 100);
			
		}
		
		for(Rectangle rect: dashedRectangles){
			drawDashedRect(g2, rect.getX(), rect.getY());
			
		}
		
		for (Line l: lines){
			g.drawLine(l.getX1(), l.getY1(), l.getX2(),l.getY2());
			drawArrowHead(g2, l.getX2(), l.getX1(), l.getY2(), l.getY1());
		}
		
		if( curlLine != null && state == State_line){
			g.drawLine(curlLine.getX1(), curlLine.getY1(), curlLine.getX2(), curlLine.getY2());
			drawArrowHead(g2, curlLine.getX2(), curlLine.getX1(), curlLine.getY2(), curlLine.getY1());
		}
		
		
		for (Line dashedL: dashedLines){
			drawDashedLine(g2, dashedL.getX1(), dashedL.getY1(), dashedL.getX2(),dashedL.getY2());
			drawArrowHead(g2, dashedL.getX2(), dashedL.getX1(), dashedL.getY2(), dashedL.getY1());
		}
		if( dashedLine != null && state == State_line){
			drawDashedLine(g2, dashedLine.getX1(), dashedLine.getY1(), dashedLine.getX2(), dashedLine.getY2());
			drawArrowHead(g2, dashedLine.getX2(), dashedLine.getX1(), dashedLine.getY2(), dashedLine.getY1());
		}
		
		
	}
	
	private void drawDashedRect(Graphics g, int x1, int y1){
		//creates a copy of the Graphics instance
        Graphics2D g2d = (Graphics2D) g;

        //set the stroke of the copy, not the original 
        Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2d.setStroke(dashed);
        g2d.drawRect(x1, y1, 100, 100);
        
        
        g2d.drawString("Dashed Rectangle", x1, y1-10);
     
	}
	
	private void drawDashedLine(Graphics g, int x1, int y1, int x2, int y2){
        //creates a copy of the Graphics instance
        Graphics2D g2d = (Graphics2D) g.create();

        //set the stroke of the copy, not the original 
        Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2d.setStroke(dashed);
        g2d.drawLine(x1, y1, x2, y2);
        
        int x = Math.abs(x1 + (x2 - x1)/2);
        int y = Math.abs(y1 + ((y2 - y1)/2));
        
        g2d.drawString("Dashed Line", x , y);

        //gets rid of the copy
        g2d.dispose();
}
	
	private void drawArrowHead(Graphics2D g, int x1, int x2, int y1, int y2){
		g.setPaint(Color.blue);
		double phi = Math.toRadians(40);
		int barb = 20;
		double dy = y1 - y2;
		double dx = x1 - x2;
		double theta = Math.atan2(dy, dx);
		double x, y, rho = theta + phi;
		for(int j = 0; j < 2; j++)
        {
            x = x1 - barb * Math.cos(rho);
            y = y1 - barb * Math.sin(rho);
            g.draw(new Line2D.Double(x1, y1, x, y));
            rho = theta - phi;
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
							curlLine.setDest(x-5, y-5);	
							this.repaint();
						}
					}
				}
			}
			
		}
		else if (dashedLine != null && state == State_DashedLine){
			
			if (dashedRectangles != null){
				for (Rectangle r: dashedRectangles){
					if( e.getX() >= r.getX() && e.getX() <= (r.getX()+100) ){
						if(e.getY() >= r.getY() && e.getY() <= (r.getY()+100)){
							int x = (r.getX()+(r.getX()+100))/2;
							int y = (r.getY()+(r.getY()+100))/2;
							dashedLine.setDest(x-5, y-5);	
							this.repaint();
						}
					}
				}
			}
		}
		
		
		
	}
	
}
