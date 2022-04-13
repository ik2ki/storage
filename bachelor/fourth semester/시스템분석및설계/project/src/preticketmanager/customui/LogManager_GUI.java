package preticketmanager.customui;
import preticketmanager.*;
import preticketmanager.System.*;
import preticketmanager.model.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class LogManager_GUI implements ActionListener, ItemListener 
{
	public static User user = null;
	boolean agreement;
	String selectRadioButtonText;
	
	JFrame frame;
	//login메소드에서 쓰는 변수들
	JDialog loginDialog;
	ButtonGroup buttonGroup;
	JRadioButton memberRadioButton;
	JRadioButton adminRadioButton;
	JButton loginButton;
	JButton entryButton;
	JTextField idTextField;
	JPasswordField passwordPasswordField;
	
	//entry메소드에서 쓰는 변수들
	JDialog entryDialog;
	JCheckBox agreementCheckBox;
	JTextField entryIdTextField;
	JPasswordField entryPasswordPasswordField;
	JTextField nameTextField;
	JTextField ageTextField;
	JTextField cellPhoneTextField;
	JTextField emailTextField;
	
	JButton submitButton;
	JButton cancelButton;

	public LogManager_GUI(){
		loginDialog = new JDialog(frame, "로그인");
		buttonGroup = new ButtonGroup();
		memberRadioButton = new JRadioButton("일반회원 로그인");
		adminRadioButton = new JRadioButton("관리자 로그인");
		loginButton = new JButton("로그인");
		entryButton = new JButton("회원가입");
		idTextField = new JTextField(8);
		passwordPasswordField = new JPasswordField(8);
		
		entryDialog = new JDialog(frame, "회원가입");
		agreementCheckBox = new JCheckBox("이용 약관에 동의합니다");
		entryIdTextField = new JTextField(5);
		entryPasswordPasswordField = new JPasswordField(5);
		nameTextField = new JTextField(5);
		ageTextField = new JTextField(5);
		cellPhoneTextField = new JTextField(5);
		emailTextField = new JTextField(5);
		
		submitButton = new JButton("등록");
		cancelButton = new JButton("취소");
	}
	public void login(JFrame frame){
		this.frame = frame;
		
		try
	    {
	       UIManager.setLookAndFeel ("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	    } catch (Exception e)
	    {
	        e.printStackTrace();
	    }

		
		loginDialog.setSize(270, 200);
		loginDialog.setResizable(false);
		loginDialog.setModal(true);
		loginDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		Dimension di, di1;
		di = Toolkit.getDefaultToolkit().getScreenSize();
		di1 = loginDialog.getSize();
		loginDialog.setLocation((int)(di.getWidth()/2 - di1.getWidth()/2), (int)(di.getHeight()/2 - di1.getHeight()/2));
		
		Container con = loginDialog.getContentPane();
		con.setLayout(new BorderLayout());
		
		JLabel northLabel = new JLabel("  로그인/Login");
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(2, 1));
		
		JPanel layoutPanel1 = new JPanel();
		layoutPanel1.setLayout(new GridLayout(1, 2));
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2, 1));
		JLabel idLabel = new JLabel("  ID : ");
		JLabel passwordLabel = new JLabel("  PASSWORD : ");
		panel1.add(idLabel);
		panel1.add(passwordLabel);
		JPanel panel2 = new JPanel();
		panel2.add(idTextField);
		panel2.add(passwordPasswordField);
		layoutPanel1.add(panel1);
		layoutPanel1.add(panel2);
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout());
		panel3.add(loginButton);
		panel3.add(entryButton);
		
		centerPanel.add(layoutPanel1);
		centerPanel.add(panel3);
		
		JPanel southPanel = new JPanel();
		memberRadioButton.setSelected(true);
		selectRadioButtonText = "일반회원 로그인";
		buttonGroup.add(memberRadioButton);
		buttonGroup.add(adminRadioButton);
		southPanel.add(memberRadioButton);
		southPanel.add(adminRadioButton);

		con.add("North", northLabel);
		con.add("Center", centerPanel);
		con.add("South", southPanel);

		/*액션리스너등록*/
		loginButton.addActionListener(this);
		passwordPasswordField.addActionListener(this);
		entryButton.addActionListener(this);
		memberRadioButton.addActionListener(this);
		adminRadioButton.addActionListener(this);
		
		loginDialog.setVisible(true);
	}
	public void logout(JFrame frame){
		this.frame = frame;
		
		if(JOptionPane.showConfirmDialog(frame, "로그아웃 하시겠습니까?", "로그아웃", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == 0){
			JOptionPane.showMessageDialog(frame, "로그아웃 되었습니다");
			LogManager_GUI.setUser(null);
		}
	}
	public boolean withdrawal(JFrame frame) {
		this.frame = frame;
		
		if(JOptionPane.showConfirmDialog(frame, "정말 회원탈퇴 하시겠습니까?", "회원탈퇴", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == 0){
			try{
				UserManager mm = new UserManager(1);
				int memberID = mm.findUser(LogManager_GUI.getUser().getID(), LogManager_GUI.getUser().getPassword());
				mm.delete(memberID);
				JOptionPane.showMessageDialog(frame, "회원탈퇴 되었습니다");
				LogManager_GUI.setUser(null);
				return true;
			}
			catch(Exception e){
			}
		}
		else{
			return false;
		}
		return false;
	}
	public void entry(JFrame frame){
		this.frame = frame;
		
		entryDialog.setSize(400, 530);
		entryDialog.setResizable(false);
		entryDialog.setModal(true);
		entryDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		Dimension di, di1;
		di = Toolkit.getDefaultToolkit().getScreenSize();
		di1 = entryDialog.getSize();
		entryDialog.setLocation((int)(di.getWidth()/2 - di1.getWidth()/2), (int)(di.getHeight()/2 - di1.getHeight()/2));
		
		Container con = entryDialog.getContentPane();
		con.setLayout(new BorderLayout());
		
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BorderLayout());
		JLabel northLabel = new JLabel("회원가입", JLabel.CENTER);
		JTextArea memberStipulationTextArea = new JTextArea(" ");
		memberStipulationTextArea.setRows(15);
		try{
			TextFileSystem textFile = new TextFileSystem();
			textFile.setFile("memberStipulation.txt");
			textFile.setReader();
			memberStipulationTextArea.append(textFile.readAll());
		}catch(Exception e){}
		memberStipulationTextArea.setEditable(false);
		memberStipulationTextArea.setLineWrap(true);
		JScrollPane memberStipulationScrollPane = new JScrollPane(memberStipulationTextArea);
		//memberStipulationScrollPane.setVerticalScrollBarPolicy(JScrollPane.);
		JPanel southPanel = new JPanel();
		southPanel.add(agreementCheckBox);
		
		
		northPanel.add("North", northLabel);
		northPanel.add("Center", memberStipulationScrollPane);
		northPanel.add("South", southPanel);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(6, 2));
		JLabel idLabel = new JLabel("  ID : ");
		centerPanel.add(idLabel);
		centerPanel.add(entryIdTextField);
		
		JLabel passwordLabel = new JLabel("  PASSWORD : ");
		centerPanel.add(passwordLabel);
		centerPanel.add(entryPasswordPasswordField);
		
		JLabel nameLabel = new JLabel("  이름 : ");
		centerPanel.add(nameLabel);
		centerPanel.add(nameTextField);
		
		JLabel ageLabel = new JLabel("  나이  (숫자만입력) : ");
		centerPanel.add(ageLabel);
		centerPanel.add(ageTextField);
		
		JLabel cellPhoneLabel = new JLabel("  전화번호 ( -  없이 입력) : ");
		centerPanel.add(cellPhoneLabel);
		centerPanel.add(cellPhoneTextField);
		
		JLabel emailPhoneLabel = new JLabel("  이메일 : ");
		centerPanel.add(emailPhoneLabel);
		centerPanel.add(emailTextField);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(submitButton);
		buttonPanel.add(cancelButton);
		
		con.add("North", northPanel);
		con.add("Center", centerPanel);
		con.add("South", buttonPanel);
		
		/*액션리스너등록*/
		agreementCheckBox.addItemListener(this);
		submitButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		entryDialog.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == loginButton || e.getSource() ==  passwordPasswordField){
			UserManager userManager;
			try{
				if(selectRadioButtonText.equals("일반회원 로그인")){
					userManager = new UserManager(1);
				}
				else{
					userManager = new UserManager(2);
				}
				String id = idTextField.getText();
				String password = new String(passwordPasswordField.getPassword());

				int index = userManager.findUser(id, password);
				if(index == -1 ){
					JOptionPane.showMessageDialog(frame, "비밀번호가 틀렸습니다");
					idTextField.setText("");
					passwordPasswordField.setText("");
					idTextField.requestFocus();
				}
				else if(index == -2) {
					JOptionPane.showMessageDialog(frame, "아이디가 틀렸습니다");
					idTextField.setText("");
					passwordPasswordField.setText("");
					idTextField.requestFocus();
				}
				else{
					JOptionPane.showMessageDialog(frame, "로그인 되었습니다");
					LogManager_GUI.setUser(userManager.loadUser(index));
					loginDialog.dispose();
				}
			}catch(Exception exception){
				
			}
		}
		else if(e.getSource() == entryButton){
			loginDialog.dispose();
			entry(frame);
		}
		else if(e.getSource() == submitButton){
			if(agreement == true){
				if(!entryIdTextField.getText().equals("") && !new String(entryPasswordPasswordField.getPassword()).equals("") && !nameTextField.getText().equals("") && !ageTextField.getText().equals("") && !cellPhoneTextField.getText().equals("") && !emailTextField.getText().equals("")){
					try {
						UserManager mm = new UserManager(1);
						if(mm.checkOverlap(entryIdTextField.getText())){
							try{
							mm.create(entryIdTextField.getText(), new String(entryPasswordPasswordField.getPassword()), nameTextField.getText(), Integer.parseInt(ageTextField.getText()), cellPhoneTextField.getText(), emailTextField.getText());
							JOptionPane.showMessageDialog(frame, "회원가입을 축하합니다. 다시 로그인해주세요");
							}
							catch (NumberFormatException e1){
								JOptionPane.showMessageDialog(frame, "숫자입력칸을 확인후 다시 입력해주세요");
							}
							entryDialog.dispose();
						}
						else if(!mm.checkOverlap(entryIdTextField.getText()) || entryIdTextField.getText().equals("admin")){
							JOptionPane.showMessageDialog(frame, "아이디가 중복되셨습니다 다시 입력해주세요");
							entryPasswordPasswordField.setText("");
							entryIdTextField.requestFocus();
							entryDialog.setVisible(true);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				else{
					JOptionPane.showMessageDialog(frame, "항목을 빠짐없이 입력해주세요");
				}
			}
			else{
				JOptionPane.showMessageDialog(frame, "약관에 동의해주세요");
			}
		}
		else if(e.getSource() == cancelButton){
			entryDialog.dispose();
		}
		else if(e.getSource() == memberRadioButton || e.getSource() == adminRadioButton){
			selectRadioButtonText = e.getActionCommand();
		}
		e.setSource(null);
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == agreementCheckBox){
			if(agreement == true)
				agreement = false;
			else
				agreement = true;
		}
		else{
			agreement = false;
		}
		e.setSource(null);
	}
	public static void setUser(User user) {
		LogManager_GUI.user = user;
	}
	public static User getUser() {
		return user;
	}
}
