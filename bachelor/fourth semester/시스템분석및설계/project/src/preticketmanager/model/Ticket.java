package preticketmanager.model;

public class Ticket {
	 int ticketNum;
	 String movieName;	//영화이름
	 String theatherName;	//극장이름
	 int screenRoomNumber;	//상영관번호
	 int screenNumber;	//상영회차수
	 int screenTime;	//상영시간
	 int startHour;	//시작시간
	 int startMinute;//시작분
	 String EndTime;
	 int ticketAmount;	//티켓수
	 String screenDate;
	 String userId;
	
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getTheatherName() {
		return theatherName;
	}
	public void setTheatherName(String theatherName) {
		this.theatherName = theatherName;
	}
	public int getScreenRoomNumber() {
		return screenRoomNumber;
	}
	public void setScreenRoomNumber(int screenRoomNumber) {
		this.screenRoomNumber = screenRoomNumber;
	}
	public int getScreenNumber() {
		return screenNumber;
	}
	public void setScreenNumber(int screenNumber) {
		this.screenNumber = screenNumber;
	}
	public int getScreenTime() {
		return screenTime;
	}
	public void setScreenTime(int screenTime) {
		this.screenTime = screenTime;
	}
	public int getStartHour() {
		return startHour;
	}
	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}
	public int getStartMinute() {
		return startMinute;
	}
	public void setStartMinute(int startMinute) {
		this.startMinute = startMinute;
	}
	public int getTicketAmount() {
		return ticketAmount;
	}
	public void setTicketAmount(int ticketAmount) {
		this.ticketAmount = ticketAmount;
	}
	public String getScreenDate() {
		return screenDate;
	}
	public void setScreenDate(String screenDate) {
		this.screenDate = screenDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEndTime() {
		return EndTime;
	}
	public void setEndTime(String endTime) {
		this.EndTime = endTime;
	}
	public int getTicketNum() {
		return ticketNum;
	}
	public void setTicketNum(int ticketNum) {
		this.ticketNum = ticketNum;
	}
}