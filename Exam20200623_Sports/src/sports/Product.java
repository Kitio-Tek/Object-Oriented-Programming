package sports;

public class Product {

	private String name;
	private String activityName;
	private String categoryName;
	private int numStars;
	private int count;

	public Product(String name, String activityName, String categoryName) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.activityName=activityName;
		this.categoryName=categoryName;
	}

	public String getName() {
		return name;
	}

	public String getActivityName() {
		return activityName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void addNumStars(int numStars) {
		// TODO Auto-generated method stub
		this.numStars+=numStars;
		count++;
	}
	public double getAverageStars() {
		return numStars/count;
	}

}
