package sports;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;
import java.util.List;
import java.util.Map;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import java.util.Comparator;
import java.util.OptionalDouble;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import java.util.List;
import java.util.Map;


 
/**
 * Facade class for the research evaluation system
 *
 */
public class Sports {
  List<String> activities=new ArrayList<>();
  HashMap<String,Category> category=new HashMap();
  HashMap<String,Product> product=new HashMap();
  HashMap<Integer,Rating> rating=new HashMap();
    //R1
    /**
     * Define the activities types treated in the portal.
     * The method can be invoked multiple times to add different activities.
     * 
     * @param actvities names of the activities
     * @throws SportsException thrown if no activity is provided
     */
    public void defineActivities (String... activities) throws SportsException {
     if(activities.length==0)
    	 throw new SportsException("No Activities");
     
     for(String s:activities) {
    	  this.activities.add(s);
      }
     
    
    }

    /**
     * Retrieves the names of the defined activities.
     * 
     * @return activities names sorted alphabetically
     */
    public List<String> getActivities() {
        Collections.sort(activities);
        
        return activities;
    }


    /**
     * Add a new category of sport products and the linked activities
     * 
     * @param name name of the new category
     * @param activities reference activities for the category
     * @throws SportsException thrown if any of the specified activity does not exist
     */
    public void addCategory(String name, String... linkedActivities) throws SportsException {
     
    	for(String s:linkedActivities) {
      	  if(!activities.contains(s))
      		  throw new SportsException("Activities not previously added");
        }
    	
    	Category c=new Category(name,Arrays.asList(linkedActivities));
    	
    	category.put(name, c);

    	
    
    }

    /**
     * Retrieves number of categories.
     * 
     * @return categories count
     */
    public int countCategories() {
        return category.size();
    }

    /**
     * Retrieves all the categories linked to a given activity.
     * 
     * @param activity the activity of interest
     * @return list of categories (sorted alphabetically)
     */
    public List<String> getCategoriesForActivity(String activity) {
        return category.values().stream()
        		.filter((Category e)->e.getActivity().contains(activity))
        		.sorted(comparing(e->e.getName()))
        		.map(Category::getName)
        		.collect(toList());
    }

    //R2
    /**
     * Add a research group and the relative disciplines.
     * 
     * @param name name of the research group
     * @param disciplines list of disciplines
     * @throws SportsException thrown in case of duplicate name
     */
    public void addProduct(String name, String activityName, String categoryName) throws SportsException {
    
    	if(product.containsKey(name))
    		throw new SportsException("Product with same name");
    	
    	Product p=new Product(name,activityName,categoryName);
    	product.put(name, p);
    	
    	
    	
    	
    }

    /**
     * Retrieves the list of products for a given category.
     * The list is sorted alphabetically.
     * 
     * @param categoryName name of the category
     * @return list of products
     */
    public List<String> getProductsForCategory(String categoryName){
        return product.values().stream()
        		.filter((Product p)->p.getCategoryName().equals(categoryName))
        		.sorted(comparing((Product p)->p.getName()))
        		.map(Product::getName)
        		.collect(toList());
    }

    /**
     * Retrieves the list of products for a given activity.
     * The list is sorted alphabetically.
     * 
     * @param activityName name of the activity
     * @return list of products
     */
    public List<String> getProductsForActivity(String activityName){
    	return product.values().stream()
        		.filter((Product p)->p.getActivityName().equals(activityName))
        		.sorted(comparing((Product p)->p.getName()))
        		.map(Product::getName)
        		.collect(toList());    }

    /**
     * Retrieves the list of products for a given activity and a set of categories
     * The list is sorted alphabetically.
     * 
     * @param activityName name of the activity
     * @param categoryNames names of the categories
     * @return list of products
     */
    public List<String> getProducts(String activityName, String... categoryNames){
      List<String> list=new ArrayList<>();
      
      HashSet<String> set=new HashSet<>();
    	
    	for(String e:categoryNames)
    {  list=List.copyOf(product.values().stream()
		
    		.filter((Product p)->p. getActivityName().equals(activityName))
		    .filter((Product p)->p.getCategoryName().equals(e))
		    .sorted(comparing((Product p)->p.getName()))
		    .map(Product::getName)
		    .collect(toList())); 
       
        set.addAll(list);
    	
    	}   
    
    	
    	
      return List.copyOf(set);
    }

    //    //R3
    /**
     * Add a new product rating
     * 
     * @param productName name of the product
     * @param userName name of the user submitting the rating
     * @param numStars score of the rating in stars
     * @param comment comment for the rating
     * @throws SportsException thrown numStars is not correct
     */
    public void addRating(String productName, String userName, int numStars, String comment) throws SportsException {
     if(!(numStars>=0) || !(numStars<=5))
    	 throw new SportsException("Invalid numStars");
     
        Rating r=new Rating(product.get(productName),userName, numStars,comment,product.get(productName).getActivityName());
     
      rating.put(rating.size(),r);
    
    }



    /**
     * Retrieves the ratings for the given product.
     * The ratings are sorted by descending number of stars.
     * 
     * @param productName name of the product
     * @return list of ratings sorted by stars
     */
    public List<String> getRatingsForProduct(String productName) {
        return rating.values().stream()
        		.filter(e->e.getProduct().getName().equals(productName))
        		.sorted(comparing((Rating e)->e.getNumStars()).reversed())
        		.map((Rating e)->e.getNumStars()+" : ["+e.getComment()+"]")
        		.collect(toList());
    }


    //R4
    /**
     * Returns the average number of stars of the rating for the given product.
     * 
     * 
     * @param productName name of the product
     * @return average rating
     */
    public double getStarsOfProduct (String productName) {
        return rating.values().stream()
        		.filter(e->e.getProduct().getName().equals(productName))
        		.collect(averagingDouble(e->e.getNumStars()));
    }

    /**
     * Computes the overall average stars of all ratings
     *  
     * @return average stars
     */
    public double averageStars() {
        return rating.values().stream()
        		.collect(averagingDouble(e->e.getNumStars()));

    }

    //R5 Statistiche
    /**
     * For each activity return the average stars of the entered ratings.
     * 
     * Activity names are sorted alphabetically.
     * 
     * @return the map associating activity name to average stars
     */
    public SortedMap<String, Double> starsPerActivity() {
       return rating.values().stream()
        .filter(e->e.HasStars())
        .sorted(comparing(e->e.getActivity()))
        .collect(groupingBy( (Rating p)->p.getActivity(),
        		              TreeMap::new,
        		              averagingDouble(Rating::getNumStars)
        		              
        		  
        		));
    	
    
    }

    /**
     * For each average star rating returns a list of
     * the products that have such score.
     * 
     * Ratings are sorted in descending order.
     * 
     * @return the map linking the average stars to the list of products
     */
    public SortedMap<Double, List<String>> getProductsPerStars () {
            
    	     return     rating.values().stream()
    		            .filter(e->e.HasStars())
    		            .sorted(comparing(p->p.getProduct().getName()))
    		            .collect(groupingBy(p->p.getProduct().getAverageStars(),
    		        		 ()->new TreeMap<Double,List<String>>(reverseOrder()),
    		        		mapping(p->p.getProduct().getName(), toList())
    		        		 
    		        		
    		        		
    		        		));
    }

}