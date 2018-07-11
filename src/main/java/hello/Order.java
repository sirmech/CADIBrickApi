package hello;

public class Order {
	private final int orderAmount;
	private final String orderRefrence;
	
	public Order(int orderAmount, String orderRefrence) {
		this.orderAmount = orderAmount;
		this.orderRefrence = orderRefrence;
	}
	
	public int getOrderAmount() {
		return orderAmount;
	}
	
	public String getOrderRefrence() {
		return orderRefrence;
	}
	@Override
	public String toString() {
		return String.format("{\"orderAmount\":\"%s\",\"orderRefrence\":\"%s\"}", orderAmount,orderRefrence);
	}
}
