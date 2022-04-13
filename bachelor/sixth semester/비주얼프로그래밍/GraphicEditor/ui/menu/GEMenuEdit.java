package ui.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GEConstant;

public class GEMenuEdit extends JMenu {

	private static final long serialVersionUID = 1L;
	
	//constructor
	public GEMenuEdit(String title) {
		super(title);
		
		for(int i=0; i<GEConstant.GEEDITMENU_TMENUITEMS.length; i++){
			JMenuItem menuItem = new JMenuItem(GEConstant.GEEDITMENU_TMENUITEMS[i]);
			this.add(menuItem);
		}
		
	}
}
