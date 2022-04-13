package preticketmanager.customui;

import preticketmanager.System.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TheatherManager_GUI implements ActionListener {
	JFrame frame;
	
	TheatherFile theatherFile;
	JButton[] theatherSelectButton;
	
	public TheatherManager_GUI(){
		theatherFile = new TheatherFile();
		theatherSelectButton = new JButton[theatherFile.getNumberOfTheather()];
	}
	public JPanel display(JFrame frame){
		try
	    {
	       UIManager.setLookAndFeel ("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	    } catch (Exception e)
	    {
	        e.printStackTrace();
	    }

		this.frame = frame;
		
		JPanel mainDisplayPanel = new JPanel();
		mainDisplayPanel.setLayout(new GridLayout(theatherFile.getNumberOfTheather(), 1));
		JPanel[] movieDisplayPanel = new JPanel[theatherFile.getNumberOfTheather()];
		for(int i = 1; i < theatherFile.getNumberOfTheather(); i++){
			movieDisplayPanel[i] = new JPanel();
			movieDisplayPanel[i].setLayout(new GridLayout(6, 1));

			JLabel blank = new JLabel("---------------------------------------------------------------------------------------------------------", JLabel.CENTER);
			JLabel theatherNameLabel = new JLabel(theatherFile.getName(i), JLabel.CENTER);
			JLabel theatherLocationLabel = new JLabel(theatherFile.getTheatherLocation(i), JLabel.CENTER);
			JLabel theatherTotalScreenRoomNumber = new JLabel("??? ? : " + theatherFile.getTotalScreenRoomNumber(i), JLabel.CENTER);
			JLabel blank2 = new JLabel("---------------------------------------------------------------------------------------------------------", JLabel.CENTER);
			movieDisplayPanel[i].add(blank);
			movieDisplayPanel[i].add(theatherNameLabel);
			movieDisplayPanel[i].add(theatherLocationLabel);
			movieDisplayPanel[i].add(theatherTotalScreenRoomNumber);
			movieDisplayPanel[i].add(blank2);
			JPanel buttonPanel = new JPanel();
			theatherSelectButton[i] = new JButton("????? ????");
			buttonPanel.add(theatherSelectButton[i]);
			movieDisplayPanel[i].add(buttonPanel);
		
			mainDisplayPanel.add(movieDisplayPanel[i]);
			

			/*?????*/
			theatherSelectButton[i].addActionListener(this);
		}
		

		return mainDisplayPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for(int i = 1; i < theatherFile.getNumberOfTheather(); i++){
			if(e.getSource() == theatherSelectButton[i]){
				AdvancedPurchaseManager_GUI ap = new AdvancedPurchaseManager_GUI();
				ap.display(frame, 2, i);
			}
		}
		e.setSource(null);
	}
}