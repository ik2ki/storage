package preticketmanager.customui;
import preticketmanager.System.*;
import preticketmanager.start.Driver;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

class MovieManager_GUI implements ActionListener {
	JFrame frame;
	MovieFile mf = new MovieFile();
	
	JButton[] movieIntroductionButton; 
	JButton[] movieSelectButton;	
	
	public JPanel display(JFrame frame){ //List에 저장된 영화들을 출력한다.
		try
	    {
	       UIManager.setLookAndFeel ("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	    } catch (Exception e)
        {
            e.printStackTrace();
        }
		this.frame = frame;
		JPanel mainDisplayPanel = new JPanel();
		movieIntroductionButton = new JButton[mf.getNumberOfMovie()];
		movieSelectButton = new JButton[mf.getNumberOfMovie()];
		mainDisplayPanel.setLayout(new GridLayout(mf.getNumberOfMovie(), 1));
		JPanel[] movieDisplayPanel = new JPanel[mf.getNumberOfMovie()];
		for(int i = 1; i < mf.getNumberOfMovie(); i++){
			/*영화 제목*/
			movieDisplayPanel[i] = new JPanel();
			movieDisplayPanel[i].setLayout(new BorderLayout());
			JLabel movieNameLabel = new JLabel("    " + mf.getName(i));
			movieDisplayPanel[i].add("North", movieNameLabel);
			
			JPanel movieInfoPanel = new JPanel();	//영화 표시 전체 패널
			movieInfoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			
			/*영화 소개*/
			JPanel movieImagePanel = new JPanel();
			if(!mf.getImage(i).equals("null")){
				ImageIcon image = new ImageIcon(Driver.BASEROUTE + "image/" + mf.getImage(i));
				JLabel movieImage = new JLabel(image);
				movieImagePanel.add(movieImage);
			}
			else{
				ImageIcon image = new ImageIcon(Driver.BASEROUTE + "image/" + "noimage.jpg");
				JLabel movieImage = new JLabel(image);
				movieImagePanel.add(movieImage);
			}
			
			JPanel layoutPanel = new JPanel();
			layoutPanel.setLayout(new BorderLayout());
			JPanel movieInfoPanel2 = new JPanel();
			movieInfoPanel2.setLayout(new GridLayout(5, 1));
			JLabel blank = new JLabel("---------------------------------------------------------------------------------------------------------");
			movieInfoPanel2.add(blank);
			JLabel movieGenreLabel = new JLabel("장르:    " + mf.getGenre(i));
			movieInfoPanel2.add(movieGenreLabel);
			JLabel movieRatingLabel = new JLabel("관람등급:    " + mf.getRating(i));
			movieInfoPanel2.add(movieRatingLabel);
			if(mf.getMainActor(i).length() <= 36){
				JLabel movieMainActorNameLabel = new JLabel("주연배우:    " + mf.getMainActor(i));
				movieInfoPanel2.add(movieMainActorNameLabel);
			}
			else{
				String str = ""; 
				for(int j = 0; j < 34; j++){
					char ch = mf.getMainActor(i).charAt(j);
					str += ch + "";
				}
				str += "...";
				JLabel movieMainActorNameLabel = new JLabel("주연배우:    " + str);
				movieInfoPanel2.add(movieMainActorNameLabel);
			}
			JLabel movieIntroductionLabel = new JLabel("소개:    ");
			movieInfoPanel2.add(movieIntroductionLabel);
			JPanel textAreaPanel = new JPanel();
			textAreaPanel.setLayout(new BorderLayout());
			JTextArea movieIntroductionTextArea = new JTextArea();
			movieIntroductionTextArea.setRows(6);
			movieIntroductionTextArea.setEditable(false);
			movieIntroductionTextArea.setLineWrap(true);
			TextFileSystem introductionFile = new TextFileSystem();
			introductionFile.addRoute("introduction");
			introductionFile.setFile(mf.getName(i) + ".txt");
			try {
				introductionFile.setReader();
				movieIntroductionTextArea.append(introductionFile.readProcessDot(90));
			} catch (IOException e) {
				e.printStackTrace();
			}
			textAreaPanel.add("Center", movieIntroductionTextArea);
			JLabel blank2 = new JLabel("---------------------------------------------------------------------------------------------------------");
			textAreaPanel.add("South",blank2);
			layoutPanel.add("Center", movieInfoPanel2);
			layoutPanel.add("South", textAreaPanel);
			movieInfoPanel.add(movieImagePanel);
			movieInfoPanel.add(layoutPanel);
			movieDisplayPanel[i].add("Center", movieInfoPanel);
			
			/*버튼 */
			JPanel buttonPanel = new JPanel();
			movieIntroductionButton[i] = new JButton("영화상세보기");
			movieSelectButton[i] = new JButton("영화예매");
			buttonPanel.add(movieIntroductionButton[i]);
			buttonPanel.add(movieSelectButton[i]);
			movieDisplayPanel[i].add("South", buttonPanel);
			
			mainDisplayPanel.add(movieDisplayPanel[i]);
		}
		for(int i = 1; i < mf.getNumberOfMovie(); i++){
			movieIntroductionButton[i].addActionListener(this);
			movieSelectButton[i].addActionListener(this);
		}
		return mainDisplayPanel;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for(int i = 1; i < mf.getNumberOfMovie(); i++){
			if(e.getSource() == movieIntroductionButton[i]){
				DetailMovieInfoManager_GUI dmim = new DetailMovieInfoManager_GUI();
				dmim.display(frame, i);
			}
			else if(e.getSource() == movieSelectButton[i] ){
				AdvancedPurchaseManager_GUI ap = new AdvancedPurchaseManager_GUI();
				ap.display(frame, 1, i);
			}
		}
		e.setSource(null);
	}
}