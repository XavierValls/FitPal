import java.time.LocalDate;

public class Review {

	private String review;
	private LocalDate fechaRev;
	public Review(String review, LocalDate fechaRev) {
		super();
		this.review = review;
		this.fechaRev = fechaRev;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public LocalDate getFechaRev() {
		return fechaRev;
	}
	public void setFechaRev(LocalDate fechaRev) {
		this.fechaRev = fechaRev;
	}
	@Override
	public String toString() {
		return "Review [review=" + review + ", fechaRev=" + fechaRev + "]";
	}
	
	
}
