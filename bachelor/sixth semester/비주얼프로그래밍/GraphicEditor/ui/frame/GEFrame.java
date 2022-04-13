package ui.frame;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import constants.GEConstant;

public class GEFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	//attributes

	//components
	private GEMenuBar menuBar;
	private GEToolBar toolBar;
	private GEDrawingPanel drawingPanel;
	
	//association
	
	//constructor
	public GEFrame(String title) {
		super(title);
		
		menuBar = new GEMenuBar();
		this.setJMenuBar(menuBar);
		
		drawingPanel = new GEDrawingPanel();
		this.add(drawingPanel);
		
		toolBar = new GEToolBar();
		this.add(BorderLayout.NORTH, toolBar);
	}
	
	//methods
	//initialize
	public void initialize() {
		this.setVisible(true);
		this.setSize(GEConstant.WIDTH, GEConstant.HEIGHT);
		this.setLocation(GEConstant.X, GEConstant.Y);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuBar.initialize();
		drawingPanel.initialize();
		toolBar.initialize(drawingPanel);
	}
	
}
