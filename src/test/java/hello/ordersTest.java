package hello;

import static org.junit.Assert.assertEquals;
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
}
