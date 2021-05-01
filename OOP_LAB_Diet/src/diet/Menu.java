package diet;

import java.util.SortedMap;

/**
 * Represents a complete menu.
 * 
 * It can be made up of both packaged products and servings of given recipes.
 *
 */
public class Menu implements NutritionalElement {
	
	/**
	 * Adds a given serving size of a recipe.
	 * 
	 * The recipe is a name of a recipe defined in the
	 * {@Link Food} in which this menu has been defined.
	 * 
	 * @param recipe the name of the recipe to be used as ingredient
	 * @param quantity the amount in grams of the recipe to be used
	 * @return the same Menu to allow method chaining
	 * 
	 */
	private String name;
	private Food food;
	private double calories=0.0, proteins=0.0,  carbs=0.0;
	private double fat=0.0;
	
	
	
	public  Menu(String name, Food food) {
		this.name=name;
		this.food=food;
	}
	
	public Menu addRecipe(String recipe, double quantity) {
       NutritionalElement r=food.getRecipe(recipe);
		
		calories += r.getCalories()*quantity/100;
		proteins +=r.getProteins()*quantity/100; 
		carbs += r.getCarbs()*quantity/100;
		fat += r.getFat()*quantity/100;
		
		return this;
	}

	/**
	 * Adds a unit of a packaged product.
	 * The product is a name of a product defined in the
	 * {@Link Food} in which this menu has been defined.
	 * 
	 * @param product the name of the product to be used as ingredient
	 * @return the same Menu to allow method chaining
	 */
	public Menu addProduct(String product) {
   NutritionalElement p=food.getProduct(product);
		
		calories += p.getCalories();
		proteins +=p.getProteins(); 
		carbs += p.getCarbs();
		fat += p.getFat();
		
		return this;

	}

	/**
	 * Name of the menu
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Total KCal in the menu
	 */
	@Override
	public double getCalories() {
		return calories;
	}

	/**
	 * Total proteins in the menu
	 */
	@Override
	public double getProteins() {
		return proteins;
	}

	/**
	 * Total carbs in the menu
	 */
	@Override
	public double getCarbs() {
		return carbs;
	}

	/**
	 * Total fats in the menu
	 */
	@Override
	public double getFat() {
		return 	fat;
	}

	/**
	 * Indicates whether the nutritional values returned by the other methods
	 * refer to a conventional 100g quantity of nutritional element,
	 * or to a unit of element.
	 * 
	 * For the {@link Menu} class it must always return {@code false}:
	 * nutritional values are provided for the whole menu.
	 * 
	 * @return boolean 	indicator
	 */
	@Override
	public boolean per100g() {
		// nutritional values are provided for the whole menu.
		return false;
	}
}
