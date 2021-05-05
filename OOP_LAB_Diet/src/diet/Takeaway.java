package diet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Represents the main class in the
 * take-away system.
 * 
 * It allows adding restaurant, users, and creating orders.
 *
 */
public class Takeaway {
   private Map<String,Restaurant> restaurant=new TreeMap<>();
   private List<User> listUser=new ArrayList<>();
   private Time open;
	/**
	 * Adds a new restaurant to the take-away system
	 * 
	 * @param r the restaurant to be added
	 */
	public void addRestaurant(Restaurant r) {
		restaurant.put(r.getName(), r);
		
		
	}
	
	/**
	 * Returns the collections of restaurants
	 * 
	 * @return collection of added restaurants
	 */
	public Collection<String> restaurants() {
		
		return new LinkedList<>(restaurant.keySet());
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
		listUser.sort(Comparator.comparing(User::getLastName).thenComparing(User::getFirstName));
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
		Restaurant restaurants=restaurant.get(restaurantName);
		
	    Order o=new Order(user,restaurants,h,m);
	    restaurants .addOrder(o);
		   
		    return o;
		 
	}
	
	/**
	 * Retrieves the collection of restaurant that are open
	 * at the given time.
	 * 
	 * @param time time to check open
	 * 
	 * @return collection of restaurants
	 */
	public Collection<Restaurant> openedRestaurants(String time) {
		List<Restaurant> opened_r=new ArrayList<>();
		String [] open_h_m=time.split(":");
		
		this.open = new Time(Integer.parseInt(open_h_m[0]),Integer.parseInt(open_h_m[1]));

        for(Restaurant r:restaurant.values()) {
        	if(r!=null && r.checkTime(open)==open)
        		opened_r.add(r);
        	}
     
		//Collections.sort(opened_r);		
		return opened_r;
		}

	
	
} 
