package discounts;

public class Product {
private String categoryId,productId;
private double price;
private int percentage=0;
	public Product(String categoryId, String productId, double price) {
		// TODO Auto-generated constructor stub
		this.categoryId=categoryId;
		this.price=price;
		this.productId=productId;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public String getProductId() {
		return productId;
	}
	public double getPrice() {
		return price;
	}
	public void setPercentage(int percentage) {
		// TODO Auto-generated method stub
		this.percentage=percentage;
	}
	public int getPercentage() {
		return percentage;
	}
	public 	int getDiscountPrice() {
		return (int)price-(int)price*percentage;
	}
	

	
}
