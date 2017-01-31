package mc408;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.util.Hashtable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.plaf.basic.BasicBorders.SplitPaneBorder;
  
class RLEditor extends Frame implements MouseListener, MouseMotionListener {  
	
	private Model model;
	private Toolbar toolbar;
	private DrawingPanel drawingPanel;
	private OverViewPanel overviewPanel;
	private Tool rectangleTool;
	private Tool connectionTool;
	private Tool currentTool;
	private Tool CommentTool;
	
	RLEditor() {  
		setLayout(new BorderLayout());
		
		Hashtable<String, ItemListener> checkboxes = new Hashtable<String, ItemListener>();
	    
		checkboxes.put("Rectangle", new ItemListener() {
	        public void itemStateChanged(ItemEvent e) {
	        	currentTool = rectangleTool;
	        }});
	    
	    checkboxes.put("Line", new ItemListener() {
	        public void itemStateChanged(ItemEvent e) {
	        	currentTool = connectionTool;
	        }});
	    
	    checkboxes.put("Comment", new ItemListener() {
	        public void itemStateChanged(ItemEvent e) {
	        	currentTool = CommentTool;
	        }});
	    
	    toolbar = new Toolbar(checkboxes, "Square");
	    drawingPanel = new DrawingPanel(this, this);
	    overviewPanel = new OverViewPanel();
	    
	    model = new Model();
	    model.addObserver(drawingPanel);
	    model.addObserver(overviewPanel);
	    
	    rectangleTool = new RectangleTool(model);
		connectionTool = new ConnectionTool(model);
		CommentTool = new CommentTool(model);
		currentTool = rectangleTool;
	     
		add(toolbar, BorderLayout.NORTH);
		
		drawingPanel.setPreferredSize(new Dimension(450, 480));
		add(drawingPanel, BorderLayout.WEST);
		overviewPanel.setPreferredSize(new Dimension(200,480));
		overviewPanel.setBackground(Color.LIGHT_GRAY);
		add(overviewPanel, BorderLayout.EAST);


	
		
		setSize(650,480);
		setVisible(true);   
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
            }
        });
	}  
	
	public void mouseClicked(MouseEvent e) {
		currentTool.mouseClicked(e);
	}
	
	public void mousePressed(MouseEvent e) {
		currentTool.mousePressed(e);
	} 
	
	public void mouseReleased(MouseEvent e) {
		currentTool.mouseReleased(e);
	} 
	
	public void mouseEntered(MouseEvent e) {
		currentTool.mouseEntered(e);
	} 
	
	public void mouseExited(MouseEvent e) {
		currentTool.mouseExited(e);
	}


	public static void main(String args[]) {  
		new RLEditor();  
	}

	public void mouseDragged(MouseEvent e) {
		currentTool.mouseDragged(e);
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		currentTool.mouseMoved(e);
	}
}  