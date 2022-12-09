package order;

public interface UserServices {

	public Order purchase(int itemNumber);
	public Order[] getAllOrders();
}
