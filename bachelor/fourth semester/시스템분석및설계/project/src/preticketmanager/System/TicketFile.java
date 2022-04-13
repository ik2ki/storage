package preticketmanager.System;

public class TicketFile {
	ExcelFileSystem ticketFile;
	
	public TicketFile(){
		ticketFile = new ExcelFileSystem();
		ticketFile.setWorkbook("TicketList.xls");
		ticketFile.setSheet(0);
	}
	public int getTicketNumber(int row) {
		return Integer.parseInt(ticketFile.getCell(0, row));
	}
	public String getTheatherName(int row) {
		return ticketFile.getCell(1, row);
	}
	public String getMovieName(int row) {
		return ticketFile.getCell(2, row);
	}
	public int getScreenRoomNumber(int row) {
		return Integer.parseInt(ticketFile.getCell(3, row));
	}
	public int getScreenNumber(int row) {
		return Integer.parseInt(ticketFile.getCell(4, row));
	}
	public String getStartTime(int row) {
		return ticketFile.getCell(5, row);
	}
	public String getEndTime(int row) {
		return ticketFile.getCell(6, row);
	}
	public String getScreenTime(int row) {
		return ticketFile.getCell(7, row);
	}
	public String getId(int row) {
		return ticketFile.getCell(8, row);
	}
	public int getTicketAmount(int row) {
		return Integer.parseInt(ticketFile.getCell(9, row));
	}
	public String getScreenDay(int row){
		return ticketFile.getCell(10, row);
	}
	public String getTheatherNumber(int row){
		return ticketFile.getCell(11, row);
	}
	public void close(){
		ticketFile.closeFile();
	}
}
