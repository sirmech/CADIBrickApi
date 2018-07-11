package hello;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

	public void main(String[] args) {
		
	}
	
    @RequestMapping("/order/create")
    public String placeOrder(@RequestParam(value="amount", defaultValue="0") String amount) {
    	//Generate unique order reference
    	UUID orderRefrence = UUID.randomUUID(); 
    	//Create a new order the amount received
    	try {
    		Order newOrder = new Order(Integer.parseInt(amount),orderRefrence.toString());
    		//store the new order in the orders list
    		Orders orders = Orders.getInstance();
    		orders.addOrders(newOrder);
    		System.out.println("Order added:" + amount);
    		//return order reference
    		return orderRefrence.toString();
    	} //If amount given is not a valid number
    	catch (NumberFormatException e) {
    		return "";
    	}
    }
	
    @RequestMapping("/order/get")
    public Order getOrder(@RequestParam(value="reference", defaultValue="0") String reference) {
    	//get instance of orders
    	Orders orders = Orders.getInstance();
    	//search orders for the requested order
    	Order requestedOrder = orders.getOrder(reference);
    	//if the order exists send it
    	if(requestedOrder != null) {
    		return requestedOrder;
    	}
    	else { //else return nothing
    		return null;
    	}
    }
    
    @RequestMapping("/orders/get")
    public String getOrders() {
    	//get instance of orders
    	Orders orders = Orders.getInstance();
    	//return orders in JSON form
    	return orders.toString();
    }
    
    @RequestMapping("order/update") 
    public String updateOrder(@RequestParam(value="reference", defaultValue="") String reference, @RequestParam(value="amount", defaultValue="") String amount) {
    	//get instance of orders
    	Orders orders = Orders.getInstance();
    	//get order to update
    	Order orderToUpdate = orders.getOrder(reference);
    	if(orderToUpdate != null) { //If the order exists
    		try {
    			int intOrderAmount = Integer.parseInt(amount);
    			//create new order to replace the old order
    			UUID newOrderRefrence = UUID.randomUUID();
    			Order newOrder = new Order(intOrderAmount,newOrderRefrence.toString());
    			//remove the old order
    			orders.removeOrder(orderToUpdate);
    			//add new updated order
    			orders.addOrders(newOrder);
    			System.out.println(newOrder.toString());
    			return newOrderRefrence.toString();
    		} 
    		catch (NumberFormatException e) { //If amount is an invalid integer
    			return null;
    		}
    	
    	}//if order doesn't exist
    	else {
    		return null;
    	}
    }
    
}
