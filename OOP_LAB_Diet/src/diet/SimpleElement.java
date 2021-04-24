package diet;

public class SimpleElement implements NutritionalElement{
	String name;
	private double calories, proteins,  carbs;
	private double fat;
	private boolean per100g;
 


public SimpleElement(String name, double calories, double proteins, double carbs, double fat, boolean per100g) {
	
	this.name = name;
	this.calories = calories;
	this.proteins = proteins;
	this.carbs = carbs;
	this.fat = fat;
	this.per100g = per100g;
}



public String getName() {
	return name;
}



public double getCalories() {
	return calories;
}



public double getCarbs() {
	return carbs;
}



public double getFat() {
	return fat;
}



public boolean per100g() {
	return per100g;
}



public double getProteins() {
	return proteins;
}

	
}
