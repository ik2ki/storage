package preticketmanager.customdatamanager;

import jxl.*;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import  preticketmanager.model.*;

import java.io.*;

public class ReviewExcelWriter 
{
	private static final String BASEROUTE = "./src/preticketmanager/data/"; //엑셀 파일 저장 루트
	
	public ReviewExcelWriter() throws IOException{
		
		String dataPath = BASEROUTE+"/Movie";
		File dataDir = new File(dataPath);
		if(!dataDir.exists())
		{
			dataDir.mkdir();
		}
		
	}
	public void createReview(int movieNumber,String id, String comment)
	{
		String movieReviewDataFilePath = BASEROUTE+"/Movie/movie"+movieNumber+".xls";
		File movieFile = new File(movieReviewDataFilePath);	
		
		if(!movieFile.exists())
		{		
			try {
				WritableWorkbook workbook = Workbook.createWorkbook(movieFile);
				
				WritableSheet sheet = workbook.createSheet("Review", 0);
				sheet.addCell(new Label(0,0,"리뷰번호"));
				sheet.addCell(new Label(1,0,"아이디"));
				sheet.addCell(new Label(2,0,"리뷰내용"));
				sheet = workbook.createSheet("Reviews",1);
				sheet.addCell( new Number(0,1,0));
				sheet.addCell(new Label(0,0,"총 리뷰수"));
				
				//sheet.setColumnView(0,8);

				workbook.write();
				workbook.close();
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		try {
			Workbook book = Workbook.getWorkbook(new File(movieReviewDataFilePath));
			WritableWorkbook writeBook = Workbook.createWorkbook(new File(movieReviewDataFilePath), book); 
			WritableSheet writeSheet =writeBook.getSheet(0);
			int nRow = writeSheet.getRows();
			Number number = new Number(0,nRow,nRow);
			writeSheet.addCell(number);
			Label label = new Label(1,nRow,id);
			writeSheet.addCell(label);
			label = new Label(2,nRow,comment);
			writeSheet.addCell(label);
			
			writeSheet =writeBook.getSheet(1);
			writeSheet.addCell(new Number(0,1,Integer.parseInt(writeSheet.getCell(0,1).getContents())+1));
			
			writeBook.write();
			writeBook.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean delete(int movieNumber,int index,String id)	
	{
		String movieReviewDataFilePath = BASEROUTE+"/Movie/movie"+movieNumber+".xls";
		File movieFile = new File(movieReviewDataFilePath);	
		
		try {
			if(!movieFile.exists()) return false;
			Workbook book = Workbook.getWorkbook(new File(movieReviewDataFilePath));
			WritableWorkbook writeBook = Workbook.createWorkbook(new File(movieReviewDataFilePath), book); 
			WritableSheet writeSheet =writeBook.getSheet(1);
			Number number;
			int nRowEndIndex   = Integer.parseInt(writeSheet.getCell(0,1).getContents());
			
			writeSheet =writeBook.getSheet(0);
			if(index>0&&index<=nRowEndIndex&&id.equals(writeSheet.getCell(1,index).getContents()))
			{
				writeSheet.removeRow(index);   
				for(int i = 1;i<nRowEndIndex;i++){
					number= new Number(0,i,i); 
					writeSheet.addCell(number);//영화의 번호저장
				}
				writeSheet =writeBook.getSheet(1);
				number =new Number(0,1,nRowEndIndex-1);
				
				writeSheet.addCell(number);
				
				writeBook.write();
				writeBook.close();
				return true;
			}
			else {
				writeBook.write();
				writeBook.close();
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public Review[] getReview(int movieNumber){
		try {
			Workbook rebook = Workbook.getWorkbook(new File(BASEROUTE+"/Movie/Movie"+movieNumber+".xls"));
			Sheet sheet = rebook.getSheet(1);
			
			int reNum = Integer.parseInt(sheet.getCell(0,1).getContents());
			
			sheet = rebook.getSheet(0);
			
			if(reNum==0) return null;
			
			Review[] review = new Review[reNum];
			
			for(int i =1 ;i<=reNum;i++){
				String ID = sheet.getCell(1,i).getContents();
				String re = sheet.getCell(2,i).getContents();
				review[i-1] = new Review(ID,re);
			}
						
			return review;
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
