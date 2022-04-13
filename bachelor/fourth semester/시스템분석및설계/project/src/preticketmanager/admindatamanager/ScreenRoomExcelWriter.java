package preticketmanager.admindatamanager;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import preticketmanager.model.Movie;

import jxl.*;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WriteException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.biff.RowsExceededException;

/*
 * 입력순서는 처음에 영화관이 생기게 되고 
 * 영화관dirPath = /src/preticketmanager/data
 * 상영관 데이터 엑셀파일 =  영화관dirPath+"/ScreenRoom"+ScreenRoomNumber+".xls"
 * 입력받는 순서 
 * 현재날짜를 받고나서 현재날짜를 포함한 10개의 Sheet생성->
 * 총 좌석수 입력 -> 총 영화개수 입력 -> 각 영화에 대한 개별적인 정보입력
 * 상영관에 등록한 총 영화의 개수 - totalMovieNumber
 * 총 영화의 개수만큼 excel파일에 등록한다. 하루에 한가지 영화를 상영한다고 가정
 * 한번에 10개 이상의 영화는 등록할 수 없게한다.
 * 영화번호를 넣은 후에 영화 객체를 생성 상영회수와 쉬는 시간 입력
 * 상영회수*(영화의 상영시간 + 쉬는시간)이 극장의 폐장시간에서 개장시간을 뺀 시간을 넘어서면 상영회수를 줄이라고한다.
 * 그다음 영화 시작을과 영화 종료일을 입력한다. 
 * 영화시작일과 종료일은 총영화의 개수만큼의 배열을 만들어서 저장을 한다.
 * 나중에 다음영화 입력시에 날짜를 비교하여 적절하지 않은 영화날짜를 입력시에 적절한 메시지를 출력한다.
 * 직접입력시에 상영회수를 선택시에 시간입력을 선택한다. 
 * 상영회수만큼의 입력창이 나타나게되고 그곳에 값을 입력한다. 각 시간들을 배열에 저장하여서
 * 앞시간이 뒤 시간보다 앞이어야하고 앞의 종료시간이 뒤의 시작시간을 넘어서면은 적절한 오류내용을 알려준다.
 * 하나의 영화를 입력했을때에 상영영화를 넣을 수 있는 남은 예약일수를 넣는다.
 * 앞의 영화시작일은 테이블 작성시의 현재의 날짜로 정의하고 
 * 뒤에 영화 상영종료일은 앞의 상영시작일보다 뒤라고 하면은 등록이 가능하게 한다.
 * 각 입력의 값은 음수와 숫자 타입을 제외한 것은 입력이 되지 않게 ui부분에서 적절한 통제를 한다.
 */
public class ScreenRoomExcelWriter 
{
	private static final String BASEROUTE = "./src/preticketmanager/data/"; //엑셀 파일 저장 루트
	
	public ScreenRoomExcelWriter() throws IOException, RowsExceededException, WriteException, BiffException{
		
		String dataPath = BASEROUTE;
		File dataDir = new File(dataPath);
		if(!dataDir.exists())
		{
			dataDir.mkdir();
		}
		Workbook work = Workbook.getWorkbook(new File(BASEROUTE+"Theathers.xls"));
		int theatherNum = Integer.parseInt(work.getSheet(0).getCell(0, 1).getContents());
		for(int i = 1;i<=theatherNum;i++){
			String newScreenDataPath=BASEROUTE+"/Theather"+i;
			dataDir = new File(newScreenDataPath);
			if(!dataDir.exists()){
				dataDir.mkdir();
			}
			int screenNum = Integer.parseInt(work.getSheet(1).getCell(4, i).getContents());
			for(int j = 1;j<=screenNum;j++){
				String ScreenPath=newScreenDataPath+"/ScreenRoom"+j+".xls";
				File data = new File(ScreenPath);
				if(!data.exists()){
					WritableWorkbook screen = Workbook.createWorkbook(data);
					WritableSheet sheet = screen.createSheet("0", 0);
					sheet = screen.createSheet("상영관정보", 1);
					sheet.addCell(new Label(0,0,"상영관번호"));
					sheet.addCell(new Label(1,0,"총좌석수"));
					sheet.addCell(new Label(2,0,"총상영영화수"));
					sheet.addCell(new Number(0,1,j));
					sheet.addCell(new Number(1,1,100));
					sheet.addCell(new Number(2,1,0));
					sheet = screen.createSheet("상영영화", 2);
					sheet.addCell(new Label(0,0,"영화번호"));
					sheet.addCell(new Label(1,0,"영화제목"));
					sheet.addCell(new Label(2,0,"장르"));
					sheet.addCell(new Label(3,0,"주연배우"));
					sheet.addCell(new Label(4,0,"감독"));
					sheet.addCell(new Label(5,0,"상영시간"));
					sheet.addCell(new Label(6,0,"등급"));
					sheet.addCell(new Label(7,0,"상영시작일"));
					sheet.addCell(new Label(8,0,"상영종료일"));
					sheet.addCell(new Label(9,0,"총상영회차"));
					screen.write();
					screen.close();
				}
			}
		}
			
	}
	
	public void create(int TheatherNumber){
		try {
			Workbook work = Workbook.getWorkbook(new File(BASEROUTE+"Theathers.xls"));
			WritableWorkbook the = Workbook.createWorkbook(new File(BASEROUTE+"Theathers.xls"), work);
			String newScreenDataPath=BASEROUTE+"/Theather"+TheatherNumber;
			int screenNum = Integer.parseInt(work.getSheet(1).getCell(4, TheatherNumber).getContents())+1;
			the.getSheet(1).addCell(new Number(4,TheatherNumber,screenNum));
			String ScreenPath=newScreenDataPath+"/ScreenRoom"+screenNum+".xls";
			File data = new File(ScreenPath);
			WritableWorkbook screen;
			
			screen = Workbook.createWorkbook(data);
				
			WritableSheet sheet = screen.createSheet("0", 0);
			sheet = screen.createSheet("상영관정보", 1);
			sheet.addCell(new Label(0,0,"상영관번호"));
			sheet.addCell(new Label(1,0,"총좌석수"));
			sheet.addCell(new Label(2,0,"총상영영화수"));
			sheet.addCell(new Number(0,1,screenNum));
			sheet.addCell(new Number(1,1,100));
			sheet.addCell(new Number(2,1,0));
			sheet = screen.createSheet("상영영화", 2);
			sheet.addCell(new Label(0,0,"영화번호"));
			sheet.addCell(new Label(1,0,"영화제목"));
			sheet.addCell(new Label(2,0,"장르"));
			sheet.addCell(new Label(3,0,"주연배우"));
			sheet.addCell(new Label(4,0,"감독"));
			sheet.addCell(new Label(5,0,"상영시간"));
			sheet.addCell(new Label(6,0,"등급"));
			sheet.addCell(new Label(7,0,"상영시작일"));
			sheet.addCell(new Label(8,0,"상영종료일"));
			sheet.addCell(new Label(9,0,"총상영회차"));
			the.write();
			the.close();
			screen.write();
			screen.close();
			Calendar calendar = Calendar.getInstance();
			this.deleteDay(TheatherNumber, screenNum, calendar.get(Calendar.YEAR), 
					calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DATE));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createScreenRoomInfo(String newScreenDataPath,
			int screenRoomNumber, int totalSeat) {
		// TODO Auto-generated method stub
		Workbook workBook=null;
		WritableWorkbook copy=null;
		try {
			workBook = Workbook.getWorkbook(new File(newScreenDataPath));
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		try {
			copy = Workbook.createWorkbook(new File(newScreenDataPath), workBook);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WritableSheet sheet = copy.createSheet("상영관정보",copy.getNumberOfSheets());
		Label label = new Label(0,0,"상영관번호");
		try {
			sheet.addCell(label);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		label = new Label(1,0,"총좌석수");
		try {
			sheet.addCell(label);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Number number = new Number(0,1,screenRoomNumber);
		try {
			sheet.addCell(number);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		number = new Number(1,1,totalSeat);
		try {
			sheet.addCell(number);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			copy.write();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		try {
			copy.close();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * Thread에서 시간을 받아온다고 가정을한다. 
	 * 상영시간을 받아와서 상영시간이 현재시간의 앞이 된다면
	 * 회계부분에 예약된 좌석수에 입장료를 곱한 이익을 더하고
	 * 현재시간의  앞인 상영시간을 지운다.
	 */
	public void deleteScreenTime(int theatherNumber,int TotalscreenRoom,int currentHour, int currentMinute)
	{
		
		String newScreenDataPath=BASEROUTE+"/Theather"+theatherNumber;
		String[] time = new String[2];
		try{
			for(int i= 1;i<TotalscreenRoom;i++){
				String ScreenPath=newScreenDataPath+"/ScreenRoom"+i+".xls";
				Workbook the = Workbook.getWorkbook(new File(BASEROUTE+"Theathers.xls"));
				Workbook book = Workbook.getWorkbook(new File(ScreenPath));
				WritableWorkbook workbook = Workbook.createWorkbook(new File(ScreenPath), book);
				WritableSheet sheet = workbook.getSheet(0);
				for(int k = 1;k<=Integer.parseInt(the.getSheet(1).getCell(4, theatherNumber).getContents());k++){
					time = sheet.getCell(3,k).getContents().split(":");
					if(time[0]=="") break;
					if(Integer.parseInt(time[0])<currentHour){
						sheet.removeRow(k);
					}
					else if(Integer.parseInt(time[0])==currentHour){
						if(Integer.parseInt(time[1])<=currentMinute) sheet.removeRow(k);
					}
				}
				workbook.write();
				workbook.close();
			}
		}catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean delete(int TheatherNumber){
		try {
			Workbook work = Workbook.getWorkbook(new File(BASEROUTE+"Theathers.xls"));
			WritableWorkbook the = Workbook.createWorkbook(new File(BASEROUTE+"Theathers.xls"), work);
			String newScreenDataPath=BASEROUTE+"/Theather"+TheatherNumber;

			int screenNum = Integer.parseInt(work.getSheet(1).getCell(4, TheatherNumber).getContents());
			
			if(screenNum<1) return false;
			the.getSheet(1).addCell(new Number(4,TheatherNumber,screenNum-1));
			String ScreenPath=newScreenDataPath+"/ScreenRoom"+screenNum+".xls";
			File data = new File(ScreenPath);
			if(data.exists()){
				data.delete();
				the.write();
				the.close();
				return true;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void inputMovie(int theatherNumber, int screenRoomNumber,Movie movie,String StartDay,
			String EndDay,String StartTime,int screenNumber)
	{
		String TheatherDirPath=BASEROUTE+"/Theather"+theatherNumber;
		String ScreenDataPath=TheatherDirPath+"/ScreenRoom"+screenRoomNumber+".xls";
		try {
			Workbook book = Workbook.getWorkbook(new File(ScreenDataPath));
			WritableWorkbook workbook = Workbook.createWorkbook(new File(ScreenDataPath), book);
			WritableSheet sheet;
			int index = workbook.getNumberOfSheets()-1;
			sheet = workbook.getSheet(index-1);
			int movieNum = Integer.parseInt(sheet.getCell(2, 1).getContents());
			sheet.addCell(new Number(2,1,++movieNum));
			
			sheet = workbook.getSheet(index);
			
			sheet.addCell(new Number(0,movieNum,movieNum));
			sheet.addCell(new Label(1,movieNum,movie.getName()));
			sheet.addCell(new Label(2,movieNum,movie.getGenre()));
			sheet.addCell(new Label(3,movieNum,movie.getMainActor()));
			sheet.addCell(new Label(4,movieNum,movie.getDirector()));
			sheet.addCell(new Number(5,movieNum,movie.getRunningTime()));
			sheet.addCell(new Label(6,movieNum,movie.getRating()));
			sheet.addCell(new Label(7,movieNum,StartDay));
			sheet.addCell(new Label(8,movieNum,EndDay));
			sheet.addCell(new Number(9,movieNum,screenNumber));
			
			for(int i =0;i<screenNumber;i++)
			{
				if(i==0) sheet.addCell(new Label(10,movieNum,this.getTime(StartTime, (movie.getRunningTime()*i))));
				sheet.addCell(new Label(10+i,movieNum,this.getTime(StartTime, (movie.getRunningTime()*i)+10)));
			}
			workbook.write();
			workbook.close();
			Calendar calendar = Calendar.getInstance();
			deleteDay(theatherNumber, screenRoomNumber, calendar.get(Calendar.YEAR), 
					calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DATE));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
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
	
	public void modifyMovie(int Index,int theatherNumber, int screenRoomNumber,Movie movie,String StartDay,
			String EndDay,String StartTime,int screenNumber){
		String TheatherDirPath=BASEROUTE+"/Theather"+theatherNumber;
		String ScreenDataPath=TheatherDirPath+"/ScreenRoom"+screenRoomNumber+".xls";
		
		try {
			Workbook book = Workbook.getWorkbook(new File(ScreenDataPath));
			WritableWorkbook workbook = Workbook.createWorkbook(new File(ScreenDataPath), book);
			WritableSheet sheet;
			int index = workbook.getNumberOfSheets()-1;
			sheet = workbook.getSheet(index-1);
			int movieNum = Index;
			
			sheet = workbook.getSheet(index);
			
			sheet.addCell(new Number(0,movieNum,movieNum));
			sheet.addCell(new Label(1,movieNum,movie.getName()));
			sheet.addCell(new Label(2,movieNum,movie.getGenre()));
			sheet.addCell(new Label(3,movieNum,movie.getMainActor()));
			sheet.addCell(new Label(4,movieNum,movie.getDirector()));
			sheet.addCell(new Number(5,movieNum,movie.getRunningTime()));
			sheet.addCell(new Label(6,movieNum,movie.getRating()));
			sheet.addCell(new Label(7,movieNum,StartDay));
			sheet.addCell(new Label(8,movieNum,EndDay));
			sheet.addCell(new Number(9,movieNum,screenNumber));
			
			for(int i =0;i<screenNumber;i++)
			{
				if(i==0) sheet.addCell(new Label(10,movieNum,this.getTime(StartTime, (movie.getRunningTime()*i))));
				sheet.addCell(new Label(10+i,movieNum,this.getTime(StartTime, (movie.getRunningTime()*i)+10)));
			}
			workbook.write();
			workbook.close();
			Calendar calendar = Calendar.getInstance();
			deleteDay(theatherNumber, screenRoomNumber, calendar.get(Calendar.YEAR), 
					calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DATE));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
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
	
	public boolean deleteDay(int theatherNumber,int TotalscreenRoom,int nextYear,int nextMonth,int nextDay)
	{	
		String newScreenDataPath=BASEROUTE+"/Theather"+theatherNumber;
		String Day = new String(nextYear+"-"+nextMonth+"-"+nextDay);
		Workbook book;
		try {
			for(int i= 1;i<=TotalscreenRoom;i++){
			
				int j;
				
				String ScreenPath=newScreenDataPath+"/ScreenRoom"+i+".xls";
				book = Workbook.getWorkbook(new File(ScreenPath));
				WritableWorkbook workbook = Workbook.createWorkbook(new File(ScreenPath), book); 
				int index = workbook.getNumberOfSheets()-1;
				WritableSheet infoSheet =workbook.getSheet(index-1);
				WritableSheet movieSheet = workbook.getSheet(index);
				int movieNum = Integer.parseInt(infoSheet.getCell(2,1).getContents());
				String[] date = new String[3];
				String Tempdate;
				for(j=0;j<index-1;j++){
					if(Day.equals(workbook.getSheetNames()[j])) break;
				}
				
				int x;
				for( x = 1; x<=movieNum;x++){
					if(compareDate(Day,movieSheet.getCell(8,x).getContents())){
						x--;
						break;
					}
				}
				for(int y=1;y<=x;y++){
					movieSheet.removeRow(1);
					movieNum--;
				}
				if(movieNum<0) movieNum = 0;
				infoSheet.addCell(new Number(2,1,movieNum));
				for(int y=1;y<=movieNum;y++){
					movieSheet.addCell(new Number(0,y,y));
				}
				//System.out.println(index-1+":"+j);
				if(j==index-1){
					for(int k = 0;k<10;k++){
						index = workbook.getNumberOfSheets()-1;
						if(k<j)workbook.removeSheet(index-2);
						Tempdate=getDate(nextYear,nextMonth,nextDay,9-k);
						
						WritableSheet sheet = workbook.createSheet(Tempdate,0);
						Label label = new Label(0,0,"상영관번호");
						sheet.addCell(label);
						label = new Label(1,0,"상영회차");
						sheet.addCell(label);
						label = new Label(2,0,"총상영회차");
						sheet.addCell(label);
						label = new Label(3,0,"상영시작시간");
						sheet.addCell(label);
						label = new Label(4,0,"총좌석수");
						sheet.addCell(label);
						label = new Label(5,0,"예약좌석수");
						sheet.addCell(label);
						label = new Label(6,0,"영화번호");
						sheet.addCell(label);
						for(x = 1; x<=movieNum;x++){
							if(compareDate(movieSheet.getCell(7,x).getContents(),Tempdate)){
								if(compareDate(Tempdate,movieSheet.getCell(8,x).getContents())){
									break;
								}
							}
						}
						
						if(x<=movieNum){
							
							for(int a = 1;a<=Integer.parseInt(movieSheet.getCell(9,x).getContents());a++){
								sheet.addCell(new Number(0,a,i));
								sheet.addCell(new Number(1,a,a));
								sheet.addCell(new Number(2,a,Integer.parseInt(movieSheet.getCell(9,x).getContents())));
								sheet.addCell(new Label(3,a,movieSheet.getCell(9+a,x).getContents()));
								sheet.addCell(new Number(4,a,Integer.parseInt(infoSheet.getCell(1,1).getContents())));
								sheet.addCell(new Number(5,a,0));
								sheet.addCell(new Number(6,a,x));
							}
						}
					}
				}
				else{
					for(int b = 0;b<10;b++){
						if(b<j){workbook.removeSheet(0);}
						index = workbook.getNumberOfSheets()-1;
						if(index>11){workbook.removeSheet(index-2); break;}
						date = workbook.getSheetNames()[index-2].split("-");
						Tempdate=getDate(Integer.parseInt(date[0])
								,Integer.parseInt(date[1]),Integer.parseInt(date[2]),1);
						WritableSheet sheet = workbook.createSheet(Tempdate, index-1);
						Label label = new Label(0,0,"상영관번호");
						sheet.addCell(label);
						label = new Label(1,0,"상영회차");
						sheet.addCell(label);
						label = new Label(2,0,"총상영회차");
						sheet.addCell(label);
						label = new Label(3,0,"상영시작시간");
						sheet.addCell(label);
						label = new Label(4,0,"총좌석수");
						sheet.addCell(label);
						label = new Label(5,0,"예약좌석수");
						sheet.addCell(label);
						label = new Label(6,0,"영화번호");
						sheet.addCell(label);
						for(x = 1; x<=movieNum;x++){
							if(compareDate(movieSheet.getCell(7,x).getContents(),Tempdate)){
								if(compareDate(Tempdate,movieSheet.getCell(8,x).getContents())){
									break;
								}
							}
						}
						if(x<=movieNum){
							
							for(int a = 1;a<=Integer.parseInt(movieSheet.getCell(9,x).getContents());a++){
								sheet.addCell(new Number(0,a,i));
								sheet.addCell(new Number(1,a,a));
								sheet.addCell(new Number(2,a,Integer.parseInt(movieSheet.getCell(9,x).getContents())));
								sheet.addCell(new Label(3,a,movieSheet.getCell(9+a,x).getContents()));
								sheet.addCell(new Number(4,a,Integer.parseInt(infoSheet.getCell(1,1).getContents())));
								sheet.addCell(new Number(5,a,0));
								sheet.addCell(new Number(6,a,x));
							}
						}
					}
				}
				workbook.write();
				workbook.close();
			}
			
			String ScreenData = newScreenDataPath+"/ScreenRooms.xls";
			String ScreenPath=newScreenDataPath+"/ScreenRoom1.xls";
			Workbook temp = Workbook.getWorkbook(new File(ScreenPath));
			int Num = temp.getNumberOfSheets()-2;
			String Fdate = temp.getSheetNames()[0];
			String[] date = new String[3];
			date = Fdate.split("-");
			File screens = new File(ScreenData);
			if(screens.exists())screens.delete();
			WritableWorkbook workbook = Workbook.createWorkbook(new File(ScreenData)); 
			WritableSheet sheet;
			for(int i= 0;i<Num;i++){
				String Tempdate =  this.getDate(Integer.parseInt(date[0]), Integer.parseInt(date[1]),
						Integer.parseInt(date[2]), Num-i-1);
				sheet = workbook.createSheet(Tempdate, 0);
				sheet.addCell(new Label(0,0,"상영관번호"));
				sheet.addCell(new Label(1,0,"영화번호"));
				sheet.addCell(new Label(2,0,"영화이름"));
				for(int j = 1;j<=TotalscreenRoom ; j++){
					ScreenPath=newScreenDataPath+"/ScreenRoom"+j+".xls";
					temp = Workbook.getWorkbook(new File(ScreenPath));
					Sheet Tsheet = temp.getSheet(temp.getNumberOfSheets()-2);
					int movieNum = Integer.parseInt(Tsheet.getCell(2,1).getContents());
					Tsheet = temp.getSheet(temp.getNumberOfSheets()-1);
					int x;
					for(x = 1; x<=movieNum;x++){
						if(compareDate(Tsheet.getCell(7,x).getContents(),Tempdate)){
							if(compareDate(Tempdate,Tsheet.getCell(8,x).getContents())){
								break;
							}
						}
					}
					if(x<=movieNum){
						sheet.addCell(new Number(0,j,j));
						sheet.addCell(new Number(1,j,x));
						sheet.addCell(new Label(2,j,Tsheet.getCell(1, x).getContents()));
					}
					else{
						sheet.addCell(new Number(0,j,j));
						sheet.addCell(new Number(1,j,0));
						sheet.addCell(new Label(2,j,"NULL"));
					}
				}
			}
			workbook.write();
			workbook.close();
			return true;
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
		return false;
		
	}

	public String getEndDay(int theatherNumber,int screenRoomNumber){
		String newTheatherDirPath=BASEROUTE+"/Theather"+theatherNumber;
		String newScreenDataPath=newTheatherDirPath+"/ScreenRoom"+screenRoomNumber+".xls";
		String Day = null;
		try {
			Sheet sheet;
			Workbook workbook = Workbook.getWorkbook(new File(newScreenDataPath));
			String[] Date = new String[3];
			int index = workbook.getNumberOfSheets()-1;
			sheet = workbook.getSheet(index-1);
			int movieNum = Integer.parseInt(sheet.getCell(2,1).getContents());
			
			sheet = workbook.getSheet(index);
			
			Date = sheet.getCell(8,movieNum).getContents().split("-");
			
			Day =  getDate(Integer.parseInt(Date[0]),Integer.parseInt(Date[1]),Integer.parseInt(Date[2]),1);
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Day;
	}
	
	public int getSeatNum(int theatherNumber,int screenRoomNumber){
		String newTheatherDirPath=BASEROUTE+"/Theather"+theatherNumber;
		String newScreenDataPath=newTheatherDirPath+"/ScreenRoom"+screenRoomNumber+".xls";
		try {
			Sheet sheet;
			Workbook workbook = Workbook.getWorkbook(new File(newScreenDataPath));
			int index = workbook.getNumberOfSheets()-1;
			sheet = workbook.getSheet(index-1);
			 
			
			
			return Integer.parseInt(sheet.getCell(1,1).getContents());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public void setSeatNum(int theatherNumber,int screenRoomNumber,int seatNum){
		if(seatNum==Integer.parseInt(""))return;
		String newTheatherDirPath=BASEROUTE+"/Theather"+theatherNumber;
		String newScreenDataPath=newTheatherDirPath+"/ScreenRoom"+screenRoomNumber+".xls";
		try {
			WritableSheet sheet;
			Workbook workbook = Workbook.getWorkbook(new File(newScreenDataPath));
			WritableWorkbook book = Workbook.createWorkbook(new File(newScreenDataPath),workbook);
			int index = workbook.getNumberOfSheets()-1;
			sheet = book.getSheet(index-1);
			
			sheet.addCell(new Number(1,1,seatNum)); 
			book.write();
			book.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
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
	
	public void createScreenRooomsData(String newScreensDataPath,int totalSheet,
			String[] sheetName, int screenRoomNumber,int[] movieNumbers) {
		// TODO Auto-generated method stub
		try {
			String movieFilePath = BASEROUTE+"Movies.xls";
			Workbook movieBook=null;
			try {
				movieBook = Workbook.getWorkbook(new File(movieFilePath));
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Sheet movieSheet = movieBook.getSheet(1);
			String[] movieName = new String[totalSheet];
			for(int i=0;i<totalSheet;i++)
			{
				movieName[i] = movieSheet.getCell(1,movieNumbers[i]).getContents();
			}
			movieBook.close();
			File screenRoomsDataFilePath = new File(newScreensDataPath);
			WritableSheet sheet;
			Workbook workbook=null;
			WritableWorkbook copy;
			if(!screenRoomsDataFilePath.exists())
			{
				copy = Workbook.createWorkbook(new File(newScreensDataPath));
			}
			else
			{
				try {
					workbook = Workbook.getWorkbook(new File(newScreensDataPath));
				} catch (BiffException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				copy = Workbook.createWorkbook(new File(newScreensDataPath), workbook);
			}
			for(int i=0;i<totalSheet;i++)
			{
				copy.createSheet(sheetName[i], i);
				sheet = copy.getSheet(i);
				Label label = new Label(0,0,"상영관번호");
				try {
					sheet.addCell(label);
				} catch (RowsExceededException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				label = new Label(1,0,"영화번호");
				try {
					sheet.addCell(label);
				} catch (RowsExceededException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				label = new Label(2,0,"영화이름");
				try {
					sheet.addCell(label);
				} catch (RowsExceededException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Number number = new Number(0,screenRoomNumber,screenRoomNumber);
				try {
					sheet.addCell(number);
				} catch (RowsExceededException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				number = new Number(1,screenRoomNumber,movieNumbers[i]);
				try {
					sheet.addCell(number);
				} catch (RowsExceededException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				label = new Label(2,screenRoomNumber,movieName[i]);
				try {
					sheet.addCell(label);
				} catch (RowsExceededException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			copy.write();
			try {
				copy.close();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*이 메소드는 극장의 삭제시에 극장이 변경 되는 것을 대비한 메소드이다.
	 * 극장의 새로운 극장에 번호가 들어오게 되면은 각 상영관의 극장의 번호를
	 * 변경하게 된다. 극장 번호가 변경된다고 해도 상영관 번호와 그외의 것은
	 * 변경되지 않기 때문에 다른 정보는 건드리지 않는다.
	 */
	public void modifyTheatherNumber(int theatherNumber, int totalScreenRoomNumber)
	{
		String screenRoomsDataPath = BASEROUTE+"Theather"+theatherNumber;
		for(int i=0;i<totalScreenRoomNumber;i++)
		{
			WritableSheet sheet;
			try {
				WritableWorkbook copy = Workbook.createWorkbook(new File(screenRoomsDataPath));
				for(int j=0;j<10;j++)
				{
					sheet = copy.getSheet(j);
					for(int k=1;k<sheet.getRows();k++)
					{
						Number number = new Number(k,0,theatherNumber);
						try {
							sheet.addCell(number);
						} catch (RowsExceededException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (WriteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				copy.write();
				try {
					copy.close();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					//System.out.println("쓰는도중 오류하나 추가요");
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private String getTime(String Time, int min){
		String[] time = new String[2];
		time = Time.split(":");
		int Hour = Integer.parseInt(time[0]);
		int Min = Integer.parseInt(time[1]);
		
		Hour+=min/60;
		Min+=min%60;
		Hour+=Min/60;
		Min%=60;
		
		return Hour+":"+Min;
	}
	
	public void inputMovie(String FilePath,int totalMovieNumber, int[] movieNumber, int startYear, int startMonth,

			int startDay[], int endYear, int endMonth, int endDay[],int[] totalScreenNumber, int[][] startHour, int[][] startMinute)
	{
		
		Workbook workBook;
		WritableWorkbook copy;
		try {
			String movieFilePath = BASEROUTE+"Movies.xls";
			Workbook movieBook = Workbook.getWorkbook(new File(movieFilePath));
			Sheet movieSheet = movieBook.getSheet(1);
			int[] movieNum = new int[totalMovieNumber];
			String[] movieName = new String[totalMovieNumber];
			String[] genre=new String[totalMovieNumber];
			String[] mainActor=new String[totalMovieNumber];
			String[] directorName=new String[totalMovieNumber];
			String[] runningTime=new String[totalMovieNumber];
			int[] grade=new int[totalMovieNumber];
			for(int i=0;i<totalMovieNumber;i++)
			{
				movieNum[i] = Integer.parseInt(movieSheet.getCell(0,movieNumber[i]).getContents());
				movieName[i] = movieSheet.getCell(1,movieNumber[i]).getContents();
				genre[i] = movieSheet.getCell(2,movieNumber[i]).getContents();
				mainActor[i] = movieSheet.getCell(3,movieNumber[i]).getContents();
				directorName[i] = movieSheet.getCell(4,movieNumber[i]).getContents();
				runningTime[i] = movieSheet.getCell(5,movieNumber[i]).getContents();
				String sGrade=movieSheet.getCell(6,movieNumber[i]).getContents();
				grade[i] = Integer.parseInt(sGrade); 
			}
			movieBook.close();
			workBook = Workbook.getWorkbook(new File(FilePath)); 
			copy = Workbook.createWorkbook(new File(FilePath), workBook);
			WritableSheet sheet = copy.createSheet("상영영화",copy.getNumberOfSheets());
			for(int i=0;i<totalMovieNumber+1;i++)
			{	
				if(i==0)
				{
					Label label = new Label(0,0,"영화번호");
					sheet.addCell(label);
					label = new Label(1,0,"영화제목");
					sheet.addCell(label);
					label = new Label(2,0,"장르");
					sheet.addCell(label);
					label = new Label(3,0,"주연배우");
					sheet.addCell(label);
					label = new Label(4,0,"감독");
					sheet.addCell(label);
					label = new Label(5,0,"상영시간");
					sheet.addCell(label);
					label = new Label(6,0,"등급");
					sheet.addCell(label);
					label = new Label(7,0,"상영시작일");
					sheet.addCell(label);
					label = new Label(8,0,"상영종료일");
					sheet.addCell(label);
					label = new Label(9,0,"총상영회차");
					sheet.addCell(label);
					for(int j=0;j<totalMovieNumber;j++)
					{
						for(int k=0;k<totalScreenNumber[j];k++)
						{
							Number number = new Number(10+k,0,k+1);
							sheet.addCell(number);
						}
					}
				}
				else
				{
					Number number = new Number(0,i,movieNum[i-1]);
					sheet.addCell(number);
					Label label = new Label(1,i,movieName[i-1]);
					sheet.addCell(label);
					label = new Label(2,i,genre[i-1]);
					sheet.addCell(label);
					label = new Label(3,i,mainActor[i-1]);
					sheet.addCell(label);
					label = new Label(4,i,directorName[i-1]);
					sheet.addCell(label);
					label = new Label(5,i,runningTime[i-1]);
					sheet.addCell(label);
					number = new Number(6,i,grade[i-1]);
					sheet.addCell(number);
					label = new Label(7,i,(startYear)+"-"+(startMonth)+"-"+startDay[i-1]);
					sheet.addCell(label);
					label = new Label(8,i,(endYear)+"-"+(endMonth)+"-"+endDay[i-1]);
					sheet.addCell(label);
					number = new Number(9,i,totalScreenNumber[i-1]);
					sheet.addCell(number);
					for(int j=0;j<totalScreenNumber[i-1];j++)
					{
						if(startMinute[i-1][j]<10)
						{
							label = new Label(10+j,i,startHour[i-1][j]+":0"+startMinute[i-1][j]);
							sheet.addCell(label);
						}
						else
						{
							label = new Label(10+j,i,startHour[i-1][j]+":"+startMinute[i-1][j]);
							sheet.addCell(label);
						}
					}
				}
			}
			copy.write(); 
			copy.close();
			
		}catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e)
		{
			e.printStackTrace();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	private String getDate (int startYear, int startMonth, int startDay ,int iDay )
	{
		Calendar temp=Calendar.getInstance ( );
		temp.set(startYear, startMonth-1, startDay);
		StringBuffer sbDate=new StringBuffer ( );
		temp.add ( Calendar.DAY_OF_MONTH, iDay );
		int nYear=temp.get(Calendar.YEAR);
		int nMonth=temp.get(Calendar.MONTH)+1;
		int nDay=temp.get(Calendar.DAY_OF_MONTH);
		//System.out.println(nYear+" "+nMonth+" "+nDay);
		sbDate.append ( nYear );
		if ( nMonth < 10 )
		sbDate.append ("-0"+nMonth );
		else
		sbDate.append ("-"+nMonth );
		if ( nDay < 10 )
		sbDate.append ( "-0"+nDay );
		else
		sbDate.append ("-"+nDay );
		

		return sbDate.toString ( );
	}
	
	//delete 상영관은 상영관 파일을 지우면 된다.
	private boolean compareDate(String preDate,String compare){
		String[] pDate = new String[3];
		String[] cDate = new String[3];
		pDate = preDate.split("-");
		cDate = compare.split("-");
		
		int preYear = Integer.parseInt(pDate[0]);
		int preMonth = Integer.parseInt(pDate[1]);
		int preDay = Integer.parseInt(pDate[2]);
		int comYear = Integer.parseInt(cDate[0]);
		int comMonth = Integer.parseInt(cDate[1]);
		int comDay = Integer.parseInt(cDate[2]);
		
		
		if(comYear>preYear){
			return true;
		}
		else if(comYear==preYear){
			
			if(comMonth>preMonth){
				return true;
			}
			else if(comMonth==preMonth){
				if(comDay>=preDay) return true;
			}
		}
		
		return false;
	}
	public int GetDifferenceOfDate ( int nYear1, int nMonth1, int nDate1, int nYear2, int nMonth2, int nDate2 )
	{
		Calendar cal = Calendar.getInstance ( );
		int nTotalDate1 = 0, nTotalDate2 = 0, nDiffOfYear = 0, nDiffOfDay = 0;

		if ( nYear1 > nYear2 )
		{
			for ( int i = nYear2; i < nYear1; i++ )
			{
				cal.set ( i, 12, 0 );
				nDiffOfYear += cal.get ( Calendar.DAY_OF_YEAR );
			}
			nTotalDate1 += nDiffOfYear;
		}
		else if ( nYear1 < nYear2 )
		{
			for ( int i = nYear1; i < nYear2; i++ )
			{
				cal.set ( i, 12, 0 );
				nDiffOfYear += cal.get ( Calendar.DAY_OF_YEAR );
			}
			nTotalDate2 += nDiffOfYear;
		}

		cal.set ( nYear1, nMonth1-1, nDate1 );
		nDiffOfDay = cal.get ( Calendar.DAY_OF_YEAR );
		nTotalDate1 += nDiffOfDay;

		cal.set ( nYear2, nMonth2-1, nDate2 );
		nDiffOfDay = cal.get ( Calendar.DAY_OF_YEAR );
		nTotalDate2 += nDiffOfDay;

		return nTotalDate1-nTotalDate2;
	}
}