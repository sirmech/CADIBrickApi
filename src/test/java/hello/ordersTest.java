package hello;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import hello.OrderController;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ordersTest {

	//controller to be tested
	@Autowired
    private OrderController controller;
	
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int orderPort;
	
	@Test
	public void orderContextLoadsTest() {
		assertNotNull(controller);
	}
	
	@Test
	public void placeOrderValidTest() {
		//send a test request
		String response = restTemplate.getForObject("http://localhost:" + orderPort + "/order/create?amount=2", String.class);
		//check the response is a valid UUID
		Pattern p = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}");
		Matcher m = p.matcher(response);
		assertTrue(m.matches());
	}
	
	@Test
	public void placeOrderInvalidTest() {
		String response = restTemplate.getForObject("http://localhost:" + orderPort + "/order/create?amount=abc", String.class);
		assertNull(response);
	}
	
	@Test
	public void getOrderValidTest() {
		//create an order to be gotten
		String createOrderResponse = restTemplate.getForObject("http://localhost:" + orderPort + "/order/create?amount=4", String.class);
		String getOrderResponse = restTemplate.getForObject("http://localhost:" + orderPort + "/order/get?reference="+createOrderResponse, String.class);
		System.out.println(getOrderResponse);
		//check the order was stored
		Pattern p = Pattern.compile("\"orderAmount\":4");
		Matcher m = p.matcher(getOrderResponse);
		assertTrue(m.find());
	}
	
	
	//test that nothing is returned if an invalid reference is received
	@Test
	public void getOrderInvalidRefrence() {
		String getOrderResponse = restTemplate.getForObject("http://localhost:" + orderPort + "/order/get?reference=abc-invalid", String.class);
		assertNull(getOrderResponse);
	}
	
	//Test get orders end point
	@Test
	public void getOrdersValidTest() {
		//Enter two orders
		String referenceOne = restTemplate.getForObject("http://localhost:" + orderPort + "/order/create?amount=15", String.class);
		String referenceTwo = restTemplate.getForObject("http://localhost:" + orderPort + "/order/create?amount=12", String.class);
		//get all the orders
		String getOrdersResponse = restTemplate.getForObject("http://localhost:" + orderPort + "/orders/get", String.class);
		//test if the orders got returned
		Pattern p = Pattern.compile(referenceOne);
		Matcher m = p.matcher(getOrdersResponse);
		assertTrue(m.find());
		
		 p = Pattern.compile(referenceTwo);
		 m = p.matcher(getOrdersResponse);
		 assertTrue(m.find());
	}
	
	@Test
	public void updateOrderValidTest() {
		//create an order to be updated
		String createOrderResponse = restTemplate.getForObject("http://localhost:" + orderPort + "/order/create?amount=5", String.class);
		//update the value
		String updatedRefrence = restTemplate.getForObject("http://localhost:" + orderPort + "/order/update?reference=" 
																	+ createOrderResponse + "&amount=" + 50, String.class);
		String requestedOrder = restTemplate.getForObject("http://localhost:" + orderPort + "/order/get?reference=" + updatedRefrence, String.class);
		//check the amount has updated
		Pattern p = Pattern.compile("\"orderAmount\":50");
		Matcher m = p.matcher(requestedOrder);
		assertTrue(m.find());
		
		p = Pattern.compile(createOrderResponse);
		m = p.matcher(requestedOrder);
		assertFalse(m.find());
	}
	
	@Test
	public void updateOrderInvalidRefrence() {
		//Try to update an invalid order
		String updatedRefrence = restTemplate.getForObject("http://localhost:" + orderPort + "/order/update?reference=abcInvalid&amount=" + 50, String.class);
		assertNull(updatedRefrence);
	}
}
