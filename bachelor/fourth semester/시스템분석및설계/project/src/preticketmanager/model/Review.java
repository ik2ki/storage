package preticketmanager.model;

public class Review {
	private String ID;
	private String Review;
	
	public Review(){ 
		ID = Review = null;
	}
	public Review(String ID, String review){
		this.ID = ID;
		this.Review = review;
	}
	public String getID() {
		return ID;
	}
	public void setID(String ID) {
		this.ID = ID;
	}
	public String getReview() {
		return Review;
	}
	public void setReview(String review) {
		this.Review = review;
	}
}
