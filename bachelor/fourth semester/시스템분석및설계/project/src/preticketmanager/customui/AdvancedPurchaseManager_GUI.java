package preticketmanager.customui;

import preticketmanager.*;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

class AdvancedPurchaseManager_GUI implements ActionListener, ItemListener, ChangeListener {
	JFrame frame;
	AdvancedPurchase advancedPurchase;
	
	JDialog advancePurchaseDialog;
	List movieList;
	List theatherList;
	List dateList;
	List screenRoomList;
	List timeList;
	
	/*오른쪽아래 Spinner 와 Label*/
	String[] number = {"1명", "2명", "3명", "4명", "5명", "6명", "7명", "8명"};
	JSpinner numberSpinner;
	SpinnerListModel numberSpinnerListModel;
	JLabel priceLabel;
	JButton selectButton;
	
	public AdvancedPurchaseManager_GUI(){
		advancedPurchase = new AdvancedPurchase();
		
		advancePurchaseDialog = new JDialog(frame, "예매하기");
		movieList = new List(20, false);
		theatherList = new List(20, false);
		dateList = new List(15, false);
		screenRoomList = new List(15,false);
		timeList = new List(15, false);
		
		numberSpinner  = new JSpinner();
		numberSpinnerListModel = new SpinnerListModel(number);
		selectButton = new JButton("예약/결제 하기");
	}
	public void display(JFrame frame, int state ,int index){
		this.frame = frame;
		try
	    {
	       UIManager.setLookAndFeel ("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	    } catch (Exception e)
	    {
	        e.printStackTrace();
	    }
		
		advancePurchaseDialog.setSize(800, 450);
		advancePurchaseDialog.setResizable(false);
		advancePurchaseDialog.setModal(true);
		advancePurchaseDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		Dimension di, di1;
		di = Toolkit.getDefaultToolkit().getScreenSize();
		di1 = advancePurchaseDialog.getSize();
		advancePurchaseDialog.setLocation((int)(di.getWidth()/2 - di1.getWidth()/2), (int)(di.getHeight()/2 - di1.getHeight()/2));
		
		Container con = advancePurchaseDialog.getContentPane();
		con.setLayout(new BorderLayout());
		
		JLabel title = new JLabel("   예매 | TICKETING");
		con.add("North", title);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new FlowLayout());
		
		JPanel movieListPanel = new JPanel();
		movieListPanel.setLayout(new BorderLayout());
		
		JLabel movieListLabel = new JLabel("1. 영화 선택");
		movieListPanel.add("North", movieListLabel);
		movieListPanel.add("Center", new JScrollPane(movieList));
		centerPanel.add(movieListPanel);

		JPanel theatherListPanel = new JPanel();
		theatherListPanel.setLayout(new BorderLayout());
		
		JLabel theatherListLabel = new JLabel("2. 극장 선택");
		theatherListPanel.add("North", theatherListLabel);
		theatherListPanel.add("Center", new JScrollPane(theatherList));
		centerPanel.add(theatherListPanel);

		JPanel centerPanel2 = new JPanel();
		centerPanel2.setLayout(new BorderLayout());
		JLabel dateLabel = new JLabel("3. 날짜/상영관/시간 선택");
		centerPanel2.add("North", dateLabel);
		JPanel centerPanel3 = new JPanel();
		centerPanel3.setLayout(new FlowLayout());
		centerPanel3.add(new JScrollPane(dateList));
		centerPanel3.add(new JScrollPane(screenRoomList));
		centerPanel3.add(new JScrollPane(timeList));
		centerPanel2.add("Center", centerPanel3);

		JPanel numberPanel = new JPanel();
		numberPanel.setLayout(new BorderLayout());
		JLabel numberLabel = new JLabel("4. 인원선택 (1회 최대 8명까지 가능)");
		numberPanel.add("North", numberLabel);
		numberPanel.add("Center", numberSpinner);
		
		numberSpinner.setModel(numberSpinnerListModel);
		String spinnerValue = numberSpinner.getValue().toString().charAt(0) + "";
		priceLabel = new JLabel("현재 총티켓 가격: " + Integer.parseInt(spinnerValue) * 7000 + "원");
		numberPanel.add("South" , priceLabel);
		centerPanel2.add("South", numberPanel);

		centerPanel.add(centerPanel2);
		con.add(centerPanel);
		
		JPanel buttonPanel = new JPanel(); 
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		selectButton.setEnabled(false);
		buttonPanel.add(selectButton);
		con.add("South", buttonPanel);
		
		if(state == 1){
			view();
			movieList.select(index - 1);
			advancedPurchase.processMovieSelection(index - 1);
			view();
		}
		else if(state == 2){
			view();
			theatherList.select(index - 1);
			advancedPurchase.processTheatherSelection(index - 1);
			view();
		}
		else{
			this.view();	
		}
		

		/*리스너 등록*/
		movieList.addItemListener(this);
		theatherList.addItemListener(this);
		dateList.addItemListener(this);
		screenRoomList.addItemListener(this);
		timeList.addItemListener(this);
		numberSpinner.addChangeListener(this);
		selectButton.addActionListener(this);

		advancePurchaseDialog.setVisible(true);
		
	}

	public void updateList(LinkedList<String> item, int flag){
		if(flag == 1){
			theatherList.removeAll();
			for(int i = 0; i < item.size(); i++)
				theatherList.add(item.get(i));
		}
		else if(flag == 2){
			movieList.removeAll();
			for(int i = 0; i < item.size(); i++)
				movieList.add(item.get(i));
		}
		else if(flag == 3){
			dateList.removeAll();
			for(int i = 0; i < item.size(); i++)
				dateList.add(item.get(i));
		}
		else if(flag == 4){
			screenRoomList.removeAll();
			for(int i = 0; i < item.size(); i++)
				screenRoomList.add(item.get(i));
		}
		else if(flag == 5){
			timeList.removeAll();
			for(int i = 0; i < item.size(); i++)
				timeList.add(item.get(i));
		}
		item.clear();
	}
	public void view(){
		if(advancedPurchase.getTheather() == null && advancedPurchase.getMovie() == null && advancedPurchase.getDate() == null){
			updateList(advancedPurchase.viewMovieItem(), 2);
			updateList(advancedPurchase.viewTheatherItem(), 1);
			updateList(advancedPurchase.viewDateItem(), 3);
		}
		else if(advancedPurchase.getTheather() != null && advancedPurchase.getMovie() == null && advancedPurchase.getDate() == null){
			updateList(advancedPurchase.viewMovieItem(), 2);
			updateList(advancedPurchase.viewDateItem(), 3);
		}
		else if(advancedPurchase.getTheather() == null && advancedPurchase.getMovie() != null && advancedPurchase.getDate() == null){
			updateList(advancedPurchase.viewTheatherItem(), 1);
			updateList(advancedPurchase.viewDateItem(), 3);
		}
		else if(advancedPurchase.getTheather() == null && advancedPurchase.getMovie() == null && advancedPurchase.getDate() != null){
			updateList(advancedPurchase.viewTheatherItem(), 1);
			updateList(advancedPurchase.viewMovieItem(), 2);
		}
		else if(advancedPurchase.getTheather() != null && advancedPurchase.getMovie() != null && advancedPurchase.getDate() == null){
			updateList(advancedPurchase.viewDateItem(), 3);
		}
		else if(advancedPurchase.getTheather() != null && advancedPurchase.getMovie() == null && advancedPurchase.getDate() != null){
			updateList(advancedPurchase.viewMovieItem(), 2);
		}
		else if(advancedPurchase.getTheather() == null && advancedPurchase.getMovie() != null && advancedPurchase.getDate() != null){
			updateList(advancedPurchase.viewTheatherItem(), 1);
		}
		else if(advancedPurchase.getTheather() != null && advancedPurchase.getMovie() != null && advancedPurchase.getDate() != null && advancedPurchase.getScreenRoom() == null){
			
			updateList(advancedPurchase.viewScreenRoomItem(), 4);
		}
		else if(advancedPurchase.getTheather() != null && advancedPurchase.getMovie() != null && advancedPurchase.getDate() != null && advancedPurchase.getScreenRoom() != null){
			
			updateList(advancedPurchase.viewScreenTimeItem(), 5);
		}
	}

	public void itemStateChanged(ItemEvent e) {
		int index;
		if(e.getSource() == movieList){
			if(advancedPurchase.getScreenRoom() != null){
				screenRoomList.removeAll();
				timeList.removeAll();
			}
			index = movieList.getSelectedIndex();
			advancedPurchase.processMovieSelection(index);
			this.view();
		}
		else if(e.getSource() == theatherList){
				if(advancedPurchase.getScreenRoom() != null){
					screenRoomList.removeAll();
					timeList.removeAll();
				}
			index = theatherList.getSelectedIndex();
			advancedPurchase.processTheatherSelection(index);
			this.view();
		}
		else if(e.getSource() == dateList){
				if(advancedPurchase.getScreenRoom() != null){
					screenRoomList.removeAll();
					timeList.removeAll();
				}
			index = dateList.getSelectedIndex();
			advancedPurchase.processDateSelection(index);
			this.view();
		}
		else if(e.getSource() == screenRoomList){
			index = screenRoomList.getSelectedIndex();
			advancedPurchase.processScreenRoomSelection(index);    
		    this.view();
		}
		else if(e.getSource() == timeList){
			index = timeList.getSelectedIndex();
			advancedPurchase.processTimeSelection(index);
		}
		
		if(advancedPurchase.getMovie() != null && advancedPurchase.getTheather() != null && advancedPurchase.getDate() != null && advancedPurchase.getScreenRoom() != null && advancedPurchase.getTime() != null){
			selectButton.setEnabled(true);
		}
		else{
			selectButton.setEnabled(false);
		}
	}
	public void stateChanged(ChangeEvent e) {
		if(e.getSource() == numberSpinner){
			String spinnerValue = numberSpinner.getValue().toString().charAt(0) + "";
			priceLabel.setText("현재 총티켓 가격: " + Integer.parseInt(spinnerValue) * 7000 + "원");
		}
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == selectButton){
			if(LogManager_GUI.getUser() != null){
				advancedPurchase.combineObject();
				int emptySeatNumber = advancedPurchase.getScreenRoom().getTotalSeat() - advancedPurchase.getScreenRoom().getPreticketSeat();
				int spinnerValue = Integer.parseInt(numberSpinner.getValue().toString().charAt(0) + "");
				if(spinnerValue > emptySeatNumber){
					JOptionPane.showMessageDialog(frame, "좌석이 부족합니다 다시선택해주세요");
					advancePurchaseDialog.dispose();
				}
				else{
					advancedPurchase.closeSearchFile();
					SettlementManager_GUI sm = new SettlementManager_GUI();
					advancePurchaseDialog.dispose();
					sm.settlementDialog(frame, advancedPurchase.getMovie(), advancedPurchase.getTheather(), advancedPurchase.getScreenRoom(), advancedPurchase.getDate(), advancedPurchase.getTime(), spinnerValue);
				}
			}
			else{
				LogManager_GUI lm = new LogManager_GUI();
				JOptionPane.showMessageDialog(frame, "로그인이 필요합니다 로그인해주세요");
				lm.login(frame);
			}
		}
		e.setSource(null);
	}
}
