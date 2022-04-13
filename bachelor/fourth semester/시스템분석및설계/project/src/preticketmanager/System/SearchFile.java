package preticketmanager.System;

public class SearchFile {
	ExcelFileSystem searchFile; 
	String[] theatherNames;
	public SearchFile(){
		searchFile = new ExcelFileSystem(); 
		searchFile.setWorkbook("Search.xls");
		theatherNames = searchFile.getWorkbook().getSheetNames();
	}
	public void setTheatherSheet(String theatherName){
		int i;
		for(i = 0; i < searchFile.getNumberOfSheets() && !theatherName.equals(theatherNames[i]); i++)
			;
		searchFile.setSheet(i);
	}
	public String[] getScreenMovieList(String theatherName){
		int row, column, num = 0;
		String[] movieList = new String[searchFile.getRows()];
		setTheatherSheet(theatherName);
		for(row = 2; row < searchFile.getRows(); row++){
			boolean flag = false;
			for(column = 2; column < searchFile.getColumns(); column++)
				if(Boolean.parseBoolean(searchFile.getCell(column, row)))
					flag = true;
			if(flag){
				movieList[num] = searchFile.getCell(0, row);
				num++;
			}
		}
		return movieList;
	}
	public String[] getScreenDateList(String theatherName){
		int row, column, num = 0;
		String[] dateList = new String[searchFile.getColumns()];
		for(column = 2; column < searchFile.getColumns(); column++){
			boolean flag = false;
			//극장에서 한 영화라도 그 날짜에 상영하는 영화가 있다면 true
			for(row = 2; row < searchFile.getRows(); row++)
				if(Boolean.parseBoolean(searchFile.getCell(column, row)))
					flag = true;
			if(flag){
				dateList[num] = searchFile.getCell(column, 0);
				num++;
			}
		}
		return dateList;
	}
}