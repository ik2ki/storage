package preticketmanager.customui;

import preticketmanager.adminui.AdminMenu_GUI;
import preticketmanager.start.Driver;

import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;

public class MainFrame_GUI extends JFrame implements ActionListener, Runnable {
	private Dimension di, di1;
	private Container con;
	
	Calendar calendar;
	MovieManager_GUI movieManager_GUI;
	TheatherManager_GUI theatherManager_GUI;
	
	//메뉴를 위한 변수들
	private JMenuBar menu;
	private JMenu menu1;
	private JMenuItem login;
	private JMenuItem logout;
	private JMenuItem exit;
	private JMenu menu2;
	private JMenuItem make;
	private JMenuItem info;
	
	//시계
	JLabel timerLabel;
	
	//오른쪽 메뉴의 버튼들
	JButton loginButton;
	JButton EntryAndWithdrawalButton;
	JButton QuickAdvanceButton;
	JButton TicketAndAdminButton;
	
	private JTabbedPane mainTabbedPane;
	
	public MainFrame_GUI(){
		super("영화 예매 종합 시스템");
		movieManager_GUI = new MovieManager_GUI();
		theatherManager_GUI = new TheatherManager_GUI();
		
		//메뉴를 위한 변수들
		menu = new JMenuBar();
		menu1 = new JMenu("상태");
		login = new JMenuItem("로그인");
		logout = new JMenuItem("로그아웃");
		exit = new JMenuItem("종료");
		menu2 = new JMenu("도움말");
		make = new JMenuItem("제작자 정보");
		info = new JMenuItem("프로그램 정보");
		
		//시계
		timerLabel = new JLabel("", JLabel.CENTER);
		
		//오른쪽 메뉴의 버튼들
		loginButton = new JButton("로그인");
		EntryAndWithdrawalButton = new JButton("회원가입");
		QuickAdvanceButton = new JButton("빠른예약");
		TicketAndAdminButton = new JButton("티켓정보");
		
		mainTabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
	}
	public void display(){		//화면구성 메소드
		this.setSize(1000, 610);
		di = Toolkit.getDefaultToolkit().getScreenSize();
		di1 = this.getSize();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation((int)(di.getWidth()/2 - di1.getWidth()/2),(int)(di.getHeight()/2 - di1.getHeight()/2));
		this.setResizable(false);
		
		con = this.getContentPane();
		con.setLayout(new BorderLayout());
		
		//상단 시계
		calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int date = calendar.get(Calendar.DATE);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		
		timerLabel.setText(year + " 년 " + month + " 월 " + date + " 일 " + " - " + hour + " 시 " + minute + " 분 " + second + " 초 ");

		/*메뉴설정*/
		this.setJMenuBar(menu);
		menu1.add(login);
		logout.setEnabled(false);
		menu1.add(logout);
		menu1.addSeparator();
		menu1.add(exit);
		menu2.add(make);
		menu2.add(info);
		menu.add(menu1);
		menu.add(menu2);
		
		JPanel managerPanel = new JPanel();	//왼쪽 매니저 전체 패널
		managerPanel.setLayout(new BorderLayout());
		
		/*영화 검색*/
		JPanel movieSeachPanel = new JPanel();		
		BevelBorder bb1 = new BevelBorder(BevelBorder.RAISED);
		TitledBorder tb1 = new TitledBorder(bb1, "현재 시각", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION);
		movieSeachPanel.setBorder(tb1);
		movieSeachPanel.add(timerLabel);

		JPanel centerPanel = new JPanel();	//왼쪽매니저 중앙 패널
		centerPanel.setLayout(new GridLayout(2, 1));
		
		/*버튼 메뉴*/
		JPanel buttonPanel = new JPanel();
		JPanel layoutPanel[] = new JPanel[4];
		for(int i = 0; i < 4; i++){
			layoutPanel[i] = new JPanel();
			layoutPanel[i].setLayout(new FlowLayout());
		}
		Box buttonBox = Box.createVerticalBox();
		
		layoutPanel[0].add(loginButton);
		buttonBox.add(layoutPanel[0]);
		buttonBox.add(Box.createVerticalStrut(5));
		layoutPanel[1].add(EntryAndWithdrawalButton);
		buttonBox.add(layoutPanel[1]);
		buttonBox.add(Box.createVerticalStrut(5));
		layoutPanel[2].add(QuickAdvanceButton);
		buttonBox.add(layoutPanel[2]);
		buttonBox.add(Box.createVerticalStrut(5));
		layoutPanel[3].add(TicketAndAdminButton);
		buttonBox.add(layoutPanel[3]);
		buttonPanel.add(buttonBox);
		BevelBorder bb2 = new BevelBorder(BevelBorder.RAISED);
		TitledBorder tb2 = new TitledBorder(bb2, "메뉴", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION);
		buttonPanel.setBorder(tb2);
		
		centerPanel.add(buttonPanel);
		
		/*배너 */
		JPanel imagePanel = new JPanel();
		imagePanel.setLayout(new GridLayout(2, 1));
		ImageIcon banner1 = new ImageIcon(Driver.BASEROUTE + "image/" + "banner1.jpg");
		JLabel bannerLabel1 = new JLabel(banner1);
		ImageIcon banner2 = new ImageIcon(Driver.BASEROUTE + "image/" + "banner2.jpg");
		JLabel bannerLabel2 = new JLabel(banner2);
		
		imagePanel.add(bannerLabel1);
		imagePanel.add(bannerLabel2);
		centerPanel.add(imagePanel);
		
		/*하단 명지대 마크*/
		ImageIcon myongjiMark = new ImageIcon(Driver.BASEROUTE + "image/" + "Myoungji.gif");
		JLabel imageLabel = new JLabel(myongjiMark);
		
		managerPanel.add("North", movieSeachPanel);
		managerPanel.add("Center", centerPanel);
		managerPanel.add("South", imageLabel);
		con.add("West", managerPanel);

		/*오른쪽 메인 스크롤 창*/
		mainTabbedPane.add("영화", new JScrollPane(movieManager_GUI.display(this)));
		mainTabbedPane.add("극장", new JScrollPane(theatherManager_GUI.display(this)));
		
		con.add("Center", mainTabbedPane);
		
		/*액션리스너 등록*/
		login.addActionListener(this);
		logout.addActionListener(this);
		exit.addActionListener(this);
		loginButton.addActionListener(this);
		EntryAndWithdrawalButton.addActionListener(this);
		TicketAndAdminButton.addActionListener(this);
		QuickAdvanceButton.addActionListener(this);
		
		this.setVisible(true);
	}
	
	public void logined(){
		LogManager_GUI log = new LogManager_GUI();
		log.login(this);
		if(LogManager_GUI.getUser() != null){
			loginButton.setText("로그아웃");
			EntryAndWithdrawalButton.setText("회원탈퇴");
			logout.setEnabled(true);
			login.setEnabled(false);
			if(LogManager_GUI.getUser().getID().equals("admin")){
				TicketAndAdminButton.setText("관리자메뉴");
			}
		}
	}
	public void logouted(){
		LogManager_GUI log = new LogManager_GUI();
		log.logout(this);
		if(LogManager_GUI.getUser() == null){
			loginButton.setText("로그인");
			EntryAndWithdrawalButton.setText("회원가입");
			logout.setEnabled(false);
			login.setEnabled(true);
			TicketAndAdminButton.setText("티켓정보");
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			while(true){
				Thread.sleep(1000);
				calendar = Calendar.getInstance();
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH);
				int date = calendar.get(Calendar.DATE);
				int hour = calendar.get(Calendar.HOUR_OF_DAY);
				int minute = calendar.get(Calendar.MINUTE);
				int second = calendar.get(Calendar.SECOND);
				
				timerLabel.setText(year + " 년 " + month + " 월 " + date + " 일 " + " - " + hour + " 시 " + minute + " 분 " + second + " 초 ");
			}
		}catch(Exception e){}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == loginButton || e.getSource() == login){
			if(loginButton.getText().equals("로그인")){
				logined();
			}
			else{
				logouted();
			}
		}
		else if(e.getSource() == logout){
			logouted();
		}
		else if(e.getSource() == exit){
			System.exit(0);
		}
		else if(e.getSource() == EntryAndWithdrawalButton){
			LogManager_GUI lm = new LogManager_GUI();
			if(LogManager_GUI.getUser() == null){
				lm.entry(this);
			}
			else{
				if(lm.withdrawal(this)){
					loginButton.setText("로그인");
					EntryAndWithdrawalButton.setText("회원가입");
					logout.setEnabled(false);
					login.setEnabled(true);
				}
				else{
					
				}
			}
		}
		else if(e.getSource() == TicketAndAdminButton){
			if(LogManager_GUI.getUser() != null && LogManager_GUI.getUser().getID().equals("admin")){
				AdminMenu_GUI am = new AdminMenu_GUI();
				am.display(this);
			}
			else if(LogManager_GUI.getUser() != null && !LogManager_GUI.getUser().getID().equals("admin")){
				TicketManager_GUI tm = new TicketManager_GUI();
				tm.display(this);
			}
			else{
				JOptionPane.showMessageDialog(this, "로그인 후에 이용해주세요");
			}
		}
		else if(e.getSource() == QuickAdvanceButton){
			try{
			AdvancedPurchaseManager_GUI apm = new AdvancedPurchaseManager_GUI();
			apm.display(this, 0, 0);
			}catch(ArrayIndexOutOfBoundsException e1){
				JOptionPane.showMessageDialog(this, "추가된 목록이 존재하지 않습니다.");
			}
		}
		e.setSource(null);
	}
}
