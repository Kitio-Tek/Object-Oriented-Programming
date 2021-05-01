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
    private String tmp;
	/**
	 * Defines the possible order status
	 */
	private OrderStatus orderStatus=OrderStatus.ORDERED;
	private PaymentMethod paymentMethod=PaymentMethod.CASH;
	private String delivery_time;
	
	public Order(User user, Restaurant restaurant, int h, int m) {
	this.user=user;
	this.restaurant=restaurant;
	this.tmp=h+":"+m;
	restaurant.workingHours.sort(Comparator.comparing(WorkingHours::getOpen).thenComparing(WorkingHours::getClose));

	for(WorkingHours w:restaurant.workingHours) {
		if(w.includes(tmp)) {
			this.delivery_time=tmp;
			return;
		}
	}
	for(WorkingHours w:restaurant.workingHours) {
		if(w.getOpen().compareTo(tmp)>0) {
			this.delivery_time=w.getOpen();
			return;
		}
	}
	this.delivery_time=restaurant.workingHours.get(0).getOpen();
	
	
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
		menu_list.sort(Comparator.comparing(Menu_quantity::getMenu));
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
		result.append(" \""+restaurant.getName()+", "+user.getFirstName()+" "+user.getLastName()+" : "+delivery_time+" \n");
		
		for(Menu_quantity m:menu_list) {
			if(m!=null) {
				result.append("\t"+m.getMenu()+"->"+m.getQuantity()+"\n");
			}
		}
		
		return result.toString();
	}
	
}
