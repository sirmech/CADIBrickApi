package hello;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @RequestMapping("/order/create")
    public String placeOrder(@RequestParam(value="amount", defaultValue="0") String amount) {
    	UUID orderRefrence = UUID.randomUUID();
    	Order newOrder = new Order(Integer.parseInt(amount),orderRefrence.toString());
    	//store order
        return orderRefrence.toString();
    }
	
}
