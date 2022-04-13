package ui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GEConstant;

public class GEMenuFile extends JMenu {

	private static final long serialVersionUID = 1L;
	
	private ActionHandler actionHandler;
	
	//constructor
	public GEMenuFile(String title) {
		super(title);
		
		actionHandler = new ActionHandler();	
		
		for(int i=0; i<GEConstant.GEFILEMENU_TMENUITEMS.length; i++){
			JMenuItem menuItem = new JMenuItem(GEConstant.GEFILEMENU_TMENUITEMS[i]);
			menuItem.addActionListener(actionHandler);	
			this.add(menuItem);
		}
	}
	
	private class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if( e.getActionCommand().equals(GEConstant.GEFILEMENU_TMENUITEMS[0])) {
				System.out.println("열기 선택");
			} else if( e.getActionCommand().equals(GEConstant.GEFILEMENU_TMENUITEMS[1])) {
				System.out.println("닫기 선택");
			} else if( e.getActionCommand().equals(GEConstant.GEFILEMENU_TMENUITEMS[2])) {
				System.out.println("저장 선택");
			}
		}
	}
	
}
