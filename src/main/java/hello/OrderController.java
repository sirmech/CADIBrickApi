package hello;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @RequestMapping("/order/create")
    public String placeOrder(@RequestParam(value="amount", defaultValue="0") String amount) {
    	//Generate unique order reference
    	UUID orderRefrence = UUID.randomUUID(); 
    	//Create a new order the amount received
    	Order newOrder = new Order(Integer.parseInt(amount),orderRefrence.toString());
    	//store the new order in the orders list
    	Orders orders = Orders.getInstance();
    	orders.addOrders(newOrder);
    	System.out.println("Order added:" + amount);
    	//return order reference
        return orderRefrence.toString();
    }
	
}
