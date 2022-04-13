package preticketmanager.admindatamanager;

import java.io.File;
import java.io.IOException;

import preticketmanager.model.Movie;

import jxl.*;
import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.Number;
import jxl.write.biff.RowsExceededException;

public class SearchExcelWriter {
	protected static final String BASEROUTE = "./src/preticketmanager/data/";
	public SearchExcelWriter() throws IOException, RowsExceededException, WriteException{

		String dataPath = BASEROUTE;
		String Path=BASEROUTE+"Search.xls";
		
		File dataDir = new File(dataPath);
		if(!dataDir.exists())
		{
			dataDir.mkdir();
		}
		File excelFilePath = new File(Path);
		if(!excelFilePath.exists())
		{
			WritableWorkbook makebook = Workbook.createWorkbook(excelFilePath);
			makebook.createSheet("0", 0);
			makebook.write();
			makebook.close();
		}
		
	}
	
	public void Loader(){//반드시 Loader를 호출할것
		try {
			if((new File(BASEROUTE+"Search.xls").exists())){
				(new File(BASEROUTE+"Search.xls")).delete();
				WritableWorkbook makebook = Workbook.createWorkbook(new File(BASEROUTE+"Search.xls"));
				makebook.createSheet("0", 0);	
				makebook.write();
				makebook.close();
			}
			
			Workbook workbook = Workbook.getWorkbook(new File(BASEROUTE+"Search.xls"));
			WritableWorkbook writeBook = Workbook.createWorkbook(new File(BASEROUTE+"Search.xls"), workbook);
			WritableSheet makesheet;
			writeBook.removeSheet(0);
			Workbook theather = Workbook.getWorkbook(new File(BASEROUTE+"Theathers.xls"));
			Sheet theasheet = theather.getSheet(0);
			Workbook movie = Workbook.getWorkbook(new File(BASEROUTE+"Movies.xls"));
			Sheet movisheet = movie.getSheet(0);
			String[] date;
			int dateNum;
			int screNum;
			int theaNum = Integer.parseInt(theasheet.getCell(0,1).getContents());
			int moviNum = Integer.parseInt(movisheet.getCell(0,1).getContents());
			theasheet = theather.getSheet(1);
			movisheet = movie.getSheet(1);
			
			for(int i = 1;i<=theaNum;i++){
				makesheet = writeBook.createSheet(theasheet.getCell(1, i).getContents(),i-1);
				screNum = Integer.parseInt(theasheet.getCell(4, i).getContents());
				makesheet.addCell(new Number(0,0,i));
				makesheet.addCell(new Label(1,0,"날짜"));
				makesheet.addCell(new Label(0,1,"영화이름"));
				makesheet.addCell(new Label(1,1,"영화번호"));
				
				Workbook screen = Workbook.getWorkbook(new File(BASEROUTE+"/Theather"+i+"/ScreenRooms.xls"));
				Sheet scrsheet;
				dateNum = screen.getNumberOfSheets();
				date = new String[dateNum];
				date = screen.getSheetNames();
				
				
				for(int j = 0;j<dateNum;j++){
					makesheet.addCell(new Label(j+2,0, date[j]));
				}
				for(int k = 1;k<=moviNum;k++){
					String title = movisheet.getCell(1,k).getContents();
					makesheet.addCell(new Label(0,k+1,title));
					makesheet.addCell(new Number(1,k+1,k));
					for(int j = 0;j<dateNum;j++){
						scrsheet = screen.getSheet(j);
						makesheet.addCell(new Number(j+2,1,j+1));
						makesheet.addCell(new Label(j+2,k+1,"FALSE"));
						for(int h = 1;h<=screNum;h++){
							if(title.equals(scrsheet.getCell(2,h).getContents())){
								makesheet.addCell(new Label(j+2,k+1,"TRUE"));
							}
						}
					}
				}
				screen.close();
			}
			movie.close();
			theather.close();
			writeBook.write();
			writeBook.close();
			
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
	}
	
	public void Loader(int theatherNumber,Movie movie,String Startday,String EndDay){
		Workbook workbook;
		try {
			workbook = Workbook.getWorkbook(new File(BASEROUTE+"Search.xls"));
			WritableWorkbook writeBook = Workbook.createWorkbook(new File(BASEROUTE+"Search.xls"), workbook);
			Sheet DateSheet = writeBook.getSheet(theatherNumber);
			WritableSheet sheet = writeBook.getSheet(theatherNumber);
			String Date;
			for(int i = 0 ;i<10;i++){
				Date = DateSheet.getCell(2+i,0).getContents();
				if(compareDate(Startday,Date)){
					if(compareDate(Date,EndDay)){
						sheet.addCell(new Label(2+i,movie.getMovieNumber(),"TRUE"));
					}
				}
			}
			writeBook.write();
			writeBook.close();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}
		
		
	}
	
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
}
