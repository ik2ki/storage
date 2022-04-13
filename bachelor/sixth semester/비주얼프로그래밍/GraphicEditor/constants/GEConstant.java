package constants;

import java.awt.Color;

public class GEConstant {

	//GEFrame
	public static final String GEFRAME_TITLE = "그래픽 에디터 v0.63";
	public static final int X = 100;
	public static final int Y = 100;
	public static final int WIDTH = 400;
	public static final int HEIGHT = 500;
	
	//GEDrawingPanel
	public static final Color GEPANEL_CBACKGROUND = Color.WHITE;
	
	//GEToolBar (한 줄로 설정을  할 수 있으면 추가점수)
	public static enum EToolButtons {네모, 원형, 라인, 다각형 };
	public static final String IMGDIR="img/";
	public static final String IMGFORMAT=".gif";
 	// Selected ImageIcons array
	
	//GEMenuBar
	public static final String GEMENUBAR_TFILEMENU = "파일";
	public static final String GEMENUBAR_TEDITMENU = "편집";
	public static final String GEMENUBAR_TCOLORMENU = "색";
	
	//MenuItems
	public static final String[] GEFILEMENU_TMENUITEMS = {"열기", "닫기", "저장"};
	public static final String[] GEEDITMENU_TMENUITEMS = {"잘라내기", "붙여넣기", "복사"};
	public static final String[] GECOLORMENU_TMENUITEMS = {"선색", "면색"};
	
	//drawing state
	public enum EDrawingState {Idle, Select}
}