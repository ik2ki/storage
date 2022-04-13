package preticketmanager.model;

public class Theather {
	private int theatherNumber;
	private String name;
	private String theatherLocation;
	private int enterPrice;
	private int totalScreenRoomNumber;
	private ScreenRoom screenRoom;
	private int screenMovieNumber;
	private int totalScreenNumber;
	private int screenRoomNumber;
	private int screenNumber;
	private String screenDay;
	
	public void setTheatherNumber(int theatherNumber) {
		this.theatherNumber = theatherNumber;
	}
	public int getTheatherNumber() {
		return theatherNumber;
	}
	public void setName(String theatherName) {
		this.name = theatherName;
	}
	public String getName() {
		return name;
	}
	public void setEnterPrice(int enterPrice) {
		this.enterPrice = enterPrice;
	}
	public int getEnterPrice() {
		return enterPrice;
	}
	public void setTheatherLocation(String theatherLocation) {
		this.theatherLocation = theatherLocation;
	}
	public String getTheatherLocation() {
		return theatherLocation;
	}
	public void setTotalScreenRoomNumber(int totalScreenRoomNumber) {
		this.totalScreenRoomNumber = totalScreenRoomNumber;
	}
	public int getTotalScreenRoomNumber() {
		return totalScreenRoomNumber;
	}
	public void setScreenNumber(int screenNumber) {
		this.screenNumber = screenNumber;
	}
	public int getScreenNumber() {
		return screenNumber;
	}
	public int getScreenMovieNumber() {
		return screenMovieNumber;
	}
	public int getTotalScreenNumber() {
		return totalScreenNumber;
	}
	public int getScreenRoomNumber() {
		return screenRoomNumber;
	}
	public String getScreenDay() {
		return screenDay;
	}
	public void setScreenMovieNumber(int screenMovieNumber) {
		this.screenMovieNumber = screenMovieNumber;
	}
	public void setTotalScreenNumber(int totalScreenNumber) {
		this.totalScreenNumber = totalScreenNumber;
	}
	public void setScreenRoomNumber(int screenRoomNumber) {
		this.screenRoomNumber = screenRoomNumber;
	}
	public void setScreenDay(String screenDay) {
		this.screenDay = screenDay;
	}
	public ScreenRoom getScreenRoom() {
		return screenRoom;
	}
	public void setScreenRoom(ScreenRoom screenRoom) {
		this.screenRoom = screenRoom;
	}
}