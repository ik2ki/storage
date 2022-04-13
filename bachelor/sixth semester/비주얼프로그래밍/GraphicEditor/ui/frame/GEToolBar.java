package ui.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import shapes.GEEllipse;
import shapes.GELine;
import shapes.GEPolygon;
import shapes.GERectangle;
import ui.frame.GEDrawingPanel.EDrawingState;
import constants.GEConstant;
import constants.GEConstant.EToolButtons;

public class GEToolBar extends JToolBar {
	
	private static final long serialVersionUID = 1L;
	
	private Vector<JButton> buttonList;
	private ButtonGroup buttonGroup;
	private EventHandler eventHandler;
	

	//components
	private GEDrawingPanel drawingPanel;
	
	//constructor
	public GEToolBar() {
		super();
	
		buttonList = new Vector<JButton>();
		buttonGroup = new ButtonGroup();
		
		eventHandler = new EventHandler();
			
		for(EToolButtons btn: EToolButtons.values()){
			JButton button = new JButton(btn.name());
			button.setIcon(new ImageIcon(GEConstant.IMGDIR
					 +btn.name()+GEConstant.IMGFORMAT));
			button.addActionListener(eventHandler);
			buttonGroup.add(button);
			buttonList.add(button);
			this.add(button);
		}
	}
	
	//methods
	//initialize
	public void initialize(GEDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}
	
	//event handler
	private class EventHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals(EToolButtons.네모.name())){
				drawingPanel.setCurrentTool(new GERectangle());
			} else if (e.getActionCommand().equals(EToolButtons.원형.name())){
				drawingPanel.setCurrentTool(new GEEllipse());
			} else if (e.getActionCommand().equals(EToolButtons.라인.name())){
				drawingPanel.setCurrentTool(new GELine());
			} else if (e.getActionCommand().equals(EToolButtons.다각형.name())){
				drawingPanel.setCurrentTool(new GEPolygon());
			} 
		}
	}
}