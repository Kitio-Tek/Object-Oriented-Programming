package diet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * Represents the main class in the
 * take-away system.
 * 
 * It allows adding restaurant, users, and creating orders.
 *
 */
public class Takeaway {
   private List<Restaurant> restaurant=new ArrayList<>();
   private List<String> restaurantName=new ArrayList<>();
   private List<User> listUser=new ArrayList<>();
	/**
	 * Adds a new restaurant to the take-away system
	 * 
	 * @param r the restaurant to be added
	 */
	public void addRestaurant(Restaurant r) {
		restaurant.add(r);
		
		
	}
	
	/**
	 * Returns the collections of restaurants
	 * 
	 * @return collection of added restaurants
	 */
	public Collection<String> restaurants() {
		for(Restaurant r:restaurant)
		{ if(r!=null)
		  restaurantName.add(r.getName());
			} 
		return restaurantName;
	}
	
	/**
	 * Define a new user
	 * 
	 * @param firstName first name of the user
	 * @param lastName  last name of the user
	 * @param email     email
	 * @param phoneNumber telephone number
	 * @return
	 */
	public User registerUser(String firstName, String lastName, String email, String phoneNumber) {
		User u=new User(firstName,lastName);
		u.SetEmail(email);
		u.setPhone(phoneNumber);
		
		listUser.add(u);
		return u;
		
	}
	
	/**
	 * Gets the collection of registered users
	 * 
	 * @return the collection of users
	 */
	public Collection<User> users(){
		listUser.sort(Comparator.comparing(User::getFirstName).thenComparing(User::getLastName));
		return listUser;
	}
	
	/**
	 * Create a new order by a user to a given restaurant.
	 * 
	 * The order is initially empty and is characterized
	 * by a desired delivery time. 
	 * 
	 * @param user				user object
	 * @param restaurantName	restaurant name
	 * @param h					delivery time hour
	 * @param m					delivery time minutes
	 * @return
	 */
	public Order createOrder(User user, String restaurantName, int h, int m) {
		
		for(Restaurant r:restaurant)
		{ if(r!=null && r.getName().equals(restaurantName))
		{  Order o=new Order(user,r,h,m);
		    return o;
			}} 
		
		
		
		return null;
	}
	
	/**
	 * Retrieves the collection of restaurant that are open
	 * at the given time.
	 * 
	 * @param time time to check open
	 * 
	 * @return collection of restaurants
	 */
	public Collection<Restaurant> openedRestaurants(String time){
		return null;
	}

	
	
}
