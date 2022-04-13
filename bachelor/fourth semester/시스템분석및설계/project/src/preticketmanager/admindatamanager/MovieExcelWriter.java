package preticketmanager.admindatamanager;

import java.io.*;

import jxl.*;
import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.Number;
import jxl.write.biff.RowsExceededException;

//이 클래스는 관리자에 관한 클래스이다.
//관리자는 영화데이터에 관하여 조정을 할 수가 있다.
//영화데이터의 생성, 수정, 삭제가 가능하다.
//영화데이터의 생성에 필요한 데이터는
//영화제목,
//주연배우 
//감독
//상영시간 등급이 된다.
//영화에 관해서는 데이터의 수정과 삭제가 빈번함으로 
//여러가지 데이터를 처리하도록 노력한다.

public class MovieExcelWriter {
	private static final String BASEROUTE = "./src/preticketmanager/data/"; //엑셀 파일 저장 루트

	//이 메소드는 새로운 영화 데이터를 넣는다.
	//영화가 저장되어 있는 데이터 파일의 경로
	//새로운 영화가 생기면 영화에 대한 평에 대해 xls파일 이 생성된다.
	//경로는 BASEROUTE/Movie/Movie+movieNumber+".xls"가 생기게된다.
	String movieDataFilePath=BASEROUTE+"Movies.xls";
	
	public MovieExcelWriter()throws IOException, RowsExceededException, WriteException{
			
			String dataPath = BASEROUTE;
			File dataDir = new File(dataPath);
			if(!dataDir.exists())
			{
				dataDir.mkdir();
			}
			File excelFilePath =new File(movieDataFilePath);
			
			if(!excelFilePath.exists())
			{
				WritableWorkbook workbook = Workbook.createWorkbook(excelFilePath);
				WritableSheet sheet;
				sheet = workbook.createSheet("총영화", 0);
				sheet.addCell(new Label(0,0,"총 상영영화"));
				sheet.addCell(new Number(0,1,0));
				sheet = workbook.createSheet("상영영화",1);
				sheet.addCell(new Label(0,0,"영화번호"));
				sheet.addCell(new Label(1,0,"영화제목"));
				sheet.addCell(new Label(2,0,"장르"));
				sheet.addCell(new Label(3,0,"주연배우"));
				sheet.addCell(new Label(4,0,"감독"));
				sheet.addCell(new Label(5,0,"상영시간"));
				sheet.addCell(new Label(6,0,"등급"));
				sheet.addCell(new Label(7,0,"개봉일"));
				sheet.addCell(new Label(8,0,"이미지파일이름"));
				sheet.addCell(new Label(9,0,"Intro파일이름"));
				
				
				//sheet.setColumnView(0,8);

				workbook.write();
				workbook.close();
			}
		
	}
	public boolean CheckOverlap(String movieName){ // true일때가 중복이 있는 경우다.
		try {
			Workbook workbook = Workbook.getWorkbook(new File(movieDataFilePath));
			Sheet sheet = workbook.getSheet(0);
			int nRow = Integer.parseInt(sheet.getCell(0,1).getContents());
			sheet = workbook.getSheet(1);
			for(int i = 0; i<=nRow;i++){
				if(movieName.equals(sheet.getCell(1,i).getContents()))return true;
			}
			return false;
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public void creteMovie(String movieName,String genre, String mainActor, String directorName
			, int runningTime, String grade,int startYear,int startMonth,int startDay ,String imageFilePath, String introductionFilePath)
	{
		try{
			//개별적 영화파일 작성 개별적 영화파일은 0Sheet는 영화에 대한 소개만으로 이루어진다.
			Label label = null;
			String image = null;
			String intro = null;
			Number number;
			Workbook workbook = Workbook.getWorkbook(new File(movieDataFilePath));
			WritableWorkbook writeBook = Workbook.createWorkbook(new File(movieDataFilePath), workbook);
			Sheet sheet = writeBook.getSheet(0);
			WritableSheet writeSheet = writeBook.getSheet(1);
			//절대 경로를 받아서 이미지 파일을 업데이트 한다.
			//이미지 파일에 대한 파일 경로를 설정한 이미지 파일 경로로 파일 객체를 생성한다.
			
			if(imageFilePath!=null){
					//이미지 객체생성 생성된 이미지 객체를 
				//내가 설정한 경로로 옮긴다.
				//만약에 이미지 파일이 없다고 하면은 새로 디렉토리를 생성한다.
				File orginImage = new File(imageFilePath);
				image = orginImage.getName();
								
				File imageRoot = new File(BASEROUTE+"/image/");
				File myImageFile = new File(BASEROUTE+"/image/"+orginImage.getName());
				if(imageRoot.exists())
				{
					//새로운 디렉토리를 생성한다.
					imageRoot.mkdir();
				}
				orginImage.renameTo(myImageFile);
				
			}
			if(introductionFilePath!=null){
				File orginIntro = new File(introductionFilePath);
				intro = orginIntro.getName();
				
				File imageRoot = new File(BASEROUTE+"/Moive/");
				File myImageFile = new File(BASEROUTE+"/Moive/"+ intro);
				if(imageRoot.exists())
				{
					//새로운 디렉토리를 생성한다.
					imageRoot.mkdir();
				}
				orginIntro.renameTo(myImageFile);
				
			}
			//극장의 다음 극장의 번호를 저장하였다.
			int nRows=Integer.parseInt(sheet.getCell(0,1).getContents())+1;
			
				number = new Number(0,nRows,nRows);//영화의 번호저장
				writeSheet.addCell(number);
						
				label = new Label(1,nRows,movieName);
				writeSheet.addCell(label);
					
				label = new Label(2,nRows,genre);
				writeSheet.addCell(label);
						
				label = new Label(3,nRows,mainActor);
				writeSheet.addCell(label);
						
				label = new Label(4,nRows,directorName);
				writeSheet.addCell(label);
						
				number = new Number(5,nRows,runningTime);
				writeSheet.addCell(number);
					
				label = new Label(6,nRows,grade);
				writeSheet.addCell(label);
						
			//	Date startTime = new Date(startYear-1900,startMonth-1,startDay);
			//	DateFormat userdatafmt = new DateFormat("yyyy-mm-dd");
			//	WritableCellFormat dataformat = new WritableCellFormat(userdatafmt);
			//	DateTime datetime = new DateTime(7,nRows,startTime,dataformat);
				String Date = startYear+"-"+startMonth+"-"+startDay;
				writeSheet.addCell(new Label(7,nRows,Date));
				
				label = new Label(8,nRows,image);
				writeSheet.addCell(label);
				
				label = new Label(9,nRows,intro);
				writeSheet.addCell(label);
					
	
				//총 영화 수만 업데이트 하면된다.
				writeSheet = writeBook.getSheet(0);
				number = new Number(0,1,nRows);
				writeSheet.addCell(number);
				
				writeBook.write();
				writeBook.close();
				
				//그리고 개별 데이터영화 데이터들에 관한 디렉토리와 영화+Index+".xls"를 생성한다.
				String movieFilePath=BASEROUTE+"/Movie";
				File movieDir = new File(movieFilePath);
				if(!movieDir.exists())
				{
					movieDir.mkdir();
					String movieXlsFilePath = BASEROUTE+"/Movie"+"/Movie"+nRows+".xls";
					File reviewFile = new File(movieXlsFilePath);
					WritableWorkbook workbook1 = Workbook.createWorkbook(reviewFile);
					workbook1.createSheet("Review", 0).addCell(new Label(0,0,"리뷰"));
					workbook1.createSheet("Reviews",1).addCell( new Number(0,1,0));
					
					//sheet.setColumnView(0,8);

					workbook1.write();
					workbook1.close();
				}
				else
				{
					String movieXlsFilePath = BASEROUTE+"/Movie"+"/Movie"+nRows+".xls";
					File reviewFile = new File(movieXlsFilePath);
					WritableWorkbook workbook1 = Workbook.createWorkbook(reviewFile);
					workbook1.createSheet("Review", 0).addCell(new Label(0,0,"리뷰"));
					workbook1.createSheet("Reviews",1).addCell( new Number(0,1,0));
					
					//sheet.setColumnView(0,8);

					workbook1.write();
					workbook1.close();
				}
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public boolean modifyMovie(int movieNumber,String movieName,String genre, String mainActor, String directorName
			, int runningTime, String grade,int startYear,int startMonth,int startDay ,String imageFilePath, String introductionFilePath)
	{
		//위의 프로세스에서 알맞은 영화번호임을 처리해 주었다고 가정을 한다.
		//하지만 만일을 위해서 예외처리를 한다.
		try{
			Label label = null;
			Number number;
			Workbook workbook = Workbook.getWorkbook(new File(movieDataFilePath));
			WritableWorkbook writeBook = Workbook.createWorkbook(new File(movieDataFilePath), workbook);
			Sheet sheet = writeBook.getSheet(0);
			WritableSheet writeSheet = writeBook.getSheet(1);
			//절대 경로를 받아서 이미지 파일을 업데이트 한다.
			//이미지 파일에 대한 파일 경로를 설정한 이미지 파일 경로로 파일 객체를 생성한다.
			File orginImage = new File(imageFilePath);	//이미지 객체생성 생성된 이미지 객체를 
			//내가 설정한 경로로 옮긴다.
			//만약에 이미지 파일이 없다고 하면은 새로 디렉토리를 생성한다.
			File imageRoot = new File(BASEROUTE+"/image");
			File myImageFile = new File(BASEROUTE+"/image"+orginImage.getName());
			if(imageRoot.exists())
			{
				//새로운 디렉토리를 생성한다.
				imageRoot.mkdir();
			}
			orginImage.renameTo(myImageFile);
			//극장의 다음 극장의 번호를 저장하였다.
			int nRows=Integer.parseInt(sheet.getCell(1,0).getContents());
			if(nRows>=movieNumber&&movieNumber>0)
			{
				label = new Label(movieNumber,1,movieName);
				writeSheet.addCell(label);
					
				label = new Label(movieNumber,2,genre);
				writeSheet.addCell(label);
						
				label = new Label(movieNumber,3,mainActor);
				writeSheet.addCell(label);
						
				label = new Label(movieNumber,4,directorName);
				writeSheet.addCell(label);
						
				number = new Number(movieNumber,5,runningTime);
				writeSheet.addCell(number);
					
				label = new Label(movieNumber,6,grade);
				writeSheet.addCell(label);
						
				number = new Number(movieNumber,7,startYear);
				writeSheet.addCell(number);
				
				number = new Number(movieNumber,8,startMonth);
				writeSheet.addCell(number);
				
				number = new Number(movieNumber,9,startDay);
				writeSheet.addCell(number);
					
				label = new Label(movieNumber,10,myImageFile.getName());
				writeSheet.addCell(label);
				
				label = new Label(movieNumber,11,introductionFilePath);
				writeSheet.addCell(label);
				
				writeBook.write();
				writeBook.close();
				return true;
			}
			else
			{
				return false;
				//System.out.println("영화의 번호가 목록에 없습니다.");
				//총 영화 수는 변함이 없다.
			}
			
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public  boolean delete(int movieNumber)
	{
		WritableWorkbook workbook = null;
		WritableSheet sheet = null;
			    
	    try{
	    	Workbook book = Workbook.getWorkbook(new File(movieDataFilePath));
			workbook = Workbook.createWorkbook(new File(movieDataFilePath), book); 
			
	    
	    	if( workbook != null){
	    		sheet = workbook.getSheet(0);
	    	    
	    	    if( sheet != null){
	    	    	int nRowEndIndex   = Integer.parseInt(sheet.getCell(0,1).getContents());
		    		sheet = workbook.getSheet(1);
		    		Number number = new Number(0,1,1);
		    	    if(movieNumber<=0||movieNumber>nRowEndIndex) return false;
	    	    	
	    			if(movieNumber<=nRowEndIndex){
	    				sheet.removeRow(movieNumber);   
	    				for(int i = 1;i<nRowEndIndex;i++){
	    					number = new Number(0,i,i);//영화의 번호저장
	    					sheet.addCell(number);
	    				}
	    				sheet = workbook.getSheet(0);
	    				number= new Number(0,1,nRowEndIndex-1);
	    				sheet.addCell(number);
	    				
	    				workbook.write();
	    				workbook.close();
	    				
	    				//그리고 개별 데이터영화 데이터들을 삭제한다.
	    				String movieFilePath=BASEROUTE+"/Movie";
	    				File movieDir = new File(movieFilePath);
	    				File change = null;
	    				if(!movieDir.isFile())
	    				{
	    					
	    					String movieXlsFilePath = BASEROUTE+"/Movie"+"/Movie"+movieNumber+".xls";
	    					File reviewFile = new File(movieXlsFilePath);
	    					
	    					if(reviewFile.exists()){
	    						
	    						reviewFile.delete();
	    						for(int i =movieNumber+1;i<=nRowEndIndex;i++)
	    						{
	    							reviewFile = new File(BASEROUTE+"/Movie"+"/Movie"+i+".xls");
	    							change = new File(BASEROUTE+"/Movie"+"/Movie"+(i-1)+".xls");
	    							if(reviewFile.exists()) reviewFile.renameTo(change);
	    							else return false;
	    						}
	    						
	    						return true;
	    					}
	    					return false;
	    				}
	    				else
	    				{
	    					return false;
	    				}
	    			}
	    		}
	    	}
	    }catch(Exception e)
		{
			e.printStackTrace();
		}
	    return false;

	}
}