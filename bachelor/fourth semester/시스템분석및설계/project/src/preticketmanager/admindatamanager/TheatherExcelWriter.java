package preticketmanager.admindatamanager;

import java.io.File; 
import java.io.IOException;
import jxl.*; 
import jxl.write.*; 
import jxl.write.Number;


//이 클래스는 관리자에 관한 클래스이다. 
//관리자는 극장에 관하여 조정할 수 가 있다. 
//극장의 생성 수정 삭제가 가능하다.
//극장의 생성에 입력해야할 데이터들은 
//총 상영관의 수
//극장의 번호는 자동으로 갱신된다.
//극장의 이름
//극장의 위치 
//입장료등이 자료값으로 들어와야한다.


public class TheatherExcelWriter {
	private static final String BASEROUTE = "./src/preticketmanager/data/"; //엑셀 파일 저장 루트
	//극장의 정보들을 데이터에 넣는다.
	private String theatherDataFilePath=BASEROUTE+"Theathers.xls";
	
	public TheatherExcelWriter(){
		File theathersDataFile = new File(theatherDataFilePath);
		if(!theathersDataFile.exists())
		{
			try {
				WritableWorkbook workbook = Workbook.createWorkbook(theathersDataFile);
				
				try {
					WritableSheet sheet = workbook.createSheet("총정보",0);
					sheet.addCell( new Number(0,1,0));
					Label label = new Label(0,0,"총영화관개수");
					sheet.addCell(label);
					
					sheet = workbook.createSheet("극장정보",1);
					
					
					label = new Label(0,0,"번호");
					sheet.addCell(label);
					label = new Label(1,0,"극장이름");
					sheet.addCell(label);
					label = new Label(2,0,"극장위치");
					sheet.addCell(label);
					label = new Label(3,0,"입장료");
					sheet.addCell(label);
					label = new Label(4,0,"총상영관개수");
					sheet.addCell(label);
					
					
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				//sheet.setColumnView(0,8);

				workbook.write();
				try {
					workbook.close();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void createTheather(String theatherName, String theatherLocation,
			int totalScreenRoomsNumber)
	{
		int enterPrice=7000;
		
		//극장 정보가 저장되어 있는 파일의 경로
		try{
			
			Workbook workbook = Workbook.getWorkbook(new File(BASEROUTE+"/Theathers.xls"));
			WritableWorkbook copy = Workbook.createWorkbook(new File(BASEROUTE+"/Theathers.xls"), workbook); 
			WritableSheet sheet = copy.getSheet(0);
			int nRow = Integer.parseInt(sheet.getCell(0,1).getContents())+1;
			Label label = null;
			sheet = copy.getSheet(1);
			
			Number number = new Number(0,nRow,nRow);
			sheet.addCell(number);
			String theatherDirPath = BASEROUTE+"/Theather"+nRow;
			File theatherDir = new File(theatherDirPath);
			if(!theatherDir.exists())
			{
				theatherDir.mkdir();
			}
			label = new Label(1,nRow,theatherName);
			sheet.addCell(label);
			label = new Label(2,nRow,theatherLocation);
			sheet.addCell(label);
			number = new Number(3,nRow,enterPrice);
			sheet.addCell(number);
			number = new Number(4,nRow,totalScreenRoomsNumber);
			sheet.addCell(number);
			sheet = copy.getSheet(0);
			number = new Number(0,1,nRow);
			sheet.addCell(number);
			copy.write(); 
			copy.close(); 
			
			@SuppressWarnings("unused")
			ScreenRoomExcelWriter opti = new ScreenRoomExcelWriter();
			
			
			//수정될 엑셀 파일을 인식한다.
			//처음으로 극장의 정보를 추가한다.
			//극장의 다음 극장의 번호를 저장하였다.
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	//극장의 정보를 지운다.
	//삭제 부분이 중간일때를 생각하지 않음 리팩토링 필요
	public void deleteTeather(int theatherNumber)
	{
		try {
			Workbook workbook = Workbook.getWorkbook(new File(BASEROUTE+"/Theathers.xls"));
			WritableWorkbook copy = Workbook.createWorkbook(new File(BASEROUTE+"/Theathers.xls"), workbook); 
			WritableSheet sheet = copy.getSheet(1);
			//극장의 다음 극장의 번호를 저장하였다.
			int nRows=theatherNumber;
			sheet.removeRow(nRows);
			for(int i=theatherNumber;i<sheet.getRows();i++)
			{
				Number number = new Number(0,i,i);
				sheet.addCell(number);
			}
			sheet = copy.getSheet(1);

			int totalTheatherNumber=sheet.getRows()-1;
			sheet = copy.getSheet(0);
			Number number = new Number(0,1,totalTheatherNumber);//검증 필요
			sheet.addCell(number);
			delete(new File(BASEROUTE+"/Theather"+theatherNumber));
			sheet = copy.getSheet(1);
			for(int i=theatherNumber+1;i<totalTheatherNumber+2;i++)
			{
				File originFilePath = new File(BASEROUTE+"/Theather"+i);
				originFilePath.renameTo(new File(BASEROUTE+"/Theather"+(i-1)));	
			}
			copy.write(); 
			copy.close(); 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	//극장의 정보를 수정한다. 극장의 정보중에서 상영하는 상영관이 상영을 안하는것 빼고는
	//특별한 변경 사항이 있을 수가 없기에 이름이 같은 새로운 극장이면은 
	//삭제후 생성을 가정하에 메소드를 작성했다.
	public void modifyTheather(int theatherNumber,int totalScreenRoomNumber)
	{

		WritableWorkbook writeBook;
		try {		
			//Label label = null;
			writeBook = Workbook.createWorkbook(new File(theatherDataFilePath));
			//극장의 번호를 받아서 극장의 정보를 지운다.
			WritableSheet writeSheet = writeBook.getSheet(1);
			//극장정보에 해당하는 상영관을 다 지운다.
			String theatherDirName=BASEROUTE+"/"+theatherNumber;
			File deleteDir = new File(theatherDirName);
			delete(deleteDir);
			//극장의 번호로 가서 상영관수만 줄이면된다.
			int totalTheatherNumber=Integer.parseInt(writeSheet.getCell(theatherNumber,4).getContents());
			totalTheatherNumber--;
			//label = new Label(theatherNumber,4,totalTheatherNumber+"");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	private void delete(File deleteDir) {
		// TODO Auto-generated method stub
		if(!deleteDir.exists())
			return;
		File[] files = deleteDir.listFiles();
		for(int i=0;i< files.length;i++)
		{
			if(files[i].isDirectory())
			{
				delete(files[i]);
			}
			else
			{
				files[i].delete();
			}
		}
		deleteDir.delete();
	}
	//총 상영관의 개수를 받아서 하위 상영관들의 상영시간중에서
	//상영시간이 현재시간의 앞이라고 하면은 지운다.
	public void deleteScreenTime(int currentHour, int currentMinute)
	{
		int totalScreenNumber=0;
		try{
			Workbook workbook = Workbook.getWorkbook(new File(BASEROUTE+"/Theathers.xls"));
			Sheet sheet = workbook.getSheet(0);
			int theatherNumber = Integer.parseInt(sheet.getCell(0,1).getContents());
			sheet = workbook.getSheet(1);
			for(int i = 1; i<theatherNumber;i++){
				String sTotalScreenNumber = sheet.getCell(4,theatherNumber).getContents();
				totalScreenNumber = Integer.parseInt(sTotalScreenNumber);
				ScreenRoomExcelWriter writer = new ScreenRoomExcelWriter();
				writer.deleteScreenTime(i, totalScreenNumber, currentHour, currentMinute);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public void deleteDay(int nextYear, int nextMonth, int nextDay)
	{
		int totalScreenNumber=0;
		try{
			Workbook workbook = Workbook.getWorkbook(new File(BASEROUTE+"/Theathers.xls"));
			Sheet sheet = workbook.getSheet(0);
			int theatherNumber = Integer.parseInt(sheet.getCell(0,1).getContents());
			sheet = workbook.getSheet(1);
			for(int i = 1; i<=theatherNumber;i++){
				String sTotalScreenNumber = sheet.getCell(4,i).getContents();
				totalScreenNumber = Integer.parseInt(sTotalScreenNumber);
				ScreenRoomExcelWriter writer = new ScreenRoomExcelWriter();
				writer.deleteDay(i, totalScreenNumber, nextYear, nextMonth, nextDay);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
