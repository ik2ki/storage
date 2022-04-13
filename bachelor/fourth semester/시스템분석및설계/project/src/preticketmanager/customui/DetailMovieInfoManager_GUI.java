package preticketmanager.customui;

import preticketmanager.System.*;
import preticketmanager.start.*;
import preticketmanager.adminui.ReviewManager_GUI;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;

public class DetailMovieInfoManager_GUI implements ActionListener {
	JFrame frame;
	int index;
	
	MovieFile movieFile = new MovieFile();
	ReviewCreateManager_GUI reviewCreateManager_GUI;
	ReviewManager_GUI reviewManager_GUI;

	JDialog detailMovieInfoDialog;
	JButton okButton;
	JButton seachReviewButton;
	JButton reviewButton;

	public DetailMovieInfoManager_GUI(){
		movieFile = new MovieFile();
		reviewCreateManager_GUI = new ReviewCreateManager_GUI();
		reviewManager_GUI = new ReviewManager_GUI();

		detailMovieInfoDialog = new JDialog();
		okButton = new JButton("확인");
		seachReviewButton = new JButton("리뷰보기");
		reviewButton = new JButton("리뷰남기기");
	}
	public void display(JFrame frame, int index){
		this.frame = frame;
		this.index = index;
		
		try
	    {
	       UIManager.setLookAndFeel ("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	    } catch (Exception e)
	    {
	        e.printStackTrace();
	    }

		
		detailMovieInfoDialog.setLayout(new BorderLayout());
		detailMovieInfoDialog.setSize(500, 600);
		detailMovieInfoDialog.setResizable(false);
		detailMovieInfoDialog.setModal(true);
		detailMovieInfoDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		Dimension di, di1;
		di = Toolkit.getDefaultToolkit().getScreenSize();
		di1 = detailMovieInfoDialog.getSize();
		detailMovieInfoDialog.setLocation((int)(di.getWidth()/2 - di1.getWidth()/2), (int)(di.getHeight()/2 - di1.getHeight()/2));
		

		BevelBorder bb = new BevelBorder(BevelBorder.RAISED);
		TitledBorder tb = new TitledBorder(bb, movieFile.getName(index), TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.setBorder(tb);
		ImageIcon image = new ImageIcon(Driver.BASEROUTE + "image/" + movieFile.getImage(index));
		JLabel imageLabel = new JLabel(image);
		centerPanel.add("North", imageLabel);
		
		JPanel centerSouthPanel = new JPanel();
		centerSouthPanel.setLayout(new BorderLayout());
		JPanel centerSouthNorthPanel = new JPanel();
		centerSouthNorthPanel.setLayout(new GridLayout(6, 1));
		JLabel genreLabel = new JLabel("장르 : " + movieFile.getGenre(index), JLabel.CENTER);
		centerSouthNorthPanel.add(genreLabel);
		JLabel ratingLabel = new JLabel("등급 : " + movieFile.getRating(index), JLabel.CENTER);
		centerSouthNorthPanel.add(ratingLabel);
		JLabel directorLabel = new JLabel("감독 : " + movieFile.getDirector(index), JLabel.CENTER);
		centerSouthNorthPanel.add(directorLabel);
		JLabel mainActorLabel = new JLabel("주연 배우 : " + movieFile.getMainActor(index), JLabel.CENTER);
		centerSouthNorthPanel.add(mainActorLabel);
		JLabel runningTimeLabel = new JLabel("상영 시간 : " + movieFile.getRunningTime(index) + "분", JLabel.CENTER);
		centerSouthNorthPanel.add(runningTimeLabel);
		JLabel releaseDateLabel = new JLabel("개봉 날짜 : " + movieFile.getReleaseDate(index), JLabel.CENTER);
		centerSouthNorthPanel.add(releaseDateLabel);
		centerSouthPanel.add("Center", centerSouthNorthPanel);
		JTextArea movieIntroductionTextArea = new JTextArea(" ");
		try {
			TextFileSystem textFile = new TextFileSystem();
			textFile.addRoute("introduction/");
			textFile.setFile(movieFile.getIntroductionFile(index));
			textFile.setReader();
			movieIntroductionTextArea.setRows(10);
			movieIntroductionTextArea.setLineWrap(true);
			movieIntroductionTextArea.append(textFile.readAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		movieIntroductionTextArea.setEditable(false);
		centerSouthPanel.add("South", new JScrollPane(movieIntroductionTextArea));
		centerPanel.add("Center", centerSouthPanel);
		detailMovieInfoDialog.add("Center", centerPanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okButton);
		buttonPanel.add(seachReviewButton);
		buttonPanel.add(reviewButton);
		detailMovieInfoDialog.add("South", buttonPanel);
		
		/*리스너 등록*/
		okButton.addActionListener(this);
		reviewButton.addActionListener(this);
		seachReviewButton.addActionListener(this);
		
		detailMovieInfoDialog.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == okButton){
			detailMovieInfoDialog.dispose();
		}
		else if(e.getSource() == reviewButton){
			if(LogManager_GUI.getUser() == null || LogManager_GUI.getUser().getID() == "admin"){
				JOptionPane.showMessageDialog(frame, "회원로그인이 필요합니다");
				Driver.mainFrame.logined();
			}
			else{
				reviewCreateManager_GUI.display(frame, index);
				detailMovieInfoDialog.dispose();
			}
		}
		else if(e.getSource() == seachReviewButton){
			reviewManager_GUI.display(frame, index);
		}
		e.setSource(null);
	}
}