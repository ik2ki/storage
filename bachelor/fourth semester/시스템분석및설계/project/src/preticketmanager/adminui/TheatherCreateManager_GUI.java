package preticketmanager.adminui;

import preticketmanager.System.*;
import preticketmanager.admindatamanager.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class TheatherCreateManager_GUI implements ActionListener, ItemListener {
	JFrame frame;
	
	TheatherExcelWriter theatherExcelWriter;
	
	/*입력시 정보들을 저장하는 모든 변수들 - 영화관*/
	String theatherName;
	String theatherLocation;
	int screenRoomNumber;
	int theaterStartTime, theatherStartMinute;
	int theatherCloseTime, theatherCloseMinute;
	
	/*display에서 쓰는 변수들*/
	JDialog theatherCreateDialog;
	JButton nextButton;
	JButton cancelButton;
	JTextField theatherNameTextField;
	JTextField theatherLocationTextField;
	JTextField screenRoomNumberTextField;
	JTextField theatherStartTimeTextField;
	JTextField theatherStartMinuteTextField;
	JTextField theatherCloseTimeTextField;
	JTextField theatherCloseMinuteTextField;
	
	/*deleteTheather 에서 쓰는 변수들*/
	JDialog theatherDeleteDialog;
	List theatherList;
	JButton theatherDeleteSubmitButton;
	JButton theatherDeleteCancelButton;
	int theatherSelectNumber;
	
	public TheatherCreateManager_GUI(){
		theatherExcelWriter = new TheatherExcelWriter();
		
		theatherCreateDialog = new JDialog();
		nextButton = new JButton("등록");
		cancelButton = new JButton("취소");
		theatherNameTextField = new JTextField(15);
		theatherLocationTextField = new JTextField(15);
		screenRoomNumberTextField = new JTextField(15);
		theatherStartTimeTextField = new JTextField(5);
		theatherStartMinuteTextField = new JTextField(5);
		theatherCloseTimeTextField = new JTextField(5);
		theatherCloseMinuteTextField = new JTextField(5);
		
		theatherDeleteDialog = new JDialog();
		theatherList = new List();
		theatherDeleteSubmitButton = new JButton("삭제");
		theatherDeleteCancelButton = new JButton("취소");
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
		
		theatherCreateDialog.setLayout(new BorderLayout());
		theatherCreateDialog.setSize(370, 200);
		theatherCreateDialog.setResizable(false);
		theatherCreateDialog.setModal(true);
		theatherCreateDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		Dimension di, di1;
		di = Toolkit.getDefaultToolkit().getScreenSize();
		di1 = theatherCreateDialog.getSize();
		theatherCreateDialog.setLocation((int)(di.getWidth()/2 - di1.getWidth()/2), (int)(di.getHeight()/2 - di1.getHeight()/2));	
		
		
		JLabel titleLabel = new JLabel("극장 생성 | MAKE THEATHER  ", JLabel.CENTER);
		theatherCreateDialog.add("North", titleLabel);
		
		JPanel[] layoutPanel = new JPanel[10];
		for(int i = 0; i < 10; i++){
			layoutPanel[i] = new JPanel();
			if(i%2 == 0){
				layoutPanel[i].setLayout(new FlowLayout(FlowLayout.LEFT));
			}
		}
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(4, 2));
		JLabel theatherNameLabel = new JLabel("극장 이름 : ");
		layoutPanel[0].add(theatherNameLabel);
		layoutPanel[1].add(theatherNameTextField);
		centerPanel.add(layoutPanel[0]);
		centerPanel.add(layoutPanel[1]);
		JLabel theatherLocationLabel = new JLabel("극장 위치 : ");
		layoutPanel[2].add(theatherLocationLabel);
		layoutPanel[3].add(theatherLocationTextField);
		centerPanel.add(layoutPanel[2]);
		centerPanel.add(layoutPanel[3]);
		JLabel screenRoomNumberLabel = new JLabel("상영관 개수 : ");
		layoutPanel[4].add(screenRoomNumberLabel);
		layoutPanel[5].add(screenRoomNumberTextField);
		centerPanel.add(layoutPanel[4]);
		centerPanel.add(layoutPanel[5]);
		theatherCreateDialog.add("Center", centerPanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(nextButton);
		buttonPanel.add(cancelButton);
		theatherCreateDialog.add("South", buttonPanel);
		
		/*리스너 등록*/
		nextButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		theatherCreateDialog.setVisible(true);
	}

	public void deleteTheather(){
		theatherDeleteDialog.setLayout(new BorderLayout());
		theatherDeleteDialog.setSize(400, 380);
		theatherDeleteDialog.setResizable(false);
		theatherDeleteDialog.setModal(true);
		theatherDeleteDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		Dimension di, di1;
		di = Toolkit.getDefaultToolkit().getScreenSize();
		di1 = theatherDeleteDialog.getSize();
		theatherDeleteDialog.setLocation((int)(di.getWidth()/2 - di1.getWidth()/2), (int)(di.getHeight()/2 - di1.getHeight()/2));	
		
		
		JLabel titleLabel = new JLabel("극장 삭제", JLabel.CENTER);
		theatherDeleteDialog.add("North", titleLabel);
		JPanel centerPanel = new JPanel();
		TheatherFile tf = new TheatherFile();
		for(int i = 1; i < tf.getNumberOfTheather(); i++){
			theatherList.add(tf.getName(i));
		}
		centerPanel.add("Center", new JScrollPane(theatherList));
		theatherDeleteDialog.add(centerPanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(theatherDeleteSubmitButton);
		buttonPanel.add(theatherDeleteCancelButton);
		theatherDeleteDialog.add("South", buttonPanel);
		
		/*리스너 등록*/
		theatherList.addItemListener(this);
		theatherDeleteCancelButton.addActionListener(this);
		theatherDeleteSubmitButton.addActionListener(this);
		
		theatherDeleteDialog.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == nextButton){
			try{
				theatherName = theatherNameTextField.getText();
				theatherLocation = theatherLocationTextField.getText();
				screenRoomNumber = Integer.parseInt(screenRoomNumberTextField.getText());

				theatherExcelWriter.createTheather(theatherName, theatherLocation, screenRoomNumber);
				JOptionPane.showMessageDialog(frame, "극장이 생성되었습니다");
				theatherCreateDialog.dispose();
			}
			catch (NumberFormatException e1){
				JOptionPane.showMessageDialog(frame, "숫자입력칸을 확인후 다시 입력해주세요");
				theatherCreateDialog.dispose();
			}
		}
		if(e.getSource() == cancelButton){
			theatherCreateDialog.dispose();
		}
		/*theatherDelete 관련 액션*/
		else if(e.getSource() == theatherDeleteSubmitButton){
			try{
				TheatherExcelWriter mew = new TheatherExcelWriter();
				mew.deleteTeather(theatherSelectNumber+1);
				JOptionPane.showMessageDialog(frame, "극장이 삭제되었습니다");
				theatherList.remove(theatherSelectNumber);
			}catch(Exception e2){}
		}
		else if(e.getSource() == theatherDeleteCancelButton){
			theatherDeleteDialog.dispose();
		}
		e.setSource(null);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == theatherList){
			theatherSelectNumber = theatherList.getSelectedIndex();
		}
		e.setSource(null);
	}
}
	