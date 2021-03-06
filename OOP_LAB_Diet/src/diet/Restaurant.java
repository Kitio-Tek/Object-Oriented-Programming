package diet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import diet.Order.OrderStatus;

/**
 * Represents a restaurant in the take-away system
 *
 */
public class Restaurant implements Comparable<Restaurant> {
	private String name;
	private Food food;
	private SortedMap<String,Menu> menu=new TreeMap<>();
	public List<WorkingHours> workingHours=new ArrayList<>();
	public List<Order> order=new ArrayList<>();
	/**
	 * Constructor for a new restaurant.
	 * 
	 * Materials and recipes are taken from
	 * the food object provided as argument.
	 * 
	 * @param name	unique name for the restaurant
	 * @param food	reference food object
	 */
	public Restaurant(String name, Food food) {
		// TODO: implement constructor
		this.name=name;
		this.food=food;

	}
	
	/**
	 * gets the name of the restaurant
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Define opening hours.
	 * 
	 * The opening hours are considered in pairs.
	 * Each pair has the initial time and the final time
	 * of opening intervals.
	 * 
	 * for a restaurant opened from 8:15 until 14:00 and from 19:00 until 00:00, 
	 * is thoud be called as {@code setHours("08:15", "14:00", "19:00", "00:00")}.
	 * 
	 * @param hm a list of opening hours
	 */
	public void setHours(String ... hm) {
		workingHours.clear();
		
		for(int i=0;i<hm.length/2;i=+2)
			{workingHours.add(new WorkingHours(hm[i],hm[i+1]));
			 workingHours.add(new WorkingHours(hm[i+2],hm[i+3]));
			}
			

	}
	
	
	public Menu getMenu(String name) {
		return menu.get(name);
	}
	
	/**
	 * Creates a new menu
	 * 
	 * @param name name of the menu
	 * 
	 * @return the newly created menu
	 */
	public Menu createMenu(String name) {
        Menu m=new Menu(name,food);
		
		menu.put(name, m);
		
		return m;
	}

	
	public List<WorkingHours> getHours() {
		return workingHours;
	}

	/**
	 * Find all orders for this restaurant with 
	 * the given status.
	 * 
	 * The output is a string formatted as:
	 * <pre>
	 * Napoli, Judi Dench : (19:00):
	 * 	M6->1
	 * Napoli, Ralph Fiennes : (19:00):
	 * 	M1->2
	 * 	M6->1
	 * </pre>
	 * 
	 * The orders are sorted by name of restaurant, name of the user, and delivery time.
	 * 
	 * @param status the status of the searched orders
	 * 
	 * @return the description of orders satisfying the criterion
	 */
	public String ordersWithStatus(OrderStatus status) {
      StringBuffer b=new StringBuffer();
      
      order.sort(Comparator.comparing((Order o)->o.getRestaurant().getName())
    		     .thenComparing((Order o)->o.getUser().getLastName())
    		     .thenComparing((Order o)->o.getUser().getFirstName())
    		     .thenComparing(Order::getDelivery_time));
      for(Order o:order) {
    	  if(o.getStatus()==status)
    		  b.append(o);
      }
 return b.toString();
	}

	public void addOrder(Order o) {
		// TODO Auto-generated method stub
		order.add(o);
	}

	public Time checkTime(Time time) {
		
		
		for(WorkingHours w:workingHours)
		{ if(w.includes(time))
			return time;
			}
		for(WorkingHours w:workingHours)
		{ if(w.getOpen().compareTo(time)>0)
			return w.getOpen();
			}
		
		
		return workingHours.get(0).getOpen();
	}

	@Override
	public int compareTo(Restaurant o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
