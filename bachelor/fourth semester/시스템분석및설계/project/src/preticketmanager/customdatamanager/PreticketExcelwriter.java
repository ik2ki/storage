package preticketmanager.customdatamanager;

import java.io.*;
import java.util.Calendar;
import java.util.LinkedList;

import preticketmanager.model.Ticket;
import jxl.*;
import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.Number;
import jxl.write.biff.RowsExceededException;

public class PreticketExcelwriter {
	//엑셀데이터를 쓰는 부분이다. 이부분은 
	//관리자의 데이터 추가 삭제 수정등을 지원하고
	//지금 상태에서는 표의 예매시에 최종적으로 
	//ScreenRoom.xls에서의 데이터부분을 올려줄때에 이용이된다.
	//TicketList업데이트부분 추가
	//그리고 파라미터로 넘겨준표가 이미 모든 과정를 통과했다고 가정을 한다.
	protected static final String BASEROUTE = "./src/preticketmanager/data/"; //엑셀 파일 저장 루트
	
	LinkedList<Ticket> ticketList = new LinkedList<Ticket>();
	
	public PreticketExcelwriter() throws IOException, RowsExceededException, WriteException{
		
		String dataPath = BASEROUTE;
		String ticketListPath=BASEROUTE+"TicketList.xls";
		
		File dataDir = new File(dataPath);
		if(!dataDir.exists())
		{
			dataDir.mkdir();
		}
		File excelFilePath = new File(ticketListPath);
		if(!excelFilePath.exists())
		{
			WritableWorkbook makebook = Workbook.createWorkbook(excelFilePath);
			WritableSheet makesheet;	
			
			makesheet = makebook.createSheet("티켓정보", 0);
			makesheet.addCell(new Label(0,0,"티켓번호"));
			makesheet.addCell(new Label(1,0,"극장이름"));
			makesheet.addCell(new Label(2,0,"영화제목"));
			makesheet.addCell(new Label(3,0,"상영관번호"));
			makesheet.addCell(new Label(4,0,"상영회차"));
			makesheet.addCell(new Label(5,0,"시작시간"));
			makesheet.addCell(new Label(6,0,"종료시간"));
			makesheet.addCell(new Label(7,0,"상영시간"));
			makesheet.addCell(new Label(8,0,"아이디"));
			makesheet.addCell(new Label(9,0,"티켓예매수"));
			makesheet.addCell(new Label(10,0,"상영날짜"));
			makesheet.addCell(new Label(11,0,"극장번호"));
			
			makesheet = makebook.createSheet("총티켓",1);
			makesheet.addCell( new Number(0,1,0));
			makesheet.addCell(new Number(1,1,0));
			makesheet.addCell(new Label(0,0,"예약표수"));
			makesheet.addCell(new Label(1,0,"총 예매량"));
			
			makebook.write();
			makebook.close();
			
		}
	}
	
	//이 메소드는 티겟을 매개변수로 받고 그 티켓에 정보에 해당하는 상영관에 가서 예약된 좌석을 
	//하나 증가시켜준다.
	public boolean create(
			 int theatherNumber,
			 String movieName,	//영화이름
			 String theatherName,	//극장이름
			 int screenRoomNumber,	//상영관번호
			 int screenNumber,	//상영회차수
			 int screenTime,	//상영시간
			 String startTime,
			 int ticketAmount,	//티켓수
			 String screenDate, String userId)
	
	{
		//영화관을 타고서 내려간다. 영화관의 폴더의 파일 안에는 상영관들이 들어가 있다
		//첫번째 영화관을 BASEROUTE/Theather1에 들어가 있으며
		//첫번째 상영관은 ScreenRoom1.xls에 들어가 있으며 
		String searchFilePath;//검색되는 파일의 경로
		Workbook workbook;
		Sheet sheet;
		WritableWorkbook copy;
		Number number;
		//수정을 가할 파일을 알게되었다.
		searchFilePath=BASEROUTE+"/Theather"+theatherNumber+"/ScreenRoom"+screenRoomNumber+".xls";
		
		File excelFilePath = new File(searchFilePath);
		if(excelFilePath.exists())
		{
			
		try{
			//수정될 엑셀 파일을 인식한다.
			workbook = Workbook.getWorkbook(new File(searchFilePath));
			copy = Workbook.createWorkbook(new File(searchFilePath), workbook);
			//티켓의 저장된 예약된 시간과 날짜들을 돌면서 sheetNumber에 접근한다.
			//만약 sheetNumber와 저장된 예약된 시간이 같게 되면은
			//총 상영회차만큼 반복문을 돌면서 예약된 회차에 
			//예약된 번호를 하나 증가 시키게된다.
			//만약의 사태를 가능하여서 총좌석수와 증가된 예약수가 총 좌석수를 넘어서게되면은
			//총 좌석수가 다 찼다는 정보를 보내게된다.
			
			WritableSheet writeSheet=copy.getSheet(screenDate);
			int ticketNum = Integer.parseInt(writeSheet.getCell(5, screenNumber).getContents());
			if(Integer.parseInt(writeSheet.getCell(4, screenNumber).getContents())<ticketNum+ticketAmount){
				copy.write();
				copy.close(); 
				return false;
			}
			else{
				writeSheet.addCell(new Number(5,screenNumber,ticketNum+ticketAmount));
			}
			copy.write();
			copy.close();
			
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		//이부분은 티켓의 정보를 TicketList.xls에 추가시키는 부분이다.
		String ticketListPath=BASEROUTE+"TicketList.xls";
		try{
			workbook = Workbook.getWorkbook(new File(ticketListPath));
			copy = Workbook.createWorkbook(new File(ticketListPath), workbook);	//티켓 리스트를 수정할 파일을 할당한다.
			sheet=copy.getSheet(1);	//티켓리스트의 총예약수를 읽어 들일 Sheet
			WritableSheet writeSheet = copy.getSheet(1);	//티켓리스트를 추가할 Sheet
			int nRows = Integer.parseInt(sheet.getCell(0,1).getContents())+1;//총 티켓번호를 읽어들여서 그 다음 열에 저장하기 시작한다.
			writeSheet = copy.getSheet(0);
			Label label = null;
			for(int i=0;i<12;i++)
			{
				switch(i)
				{
				case 0://예약번호를 저장한다.
					   writeSheet.addCell( new Number(i,nRows,Integer.parseInt(sheet.getCell(0,1).getContents())+1));
					   break;
				case 1:label = new Label(i,nRows,theatherName);	//극장이름을 저장한다.
					   writeSheet.addCell(label);
					   break;
				case 2:label = new Label(i,nRows,movieName);		//영화이름을 저장한다.
					   writeSheet.addCell(label);
					   break;
				case 3:number = new Number(i,nRows,screenRoomNumber);	//상영관이름을 저장한다.
					   writeSheet.addCell(number);
					   break;
				case 4:number = new Number(i,nRows,screenNumber);//상영회차를 저장한다.
					   writeSheet.addCell(number);
					   break;
					   
				case 5: 
					   DateFormat userdatafmt = new DateFormat("hh:mm");
			   	   	   WritableCellFormat dataformat = new WritableCellFormat(userdatafmt);
			   	   	   label = new Label(i,nRows,startTime,dataformat);
					   writeSheet.addCell(label);
				
					   break;
				case 6:
					   number = new Number(7,nRows, screenTime);//상영시간을 저장한다.
				   	   writeSheet.addCell(number);
				   	   
				   	   String End;
				   	   
				   	   String[] scrstarttimesplit = new String[2];
				   	   scrstarttimesplit = startTime.split(":");
				   	   int startHour = Integer.parseInt(scrstarttimesplit[0]);
				   	   int startMinute = Integer.parseInt(scrstarttimesplit[1]);
				   	   if(startMinute+( screenTime%60)>=60){
				   		   startMinute =startMinute+( screenTime%60)-60; startHour++;
				   		   End = (startHour+( screenTime/60))+":"+(startMinute);
				   	   }
				   	   else{
				   		   End = (startHour+( screenTime/60))+":"+(startMinute+( screenTime%60));
				   	   }
				   	   userdatafmt = new DateFormat("hh:mm");
			   	   	   dataformat = new WritableCellFormat(userdatafmt);
					   label = new Label(i,nRows,End,dataformat);
					 
						//종료시간을 저장한다.
					   writeSheet.addCell(label);
					   break;
					   
					   
					  
				case 8:label = new Label(i,nRows,userId);
					   writeSheet.addCell(label);
					   break;
				case 9:number = new Number(i,nRows,ticketAmount);
					   writeSheet.addCell(number);
					   break;
				case 10:label = new Label(i,nRows,screenDate);
					   writeSheet.addCell(label);
					   break;
				case 11:number = new Number(i,nRows,theatherNumber);
						writeSheet.addCell(number);
						break;
				}
			}//티켓리스트데이터 넣기 종료
			//마지막으로 총 예약수를 하나 증가시킨다.
			writeSheet=copy.getSheet(1);
			number = new Number(0,1,nRows);
			writeSheet.addCell(number);
			number = new Number(1,1,Integer.parseInt(writeSheet.getCell(1, 1).getContents())+ticketAmount);
			writeSheet.addCell(number);
			
			copy.write();
			copy.close();
			return true;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	
		}
		return false;
	}
	
	public boolean delete(int preticketNum)
	{
		String ticketListPath=BASEROUTE+"TicketList.xls";
		String theatherPath = BASEROUTE+"Theathers.xls";
		String screenRoomPath = null;
	    try{
	    	Workbook Ticketbook = Workbook.getWorkbook(new File(ticketListPath));
	    	WritableWorkbook Ticketworkbook = Workbook.createWorkbook(new File(ticketListPath), Ticketbook); 
	    	WritableSheet Ticketsheet = Ticketworkbook.getSheet(0);
	    	Ticketsheet = Ticketworkbook.getSheet(1);
	    	int nRows = Integer.parseInt(Ticketsheet.getCell(0, 1).getContents());
	    	
	    	
	    	if (nRows==0) return false;
			
	    	
	    	Workbook Teatherbook = Workbook.getWorkbook(new File(theatherPath));
			Sheet temp = Teatherbook.getSheet(0);
			int theatherNum, theatherTotalNum = Integer.parseInt(temp.getCell(0,1).getContents());
		
			temp = Teatherbook.getSheet(1);
			Ticketsheet = Ticketworkbook.getSheet(0);
			
			for(theatherNum =1;theatherNum<=theatherTotalNum;theatherNum++) if(Ticketsheet.getCell(1,preticketNum).getContents().equals(temp.getCell(1,theatherNum).getContents())) break;
			
			int screenNum = Integer.parseInt(Ticketsheet.getCell(3,preticketNum).getContents());
			
			int ticketNum = Integer.parseInt(Ticketsheet.getCell(9, preticketNum).getContents());
			
			screenRoomPath = BASEROUTE+"/Theather"+theatherNum+"/ScreenRoom"+screenNum+".xls";
			
			Workbook screenbook = Workbook.getWorkbook(new File(screenRoomPath));
	    	WritableWorkbook screenworkbook = Workbook.createWorkbook(new File(screenRoomPath), screenbook); 
	    	
	    	WritableSheet screenSheet = screenworkbook.getSheet(Ticketsheet.getCell(10, preticketNum).getContents());
	    	
	    	int seatNum = Integer.parseInt(screenSheet.getCell(5,Integer.parseInt(Ticketsheet.getCell(4, preticketNum).getContents())).getContents());//좌석예약수 찾기
			
	    	seatNum -= ticketNum;
	    	
	    	Number number = new Number(5,Integer.parseInt(Ticketsheet.getCell(4, preticketNum).getContents()),seatNum);
	    	screenSheet.addCell(number);
	    	screenworkbook.write();
	    	screenworkbook.close();
	    	
	    	
	    	
	    	Ticketsheet.removeRow(preticketNum);
	    	
	    	for(int i = 1;i<nRows;i++){
				number = new Number(0,i,i);
				Ticketsheet.addCell(number);
			}
	    	
	    	Ticketsheet = Ticketworkbook.getSheet(1);
	    	Ticketsheet.addCell(new Number(0,1,nRows-1));
	    	Ticketsheet.addCell(new Number(1,1,(Integer.parseInt(Ticketsheet.getCell(1, 1).getContents())-ticketNum)));
	    	
	    	Ticketworkbook.write();
	    	Ticketworkbook.close();
	    	return true;
	    	
	    }catch(Exception e)
		{
			e.printStackTrace();
		}
	    return false;

	}

	public boolean deleteTheather(int index){//주의 극장 지우기 전에 먼저 호출할 것~!!
		
		String ticketListPath=BASEROUTE+"TicketList.xls";
		
	    try{
	    	Workbook Ticketbook = Workbook.getWorkbook(new File(ticketListPath));
	    	WritableWorkbook Ticketworkbook = Workbook.createWorkbook(new File(ticketListPath), Ticketbook); 
	    	WritableSheet Ticketsheet = Ticketworkbook.getSheet(1);
	    
	    	int nRows = Integer.parseInt(Ticketsheet.getCell(0, 1).getContents());
	    	    	
	    	if (nRows==0) return true;
			
	    	Ticketsheet = Ticketworkbook.getSheet(0);
	    	int[] TheIndex = new int[nRows];
	    	int j =0, totalpre = 0;
	    	for(int i = 1; i<=nRows ; i++){
	    		if(Integer.parseInt(Ticketsheet.getCell(11, i).getContents())==index){
	    			TheIndex[j] = i;
	    			totalpre +=Integer.parseInt(Ticketsheet.getCell(9, i).getContents());
	    			j++;
	    		}
	    	}
	    	if(j==0) return true;
	    	TheIndex[j]= -1;
	    	
	    	for(int i = 0;TheIndex[i]!=-1;i++){
	    		Ticketsheet.removeRow(TheIndex[i]);
	    	}
	    	
	    	Ticketsheet = Ticketworkbook.getSheet(1);
	    	Ticketsheet.addCell(new Number(0,1,nRows-j));
	    	Ticketsheet.addCell(new Number(1,1,Integer.parseInt(Ticketsheet.getCell(1, 1).getContents())-totalpre));
	    	
	    	Ticketworkbook.write();
	    	Ticketworkbook.close();
	    	
	    	return true;
	    }catch(Exception e)
		{
			e.printStackTrace();
		}
	    return false;
	}
	
	public void loader(int hour,int min,int year,int month,int date){//시간과 날짜에 맞게 초기화해줌
		/*
		Calendar calender = Calendar.getInstance();
		int year = calender.get(Calendar.YEAR);
		int month = calender.get(Calendar.MONTH)+1;
		int date = calender.get(Calendar.DATE);
		*/
		String ticketListPath=BASEROUTE+"TicketList.xls";
				
		Workbook Ticketbook;
		try {
			Ticketbook = Workbook.getWorkbook(new File(ticketListPath));
			WritableWorkbook Ticketworkbook = Workbook.createWorkbook(new File(ticketListPath), Ticketbook); 
	    	WritableSheet Ticketsheet1 = Ticketworkbook.getSheet(1);
	    	
	    	int nRows = Integer.parseInt(Ticketsheet1.getCell(0,1).getContents());
	    	int total = Integer.parseInt(Ticketsheet1.getCell(1,1).getContents());
	    	WritableSheet Ticketsheet0 = Ticketworkbook.getSheet(0);
	    	String[] splitdate = new String[3];
	    	String[] splitTime = new String[2];
	    	int exception = 0;
	    	
	    	
	    	for(int i = 1;i<=nRows;){
	    		
			   	splitdate = Ticketsheet0.getCell(10,i).getContents().split("-");
			   	
			   	splitTime = Ticketsheet0.getCell(5,i).getContents().split(":");
			   	
			   	if(splitTime[0]=="")break;
			    if(year>Integer.parseInt(splitdate[0])){
			   		exception=1;
			   	}
			   	else if(year==Integer.parseInt(splitdate[0])){
			   		if(month>Integer.parseInt(splitdate[1])){
			   			exception=1;
			   		}
			   		else if(month==Integer.parseInt(splitdate[1])){
			   			
			   			if(date>Integer.parseInt(splitdate[2])){
			   				
			   				exception=1;			   			
			   			}
			   			else if(date==Integer.parseInt(splitdate[2])){
			   				if(hour>Integer.parseInt(splitTime[0])){
			   					exception=1;
						   	}
						   	else if(hour==Integer.parseInt(splitTime[0])){
						   		if(min>Integer.parseInt(splitTime[1])){
						   			exception=1;
						   		}
						   		
						   	}
			   			}
			   		}
			   	} 
			   	
			   	if(exception==1){
			   		Ticketsheet1.addCell(new Number(0,1,--nRows));
			   		Ticketsheet1.addCell(new Number(1,1,total-=Integer.parseInt(Ticketsheet0.getCell(9,i).getContents())));
			   		Ticketsheet0.removeRow(i);
			   		exception=0;
			   		for(int j = i;j<=nRows;j++){
						Ticketsheet0.addCell(new Number(0,j,j));
					}
			   	}
			   	else{
			   		exception=0;
			   		i++;
			   	}
	    	}
	    	
	    	Ticketworkbook.write();
	    	Ticketworkbook.close();
	    	
	    	
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		
	}
	public boolean refund(int index){
		Ticket temp = ticketList.get(index-1);
		Calendar rightNow = Calendar.getInstance();
		//	scradate : 상영관으로부터 상영날짜를 받아서 저장
		String scrdate = temp.getScreenDate();
		//	문자열을 split 해서 저장하기 위한 변수 scrdatesplit
		String[] scrdatesplit = new String[3];
		//String[] scrstarttimesplit = new String[2];
		//	scrdatesplit[] : 상영시간을(string)을 3개의 문자열로 쪼갬(파라미터는 "-" 기준)
		scrdatesplit = scrdate.split("-");
		//scrstarttimesplit = scrstarttime.split(":");
		//	쪼갠 문자열을 정수형으로 파싱
		int year = Integer.parseInt(scrdatesplit[0]); 
		int month = Integer.parseInt(scrdatesplit[1]); 
		int day = Integer.parseInt(scrdatesplit[2]);
		int hour = temp.getStartHour();
		int minute = temp.getStartMinute();
		//
		//상영시각까지 10분이 이상이상 남았다면 환불해준다.
		if(rightNow.get(Calendar.YEAR) <= year)
			if(rightNow.get(Calendar.MONTH) <= month)
				if(rightNow.get(Calendar.DAY_OF_MONTH) <= day)
					if(rightNow.get(Calendar.HOUR_OF_DAY) <= hour)
						if(rightNow.get(Calendar.MINUTE) <= minute - 10){
							//ioSystem.print("티켓예약이 취소되었습니다");
							return true; 
						}
		//ioSystem.print("티켓을 취소할수 없습니다");
		return false;
	}
	//해당 티켓을 사용자 id로 찾아 ticketList에 저장한다.
	public LinkedList<Ticket> findTicket(String userId){
		
		ticketList.clear();
		
		String ticketListPath=BASEROUTE+"TicketList.xls";
		Workbook Ticketbook;
		try {
			Ticketbook = Workbook.getWorkbook(new File(ticketListPath));
			Sheet sheet = Ticketbook.getSheet(1);
	    	int nRows = Integer.parseInt(sheet.getCell(0,1).getContents());
	    	sheet = Ticketbook.getSheet(0);
	    	
	    	for(int i = 1;i<=nRows;i++){
	    		if(sheet.getCell(8,i).getContents().equals(userId)){
	    			
	    			Ticket ticket = new Ticket();
	    			
	    			ticket.setTicketNum(Integer.parseInt(sheet.getCell(0,i).getContents()));
	    			ticket.setTheatherName(sheet.getCell(1,i).getContents());
	    			ticket.setMovieName(sheet.getCell(2,i).getContents());
	    			ticket.setScreenRoomNumber(Integer.parseInt(sheet.getCell(3,i).getContents()));
	    			ticket.setScreenNumber(Integer.parseInt(sheet.getCell(4,i).getContents()));
	     			String[] scrstarttimesplit = new String[2];
	    			String scrstarttime = new String(sheet.getCell(5,i).getContents());
	    			scrstarttimesplit = scrstarttime.split(":");
	    			ticket.setStartHour(Integer.parseInt(scrstarttimesplit[0]));
	    			ticket.setStartMinute(Integer.parseInt(scrstarttimesplit[0]));
	    			
	    			ticket.setEndTime(sheet.getCell(6,i).getContents());
	    			ticket.setScreenTime(Integer.parseInt(sheet.getCell(7,i).getContents()));
	    			ticket.setUserId(userId);
	    			
	    			ticket.setTicketAmount(Integer.parseInt(sheet.getCell(9,i).getContents()));
	    			ticket.setScreenDate(sheet.getCell(10,i).getContents());
	    			
	    			ticketList.add(ticket);
	    		}
	    		else return null;
	    	}
	    	
	    	
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ticketList;
    	
	}
	
	
/*	public boolean modify(){
		return false;
	}*/
}