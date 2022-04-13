package constants;

import java.awt.Color;

public class GEConstant {

	//GEFrame
	public static final String GEFRAME_TITLE = "�׷��� ������ v0.63";
	public static final int X = 100;
	public static final int Y = 100;
	public static final int WIDTH = 400;
	public static final int HEIGHT = 500;
	
	//GEDrawingPanel
	public static final Color GEPANEL_CBACKGROUND = Color.WHITE;
	
	//GEToolBar (�� �ٷ� ������  �� �� ������ �߰�����)
	public static enum EToolButtons {�׸�, ����, ����, �ٰ��� };
	public static final String IMGDIR="img/";
	public static final String IMGFORMAT=".gif";
 	// Selected ImageIcons array
	
	//GEMenuBar
	public static final String GEMENUBAR_TFILEMENU = "����";
	public static final String GEMENUBAR_TEDITMENU = "����";
	public static final String GEMENUBAR_TCOLORMENU = "��";
	
	//MenuItems
	public static final String[] GEFILEMENU_TMENUITEMS = {"����", "�ݱ�", "����"};
	public static final String[] GEEDITMENU_TMENUITEMS = {"�߶󳻱�", "�ٿ��ֱ�", "����"};
	public static final String[] GECOLORMENU_TMENUITEMS = {"����", "���"};
	
	//drawing state
	public enum EDrawingState {Idle, Select}
}