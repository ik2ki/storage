package preticketmanager.model;

public class ScreenRoom 
{
	private int theatherNumber;
	private int screenRoomNumber;
	private int screenNumber;
	private String screenDay;
	private String startTime;
	private int totalSeat;
	private int preticketSeat;
	private int screenMovieNumber;
	private Movie screenMovie;
	
	public void setTheatherNumber(int theatherNumber) {
		this.theatherNumber = theatherNumber;
	}

	public int getTheatherNumber() {
		return theatherNumber;
	}

	public void setScreenRoomNumber(int screenRoomNumber) {
		this.screenRoomNumber = screenRoomNumber;
	}

	public int getScreenRoomNumber() {
		return screenRoomNumber;
	}

	public void setScreenNumber(int screenNumber) {
		this.screenNumber = screenNumber;
	}

	public int getScreenNumber() {
		return screenNumber;
	}

	public void setScreenDay(String screenDay) {
		this.screenDay = screenDay;
	}

	public String getScreenDay() {
		return screenDay;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setTotalSeat(int totalSeat) {
		this.totalSeat = totalSeat;
	}

	public int getTotalSeat() {
		return totalSeat;
	}

	public void setPreticketSeat(int preticketSeat) {
		this.preticketSeat = preticketSeat;
	}

	public int getPreticketSeat() {
		return preticketSeat;
	}

	public void setScreenMovieNumber(int screenMovieNumber) {
		this.screenMovieNumber = screenMovieNumber;
	}

	public int getScreenMovieNumber() {
		return screenMovieNumber;
	}

	public void setScreenMovie(Movie screenMovie) {
		this.screenMovie = screenMovie;
	}

	public Movie getScreenMovie() {
		return screenMovie;
	}
}