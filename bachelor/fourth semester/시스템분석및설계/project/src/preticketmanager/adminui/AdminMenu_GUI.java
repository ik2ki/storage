package preticketmanager.adminui;

import preticketmanager.System.*;
import preticketmanager.admindatamanager.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class AdminMenu_GUI implements ActionListener, ItemListener {
	JFrame frame;
	
	/*������ ù�޴� ǥ�� ���� ���Ǵ� ������*/
	JDialog adminMenuDialog;
	JButton movieInsertButton;
	JButton movieDeleteButton;
	JButton theatherInsertButton;
	JButton theatherDeleteButton;
	
	/*��ȭ������ ���Ǵ� ������*/
	JDialog movieDeleteDialog;
	List movieList;
	int movieSelectNumber;
	JButton movieDeleteSubmitButton;
	JButton movieDeleteCancelButton;
	
	/*�����Ͽ� ���Ǵ� ������*/
	JDialog theatherInsertDialog;
	JButton theatherCreateButton;
	JButton screenRoomCreateButton;

	public AdminMenu_GUI(){
		adminMenuDialog = new JDialog();
		movieInsertButton = new JButton("��ȭ ���/����");
		movieDeleteButton = new JButton("��ȭ����");
		theatherInsertButton = new JButton("��ȭ�� ���/����");
		theatherDeleteButton = new JButton("��ȭ�� ����");
		
		movieList = new List(18, false);
		movieDeleteSubmitButton = new JButton("��ȭ����");
		movieDeleteCancelButton = new JButton("���");
		
		/*�����Ͽ� ���Ǵ� ������*/
		theatherInsertDialog = new JDialog();
		theatherCreateButton = new JButton("���� ���");
		screenRoomCreateButton = new JButton("�󿵰� ���/����");
		try
	    {
	       UIManager.setLookAndFeel ("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	    } catch (Exception e)
        {
            e.printStackTrace();
        }
		theatherInsertMenu();
	}

	
	public void display(JFrame frame){
		this.frame = frame;
		
		adminMenuDialog.setLayout(new BorderLayout());
		adminMenuDialog.setSize(270, 250);
		adminMenuDialog.setResizable(false);
		adminMenuDialog.setModal(true);
		adminMenuDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		Dimension di, di1;
		di = Toolkit.getDefaultToolkit().getScreenSize();
		di1 = adminMenuDialog.getSize();
		adminMenuDialog.setLocation((int)(di.getWidth()/2 - di1.getWidth()/2), (int)(di.getHeight()/2 - di1.getHeight()/2));
		
		
		JLabel titleLabel = new JLabel("������ �޴�", JLabel.CENTER);
		adminMenuDialog.add("North", titleLabel);
		
		JPanel buttonPanel = new JPanel();
		JPanel[] layoutPanel = new JPanel[4];
		for(int i = 0; i < 4 ; i++){
			layoutPanel[i] = new JPanel();
		}
		Box buttonBox = Box.createVerticalBox();
		
		layoutPanel[0].add(movieInsertButton);
		buttonBox.add(layoutPanel[0]);
		buttonBox.add(Box.createVerticalStrut(5));
		layoutPanel[1].add(movieDeleteButton);
		buttonBox.add(layoutPanel[1]);
		buttonBox.add(Box.createVerticalStrut(5));
		layoutPanel[2].add(theatherInsertButton);
		buttonBox.add(layoutPanel[2]);
		buttonBox.add(Box.createVerticalStrut(5));
		layoutPanel[3].add(theatherDeleteButton);
		buttonBox.add(layoutPanel[3]);
		buttonPanel.add(buttonBox);
		
		adminMenuDialog.add("Center", buttonPanel);
		
		/*������ ���*/
		movieInsertButton.addActionListener(this);
		movieDeleteButton.addActionListener(this);
		theatherInsertButton.addActionListener(this);
		theatherDeleteButton.addActionListener(this);

		adminMenuDialog.setVisible(true);
	}
	public void movieDeleteMenu(){
		movieDeleteDialog = new JDialog();
		movieDeleteDialog.setLayout(new BorderLayout());
		movieDeleteDialog.setSize(400, 380);
		movieDeleteDialog.setResizable(false);
		movieDeleteDialog.setModal(true);
		movieDeleteDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		Dimension di, di1;
		di = Toolkit.getDefaultToolkit().getScreenSize();
		di1 = movieDeleteDialog.getSize();
		movieDeleteDialog.setLocation((int)(di.getWidth()/2 - di1.getWidth()/2), (int)(di.getHeight()/2 - di1.getHeight()/2));	
		
		JLabel titleLabel = new JLabel("��ȭ ����", JLabel.CENTER);
		movieDeleteDialog.add("North", titleLabel);
		JPanel centerPanel = new JPanel();
		MovieFile fs = new MovieFile();
		movieList.removeAll();
		for(int i = 1; i < fs.getNumberOfMovie(); i++){
			movieList.add(fs.getName(i));
		}
		centerPanel.add("Center", new JScrollPane(movieList));
		movieDeleteDialog.add(centerPanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(movieDeleteSubmitButton);
		buttonPanel.add(movieDeleteCancelButton);
		movieDeleteDialog.add("South", buttonPanel);
		
		
		/*������ ���*/
		movieList.addItemListener(this);
		movieDeleteSubmitButton.addActionListener(this);
		movieDeleteCancelButton.addActionListener(this);
		
		movieDeleteDialog.setVisible(true);
	}
	public void theatherInsertMenu(){
		theatherInsertDialog.setLayout(new BorderLayout());
		theatherInsertDialog.setSize(300, 150);
		theatherInsertDialog.setResizable(false);
		theatherInsertDialog.setModal(true);
		theatherInsertDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		Dimension di, di1;
		di = Toolkit.getDefaultToolkit().getScreenSize();
		di1 = theatherInsertDialog.getSize();
		theatherInsertDialog.setLocation((int)(di.getWidth()/2 - di1.getWidth()/2), (int)(di.getHeight()/2 - di1.getHeight()/2));
		
		JLabel titleLabel = new JLabel("����and�󿵰� ���/����", JLabel.CENTER);
		theatherInsertDialog.add("North", titleLabel);
		JPanel centerPanel = new JPanel();
		JPanel[] layoutPanel = new JPanel[2];
		for(int i = 0; i < 2 ; i++){
			layoutPanel[i] = new JPanel();
		}
		Box buttonBox = Box.createVerticalBox();
		
		layoutPanel[0].add(theatherCreateButton);
		buttonBox.add(layoutPanel[0]);
		buttonBox.add(Box.createVerticalStrut(5));
		layoutPanel[1].add(screenRoomCreateButton);
		buttonBox.add(layoutPanel[1]);
		centerPanel.add(buttonBox);
		theatherInsertDialog.add("Center", centerPanel);
		
		/*������ ���*/
		theatherCreateButton.addActionListener(this);
		screenRoomCreateButton.addActionListener(this);
		
	}
	public void theatherDeleteMenu(){
		TheatherCreateManager_GUI tcm = new TheatherCreateManager_GUI();
		tcm.deleteTheather();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		/*ù ������ �׼�*/
		if(e.getSource() == movieInsertButton){
			MovieCreateManager_GUI mcm = new MovieCreateManager_GUI();
			mcm.movieModifyMenu();
		}
		else if(e.getSource() == movieDeleteButton){
			movieList.removeAll();
			MovieFile fs = new MovieFile();
			for(int i = 1; i < fs.getNumberOfMovie(); i++){
				movieList.add(fs.getName(i));
			}
			movieDeleteMenu();
		}
		else if(e.getSource() == theatherInsertButton){
			theatherInsertDialog.setVisible(true);
		}
		/*movieInsert ���� �׼�*/

		/*movieDelete ���� �׼�*/
		else if(e.getSource() == movieDeleteSubmitButton){
			try{
				MovieExcelWriter mew = new MovieExcelWriter();
				mew.delete(movieSelectNumber+1);
				JOptionPane.showMessageDialog(frame, "��ȭ�� �����Ǿ����ϴ�");
				movieList.remove(movieSelectNumber);
			}catch(Exception e2){}
		}
		else if(e.getSource() == movieDeleteCancelButton){
			movieDeleteDialog.dispose();
		}
		/*TheatherInsert ���� �׼�*/
		else if(e.getSource() == theatherCreateButton){
			TheatherCreateManager_GUI tcm = new TheatherCreateManager_GUI();
			tcm.display(frame);
		}
		else if(e.getSource() == screenRoomCreateButton){
			ScreenRoomCreateManager_GUI scm = new ScreenRoomCreateManager_GUI();
			scm.display(frame);
		}
		/*TheatherDelete ���� �׼�*/
		else if(e.getSource() == theatherDeleteButton){
			theatherDeleteMenu();
		}
		e.setSource(null);
	}
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == movieList){
			movieSelectNumber = movieList.getSelectedIndex();
		}

		e.setSource(null);
	}
}
