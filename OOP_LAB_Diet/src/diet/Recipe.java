package diet;

import java.util.SortedMap;

/**
 * Represents a recipe of the diet.
 * 
 * A recipe consists of a a set of ingredients that are given amounts of raw materials.
 * The overall nutritional values of a recipe can be computed
 * on the basis of the ingredients' values and are expressed per 100g
 * 
 *
 */
public class Recipe implements NutritionalElement {
    private String name;
    private SortedMap<String,NutritionalElement> map;
    private double calories=0.0, proteins=0.0,  carbs=0.0;
	private double fat=0.0;
	private double Total_quantity=0.0;
	private StringBuffer layout=new StringBuffer();

	/**
	 * Adds a given quantity of an ingredient to the recipe.
	 * The ingredient is a raw material.
	 * 
	 * @param material the name of the raw material to be used as ingredient
	 * @param quantity the amount in grams of the raw material to be used
	 * @return the same Recipe object, it allows method chaining.
	 */
	public Recipe(String name, SortedMap<String,NutritionalElement> map)
	{ this.name=name;
	  this.map=map;
		
	}
	public Recipe addIngredient(String material, double quantity) {
		NutritionalElement rawMaterial=map.get(material);
		
		calories += rawMaterial.getCalories()*quantity/100;
		proteins +=rawMaterial.getProteins()*quantity/100; 
		carbs += rawMaterial.getCarbs()*quantity/100;
		fat += rawMaterial.getFat()*quantity/100;
		Total_quantity+=quantity;
		
		layout.append(String.format(" %s: %.1f\n", material, quantity));
		
		return this;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getCalories() {
		return calories*100/Total_quantity;
	}

	@Override
	public double getProteins() {
		return proteins*100/Total_quantity;
	}

	@Override
	public double getCarbs() {
		return carbs*100/Total_quantity;
	}

	@Override
	public double getFat() {
		return fat*100/Total_quantity;

	}

	/**
	 * Indicates whether the nutritional values returned by the other methods
	 * refer to a conventional 100g quantity of nutritional element,
	 * or to a unit of element.
	 * 
	 * For the {@link Recipe} class it must always return {@code true}:
	 * a recipe expresses nutritional values per 100g
	 * 
	 * @return boolean indicator
	 */
	@Override
	public boolean per100g() {
		return true;
	}
	
	
	/**
	 * Returns the ingredients composing the recipe.
	 * 
	 * A string that contains all the ingredients, one per per line, 
	 * using the following format:
	 * {@code "Material : ###.#"} where <i>Material</i> is the name of the 
	 * raw material and <i>###.#</i> is the relative quantity. 
	 * 
	 * Lines are all terminated with character {@code '\n'} and the ingredients 
	 * must appear in the same order they have been added to the recipe.
	 */
	@Override
	public String toString() {
		return layout.toString();
	}
}
