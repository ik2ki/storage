package preticketmanager;

import preticketmanager.System.ExcelFileSystem;
import preticketmanager.model.*;

import java.util.LinkedList;

public class AdvancedPurchase {
	ExcelFileSystem excelFileSystem;
	ExcelFileSystem screenRoomFile;
	ExcelFileSystem screenTimeFile;
	
	LinkedList<Integer> movieItem;
	LinkedList<Integer> theatherItem;
	LinkedList<Integer> dateItem;
	LinkedList<Integer> screenRoomItem;
	LinkedList<Integer> timeItem;
	LinkedList<String> viewItem;

	Ticket ticket;
	Movie movie;
	Theather theather;
	String date;
	ScreenRoom screenRoom;
	String time;
	int dateNumber;

	String[] sequence = new String[3];
	String[] theatherNames;
	
	public AdvancedPurchase(){
		excelFileSystem = new ExcelFileSystem();
		screenRoomFile = new ExcelFileSystem();
		screenTimeFile = new ExcelFileSystem();
		
		movieItem = new LinkedList<Integer>();
		theatherItem = new LinkedList<Integer>();
		dateItem = new LinkedList<Integer>();
		screenRoomItem = new LinkedList<Integer>();
		timeItem = new LinkedList<Integer>();
		viewItem = new LinkedList<String>();
		ticket = new Ticket();
		theather = null;
		movie = null;
		date = null;
		time = null;
		screenRoom = null;
		excelFileSystem.setWorkbook("Search.xls");
		theatherNames = excelFileSystem.getWorkbook().getSheetNames();
	}

	public Movie getMovie() {
		return movie;
	}
	public Theather getTheather() {
		return theather;
	}
	public String getDate() {
		return date;
	}
	public ScreenRoom getScreenRoom() {
		return screenRoom;
	}	
	public String getTime() {
		return time;
	}

	public LinkedList<String> viewTheatherItem(){
		theatherItem.clear();
		viewItem.clear();
	    int row, column; //읽어들일 수, 행, 열의 변수
    	int size = excelFileSystem.getNumberOfSheets();
	    if(movie == null && date == null){
			for(int i=0; i < size; i++){
				theatherItem.add(i);
			}
		}
		else if(movie != null && date == null){
			for(int i=0; i < size; i++){
				excelFileSystem.setSheet(i);
				for(row = 2; movie.getMovieNumber() != Integer.parseInt(excelFileSystem.getCell(1, row)); row++)
					;	//선택한 영화의 movieNumber와 같은 것을 찾는다.
				boolean flag = false;
				//해당영화가 극장에서 상영하는지 조사한다.
				for(column = 2; column < excelFileSystem.getColumns(); column++)
					//그 영화가 한번이라도 상영된다면 true
					if(Boolean.parseBoolean(excelFileSystem.getCell(column, row)))
						flag = true;
				if(flag){
					theatherItem.add(i);
				}
			}
		}
		else if(movie == null && date != null){
			for(int i=0; i < size; i++){
				excelFileSystem.setSheet(i);
				for(column = 2; dateNumber != Integer.parseInt(excelFileSystem.getCell(column, 1)); column++)
					;	//선택한 날짜의 dateNumber와 같은 것을 찾는다.
				boolean flag = false;
				//정해진 기간동안 영화가 상영되는 극장을 조사한다.
				for(row = 2; row < excelFileSystem.getRows(); row++)
					//극장에서 그 정해진 기간동안 영화가 한번이라도 상영된다면 true
					if(Boolean.parseBoolean(excelFileSystem.getCell(column, row)))
						flag = true;
				if(flag){
					theatherItem.add(i);
				}
			}
		}
		else{
			for(int i=0; i < size; i++){
				excelFileSystem.setSheet(i);
				for(row = 2; movie.getMovieNumber() != Integer.parseInt(excelFileSystem.getCell(1, row)); row++)
					;	//선택한 영화의 movieName와 같은 것을 찾는다.
				for(column = 2; dateNumber != Integer.parseInt(excelFileSystem.getCell(column, 1)); column++)
					;	//선택한 날짜의 date와 같은 것을 찾는다
				if(Boolean.parseBoolean(excelFileSystem.getCell(column, row))){
					theatherItem.add(i);
				}
			}
		}
		for(int i = 0; i < theatherItem.size(); i++)
			viewItem.add(theatherNames[theatherItem.get(i)]);
		return viewItem;
	}
	public LinkedList<String> viewMovieItem(){	
		movieItem.clear();
		viewItem.clear();
	    int row, column; //읽어들일 수, 행, 열의 변수
		if(theather == null && date == null){
			excelFileSystem.setSheet(0);
			for(row=2; row < excelFileSystem.getRows(); row++){
				movieItem.add(row);
			}
		}
		else if(theather != null && date == null){
			int i;
			for(i=0; !theather.getName().equals(theatherNames[i]); i++)
				;
			excelFileSystem.setSheet(i);
			for(row = 2; row < excelFileSystem.getRows(); row++){
				boolean flag = false;
				for(column = 2; column < excelFileSystem.getColumns(); column++)
					if(Boolean.parseBoolean(excelFileSystem.getCell(column, row)))
						flag = true;
				if(flag){
					movieItem.add(row);
				}
			}
		}
		else if(theather == null && date != null){
			excelFileSystem.setSheet(0);//기본 설정값
			int rowSize = excelFileSystem.getRows();
			int sheetSize = excelFileSystem.getNumberOfSheets();
			boolean[] flag = new boolean[rowSize];
			for(row = 2; row < rowSize; row++)
				flag[row-2] = false;
			for(int i=0; i < sheetSize; i++){
				excelFileSystem.setSheet(i);
				for(column = 2; dateNumber != Integer.parseInt(excelFileSystem.getCell(column, 1)); column++)
					;
				for(row = 2; row < rowSize; row++)
					if(Boolean.parseBoolean(excelFileSystem.getCell(column, row)))
						flag[row-2] = true;
			}
			for(row = 2; row < rowSize; row++)
				if(flag[row-2]){
					movieItem.add(row);
				}
		}
		else{
			int i;
			for(i=0; !theather.getName().equals(theatherNames[i]); i++)
				;
			excelFileSystem.setSheet(i);
			for(column = 2; dateNumber != Integer.parseInt(excelFileSystem.getCell(column, 1)); column++)
				;	//선택한 날짜의 dateNumber와 같은 것을 찾는다.
			for(row = 2; row < excelFileSystem.getRows(); row++){
				if(Boolean.parseBoolean(excelFileSystem.getCell(column, row))){
					movieItem.add(row);
				}
			}
		}
		excelFileSystem.setSheet(0);
		for(int i = 0; i < movieItem.size(); i++)
			viewItem.add(excelFileSystem.getCell(0, movieItem.get(i)));
		return viewItem;
	}
	public LinkedList<String> viewDateItem(){
		dateItem.clear();
		int row, column; //읽어들일 수, 행, 열의 변수
		if(theather == null && movie == null){
			excelFileSystem.setSheet(0);
			for(column=2; column < excelFileSystem.getColumns(); column++){
				dateItem.add(column);
			}
		}
		else if(theather != null && movie == null){
			int i;
			for(i=0; !theather.getName().equals(theatherNames[i]); i++)
				;
			excelFileSystem.setSheet(i);
			//극장을 보며 영화가 상영하는 날을 찾아본다.
			for(column = 2; column < excelFileSystem.getColumns(); column++){
				boolean flag = false;
				//극장에서 한 영화라도 그 날짜에 상영하는 영화가 있다면 true
				for(row = 2; row < excelFileSystem.getRows(); row++)
					if(Boolean.parseBoolean(excelFileSystem.getCell(column, row)))
						flag = true;
				if(flag){
					dateItem.add(column);
				}
			}
		}
		else if(theather == null && movie != null){
			excelFileSystem.setSheet(0);		//기본 설정값
			int columnSize = excelFileSystem.getColumns();
			int sheetSize = excelFileSystem.getNumberOfSheets();
			boolean[] flag = new boolean[columnSize];
			for(column = 2; column < columnSize; column++)	
				flag[column-2] = false;
			for(int i=0; i < sheetSize; i++){
				excelFileSystem.setSheet(i);
				for(row = 2; movie.getMovieNumber() != Integer.parseInt(excelFileSystem.getCell(1, row)); row++)
					;	//선택한 영화의 movieNumber와 같은 것을 찾는다.
				for(column = 2; column < columnSize; column++)
					if(Boolean.parseBoolean(excelFileSystem.getCell(column, row)))
						flag[column-2] = true;
			}
			for(column = 2; column < columnSize; column++)
				if(flag[column-2]){
					dateItem.add(column);
				}
		}
		else{
			int i;
			for(i=0; !theather.getName().equals(theatherNames[i]); i++)
				;
			excelFileSystem.setSheet(i);
			for(row = 2; movie.getMovieNumber() != Integer.parseInt(excelFileSystem.getCell(1, row)); row++)
				;	//선택한 영화의 movieNumber와 같은 것을 찾는다.
			for(column = 2; column < excelFileSystem.getColumns(); column++){
				//해당 극장, 해당 날짜에 맞는 영화찾기
				if(Boolean.parseBoolean(excelFileSystem.getCell(column, row))){
					dateItem.add(column);
				}
			}
		}
		for(int i = 0; i < dateItem.size(); i++)
			viewItem.add(excelFileSystem.getCell(dateItem.get(i), 0));
		return viewItem;
	}
	public LinkedList<String> viewScreenRoomItem(){
		screenRoomItem.clear();
		screenRoomFile.setWorkbook("Theather" + theather.getTheatherNumber() +"/ScreenRooms.xls");
	    int row, i; //읽어들일 수, 행, 열의 변수
	    String[] dateNames = screenRoomFile.getWorkbook().getSheetNames();
	    for(i=0; !date.equals(dateNames[i]); i++)
	    	;
	    screenRoomFile.setSheet(i);
	    for(row=0; row < screenRoomFile.getRows(); row++){
	    	if(movie.getName().equals(screenRoomFile.getCell(2, row))){
	    		screenRoomItem.add(row);
	    	}
	    }
	    for(i = 0; i < screenRoomItem.size(); i++)
			viewItem.add(screenRoomFile.getCell(0, screenRoomItem.get(i)) + "관");
	    return viewItem;
	}
	public LinkedList<String> viewScreenTimeItem(){
		//상영시간을 선택합니다.
		timeItem.clear();
		screenTimeFile.setWorkbook("Theather" + theather.getTheatherNumber() + "/ScreenRoom" + screenRoom.getScreenRoomNumber() + ".xls");
	    int row, i; //읽어들일 수, 행, 열의 변수
	    String[] dateNames = screenRoomFile.getWorkbook().getSheetNames();
	    for(i=0; !date.equals(dateNames[i]); i++)
			;
	    screenTimeFile.setSheet(i);
	    //상영회차   상영시간    좌석
	    for(row = 1; row < screenTimeFile.getRows(); row++){
	    	timeItem.add(row);
	    }
	    for(i = 0; i < timeItem.size(); i++)
			viewItem.add(screenTimeFile.getCell(1, timeItem.get(i)) + "회차   " + screenTimeFile.getCell(3, timeItem.get(i)) + "   " + screenTimeFile.getCell(5, timeItem.get(i)) + "/" + screenTimeFile.getCell(4, timeItem.get(i)));
	    return viewItem;
	}
	public void processMovieSelection(int index){
		if(sequence[0] == null)
			sequence[0] = "movie";
		else if(sequence[0] != "movie" && sequence[0] != null && sequence[1] == null)
			sequence[1] = "movie";
		else if(sequence[0] != "movie" && sequence[0] != null && sequence[1] != "movie" && sequence[1] != null && sequence[2] == null)
			sequence[2] = "movie";
		else if(sequence[0] == "movie" && sequence[1] == "theather" && sequence[2] == null){
			sequence[1] = null;
			theather = null;
		}				
		else if(sequence[0] == "movie" && sequence[1] == "date" && sequence[2] == null){
			sequence[1] = null;
			date = null;
		}
		else if(sequence[0] == "movie" && sequence[1] != null && sequence[2] != null){
			sequence[1] = null;
			sequence[2] = null;
			theather = null;
			date = null;
		}
		else if(sequence[0] == "theather" && sequence[1] == "movie")
			date = null;
		else if(sequence[0] == "date" && sequence[1] == "movie")
			theather = null;
		if(screenRoom != null){
			screenRoomItem.clear();
			screenRoom = null;
			time = null;
		}
		
		excelFileSystem.setSheet(0);
		movie = new Movie();
		movie.setMovieNumber(Integer.parseInt(excelFileSystem.getCell(1, movieItem.get(index))));
		movie.setName(excelFileSystem.getCell(0, movieItem.get(index)));
	}
	public void processTheatherSelection(int index){
		if(sequence[0] == null)
			sequence[0] = "theather";
		else if(sequence[0] != "theather" && sequence[0] != null && sequence[1] == null)
			sequence[1] = "theather";
		else if(sequence[0] != "theather" && sequence[0] != null && sequence[1] != "theather" && sequence[1] != null && sequence[2] == null)
			sequence[2] = "theather";
		else if(sequence[0] == "theather" && sequence[1] == "movie" && sequence[2] == null){
			sequence[1] = null;
			movie = null;
		}
		else if(sequence[0] == "theather" && sequence[1] == "date" && sequence[2] == null){
			sequence[1] = null;
			date = null;
		}
		else if(sequence[0] == "theather" && sequence[1] != null && sequence[2] != null){
			sequence[1] = null;
			sequence[2] = null;
			movie = null;
			date = null;
		}
		else if(sequence[0] == "movie" && sequence[1] == "theather")
			date = null;
		else if(sequence[0] == "date" && sequence[1] == "theather")
			movie = null;
		if(screenRoom != null){
			screenRoom = null;
			time = null;
		}
		
		excelFileSystem.setSheet(theatherItem.get(index));
		theather = new Theather();
		theather.setTheatherNumber(Integer.parseInt(excelFileSystem.getCell(0, 0)));
		theather.setName(excelFileSystem.getSheet().getName());
	}
	public void processDateSelection(int index){
		if(sequence[0] == null)
			sequence[0] = "date";
		else if(sequence[0] != "date" && sequence[0] != null && sequence[1] == null)
			sequence[1] = "date";
		else if(sequence[0] != "date" && sequence[0] != null && sequence[1] != "date" && sequence[1] != null && sequence[2] == null)
			sequence[2] = "date";
		else if(sequence[0] == "date" && sequence[1] == "movie" && sequence[2] == null){
			sequence[1] = null;
			movie = null;
		}
		else if(sequence[0] == "date" && sequence[1] == "theather" && sequence[2] == null){
			sequence[1] = null;
			theather = null;
		}
		else if(sequence[0] == "date" && sequence[1] != null && sequence[2] != null){
			sequence[1] = null;
			sequence[2] = null;
			movie = null;
			theather = null;
		}
		else if(sequence[0] == "theather" && sequence[1] == "date")
			date = null;
		else if(sequence[0] == "date" && sequence[1] == "date")
			movie = null;
		if(screenRoom != null){
			screenRoom = null;
			time = null;
		}
		
		excelFileSystem.setSheet(0);
		dateNumber = Integer.parseInt(excelFileSystem.getCell(dateItem.get(index), 1));
		date = excelFileSystem.getCell(dateItem.get(index), 0);
	}
	public void processScreenRoomSelection(int index){
		//코드정리필요
		screenRoomFile.setWorkbook("Theather" + theather.getTheatherNumber() +"/ScreenRooms.xls");
		int i;
		String[] dateNames = screenRoomFile.getWorkbook().getSheetNames();
		for(i=0; !date.equals(dateNames[i]); i++)
			;
		screenRoomFile.setSheet(i);
	    screenRoom = new ScreenRoom();
	    screenRoom.setScreenRoomNumber(Integer.parseInt(screenRoomFile.getCell(0, screenRoomItem.get(index))));
	}
	public void processTimeSelection(int index){
		//코드정리필요
		screenTimeFile.setWorkbook("Theather" + theather.getTheatherNumber() + "/ScreenRoom" + screenRoom.getScreenRoomNumber() + ".xls");
	    int i;
	    String[] dateNames = screenRoomFile.getWorkbook().getSheetNames();
	    for(i=0; !date.equals(dateNames[i]); i++)
			;
	    screenTimeFile.setSheet(i);
	    
		time = screenTimeFile.getCell(3, timeItem.get(index));
	    screenRoom.setScreenNumber(Integer.parseInt(screenTimeFile.getCell(1, timeItem.get(index))));
	    screenRoom.setStartTime(time);
	    screenRoom.setTotalSeat(Integer.parseInt(screenTimeFile.getCell(4, timeItem.get(index))));
	    screenRoom.setPreticketSeat(Integer.parseInt(screenTimeFile.getCell(5, timeItem.get(index))));
	}
	public void combineObject(){
		excelFileSystem.setWorkbook("Theathers.xls");		//Theathers.xls파일을 불러온다.
		excelFileSystem.setSheet(1);        	//극장들의 정보가 들어있는 sheet2를 연다.
    	int row;
        for(row=1; theather.getTheatherNumber() != Integer.parseInt(excelFileSystem.getCell(0, row)); row++)
        	; //영화번호에 해당하는 정보를 찾는다.
        theather.setTheatherLocation(excelFileSystem.getCell(2, row));
        theather.setEnterPrice(Integer.parseInt(excelFileSystem.getCell(3, row)));
        //극장 총수를 넣는다.
        //특정 극장에 해당하는 폴더로 이동, 상영관들 정보가 들어있는 엑셀 파일을 연다.
        theather.setScreenRoom(screenRoom); //극장-상영관 연결
        screenRoom.setScreenMovie(movie);
        excelFileSystem.closeFile();
        excelFileSystem.setWorkbook("Movies.xls");
        //상영관 정보를 읽어들일려 했지만 상영관 선택했을 때 정보를 다 읽어들였다.
    	excelFileSystem.setSheet(1);  
        for(row = 1; row < excelFileSystem.getSheet().getRows() && movie.getMovieNumber() != Integer.parseInt(excelFileSystem.getCell(0, row)); row++)
        	;
        movie.setGenre(excelFileSystem.getCell(2, row));
        movie.setMainActor(excelFileSystem.getCell(3, row));
        movie.setDirector(excelFileSystem.getCell(4, row));
        movie.setRunningTime(Integer.parseInt(excelFileSystem.getCell(5, row)));
        movie.setRating(excelFileSystem.getCell(6, row));
        movie.setReleaseDate(excelFileSystem.getCell(7, row));
        movie.setImage(excelFileSystem.getCell(8, row));
        movie.setIntroduction(excelFileSystem.getCell(9, row));
        excelFileSystem.closeFile();
	}
	public void closeSearchFile(){
		excelFileSystem.closeFile();
		screenRoomFile.closeFile();
		screenTimeFile.closeFile();
	}
}