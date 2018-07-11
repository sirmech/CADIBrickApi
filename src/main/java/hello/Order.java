package hello;

public class Order {
	private final int orderAmount;
	private final String orderRefrence;
	
	public Order(int orderAmount, String orderRefrence) {
		this.orderAmount = orderAmount;
		this.orderRefrence = orderRefrence;
	}
	
	/**
	 * Get the order amount
	 * @return The order amount
	 */
	public int getOrderAmount() {
		return orderAmount;
	}
	
	/**
	 * Get the order reference
	 * @return The order reference
	 */
	public String getOrderRefrence() {
		return orderRefrence;
	}
	
	@Override
	public String toString() {
		return String.format("{\"orderAmount\":\"%s\",\"orderRefrence\":\"%s\"}", orderAmount,orderRefrence);
	}
}
