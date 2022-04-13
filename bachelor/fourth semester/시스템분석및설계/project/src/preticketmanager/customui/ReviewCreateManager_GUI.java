package preticketmanager.customui;

import preticketmanager.admindatamanager.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class ReviewCreateManager_GUI implements ActionListener {
	JFrame frame;
	int index;
	ReviewExcelWriter reviewExcelWriter;

	JDialog reviewDialog = new JDialog();
	JTextField reviewWriteTextField = new JTextField(10);
	JButton reviewSaveButton = new JButton("저장하기");
	
	public ReviewCreateManager_GUI(){
		reviewDialog = new JDialog();
		reviewWriteTextField = new JTextField(10);
		reviewSaveButton = new JButton("저장하기");
		try
	    {
	       UIManager.setLookAndFeel ("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	    } catch (Exception e)
	    {
	        e.printStackTrace();
	    }
		try{
			reviewExcelWriter = new ReviewExcelWriter();
		}catch(Exception e){}
	}
	public void display(JFrame frame, int index){
		this.frame = frame;
		this.index = index;
		
		reviewDialog.setSize(300, 120);
		reviewDialog.setModal(true);
		reviewDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		Dimension di, di1;
		di = Toolkit.getDefaultToolkit().getScreenSize();
		di1 = reviewDialog.getSize();
		reviewDialog.setLocation((int)(di.getWidth()/2 - di1.getWidth()/2), (int)(di.getHeight()/2 - di1.getHeight()/2));
		
		reviewDialog.setLayout(new BorderLayout());
		JLabel titleLabel = new JLabel("REVIEW / 리뷰 (아래에 글을 작성해주세요)", JLabel.CENTER);
		reviewDialog.add("North", titleLabel);
		reviewDialog.add("Center", new JScrollPane(reviewWriteTextField));
		reviewDialog.add("South", reviewSaveButton);
		
		/*리스너등록*/
		reviewSaveButton.addActionListener(this);
		
		reviewDialog.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == reviewSaveButton){
			reviewExcelWriter.createReview(index, LogManager_GUI.getUser().getID(), reviewWriteTextField.getText());
			JOptionPane.showMessageDialog(frame, "리뷰가 등록되었습니다.");
			reviewWriteTextField.setText("");
			reviewDialog.dispose();
		}
		e.setSource(null);
	}
}
