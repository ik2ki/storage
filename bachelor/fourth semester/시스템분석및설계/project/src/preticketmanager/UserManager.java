package preticketmanager;

import preticketmanager.System.UserFile;
import preticketmanager.model.*;
import preticketmanager.admindatamanager.*;

import java.io.IOException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class UserManager extends MemberExcelWriter{
	UserFile userFile;
	public UserManager(int flag) throws IOException, RowsExceededException, WriteException {
		super();
		userFile = new UserFile(flag);
	}
	public User loadUser(int row){
		userFile.setUser(row);
        return userFile.getUser();
	}
	public int findUser(String ID, String password){
		for(int row=1; row < userFile.getNumberOfUser(); row++)
			if(userFile.getID(row).equals(ID))
			{
				if(userFile.getPassword(row).equals(password)){
					return row;	
				}
				else{ 
					return -1;
				}
			}
		return -2;	
	}
	public boolean checkOverlap(String ID){
		userFile.setUser(1);
		for(int row = 1; row < userFile.getNumberOfUser(); row++){
			if(userFile.getID(row).equals(ID))
				return false;
		}
		return true;
	}
}
