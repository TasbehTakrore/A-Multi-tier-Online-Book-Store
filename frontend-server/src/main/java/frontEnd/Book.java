package frontEnd;

public class Book {
	private int itemNumber;
	private String title;
	private int quantity;
	private int price;
	private String topic;
	private String message;
	
	@Override
	public String toString() {
		return "Book [itemNumber=" + itemNumber + ", title=" + title + ", price=" + price + ", quantity=" + quantity
				+ ", topic=" + topic + ", message=" + message + "]";
	}

	public Book(int itemNumber,String title, int quantity,int price,  String topic,String message) {
		
		this.title = title;
		this.price = price;
		this.quantity = quantity;
		this.topic = topic;
		this.itemNumber = itemNumber;
		this.message=message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Book() {}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}
	
	

}
