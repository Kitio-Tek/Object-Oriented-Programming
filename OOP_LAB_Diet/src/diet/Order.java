package diet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents an order in the take-away system
 */
public class Order {
    private User user;
    private Restaurant restaurant;
    private List<Menu_quantity> menu_list=new ArrayList<>();
    private StringBuffer tmp=new StringBuffer();
	/**
	 * Defines the possible order status
	 */
	private OrderStatus orderStatus;
	private PaymentMethod paymentMethod;
	private Time delivery_time;
	
	public Order(User user, Restaurant restaurant, int h, int m) {
	this.user=user;
	this.restaurant=restaurant;
	orderStatus=OrderStatus.ORDERED;
	paymentMethod=PaymentMethod.CASH;
    this.delivery_time=restaurant.checkTime(new Time(h,m));
	
	
	}
	public Time getDelivery_time() {
		return delivery_time;
	}
	public User getUser() {
		return user;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public enum OrderStatus {
		ORDERED, READY, DELIVERED;
	}
	/**
	 * Defines the possible valid payment methods
	 */
	public enum PaymentMethod {
		PAID, CASH, CARD;
	}
		
	/**
	 * Total order price
	 * @return order price
	 */
	public double Price() {
		return -1.0;
	}
	
	/**
	 * define payment method
	 * 
	 * @param method payment method
	 */
	public void setPaymentMethod(PaymentMethod method) {
		paymentMethod=method;
	}
	
	/**
	 * get payment method
	 * 
	 * @return payment method
	 */
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	
	/**
	 * change order status
	 * @param newStatus order status
	 */
	public void setStatus(OrderStatus newStatus) {
		orderStatus=newStatus;
	}
	
	/**
	 * get current order status
	 * @return order status
	 */
	public OrderStatus getStatus(){
		return orderStatus;
	}
	
	/**
	 * Add a new menu with the relative order to the order.
	 * The menu must be defined in the {@link Food} object
	 * associated the restaurant that created the order.
	 * 
	 * @param menu     name of the menu
	 * @param quantity quantity of the menu
	 * @return this order to enable method chaining
	 */
	public Order addMenus(String menu, int quantity) {
		
		
		for(Menu_quantity i:menu_list)
		{
			if(i!=null && i.getMenu().equals(menu))
		{        i.setQuantity(quantity);
				 return this;
				}
		}
		menu_list.add(new Menu_quantity(menu,quantity));
		
			return this;
	}
	
	/**
	 * Converts to a string as:
	 * <pre>
	 * RESTAURANT_NAME, USER_FIRST_NAME USER_LAST_NAME : DELIVERY(HH:MM):
	 * 	MENU_NAME_1->MENU_QUANTITY_1
	 * 	...
	 * 	MENU_NAME_k->MENU_QUANTITY_k
	 * </pre>
	 */
	@Override
	public String toString() {
		StringBuffer result=new StringBuffer();
		menu_list.sort(Comparator.comparing(Menu_quantity::getMenu));
		result.append(restaurant.getName()+", "+user.getFirstName()+" "+user.getLastName()+" : ("+delivery_time+"):\n");
		
		for(Menu_quantity m:menu_list) {
			if(m!=null) {
				result.append("\t"+m.getMenu()+"->"+m.getQuantity()+"\n");
			}
		}
		
		return result.toString();
	}
	
}
