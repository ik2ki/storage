package preticketmanager.admindatamanager;

import java.io.*;

import jxl.*;
import jxl.write.*;
import jxl.write.Number;
import jxl.write.biff.RowsExceededException;

public class MemberExcelWriter {
	protected static final String BASEROUTE = "./src/preticketmanager/data/";
	protected String memberDataFilePath = BASEROUTE+"/Member.xls";
	
	public MemberExcelWriter() throws IOException, RowsExceededException, WriteException{
			
		String dataPath = BASEROUTE;
		File dataDir = new File(dataPath);
		if(!dataDir.exists())
		{
			dataDir.createNewFile();
		}
		File excelFilePath =new File(memberDataFilePath);
		
		if(!excelFilePath.exists())
		{
			WritableWorkbook workbook = Workbook.createWorkbook(excelFilePath);
			WritableSheet sheet;			
			sheet = workbook.createSheet("Member", 0);
			sheet.addCell(new Label(0,0,"아이디번호"));
			sheet.addCell(new Label(1,0,"아이디"));
			sheet.addCell(new Label(2,0,"패스워드"));
			sheet.addCell(new Label(3,0,"이름"));
			sheet.addCell(new Label(4,0,"나이"));
			sheet.addCell(new Label(5,0,"폰번호"));
			sheet.addCell(new Label(6,0,"이메일"));
			sheet = workbook.createSheet("Members",1);
			sheet.addCell( new Number(0,1,0));
			sheet.addCell(new Label(0,0,"총회원수"));
			
			//sheet.setColumnView(0,8);

			workbook.write();
			workbook.close();
		}
		
	}
	
	public void create(String id, String password, String name, int age,
			String cellPhone, String email){
		
		try{
			Label label = null;
			Workbook workbook = Workbook.getWorkbook(new File(memberDataFilePath));
			WritableWorkbook copy = Workbook.createWorkbook(new File(memberDataFilePath), workbook); 
			WritableSheet writeSheet = copy.getSheet(1); 
			int nRows = Integer.parseInt(writeSheet.getCell(0, 1).getContents())+1;
			writeSheet = copy.getSheet(0);
			
			Number number = new Number(0,nRows,nRows);//영화의 번호저장
			writeSheet.addCell(number);
			
			label = new Label(1,nRows,id);
			writeSheet.addCell(label);
			
			label = new Label(2,nRows,password);
			writeSheet.addCell(label);
			
			label = new Label(3,nRows,name);
			writeSheet.addCell(label);
			
			number = new Number(4,nRows,age);
			writeSheet.addCell(number);
			
			label = new Label(5,nRows,cellPhone);
			writeSheet.addCell(label);
			
			label = new Label(6,nRows,email);
			writeSheet.addCell(label);
			
			
			//총 영화 수만 업데이트 하면된다.
			writeSheet = copy.getSheet(1);
			number = new Number(0,1,nRows);
			writeSheet.addCell(number);
			copy.write(); 
			copy.close();
			
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public boolean delete(int idNumber)
	{
		WritableWorkbook workbook = null;
		WritableSheet sheet = null;	   
	    
	    try{
	    	Workbook book = Workbook.getWorkbook(new File(memberDataFilePath));
			workbook = Workbook.createWorkbook(new File(memberDataFilePath), book); 
			
	    
	    	if( workbook != null){
	    		sheet = workbook.getSheet(1);
	    	    
	    	    if( sheet != null){
	    	    	int nRowEndIndex   = Integer.parseInt(sheet.getCell(0,1).getContents());
		    		sheet = workbook.getSheet(0);
		    		Number number = new Number(0,1,1);
		    	    if(idNumber<=0||idNumber>nRowEndIndex) return false;
	    	    	
	    			if(idNumber<=nRowEndIndex){
	    				sheet.removeRow(idNumber);   
	    				for(int i = 1;i<nRowEndIndex;i++){
	    					number = new Number(0,i,i);//영화의 번호저장
	    					sheet.addCell(number);
	    				}
	    				sheet = workbook.getSheet(1);
	    				number= new Number(0,1,nRowEndIndex-1);
	    				sheet.addCell(number);
	    				workbook.write();
	    				workbook.close();
	    				return true;
	    			}
	    		}
	    	}
	    }catch(Exception e)
		{
			e.printStackTrace();
		}
	    return false;

	}
	
	public void modify(int idNumber,String id, String password, String name, int age,
			String cellPhone, String email){
		
		WritableWorkbook workbook = null;
		WritableSheet writeSheet = null;
	    Label label = null;
	    
		try{
	    	workbook =  Workbook.createWorkbook(new File(memberDataFilePath));
	    	
	    
	    	if( workbook != null){
	    		writeSheet = workbook.getSheet(0);
	    	    
	    	    if( writeSheet != null){
	    	    	int nRows   = writeSheet.getColumn( 1).length-1;
		    		
		    	    if(idNumber==0||idNumber>nRows) ; // 예외처리
	    	    	
	    			if(idNumber<=nRows){
	    				label = new Label(1,nRows,id);
	    				writeSheet.addCell(label);
	    				
	    				label = new Label(2,nRows,password);
	    				writeSheet.addCell(label);
	    				
	    				label = new Label(3,nRows,name);
	    				writeSheet.addCell(label);
	    				
	    				Number number = new Number(4,nRows,age);
	    				writeSheet.addCell(number);
	    				
	    				label = new Label(5,nRows,cellPhone);
	    				writeSheet.addCell(label);
	    				
	    				label = new Label(6,nRows,email);
	    				writeSheet.addCell(label);
	    			}
	    		}
	    	}
	    }catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
