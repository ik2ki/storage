package preticketmanager.customui;

import java.awt.*;
import java.text.*;
import javax.swing.*;

class TimeManager_GUI {
	JFrame frame;
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
		mainDisplayPanel.setLayout(new GridLayout(5, 1));

		JPanel datePanel = new JPanel();
		DateFormatSymbols dfs = new DateFormatSymbols();  //import java.text.*;
		String[] months = dfs.getMonths();
		JComboBox monthsComboBox = new JComboBox(months);
		String[] day = new String[31];
		for(int i = 0; i < 31; i++){
			day[i] = (i+1) + "일";
		}
		JComboBox dayComboBox = new JComboBox(day);
		datePanel.add(monthsComboBox);
		datePanel.add(dayComboBox);
		
		JPanel buttonPanel = new JPanel();
		JButton timeSelectButton = new JButton("이날짜에 예약하기");
		buttonPanel.add(timeSelectButton);

		mainDisplayPanel.add(datePanel);
		mainDisplayPanel.add(buttonPanel);
		
		return mainDisplayPanel;
	}
}