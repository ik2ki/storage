package preticketmanager.adminui;


import preticketmanager.admindatamanager.ReviewExcelWriter;
import preticketmanager.customui.LogManager_GUI;
import preticketmanager.model.Review;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class ReviewManager_GUI implements ActionListener, MouseListener {
	JFrame frame;

	int index;
	int selectRow;
	Review[] reviewArray;
	ReviewExcelWriter reviewExcelWriter;
	JDialog reviewDialog;
	JButton okButton;
	JButton reviewDeleteButton;
	JTable reviewTable;

	public ReviewManager_GUI(){
		okButton = new JButton("확인");
		reviewDeleteButton = new JButton("리뷰 삭제");
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
		
		reviewDialog = new JDialog();
		
		reviewDialog.setSize(500, 520);
		reviewDialog.setResizable(false);
		reviewDialog.setModal(true);
		reviewDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		Dimension di, di1;
		di = Toolkit.getDefaultToolkit().getScreenSize();
		di1 = reviewDialog.getSize();
		reviewDialog.setLocation((int)(di.getWidth()/2 - di1.getWidth()/2), (int)(di.getHeight()/2 - di1.getHeight()/2));
		
		JLabel titleLabel = new JLabel("리뷰", JLabel.CENTER);
		reviewDialog.add("North", titleLabel);
		
		JPanel centerPanel = new JPanel();
		reviewArray = reviewExcelWriter.getReview(index);
		String[][] data;
		String[] title = {"아이디", "내용"};
		if(reviewArray == null){
			data = new String[1][2];
			data[0][0] = "    ";
			data[0][1] = "     ";
		}
		else{
			data = new String[reviewArray.length][2];
			for(int i = 0; i < reviewArray.length; i++){
					data[i][0] = reviewArray[i].getID();
					data[i][1] = reviewArray[i].getReview();
			}
		}
		reviewTable = new JTable(data, title);
		JScrollPane reviewScrollPane = new JScrollPane(reviewTable);
		centerPanel.add(reviewScrollPane);
		reviewDialog.add("Center", centerPanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okButton);
		buttonPanel.add(reviewDeleteButton);
		reviewDialog.add("South", buttonPanel);
		
		/*리스너 등록*/
		okButton.addActionListener(this);
		reviewDeleteButton.addActionListener(this);
		reviewTable.addMouseListener(this);
		
		
		reviewDialog.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == okButton){
			reviewDialog.dispose();
		}
		else if(e.getSource() == reviewDeleteButton){
			if(LogManager_GUI.getUser() == null){
				JOptionPane.showMessageDialog(frame, "로그인후 이용해주세요");
				reviewDialog.dispose();
			}
			else{
				if(reviewExcelWriter.delete(index, selectRow, LogManager_GUI.getUser().getID())){
					JOptionPane.showMessageDialog(frame, "삭제되었습니다");
					reviewDialog.dispose();
				}
				else{
					JOptionPane.showMessageDialog(frame, "삭제할 수 없습니다. 확인후 다시 시도해주세요");
				}
			}
		}
		e.setSource(null);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == reviewTable){
			selectRow = reviewTable.getSelectedRow() + 1;
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
