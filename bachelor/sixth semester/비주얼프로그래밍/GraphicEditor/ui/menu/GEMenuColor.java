package ui.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GEConstant;

public class GEMenuColor extends JMenu {
	
	private static final long serialVersionUID = 1L;
	
	//constructor
	public GEMenuColor(String title) {
		super(title);
		
		for(int i=0; i<GEConstant.GECOLORMENU_TMENUITEMS.length; i++){
			JMenuItem menuItem = new JMenuItem(GEConstant.GECOLORMENU_TMENUITEMS[i]);
			this.add(menuItem);
		}
		
	}

}
