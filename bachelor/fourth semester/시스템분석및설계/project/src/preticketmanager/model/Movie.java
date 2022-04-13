package preticketmanager.model;

public class Movie {
	private int movieNumber; 
	private String name;
	private String genre;
	private String rating;
	private String director;
	private String mainActor;
	private String introduction;
	private int grade;
	private int runningTime;
	private String releaseDate;
	private String image;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getMainActor() {
		return mainActor;
	}
	public void setMainActor(String mainActor) {
		this.mainActor = mainActor;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public float getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getRunningTime() {
		return runningTime;
	}
	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}
	public void setMovieNumber(int movieNumber) {
		this.movieNumber = movieNumber;
	}
	public int getMovieNumber() {
		return movieNumber;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String startTime) {
		this.releaseDate = startTime;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
