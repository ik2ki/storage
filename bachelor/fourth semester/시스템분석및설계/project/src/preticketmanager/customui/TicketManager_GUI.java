package preticketmanager.customui;

import preticketmanager.customdatamanager.PreticketExcelwriter;
import preticketmanager.System.*;
import preticketmanager.model.Ticket;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

class TicketManager_GUI implements ActionListener, ItemListener {
	JFrame frame;
	
	TicketFile ticketFile = new TicketFile();
	PreticketExcelwriter pe;

	JDialog ticketTableDialog;
	List ticketList = new List(15, false);
	JButton ticketSelectButton;
	JButton ticketCancelButton;
	LinkedList<Ticket> ticketLinkedList;
	JLabel movieNameAndTheatherNameLabel;
	JLabel dateLabel;
	JLabel timeLabel;
	JLabel numberLabel;
	
	public TicketManager_GUI(){
		ticketFile = new TicketFile();

		ticketList = new List(15, false);
		ticketSelectButton = new JButton("영화 티켓 발급(출력)");
		ticketCancelButton = new JButton("티켓 환불");
		movieNameAndTheatherNameLabel = new JLabel("-", JLabel.CENTER);
		dateLabel = new JLabel("-", JLabel.CENTER);
		timeLabel = new JLabel("-", JLabel.CENTER);
		numberLabel = new JLabel("-", JLabel.CENTER);
		
		try
	    {
	       UIManager.setLookAndFeel ("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	    } catch (Exception e)
	    {
	        e.printStackTrace();
	    }

		try{
			pe = new PreticketExcelwriter(); 
		}catch(Exception e){}
	}
	
	public void display(JFrame frame){
		this.frame = frame;

		ticketTableDialog = new JDialog();
		ticketTableDialog.setSize(500, 530);
		ticketTableDialog.setResizable(false);
		ticketTableDialog.setModal(true);
		ticketTableDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		Dimension di, di1;
		di = Toolkit.getDefaultToolkit().getScreenSize();
		di1 = ticketTableDialog.getSize();
		ticketTableDialog.setLocation((int)(di.getWidth()/2 - di1.getWidth()/2), (int)(di.getHeight()/2 - di1.getHeight()/2));
		ticketTableDialog.setLayout(new BorderLayout());
		
		JLabel titleLabel = new JLabel("TICKET / 표", JLabel.CENTER);
		ticketTableDialog.add("North", titleLabel);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(2, 1));
		ticketLinkedList = pe.findTicket(LogManager_GUI.getUser().getID());
		if(ticketLinkedList != null)
			for(int i = 0; i < ticketLinkedList.size() ; i++){
				ticketList.add(ticketLinkedList.get(i).getMovieName() + " - " + ticketLinkedList.get(i).getTheatherName());
			}
		centerPanel.add(ticketList);
		JPanel detailTicketInfoPanel = new JPanel();
		detailTicketInfoPanel.setLayout(new GridLayout(4, 1));
		detailTicketInfoPanel.add(movieNameAndTheatherNameLabel);
		detailTicketInfoPanel.add(dateLabel);
		detailTicketInfoPanel.add(timeLabel);
		detailTicketInfoPanel.add(numberLabel);
		centerPanel.add(detailTicketInfoPanel);
		
		ticketTableDialog.add("Center", centerPanel);
		
		JPanel southPanel = new JPanel();
		southPanel.add(ticketSelectButton);
		southPanel.add(ticketCancelButton);
		ticketTableDialog.add("South", southPanel);
		
		/*리스너 등록*/
		ticketSelectButton.addActionListener(this);
		ticketCancelButton.addActionListener(this);
		ticketList.addItemListener(this);
		
		ticketTableDialog.setVisible(true);
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == ticketSelectButton){
			JOptionPane.showMessageDialog(frame, "티켓이 출력되었습니다.");
			ticketTableDialog.dispose();
		}
		else if(e.getSource() == ticketCancelButton){
			if(pe.delete(ticketList.getSelectedIndex() + 1) == true){
				JOptionPane.showMessageDialog(frame, "티켓이 환불되었습니다.");
			}
			else{
				JOptionPane.showMessageDialog(frame, "시간이 지나 티켓을 환불할 수 없습니다.");
			}
			ticketTableDialog.dispose();
		}
		e.setSource(null);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == ticketList){
			int index = ticketList.getSelectedIndex() + 1;
			movieNameAndTheatherNameLabel.setText(ticketFile.getMovieName(index) + " - " + ticketFile.getTheatherName(index));
			dateLabel.setText("상영 날짜 : " + ticketFile.getScreenDay(index));
			timeLabel.setText("상영 시간 : " + ticketFile.getStartTime(index));
			numberLabel.setText("예약인원수 : " + ticketFile.getTicketAmount(index));
		}
		e.setSource(null);
	}
}
