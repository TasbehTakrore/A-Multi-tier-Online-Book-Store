
import com.google.gson.annotations.Expose;

public class Order {
	@Expose 
	private Book orderdBook;
	@Expose 
	private int orderNumber;
	@Expose 
	private String message;
	
	public Order(Book orderdBook, int orderNumber,String message) {
	
		this.orderdBook = orderdBook;
		this.orderNumber = orderNumber;
		this.message=message;
	}
	
	public Order() {
		super();
	}
	
	@Override
	public String toString() {
		
		return "Order [orderdBook=" + orderdBook + ", orderNumber=" + orderNumber + ", message=" + message + "]";
	}


	
	static public Order fromDatabasetoOrder(String[] values) {
		Book BookValues=new Book(values[1],Integer.parseInt(values[2]),Integer.parseInt(values[3]),values[4],Integer.parseInt(values[5]),"");
		Order orderValues=new Order(BookValues,Integer.parseInt(values[0]),values[6]);
		return orderValues;
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
