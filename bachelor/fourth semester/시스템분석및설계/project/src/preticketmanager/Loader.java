package preticketmanager;

import java.io.IOException;
import java.util.Calendar;

import preticketmanager.admindatamanager.SearchExcelWriter;
import preticketmanager.admindatamanager.TheatherExcelWriter;
import preticketmanager.customdatamanager.PreticketExcelwriter;
import preticketmanager.start.Driver;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Loader {
	private int Hour;
	private int min;
	private int Year;
	private int Month;
	private int Day;
	private Calendar calender;
	private SearchExcelWriter searchExcelWriter;
	private TheatherExcelWriter theatherExcelWriter;
	private PreticketExcelwriter preticketExcelWriter;
	
	public void load() throws RowsExceededException, WriteException, IOException{
		theatherExcelWriter = new TheatherExcelWriter();
		searchExcelWriter = new SearchExcelWriter();
		preticketExcelWriter = new PreticketExcelwriter();
		calender = Calendar.getInstance();
		Year = calender.get(Calendar.YEAR);
		Month = calender.get(Calendar.MONTH)+1;
		Day = calender.get(Calendar.DATE);
		Hour = calender.get(Calendar.HOUR);
		min = calender.get(Calendar.MINUTE);
		calender = Calendar.getInstance();
		System.out.println("1."+calender.get(Calendar.MINUTE) + ":" + calender.get(Calendar.SECOND));
		Driver.loadingScreen.setText("현재 날짜에 대해서 극장 업데이트 중...");
		theatherExcelWriter.deleteDay(Year, Month, Day);
		calender = Calendar.getInstance();
		System.out.println("2."+calender.get(Calendar.MINUTE) + ":" + calender.get(Calendar.SECOND));
		Driver.loadingScreen.setText("현재시간에 대해서 상영시간 업데이트 중...");
		theatherExcelWriter.deleteScreenTime(Hour, min);
		calender = Calendar.getInstance();
		System.out.println("3."+calender.get(Calendar.MINUTE) + ":" + calender.get(Calendar.SECOND));
		Driver.loadingScreen.setText("예매 검색 정보를 업데이트 중...");
		searchExcelWriter.Loader();
		calender = Calendar.getInstance();
		System.out.println("4."+calender.get(Calendar.MINUTE) + ":" + calender.get(Calendar.SECOND));
		Driver.loadingScreen.setText("티켓 정보를 업데이트 중...");
		preticketExcelWriter.loader(Hour, min, Year, Month, Day);
		calender = Calendar.getInstance();
		System.out.println("5."+calender.get(Calendar.MINUTE) + ":" + calender.get(Calendar.SECOND));
		Driver.loadingScreen.setText("파일이 시작됩니다.");
	}
}
