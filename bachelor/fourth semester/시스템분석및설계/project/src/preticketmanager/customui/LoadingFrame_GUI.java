package preticketmanager.customui;
import javax.swing.*;

import java.awt.*;

public class LoadingFrame_GUI extends JFrame {
	JLabel titleLabel;
	JLabel massageLabel;
	
	public LoadingFrame_GUI(){
		super("로딩중..");
		titleLabel = new JLabel("영화예매 프로그램");
		massageLabel = new JLabel("작업 표시", JLabel.CENTER);
		
		//swing 디자인 적용
		try
	    {
	       UIManager.setLookAndFeel ("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	    } catch (Exception e)
        {
            e.printStackTrace();
        }
	}
	public void display(){
		this.setSize (500, 200);
		this.setResizable(false);

		Dimension di, di1;
		di = Toolkit.getDefaultToolkit().getScreenSize();
		di1 = this.getSize();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation((int)(di.getWidth()/2 - di1.getWidth()/2),(int)(di.getHeight()/2 - di1.getHeight()/2));
		this.setLayout(new BorderLayout());
		
		JPanel titlePanel = new JPanel();
		ImageIcon image = new ImageIcon("./src/preticketmanager/data/image/titleImage.gif");
		JLabel titleImageLabel = new JLabel(image);
		titlePanel.add(titleImageLabel);
		this.add("Center", titlePanel);
		
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(2, 2));
		JLabel loadingLabel = new JLabel("Loding중...", JLabel.CENTER);
		southPanel.add(loadingLabel);
		JLabel programmingInfo = new JLabel("명지대학교 컴퓨터소프트웨어학과", JLabel.CENTER);
		southPanel.add(programmingInfo);
		southPanel.add(massageLabel);
		JLabel programmingInfo2 = new JLabel("김한호, 신동욱, 이환희, 김광국", JLabel.CENTER);
		southPanel.add(programmingInfo2);
		this.add("South", southPanel);
		
		this.setVisible(true);
	}
	public void setText(String str){
		massageLabel.setText(str);
	}
}
