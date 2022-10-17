package order;

public class Order {

	@Override
	public String toString() {
		return "Order [orderdBook=" + orderdBook + ", orderNumber=" + orderNumber + ", message=" + message + "]";
	}

	private Book orderdBook;
	private int orderNumber;
	private String message;
	
	public Order(Book orderdBook, int orderNumber,String message) {
	
		this.orderdBook = orderdBook;
		this.orderNumber = orderNumber;
		this.message=message;
	}
	
	public Order() {
		super();
	}
	
	public Book getOrderdBook() {
		return orderdBook;
	}
	
	public void setOrderdBook(Book orderdBook) {
		this.orderdBook = orderdBook;
	}
	
	public int getOrderNumber() {
		return orderNumber;
	}
	
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean addOrderToDataBase() {
		boolean success=true;
		
		return success;
	}
}
