package ui.frame;

import javax.swing.JMenuBar;

import ui.menu.GEMenuColor;
import ui.menu.GEMenuEdit;
import ui.menu.GEMenuFile;
import constants.GEConstant;

public class GEMenuBar extends JMenuBar {
	
	private static final long serialVersionUID = 1L;
	
	//components
	private GEMenuFile menuFile;
	private GEMenuEdit menuEdit;
	private GEMenuColor menuColor;

	//constructor
	public GEMenuBar() {
		super();
		
		menuFile = new GEMenuFile(GEConstant.GEMENUBAR_TFILEMENU);
		this.add(menuFile);
		
		menuEdit = new GEMenuEdit(GEConstant.GEMENUBAR_TEDITMENU);
		this.add(menuEdit);
		
		menuColor = new GEMenuColor(GEConstant.GEMENUBAR_TCOLORMENU);
		this.add(menuColor);
	}

	public void initialize() {
		
	}
	
}
