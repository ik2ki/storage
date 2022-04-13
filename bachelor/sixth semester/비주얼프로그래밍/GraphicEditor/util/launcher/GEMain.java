package util.launcher;

import ui.frame.GEFrame;
import constants.GEConstant;

public class GEMain {
	
	//main
	public static void main(String[] args) {
		GEFrame mainFrame = new GEFrame(GEConstant.GEFRAME_TITLE);
		mainFrame.initialize();
	}

}
