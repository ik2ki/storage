package preticketmanager.System;

import java.util.Calendar;
import java.util.LinkedList;

import preticketmanager.model.ScreenRoom;

public class ScreenRoomFile {
	ExcelFileSystem screenRoomFile;
	ScreenRoom screenRoom;
	int moviePosition;
	LinkedList<String> screenMovieName;
	
	public ScreenRoomFile(int theatherNumber, int screenRoomNumber){
		screenRoomFile = new ExcelFileSystem();
		screenMovieName = new LinkedList<String>();
		screenRoomFile.addRoute("Theather" + theatherNumber + "/");
		screenRoomFile.setWorkbook("ScreenRoom" + screenRoomNumber + ".xls");
	}
	public void setSheetScreenDay(String screenDay){
		String[] sheetNames = screenRoomFile.getWorkbook().getSheetNames();
		int sheetNumber = screenRoomFile.getWorkbook().getNumberOfSheets();
		int i;
		for(i = 0; i < sheetNumber && !screenDay.equals(sheetNames[i]); i++)
			;
		if(i == sheetNumber)
			System.out.println(screenDay + "에 맞는 Sheet가 없습니다.");
		screenRoomFile.setSheet(i);
	}
	public void setSheetScreenRoomInfo(){
		String[] sheetNames = screenRoomFile.getWorkbook().getSheetNames();
		int sheetNumber = screenRoomFile.getWorkbook().getNumberOfSheets();
		int i;
		for(i = 0; i < sheetNumber && !"상영관정보".equals(sheetNames[i]); i++)
			;
		screenRoomFile.setSheet(i);
	}
	public void setSheetScreenMovie(){
		String[] sheetNames = screenRoomFile.getWorkbook().getSheetNames();
		int sheetNumber = screenRoomFile.getWorkbook().getNumberOfSheets();
		int i;
		for(i = 0; i < sheetNumber && !"상영영화".equals(sheetNames[i]); i++)
			;
		screenRoomFile.setSheet(i);
	}
	public int getTotalSeat(int row){
		return Integer.parseInt(screenRoomFile.getCell(4, row));
	}
	public void setFindMovie(String movieName){
		int row;
		for(row = 1; row < screenRoomFile.getSheet().getRows() && !movieName.equals(screenRoomFile.getCell(1, row)); row++)
			;
		moviePosition = row;
	}
	public String findMovieStartDate(){
		return screenRoomFile.getCell(7, moviePosition);
	}
	public String findMovieEndDate(){
		return screenRoomFile.getCell(8, moviePosition);
	}
	public LinkedList<String> getFromStartDayToEndDay(String movieName){
		LinkedList<String> day = new LinkedList<String>();
		Calendar temp = Calendar.getInstance ( );
		setFindMovie(movieName);
		String startDate = findMovieStartDate();
		String endDate = findMovieEndDate();
		int startYear, startMonth, startDay;
		int endYear, endMonth, endDay;
		
		String scrStartDate = startDate;
		String scrEndDate = endDate;
		String[] startDateSplit = new String[3];
		String[] endDateSplit = new String[3];
		startDateSplit = scrStartDate.split("-");
		endDateSplit = scrEndDate.split("-");
		//	쪼갠 문자열을 정수형으로 파싱
		startYear = Integer.parseInt(startDateSplit[0]);
		startMonth = Integer.parseInt(startDateSplit[1]);
		startDay = Integer.parseInt(startDateSplit[2]);
		endYear = Integer.parseInt(endDateSplit[0]);
		endMonth = Integer.parseInt(endDateSplit[0]);
		endDay = Integer.parseInt(endDateSplit[0]);
		
		StringBuffer sbDate = new StringBuffer();
		for(temp.set(startYear, startMonth-1, startDay);
		temp.get(Calendar.YEAR) == endYear && (Calendar.MONTH) == endMonth-1 && (Calendar.DAY_OF_MONTH) == endDay;
			temp.add(Calendar.DAY_OF_MONTH, 1)){
			int nYear=temp.get(Calendar.YEAR);
			int nMonth=temp.get(Calendar.MONTH)+1;
			int nDay=temp.get(Calendar.DAY_OF_MONTH);
			sbDate.append (nYear);
			if ( nMonth < 10 )
			sbDate.append ("-0" + nMonth);
			else
			sbDate.append ("-" + nMonth);
			if ( nDay < 10 )
			sbDate.append ("-0" + nDay);
			else
			sbDate.append ("-" + nDay);
			day.add(sbDate.toString());
		}
		return day;
	}
	public void setScreenMovieNames(){
		for(int row = 1; row < screenRoomFile.getRows(); row++)
			screenMovieName.add(screenRoomFile.getCell(1, row));
	}
	public LinkedList<String> getScreenMovieNames(){
		return screenMovieName;
	}
}
