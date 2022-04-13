package preticketmanager.customui;

import preticketmanager.customdatamanager.PreticketExcelwriter;
import preticketmanager.model.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class SettlementManager_GUI implements ActionListener {
	JFrame frame;

	JButton settlementButton;
	JDialog settlementDialog;
	JLabel settlementStateLabel;
	Ticket ticket = new Ticket();
	PreticketExcelwriter pe;
	
	/*결제를 위한 자료값으로 쓰기위한 값들*/
	Movie movie;
	Theather theather;
	ScreenRoom screenRoom;
	String date;
	String time;
	int number;
	
	public SettlementManager_GUI(){
		settlementButton = new JButton("결제하기");
		settlementDialog = new JDialog(frame, "결제하기");
		settlementStateLabel = new JLabel("결제중입니다", JLabel.CENTER);
		ticket = new Ticket();
		try
	    {
	       UIManager.setLookAndFeel ("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	    } catch (Exception e)
	    {
	        e.printStackTrace();
	    }

		try{
			pe = new PreticketExcelwriter();
		}catch(Exception e){
		}
	}
	
	public void settlementDialog(JFrame frame, Movie movie, Theather theather, ScreenRoom screenRoom, String date, String time, int number){
		this.frame = frame;
		
		this.movie = movie;
		this.theather = theather;
		this.screenRoom = screenRoom;
		this.date = date;
		this.time = time;
		this.number = number;
		
		settlementDialog.setLayout(new BorderLayout());
		settlementDialog.setSize(500, 300);
		settlementDialog.setResizable(false);
		settlementDialog.setModal(true);
		settlementDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		Dimension di, di1;
		di = Toolkit.getDefaultToolkit().getScreenSize();
		di1 = settlementDialog.getSize();
		settlementDialog.setLocation((int)(di.getWidth()/2 - di1.getWidth()/2), (int)(di.getHeight()/2 - di1.getHeight()/2));
		
		JLabel titleLabel = new JLabel("현재 선택한 영화/극장 정보", JLabel.CENTER);
		settlementDialog.add("North", titleLabel);
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(5, 1));
		JLabel movieNameAndTheatherNameLabel = new JLabel(movie.getName() + " - " + theather.getName(), JLabel.CENTER);
		JLabel dateLabel = new JLabel(" 날짜 : " + date, JLabel.CENTER);
		JLabel timeLabel = new JLabel("시간 : " + time, JLabel.CENTER);
		JLabel numberLabel = new JLabel("예약인원수 : " + number + "명", JLabel.CENTER);
		centerPanel.add(movieNameAndTheatherNameLabel);
		centerPanel.add(dateLabel);
		centerPanel.add(timeLabel);
		centerPanel.add(numberLabel);
		ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton settlementRadioButton1 = new JRadioButton("카드 결제");
		JRadioButton settlementRadioButton2 = new JRadioButton("무통장 입금");
		JRadioButton settlementRadioButton3 = new JRadioButton("상품권 결제");
		buttonGroup.add(settlementRadioButton1);
		buttonGroup.add(settlementRadioButton2);
		buttonGroup.add(settlementRadioButton3);
		JPanel radioButtonPanel = new JPanel();
		JLabel radioButtonContentLabel = new JLabel("결제 방법 선택 : ");
		radioButtonPanel.add(radioButtonContentLabel);
		radioButtonPanel.add(settlementRadioButton1);
		radioButtonPanel.add(settlementRadioButton2);
		radioButtonPanel.add(settlementRadioButton3);
		settlementRadioButton1.setSelected(true);
		centerPanel.add(radioButtonPanel);
		settlementDialog.add("Center", centerPanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(settlementButton);
		settlementDialog.add("South", buttonPanel);
		
		/*리스너 등록*/
		settlementButton.addActionListener(this);
		
		settlementDialog.setVisible(true);
	}
	public void settlementfinish(){
		settlementDialog.dispose();
		JOptionPane.showMessageDialog(frame, "결제가 완료되었습니다");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == settlementButton){
			pe.create(theather.getTheatherNumber(), movie.getName(), theather.getName(), 
					screenRoom.getScreenRoomNumber(), 
					screenRoom.getScreenNumber(), 
					movie.getRunningTime(), 
					time, number, date, LogManager_GUI.getUser().getID());
			settlementfinish();
		}
		e.setSource(null);
	}
}
