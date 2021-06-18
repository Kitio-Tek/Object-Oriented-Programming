package sports;

public class Rating {

	private Product product;
	private String userName;
	private int numStars;
	private String comment;

	public Rating(Product product, String userName, int numStars, String comment) {
		// TODO Auto-generated constructor stub
		this.product=product;
		this.userName=userName;
		this.numStars=numStars;
		this.comment=comment;
	}

	public Product getProduct() {
		return product;
	}

	public String getUserName() {
		return userName;
	}

	public int getNumStars() {
		return numStars;
	}

	public String getComment() {
		return comment;
	}

}
