package hello;

import java.util.ArrayList;
import java.util.List;

public class Orders {

	private List<Order> orderList;
	private static Orders instance;
	
	public static Orders getInstance() {
		if(instance == null) {
			instance = new Orders();
			
			return instance;
		} else {
			return instance;
		}
		
	}
	
	public Orders() {
		orderList = new ArrayList<Order>();
	}
	
	public void addOrders(Order order) {
		if(orderList != null) {
			orderList.add(order);
		}
	}
	
	
	/**
	 * Get an order by order reference
	 * @param reference
	 * @return the order requested
	 */
	public Order getOrder(String reference) {
		for(Order order : orderList) {
			if(order.getOrderRefrence().equals(reference)) {
				return order;
			}
		}
		return null;
	}
}
