package preticketmanager.adminui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import preticketmanager.System.MovieFile;
import preticketmanager.admindatamanager.MovieExcelWriter;
import preticketmanager.admindatamanager.SearchExcelWriter;

public class MovieCreateManager_GUI implements ActionListener {
	JFrame frame;
	MovieFile movieFile;
	
	List movieList;
	/*영화등록에 사용되는 변수들*/
	JDialog movieInsertDialog;
	JTextField movieImageTextField;
	JFileChooser imageUploadFileChooser;
	JButton imageUploadButton;
	JTextField movieIntroductionTextField;
	JFileChooser introductionUploadFileChooser;
	JButton movieIntroductionButton;
	JTextField movieNameTextField;
	JTextField movieGenreTextField;
	JTextField movieDirectorNameTextField;
	JTextField movieMainActorNameTextField;
	JTextField movieGradeTextField;
	JTextField movieRunningTimeTextField;
	JTextField movieReleaseYearTextField;
	JTextField movieReleaseMonthTextField;
	JTextField movieReleaseDateTextField;
	JButton movieSubmitButton;
	JButton movieCancelButton;
	//임시 파일
	int check;
	
	/*영화수정에 사용되는 변수들*/
	JDialog movieModifyDialog;
	JButton movieAddButton;
	JButton movieModifyButton;
	JButton movieModifyCancelButton;
	
	public MovieCreateManager_GUI(){
		movieFile = new MovieFile();
		
		movieList = new List(18, false);
		movieImageTextField = new JTextField(7);
		imageUploadFileChooser = new JFileChooser("C://");
		imageUploadButton = new JButton("찾아보기");
		movieIntroductionTextField = new JTextField(7);
		introductionUploadFileChooser = new JFileChooser("C://");
		movieIntroductionButton = new JButton("찾아보기");
		movieNameTextField = new JTextField(15);
		movieGenreTextField = new JTextField(15);
		movieDirectorNameTextField = new JTextField(15);
		movieMainActorNameTextField = new JTextField(15);
		movieGradeTextField = new JTextField(15);
		movieRunningTimeTextField = new JTextField(15);
		movieReleaseYearTextField = new JTextField(4);
		movieReleaseMonthTextField = new JTextField(4);
		movieReleaseDateTextField = new JTextField(4);
		movieSubmitButton = new JButton("등록");
		movieCancelButton = new JButton("취소");
		
		movieAddButton = new JButton("새영화 추가");
		movieModifyButton = new JButton("수정");
		movieModifyCancelButton = new JButton("취소");
	}
	public void initTextField(){
		movieNameTextField.setText("");
		movieGenreTextField.setText("");
		movieDirectorNameTextField.setText("");
		movieMainActorNameTextField.setText("");
		movieGradeTextField.setText("");
		movieRunningTimeTextField.setText("");
		movieReleaseYearTextField.setText("");
		movieReleaseMonthTextField.setText("");
		movieReleaseDateTextField.setText("");
		movieImageTextField.setText("");
		movieIntroductionTextField.setText("");
	}
	public void setTextField(){
		int movieIndex = movieList.getSelectedIndex() + 1;
		movieNameTextField.setText(movieFile.getName(movieIndex));
		movieGenreTextField.setText(movieFile.getGenre(movieIndex));
		movieDirectorNameTextField.setText(movieFile.getDirector(movieIndex));
		movieMainActorNameTextField.setText(movieFile.getMainActor(movieIndex));
		movieGradeTextField.setText(movieFile.getRating(movieIndex));
		movieRunningTimeTextField.setText("" + movieFile.getRunningTime(movieIndex));
		String[] scrDateSplit = new String[3];
		String scrDate = movieFile.getReleaseDate(movieIndex);
		scrDateSplit = scrDate.split("-");
		String year = scrDateSplit[0]; 
		String month = scrDateSplit[1]; 
		String day = scrDateSplit[2];
		movieReleaseYearTextField.setText(year);
		movieReleaseMonthTextField.setText(month);
		movieReleaseDateTextField.setText(day);
		movieImageTextField.setText(movieFile.getImageRoute(movieIndex) + movieFile.getImage(movieIndex));
		movieIntroductionTextField.setText(movieFile.getIntroductionFileRoute(movieIndex) + movieFile.getIntroductionFile(movieIndex));
	}
	public void movieInsertMenu(int check){
		try
	    {
	       UIManager.setLookAndFeel ("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	    } catch (Exception e)
	    {
	        e.printStackTrace();
	    }

		this.check = check;
		
		movieInsertDialog = new JDialog();
		movieInsertDialog.setLayout(new BorderLayout());
		movieInsertDialog.setSize(400, 430);
		movieInsertDialog.setResizable(false);
		movieInsertDialog.setModal(true);
		movieInsertDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		Dimension di, di1;
		di = Toolkit.getDefaultToolkit().getScreenSize();
		di1 = movieInsertDialog.getSize();
		movieInsertDialog.setLocation((int)(di.getWidth()/2 - di1.getWidth()/2), (int)(di.getHeight()/2 - di1.getHeight()/2));
		
		JLabel titleLabel = new JLabel("영화 등록", JLabel.CENTER);
		movieInsertDialog.add("North", titleLabel);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(10, 2));
		
		JPanel[] layoutPanel = new JPanel[10];
		for(int i = 0; i < 10; i++){
			layoutPanel[i] = new JPanel();
		}
		
		JLabel movieImageLabel = new JLabel("영화 이미지: ");
		centerPanel.add(movieImageLabel);
		layoutPanel[0].add(movieImageTextField);
		layoutPanel[0].add(imageUploadButton);
		centerPanel.add(layoutPanel[0]);
		JLabel movieIntroductionLabel = new JLabel("영화 소개 (텍스트파일) : ");
		centerPanel.add(movieIntroductionLabel);
		layoutPanel[1].add(movieIntroductionTextField);
		layoutPanel[1].add(movieIntroductionButton);
		centerPanel.add(layoutPanel[1]);
		JLabel movieNameLabel = new JLabel("영화 이름 : ");
		centerPanel.add(movieNameLabel);
		layoutPanel[2].add(movieNameTextField);
		centerPanel.add(layoutPanel[2]);
		JLabel movieGenreLabel = new JLabel("영화 장르 : ");
		centerPanel.add(movieGenreLabel);
		layoutPanel[3].add(movieGenreTextField);
		centerPanel.add(layoutPanel[3]);
		JLabel movieDirectorNameLabel = new JLabel("영화 감독 이름 : ");
		centerPanel.add(movieDirectorNameLabel);
		layoutPanel[5].add(movieDirectorNameTextField);
		centerPanel.add(layoutPanel[5]);
		JLabel movieMainActorNameLabel = new JLabel("주연 배우 : ");
		centerPanel.add(movieMainActorNameLabel);
		layoutPanel[6].add(movieMainActorNameTextField);
		centerPanel.add(layoutPanel[6]);
		JLabel movieGradeLabel = new JLabel("영화 등급 (숫자) :");
		centerPanel.add(movieGradeLabel);
		layoutPanel[7].add(movieGradeTextField);
		centerPanel.add(layoutPanel[7]);
		JLabel movieRunningTimeLabel = new JLabel("영화 상영 시간 (숫자) : ");
		centerPanel.add(movieRunningTimeLabel);
		layoutPanel[8].add(movieRunningTimeTextField);
		centerPanel.add(layoutPanel[8]);
		JLabel movieReleaseDateLabel = new JLabel("영화 개봉 날짜 (숫자) : ");
		centerPanel.add(movieReleaseDateLabel);
		JLabel blankLabel = new JLabel("-");
		JLabel blankLabel2 = new JLabel("-");
		layoutPanel[9].add(movieReleaseYearTextField);
		layoutPanel[9].add(blankLabel);
		layoutPanel[9].add(movieReleaseMonthTextField);
		layoutPanel[9].add(blankLabel2);
		layoutPanel[9].add(movieReleaseDateTextField);
		centerPanel.add(layoutPanel[9]);
		movieInsertDialog.add("Center", centerPanel);
		
		JPanel southPanel = new JPanel();
		southPanel.add(movieSubmitButton);
		southPanel.add(movieCancelButton);
		movieInsertDialog.add("South", southPanel);
		
		/*리스너 등록*/
		imageUploadButton.addActionListener(this);
		movieIntroductionButton.addActionListener(this);
		movieSubmitButton.addActionListener(this);
		movieCancelButton.addActionListener(this);
		imageUploadFileChooser.addActionListener(this);
		introductionUploadFileChooser.addActionListener(this);	
		
		movieInsertDialog.setVisible(true);
	}
	public void movieModifyMenu(){
		movieModifyDialog = new JDialog();
		movieModifyDialog.setLayout(new BorderLayout());
		movieModifyDialog.setSize(400, 380);
		movieModifyDialog.setResizable(false);
		movieModifyDialog.setModal(true);
		movieModifyDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);//JDialog.DISPOSE_ON_CLOSE);

		Dimension di, di1;
		di = Toolkit.getDefaultToolkit().getScreenSize();
		di1 = movieModifyDialog.getSize();
		movieModifyDialog.setLocation((int)(di.getWidth()/2 - di1.getWidth()/2), (int)(di.getHeight()/2 - di1.getHeight()/2));	
		
		JLabel titleLabel = new JLabel("영화 등록/수정 (아래는 현재상영중인 영화)", JLabel.CENTER);
		movieModifyDialog.add("North", titleLabel);
		JPanel centerPanel = new JPanel();
		MovieFile fs = new MovieFile();
		movieList.removeAll();
		for(int i = 1; i < fs.getNumberOfMovie(); i++){
			movieList.add(fs.getName(i));
		}
		centerPanel.add("Center", new JScrollPane(movieList));
		movieModifyDialog.add(centerPanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(movieAddButton);
		buttonPanel.add(movieModifyButton);
		buttonPanel.add(movieModifyCancelButton);
		movieModifyDialog.add("South", buttonPanel);
		
		/*리스너 등록*/
		movieAddButton.addActionListener(this);
		movieModifyButton.addActionListener(this);
		movieModifyCancelButton.addActionListener(this);

		movieModifyDialog.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == imageUploadButton){
			imageUploadFileChooser.setDialogTitle("파일 열기");
			imageUploadFileChooser.showOpenDialog(frame);
		}
		else if(e.getSource() == imageUploadFileChooser){
			movieImageTextField.setText(imageUploadFileChooser.getSelectedFile().getAbsolutePath());	
		}
		else if(e.getSource() == movieIntroductionButton){
			introductionUploadFileChooser.setDialogTitle("파일 열기");
			introductionUploadFileChooser.showOpenDialog(frame);
		}
		else if(e.getSource() == introductionUploadFileChooser){
			movieIntroductionTextField.setText(introductionUploadFileChooser.getSelectedFile().getAbsolutePath());
		}
		else if(e.getSource() == movieSubmitButton){
			String movieImage = movieImageTextField.getText();
			String movieIntroduction = movieIntroductionTextField.getText();
			String movieName = movieNameTextField.getText();
			String movieGenre = movieGenreTextField.getText();
			String movieDirectorName = movieDirectorNameTextField.getText();
			String movieMainActorName = movieMainActorNameTextField.getText();
			String movieGrade = movieGradeTextField.getText();
			String movieRunningTime = movieRunningTimeTextField.getText();
			String movieReleaseYear = movieReleaseYearTextField.getText();
			String movieReleaseMonth = movieReleaseMonthTextField.getText();
			String movieReleaseDate = movieReleaseDateTextField.getText();
			if(!movieImage.isEmpty() && !movieIntroduction.isEmpty() && !movieName.isEmpty() && !movieGenre.isEmpty() && !movieDirectorName.isEmpty() && !movieMainActorName.isEmpty() && !movieGrade.isEmpty() && !movieRunningTime.isEmpty() && !movieReleaseMonth.isEmpty() && !movieReleaseDate.isEmpty()){
				MovieExcelWriter mew;
				if(check == 1){
					try {
						mew = new MovieExcelWriter();
						mew.creteMovie(movieName, movieGenre, movieMainActorName, movieDirectorName
								, Integer.parseInt(movieRunningTime), movieGrade, Integer.parseInt(movieReleaseYear),
								Integer.parseInt(movieReleaseMonth), Integer.parseInt(movieReleaseDate), movieImage, movieIntroduction);
						SearchExcelWriter sew = new SearchExcelWriter();
						sew.Loader();
						JOptionPane.showMessageDialog(frame, "영화가 등록 되었습니다");
						movieInsertDialog.dispose();
					} catch (RowsExceededException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (WriteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NumberFormatException e1){
						JOptionPane.showMessageDialog(frame, "숫자입력칸을 확인후 다시 입력해주세요");
						movieInsertDialog.dispose();
					}
				}
				else if(check == 0){
					try {
						mew = new MovieExcelWriter();
						mew.modifyMovie(movieList.getSelectedIndex(), movieName, movieGenre, movieMainActorName, 
								movieDirectorName, Integer.parseInt(movieRunningTime), movieGrade,  Integer.parseInt(movieReleaseYear), 
								Integer.parseInt(movieReleaseMonth), Integer.parseInt(movieReleaseDate), movieImage, movieIntroduction);
						SearchExcelWriter sew = new SearchExcelWriter();
						sew.Loader();
						JOptionPane.showMessageDialog(frame, "영화가 수정 되었습니다");
						movieInsertDialog.dispose();
					} catch (RowsExceededException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (WriteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NumberFormatException e1){
						JOptionPane.showMessageDialog(frame, "숫자입력칸을 확인후 다시 입력해주세요");
						movieInsertDialog.dispose();
					}
				}
			}
			else{
				JOptionPane.showMessageDialog(frame, "항목을 빠짐없이 입력해주세요");
			}
		}
		else if(e.getSource() == movieCancelButton){
			movieInsertDialog.dispose();
		}
		/*영화 등록,수정 관련 액션*/
		else if(e.getSource() == movieAddButton){
			// 이곳에 영화 등록 액션등록
			initTextField();
			movieInsertMenu(1);
		}
		else if(e.getSource() == movieModifyButton){
			// 이곳에 영화선택후 수정버튼 눌리고 나서의 액션 등록
			setTextField();
			movieInsertMenu(0);
		}
		else if(e.getSource() == movieModifyCancelButton){
			movieModifyDialog.dispose();
		}
		e.setSource(null);
	}
}
