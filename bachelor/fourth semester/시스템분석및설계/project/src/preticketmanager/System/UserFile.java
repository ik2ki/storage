package preticketmanager.System;

import preticketmanager.model.User;

public class UserFile {
	ExcelFileSystem userFile;
	User user;

	public UserFile(int flag){
		userFile = new ExcelFileSystem();
		if(flag == 1){
			userFile.setWorkbook("Member.xls");	
		}
		else if(flag == 2){
			userFile.setWorkbook("Admin.xls");
		}
		userFile.setSheet(0);
	}
	public int getIdNumber(int row) {
		return Integer.parseInt(userFile.getCell(0, row));
	}
	public String getID(int row) {
		return userFile.getCell(1, row);
	}
	public String getPassword(int row) {
		return userFile.getCell(2, row);
	}
	public String getName(int row) {
		return userFile.getCell(3, row);
	}
	public int getAge(int row) {
		return Integer.parseInt(userFile.getCell(4, row));
	}
	public String getCellPhone(int row) {
		return userFile.getCell(5, row);
	}
	public String getEmail(int row) {
		return userFile.getCell(6, row);
	}
	public void setUser(int row){
		user = new User();
		user.setIdNumber(this.getIdNumber(row));
		user.setID(this.getID(row));
		user.setName(this.getName(row));
		user.setAge(this.getAge(row));
		user.setCellPhone(this.getCellPhone(row));
		user.setEmail(this.getEmail(row));
	}
	public User getUser() {
		return user;
	}
	public int getNumberOfUser(){
		return userFile.getSheet().getRows();
	}
	public void close(){
		userFile.closeFile();
	}
}
