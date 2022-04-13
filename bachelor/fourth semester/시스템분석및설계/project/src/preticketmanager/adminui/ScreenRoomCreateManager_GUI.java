package preticketmanager.adminui;

import preticketmanager.System.*;
import preticketmanager.admindatamanager.*;
import preticketmanager.model.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.*;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class ScreenRoomCreateManager_GUI implements ActionListener, ItemListener {
	JFrame frame;
	
	TheatherFile theatherFile;
	MovieFile movieFile;
	ScreenRoomFile screeRoomFile;
	
	int theatherNumber;
	int screenRoomNumber;
	
	/*display 에서 쓰는 변수들*/
	JDialog theatherSelectDialog;
	List theatherList;
	List screenRoomList;
	JButton theatherSelectButton;
	JButton screenRoomAddButton;
	JButton screenRoomDelButton;
	JButton cancelButton;
	
	/*screenRoomCreateDialog 에서 쓰는 변수들*/
	JDialog screenRoomCreateDialog;
 	JTextField totalSeatTextField;
 	List nowScreenMovieList;
	JLabel movieTimeLabel;
	JButton movieSetButton;
	JButton movieAddButton;
 	LinkedList<String> nowScreenMovieLinkedList; 
	JButton screenRoomSaveButton;
	JButton screenRoomCancelButton;
	//임시자료
	int seatNumber;
	
	/*movieSelectDialog 에서 쓰는 변수들*/
	JDialog movieSelectDialog;
	List movieList;
	JButton movieSelectButton;
	JButton movieSelectCancelButton;
	
	/*movieInsertDialog 에서 쓰는 변수들*/
	JDialog movieInsertDialog;
	JTextField startYearTextField;
	JTextField startMonthTextField;
	JTextField startDateTextField;
	JTextField endYearTextField;
	JTextField endMonthTextField;
	JTextField endDateTextField;
	JTextField StartTimeTextField;
	JTextField StartMinuteTextField;
	JTextField numberOfScreenTextField;
	JButton okButton;
	JButton movieInsertCancelButton;
	//임시 변수
	Movie movie;
	String startDay;
	String endDay;
	String startTime;
	int screenNumber;
	int check;
	
	public ScreenRoomCreateManager_GUI(){
		theatherFile = new TheatherFile();
		movieFile = new MovieFile();

		theatherSelectDialog = new JDialog();
		theatherList = new List(15, false);
		screenRoomList = new List(15, false);
		theatherSelectButton = new JButton("선택");
		screenRoomAddButton = new JButton("상영관 추가");
		screenRoomDelButton = new JButton("상영관 삭제");
		cancelButton = new JButton("취소");
		
	 	totalSeatTextField = new JTextField(5);
	 	nowScreenMovieList = new List(5, false);
		movieTimeLabel = new JLabel("상영날짜 : ----년 --월 --일 ~ ----년 --월 --일", JLabel.CENTER);
		movieSetButton = new JButton("영화 수정");
		movieAddButton = new JButton("영화 추가");
	 	nowScreenMovieLinkedList = new LinkedList<String>(); 
		screenRoomSaveButton = new JButton("저장");
		screenRoomCancelButton = new JButton("취소");
		
		movieList = new List(10, false);
		movieSelectButton = new JButton("선택");
		movieSelectCancelButton = new JButton("취소");

		startYearTextField = new JTextField(5);
		startMonthTextField = new JTextField(5);
		startDateTextField = new JTextField(5);
		endYearTextField = new JTextField(5);
		endMonthTextField = new JTextField(5);
		endDateTextField = new JTextField(5);
		StartTimeTextField = new JTextField(5);
		StartMinuteTextField = new JTextField(5);
		numberOfScreenTextField = new JTextField(5);
		okButton = new JButton("등록");
		movieInsertCancelButton = new JButton("취소");
	}
	public void initTextField(){
		startYearTextField.setText("");
		startMonthTextField.setText("");
		startDateTextField.setText("");
		endYearTextField.setText("");
		endMonthTextField.setText("");
		endDateTextField.setText("");
		StartTimeTextField.setText("");
		StartMinuteTextField.setText("");
		numberOfScreenTextField.setText("");
	}
	
	public void display(JFrame frame){
		try
	    {
	       UIManager.setLookAndFeel ("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	    } catch (Exception e)
	    {
	        e.printStackTrace();
	    }

		this.frame = frame;
		
		theatherSelectDialog.setLayout(new BorderLayout());
		theatherSelectDialog.setSize(400, 380);
		theatherSelectDialog.setResizable(false);
		theatherSelectDialog.setModal(true);
		theatherSelectDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		Dimension di, di1;
		di = Toolkit.getDefaultToolkit().getScreenSize();
		di1 = theatherSelectDialog.getSize();
		theatherSelectDialog.setLocation((int)(di.getWidth()/2 - di1.getWidth()/2), (int)(di.getHeight()/2 - di1.getHeight()/2));	
		
		
		JLabel titleLabel = new JLabel("극장 선택", JLabel.CENTER);
		theatherSelectDialog.add("North", titleLabel);
		JPanel centerPanel = new JPanel();
		TheatherFile tf = new TheatherFile();
		for(int i = 1; i < tf.getNumberOfTheather(); i++){
			theatherList.add(tf.getName(i));
		}
		centerPanel.add(new JScrollPane(theatherList));
		centerPanel.add(new JScrollPane(screenRoomList));
		theatherSelectDialog.add(centerPanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(theatherSelectButton);
		buttonPanel.add(screenRoomAddButton);
		buttonPanel.add(screenRoomDelButton);
		buttonPanel.add(cancelButton);
		theatherSelectDialog.add("South", buttonPanel);
		
		/*리스너 등록*/
		theatherList.addItemListener(this);
		theatherSelectButton.addActionListener(this);
		screenRoomAddButton.addActionListener(this);
		screenRoomDelButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		theatherSelectDialog.setVisible(true);
	}
	
	public void screenRoomCreateDialog(int theatherNumber, int screenRoomNumber){
		this.theatherNumber = theatherNumber;
		this.screenRoomNumber = screenRoomNumber;
		
		screenRoomCreateDialog = new JDialog();
		screenRoomCreateDialog.setLayout(new BorderLayout());
		screenRoomCreateDialog.setSize(400, 400);
		screenRoomCreateDialog.setResizable(false);
		screenRoomCreateDialog.setModal(true);
		screenRoomCreateDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		Dimension di, di1;
		di = Toolkit.getDefaultToolkit().getScreenSize();
		di1 = screenRoomCreateDialog.getSize();
		screenRoomCreateDialog.setLocation((int)(di.getWidth()/2 - di1.getWidth()/2), (int)(di.getHeight()/2 - di1.getHeight()/2));

		JLabel titleLabel = new JLabel((screenRoomNumber+1) + "관", JLabel.CENTER);
		screenRoomCreateDialog.add("North", titleLabel);
		

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(2, 1));
		JPanel totalSeatPanel = new JPanel();
		JLabel totalSeatLabel = new JLabel("현재 총 좌석수 : - ");
		totalSeatPanel.add(totalSeatLabel);
		JPanel newSeatPanel = new JPanel();
		JLabel newSeatLabel = new JLabel("새로운 좌석수 입력 (숫자) ");
		newSeatPanel.add(newSeatLabel);
		newSeatPanel.add(totalSeatTextField);
		northPanel.add(totalSeatPanel);
		northPanel.add(newSeatPanel);
		centerPanel.add("North", northPanel);
		JPanel nowScreenMoviePanel = new JPanel();
		nowScreenMoviePanel.setLayout(new BorderLayout());
		JLabel nowScreenMovieLabel = new JLabel("현재 상영중인 영화", JLabel.CENTER);
		nowScreenMoviePanel.add("North", nowScreenMovieLabel);
		JPanel nowScreenMovieCenterPanel = new JPanel();
		nowScreenMovieCenterPanel.setLayout(new BorderLayout());
		screeRoomFile = new ScreenRoomFile(theatherNumber + 1, screenRoomNumber + 1);
		screeRoomFile.setSheetScreenMovie();
		screeRoomFile.setScreenMovieNames();
		nowScreenMovieLinkedList = screeRoomFile.getScreenMovieNames();
		nowScreenMovieList.removeAll();
		for(int i = 0; i < nowScreenMovieLinkedList.size(); i++){
			nowScreenMovieList.add(nowScreenMovieLinkedList.get(i));
		}
		nowScreenMovieCenterPanel.add("Center", nowScreenMovieList);
		nowScreenMovieCenterPanel.add("South", movieTimeLabel);
		nowScreenMoviePanel.add("Center", nowScreenMovieCenterPanel);
		JPanel nowScreenMovieButtonPanel = new JPanel();
		nowScreenMovieButtonPanel.add(movieSetButton);
		nowScreenMovieButtonPanel.add(movieAddButton);
		nowScreenMoviePanel.add("South", nowScreenMovieButtonPanel);
		centerPanel.add("Center", nowScreenMoviePanel);
		screenRoomCreateDialog.add("Center", centerPanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(screenRoomSaveButton);
		buttonPanel.add(screenRoomCancelButton);
		screenRoomCreateDialog.add("South", buttonPanel);
		
		try {
			ScreenRoomExcelWriter sew = new ScreenRoomExcelWriter();
			seatNumber = sew.getSeatNum(theatherNumber + 1, screenRoomNumber + 1);
			totalSeatLabel.setText("현재 총 좌석수 : " + seatNumber);
			totalSeatTextField.setText(seatNumber + "");
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*리스너 등록*/
		movieSetButton.addActionListener(this);
		movieAddButton.addActionListener(this);
		screenRoomSaveButton.addActionListener(this);
		screenRoomCancelButton.addActionListener(this);
		nowScreenMovieList.addItemListener(this);
		
		screenRoomCreateDialog.setVisible(true);
	}

	public void MovieSelectDialog(){
		movieSelectDialog = new JDialog();
		movieSelectDialog.setLayout(new BorderLayout());
		movieSelectDialog.setSize(370, 250);
		movieSelectDialog.setResizable(false);
		movieSelectDialog.setModal(true);
		movieSelectDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		Dimension di, di1;
		di = Toolkit.getDefaultToolkit().getScreenSize();
		di1 = movieSelectDialog.getSize();
		movieSelectDialog.setLocation((int)(di.getWidth()/2 - di1.getWidth()/2), (int)(di.getHeight()/2 - di1.getHeight()/2));	
		
		JLabel titleLabel = new JLabel("상영 영화 추가", JLabel.CENTER);
		movieSelectDialog.add("North", titleLabel);
		JPanel centerPanel = new JPanel();
		movieList.removeAll();
		for(int i = 1; i < movieFile.getNumberOfMovie(); i++){
			movieList.add(movieFile.getName(i));
		}
		centerPanel.add(movieList);
		movieSelectDialog.add("Center", centerPanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(movieSelectButton);
		buttonPanel.add(movieSelectCancelButton);
		movieSelectDialog.add("South", buttonPanel);
		
		/*리스너등록*/
		movieSelectButton.addActionListener(this);
		movieSelectCancelButton.addActionListener(this);
		
		movieSelectDialog.setVisible(true);
	}
	public void movieInsertDialog(int check, String movieName){
		this.check = check;
		
		movieInsertDialog = new JDialog();
		movieInsertDialog.setLayout(new BorderLayout());
		movieInsertDialog.setSize(370, 200);
		movieInsertDialog.setResizable(false);
		movieInsertDialog.setModal(true);
		movieInsertDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		Dimension di, di1;
		di = Toolkit.getDefaultToolkit().getScreenSize();
		di1 = movieInsertDialog.getSize();
		movieInsertDialog.setLocation((int)(di.getWidth()/2 - di1.getWidth()/2), (int)(di.getHeight()/2 - di1.getHeight()/2));	
		
		JLabel titleLabel = new JLabel(movieName, JLabel.CENTER);
		movieInsertDialog.add("North", titleLabel);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(4, 1));
		JPanel startDayPanel = new JPanel();
		JLabel startDayLabel = new JLabel("영화 시작일 : ");
		JLabel startYearLabel = new JLabel("년");
		JLabel startMonthLabel = new JLabel("월");
		JLabel startDateLabel = new JLabel("일");
		startDayPanel.add(startDayLabel);
		startDayPanel.add(startYearTextField);
		startDayPanel.add(startYearLabel);
		startDayPanel.add(startMonthTextField);
		startDayPanel.add(startMonthLabel);
		startDayPanel.add(startDateTextField);
		startDayPanel.add(startDateLabel);
		centerPanel.add(startDayPanel);
		JPanel endDayPanel = new JPanel();
		JLabel endDayLabel = new JLabel("영화 종료일 : ");
		JLabel endYearLabel = new JLabel("년");
		JLabel endMonthLabel = new JLabel("월");
		JLabel endDateLabel = new JLabel("일");
		endDayPanel.add(endDayLabel);
		endDayPanel.add(endYearTextField);
		endDayPanel.add(endYearLabel);
		endDayPanel.add(endMonthTextField);
		endDayPanel.add(endMonthLabel);
		endDayPanel.add(endDateTextField);
		endDayPanel.add(endDateLabel);
		centerPanel.add(endDayPanel);
		JPanel startTimePanel = new JPanel();
		JLabel startTimeLabel = new JLabel("영화 시작 시간 (24시): ");
		JLabel colon = new JLabel(" : ");
		startTimePanel.add(startTimeLabel);
		startTimePanel.add(StartTimeTextField);
		startTimePanel.add(colon);
		startTimePanel.add(StartMinuteTextField);
		centerPanel.add(startTimePanel);
		JPanel numberOfScreenPanel = new JPanel();
		JLabel numberOfScreenLabel = new JLabel("상영할 회차 수: ");
		numberOfScreenPanel.add(numberOfScreenLabel);
		numberOfScreenPanel.add(numberOfScreenTextField);
		centerPanel.add(numberOfScreenPanel);
		movieInsertDialog.add("Center", centerPanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okButton);
		buttonPanel.add(movieInsertCancelButton);
		movieInsertDialog.add("South", buttonPanel);
		
		movieFile.setMovie(movieFile.findMovie(movieName));
		movie = movieFile.getMovie();

		if(check == 1){
			String[] endDate = new String[3];
			
			ScreenRoomExcelWriter se;
			try {
				se = new ScreenRoomExcelWriter();
				endDate = se.getEndDay(theatherNumber + 1, screenRoomNumber + 1).split("-");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			startYearTextField.setText(endDate[0]);
			startMonthTextField.setText(endDate[1]);
			startDateTextField.setText(endDate[2]);
		}
		else if(check == 0){
			String[] startDay = new String[3];
			String[] endDay = new String[3];
			screeRoomFile.setFindMovie(movieName);
			String startDate = screeRoomFile.findMovieStartDate();
			String endDate = screeRoomFile.findMovieEndDate();
			startDay = startDate.split("-");
			endDay = endDate.split("-");
			
			startYearTextField.setText(startDay[0]);
			startMonthTextField.setText(startDay[1]);
			startDateTextField.setText(startDay[2]);
			endYearTextField.setText(endDay[0]);
			endMonthTextField.setText(endDay[1]);
			endDateTextField.setText(endDay[2]);
		}
		
		/*리스너 등록*/
		okButton.addActionListener(this);
		movieInsertCancelButton.addActionListener(this);
		
		movieInsertDialog.setVisible(true);
	}
	public JPanel movieTimePanel(int day){
		JPanel movieTimePanel = new JPanel();
		
		return movieTimePanel; 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		/*display 관련 액션*/
		if(e.getSource() == theatherSelectButton){
			screenRoomCreateDialog(theatherList.getSelectedIndex(), screenRoomList.getSelectedIndex());
		}
		else if(e.getSource() == cancelButton){
			theatherSelectDialog.dispose();
		}
		else if(e.getSource() == screenRoomAddButton){
			try {
				ScreenRoomExcelWriter sew = new ScreenRoomExcelWriter();
				sew.create(theatherList.getSelectedIndex() + 1);
				JOptionPane.showMessageDialog(frame, "생성 되었습니다");
				
			} catch (RowsExceededException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (WriteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (BiffException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			theatherSelectDialog.dispose();
		}
		else if(e.getSource() == screenRoomDelButton){
			try {
				ScreenRoomExcelWriter sew = new ScreenRoomExcelWriter();
				if(sew.delete(theatherList.getSelectedIndex() + 1)){
					JOptionPane.showMessageDialog(frame, "삭제 되었습니다");
				}
				else{
					JOptionPane.showMessageDialog(frame, "생성 실패! 확인후 다시 삭제해주세요");
				}
				
			} catch (RowsExceededException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (WriteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (BiffException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			theatherSelectDialog.dispose();
		}
		/*상영관 좌석 수정후 저장 버튼 관련 액션*/
		else if(e.getSource() == screenRoomSaveButton){
			try {
				ScreenRoomExcelWriter sew = new ScreenRoomExcelWriter();
				sew.setSeatNum(theatherNumber + 1, screenRoomNumber + 1, 
				Integer.parseInt(totalSeatTextField.getText()));
				JOptionPane.showMessageDialog(frame, "수정 되었습니다");
				theatherSelectDialog.dispose();
			} catch (RowsExceededException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (WriteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (BiffException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NumberFormatException e1){
				JOptionPane.showMessageDialog(frame, "좌석수에 숫자가 입력되지 않았습니다");
			}
			totalSeatTextField.setText("");
			screenRoomCreateDialog.dispose();
			e.setSource(null);
		}
		else if(e.getSource() == screenRoomCancelButton){
			screenRoomCreateDialog.dispose();
		}
		/*영화 추가와 수정 관련 액션*/
		else if(e.getSource() == movieSetButton){	//영화 수정
			movieInsertDialog(0, nowScreenMovieList.getSelectedItem());
		}
		else if(e.getSource() == movieAddButton){ // 영화 추가
			MovieSelectDialog();
		}
		/*영화 추가 버튼 누른후 영화 선택 관련 액션*/
		else if(e.getSource() == movieSelectButton){
			movieSelectDialog.dispose();
			movieInsertDialog(1, movieList.getSelectedItem());
		}
		else if(e.getSource() == movieSelectCancelButton){
			movieSelectDialog.dispose();
		}
		/*영화 선택후 등록 관련 액션*/
		else if(e.getSource() == okButton){
				try {
					startDay = startYearTextField.getText() + "-" + startMonthTextField.getText() + "-" + startDateTextField.getText();
					endDay = endYearTextField.getText() + "-" + endMonthTextField.getText() + "-" + endDateTextField.getText();
					startTime = StartTimeTextField.getText() + ":" + StartMinuteTextField.getText();
					screenNumber = Integer.parseInt(numberOfScreenTextField.getText());
					ScreenRoomExcelWriter se;
					se = new ScreenRoomExcelWriter();
					if(check == 1){
						SearchExcelWriter searchE = new SearchExcelWriter();
						searchE.Loader(theatherNumber, movie, startDay, endDay);
						se.inputMovie(theatherNumber+1, screenRoomNumber+1, movie, startDay, endDay, startTime, screenNumber);
					}
					else{
						SearchExcelWriter searchE = new SearchExcelWriter();
						searchE.Loader(theatherNumber, movie, startDay, endDay);
						se.modifyMovie(nowScreenMovieList.getSelectedIndex()+1, theatherNumber+1, screenRoomNumber+1, movie, startDay, endDay, startTime, screenNumber);
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(frame, "영화가 등록/수정 되었습니다");
				movieInsertDialog.dispose();
				screenRoomCreateDialog.dispose();
				initTextField();
		}
		else if(e.getSource() == movieInsertCancelButton){
			movieInsertDialog.dispose();
		}
		e.setSource(null);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == theatherList){
			screenRoomList.removeAll();
			int totalScreenRoomNumber = theatherFile.getTotalScreenRoomNumber(theatherList.getSelectedIndex() + 1);
			for(int i = 0; i < totalScreenRoomNumber; i++){
				screenRoomList.add((i+1) + "관");
			}
		}
		else if(e.getSource() == nowScreenMovieList){
			String[] startDay = new String[3];
			String[] endDay = new String[3];
			screeRoomFile.setSheetScreenMovie();
			screeRoomFile.setFindMovie(nowScreenMovieList.getSelectedItem());
			String startDate = screeRoomFile.findMovieStartDate();
			String endDate = screeRoomFile.findMovieEndDate();
			startDay = startDate.split("-");
			endDay = endDate.split("-");
			movieTimeLabel.setText(startDay[0] + "년 " + startDay[1] + "월 " + startDay[2] + "일 ~ " + endDay[0] + "년 "
					+ endDay[1] + "월 " + endDay[2] + "일");
		}
		e.setSource(null);
	}
}
