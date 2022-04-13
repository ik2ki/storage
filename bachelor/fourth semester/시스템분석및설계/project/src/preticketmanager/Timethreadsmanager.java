package preticketmanager;

import java.io.IOException;
import java.util.Calendar;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import preticketmanager.admindatamanager.*;
import preticketmanager.customdatamanager.PreticketExcelwriter;

public class Timethreadsmanager extends Thread{
	
	private int Hour;
	private int min;
	private int sec;
	private int T;
	private int Tmin;
	private int Tsec;
	private int Year;
	private int Month;
	private int Day;
	private Calendar calender;
	private SearchExcelWriter Sthread;
	private TheatherExcelWriter Tthread;
	private PreticketExcelwriter Pthread;
	public Timethreadsmanager() throws RowsExceededException, WriteException, IOException{
		super();
		Tthread = new TheatherExcelWriter();
		Sthread = new SearchExcelWriter();
		Pthread = new PreticketExcelwriter();
		calender = Calendar.getInstance();
		Year = calender.get(Calendar.YEAR);
		Month = calender.get(Calendar.MONTH)+1;
		Day = calender.get(Calendar.DATE);
		Hour = calender.get(Calendar.HOUR);
		min = calender.get(Calendar.MINUTE);
		sec = calender.get(Calendar.SECOND);
		Tsec=60-sec;
		Tmin = min%10;
		Tmin=9-Tmin;
		T = Tmin*60*1000+Tsec*1000-1;
	}
	public void start()
	{
		super.start();
	}
	public void run(){
		while(true){
			try {
				Timethreadsmanager.sleep(T);
				calender = Calendar.getInstance();
				if(Day!=calender.get(Calendar.DATE)){
					Year = calender.get(Calendar.YEAR);
					Month = calender.get(Calendar.MONTH)+1;
					Day = calender.get(Calendar.DATE);
					Hour = calender.get(Calendar.HOUR);
					min = calender.get(Calendar.MINUTE);
					sec = calender.get(Calendar.SECOND);
					Tthread.deleteDay(Year, Month, Day);
					Tthread.deleteScreenTime(Hour, min);
					Sthread.Loader();
					Pthread.loader(Hour, min, Year, Month, Day);
				}
				else{
					Tthread.deleteScreenTime(Hour, min);
					Pthread.loader(Hour, min, Year, Month, Day);
				}
				T = 10*60*1000-1;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
