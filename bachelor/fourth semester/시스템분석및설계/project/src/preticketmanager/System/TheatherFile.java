package preticketmanager.System;

import preticketmanager.model.Theather;

public class TheatherFile {
	ExcelFileSystem theatherFile;
	Theather theather;
	
	public TheatherFile(){
		theatherFile = new ExcelFileSystem();
		theatherFile.setWorkbook("Theathers.xls");
		theatherFile.setSheet(1);
	}
	public int getTheatherNumber(int row) {
		return Integer.parseInt(theatherFile.getCell(0, row));
	}
	public String getName(int row) {
		return theatherFile.getCell(1, row);
	}
	public String getTheatherLocation(int row) {
		return theatherFile.getCell(2, row);
	}
	public int getEnterPrice(int row) {
		return Integer.parseInt(theatherFile.getCell(3, row));
	}
	public int getTotalScreenRoomNumber(int row) {
		return Integer.parseInt(theatherFile.getCell(4, row));
	}
	public void setTheather(int row){
		theather = new Theather();
		theather.setTheatherNumber(this.getTheatherNumber(row));
		theather.setName(this.getName(row));
		theather.setTheatherLocation(this.getTheatherLocation(row));
		theather.setEnterPrice(this.getEnterPrice(row));
		theather.setTotalScreenRoomNumber(this.getTotalScreenRoomNumber(row));
	}
	public Theather getTheather() {
		return theather;
	}
	public int getNumberOfTheather(){
		return theatherFile.getSheet().getRows();
	}
	public int fineTheather(int theatherNumber){
		int row;
		for(row = 1; row < getNumberOfTheather() && !(theatherNumber == getTheatherNumber(row)); row++)
			;
		return row;
	}
	public int fineTheather(String theatherName){
		int row;
		for(row = 1; row < getNumberOfTheather() && !theatherName.equals(getName(row)); row++)
			;
		return row;
	}
	public void close(){
		theatherFile.closeFile();
	}
}
