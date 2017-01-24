
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ENIANT extends Frame implements WindowListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Panel eniPanel;
	// add 
	
	ENIANT(){
		this.setSize(640,480);
		eniPanel = new Panel();
		this.add(eniPanel, BorderLayout.CENTER);
		Panel toolbar = new Panel();
		Button rectangleBtn = new Button("Rectangle");
		Button lineBtn = new Button("Line");
		Button commentBtn = new Button("Comment");
		Button dashedLineBtn = new Button("Dashed Line");
		Button dashedRectangleBtn = new Button("Dashed Rectangle");
		
		rectangleBtn.addActionListener(this);
		lineBtn.addActionListener(this);
		commentBtn.addActionListener(this);
		dashedLineBtn.addActionListener(this);
		dashedRectangleBtn.addActionListener(this);
		
		toolbar.add(rectangleBtn);
		toolbar.add(lineBtn);
		toolbar.add(commentBtn);
		toolbar.add(dashedLineBtn);
		toolbar.add(dashedRectangleBtn);
		
		
		this.add(toolbar, BorderLayout.SOUTH);
		
		
		this.addWindowListener(this);
	}
	public static void main(String[] args){
		ENIANT eni404 = new ENIANT();
		eni404.setVisible(true);
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
	}
	

	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println( e.getActionCommand());
		if (e.getActionCommand().contentEquals("Rectangle")){
			eniPanel.setRectangleMode();
		}else if (e.getActionCommand().contentEquals("Line")){
			eniPanel.setLineMode();
		}else if (e.getActionCommand().contentEquals("Comment")){
			eniPanel.setCommentMode();
		}else if (e.getActionCommand().contentEquals("Dashed Line")){
			eniPanel.setDashedLineMode();
		}else if (e.getActionCommand().contentEquals("Dashed Rectangle")){
			eniPanel.setRectangleMode();
		}
		
	}
	
		
		
}
