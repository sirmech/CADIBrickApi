package hello;

import java.util.ArrayList;
import java.util.List;

public class Orders {

	private List<Order> orderList;
	private static Orders instance;
	
	/**
	 * Get instance of orders.
	 * If orders doesn't exist a new instance will be created and returned
	 * @return The orders
	 */
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
	
	/**
	 * Add an order to orders
	 * @param order The order to be added
	 */
	public void addOrders(Order order) {
		if(orderList != null) {
			orderList.add(order);
		}
	}
	
	/**
	 * Remove an order from orders
	 * @param order The order to be removed
	 */
	public void removeOrder(Order order) {
		orderList.remove(order);
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
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		for(Order order : orderList) {
				builder.append(order.toString());
				builder.append(",");
		}
		builder.append("{}]");
		return builder.toString();
	}
}
