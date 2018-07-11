package hello;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class OrdersTest {

	Orders orders;
	@Before
	public void beforeTest() {
		orders = new Orders();
	}
	
	//Test adding an order and getting an order work
	@Test
	public void AddOrderValidTest() {
		Order order = new Order(1,"abc");
		orders.addOrders(order);
		
		Order returnedOrder = orders.getOrder("abc");
		assertEquals(order.getOrderAmount(), returnedOrder.getOrderAmount());
	}
	
	//check that null is returned when an invalid refrence is used
	@Test
	public void getOrderInvalidTest() {
		Order order = new Order(1,"abc");
		orders.addOrders(order);
		
		Order returnedOrder = orders.getOrder("Invalid");
		assertNull(returnedOrder);
	}
	
	//check removeOrder removes the correct order
	@Test 
	public void removeOrderValidTest() {
		Order order = new Order(1,"abc");
		orders.addOrders(order);
		orders.removeOrder(order);
		
		assertNull(orders.getOrder("abc"));
	}
	
	@Test
	public void removeOrderInvalidRefrence() {
		Order order = new Order(1,"abc");
		Order orderInvalid = new Order(1,"invalid");
		orders.addOrders(order);
		
		orders.removeOrder(orderInvalid);
		assertNotNull(orders.getOrder("abc"));
	}
	
}
