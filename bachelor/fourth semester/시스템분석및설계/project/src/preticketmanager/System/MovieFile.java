package preticketmanager.System;
import preticketmanager.model.Movie;

public class MovieFile{
	ExcelFileSystem movieFile;
	Movie movie;
	
	public MovieFile(){
		movieFile = new ExcelFileSystem();
		movieFile.setWorkbook("Movies.xls");
		movieFile.setSheet(1);
	}
	public int getMovieNumber(int row) {
		return Integer.parseInt(movieFile.getCell(0, row));
	}
	public String getName(int row) {
		return movieFile.getCell(1, row);
	}
	public String getGenre(int row) {
		return movieFile.getCell(2, row);
	}
	public String getRating(int row) {
		return movieFile.getCell(6, row);
	}
	public String getDirector(int row) {
		return movieFile.getCell(4, row);
	}
	public String getMainActor(int row) {
		return movieFile.getCell(3, row);
	}
	public int getRunningTime(int row) {
		return Integer.parseInt(movieFile.getCell(5, row));
	}
	public String getReleaseDate(int row) {
		return movieFile.getCell(7, row);
	}
	public String getImage(int row) {
		return movieFile.getCell(8, row);
	}
	public String getImageRoute(int row){
		return movieFile.getRoute() + "image/";
	}
	public String getIntroductionFileRoute(int row){
		return movieFile.getRoute() + "introduction/";
	}
	public String getIntroductionFile(int row) {
		return getName(row) + ".txt";
	}
	public void setMovie(int row){
		movie = new Movie();
		movie.setMovieNumber(this.getMovieNumber(row));
		movie.setName(this.getName(row));
		movie.setGenre(this.getGenre(row));
		movie.setRating(this.getRating(row));
		movie.setDirector(this.getDirector(row));
		movie.setMainActor(this.getMainActor(row));
		movie.setRunningTime(this.getRunningTime(row));
		movie.setReleaseDate(this.getReleaseDate(row));
		movie.setImage(this.getImage(row));
		movie.setIntroduction(this.getIntroductionFile(row));
	}
	public Movie getMovie(){
		return movie;
	}
	public int getNumberOfMovie(){
		return movieFile.getSheet().getRows();
	}
	public int fineMovie(int movieNumber){
		int row;
		for(row = 1; row < getNumberOfMovie() && !(movieNumber == getMovieNumber(row)); row++)
			;
		return row;
	}
	public int findMovie(String movieName){
		int row;
		for(row = 1; row < getNumberOfMovie() && !movieName.equals(getName(row)); row++);
		return row;
		
	}
	public void close(){
		movieFile.closeFile();
	}
}
