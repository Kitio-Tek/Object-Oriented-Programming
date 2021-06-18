package sports;

import java.util.List;

public class Rating {

	private Product product;
	private String userName;
	private int numStars;
	private String comment;
	private String activity;
	

	public Rating(Product product, String userName, int numStars, String comment, String activityName) {
		// TODO Auto-generated constructor stub
		this.product=product;
		this.userName=userName;
		this.numStars=numStars;
		this.comment=comment;
		this.activity=activityName;
		this.product.addNumStars(numStars);
	}

	public String getActivity() {
		return activity;
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
	public boolean HasStars() {
		if(numStars==0)
			return false;
		return true;
	}

	public String getComment() {
		return comment;
	}

}
