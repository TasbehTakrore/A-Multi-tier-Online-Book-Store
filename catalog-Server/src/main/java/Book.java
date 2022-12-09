import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Book {
	@Override
	public String toString() {
		return "Book [title=" + title + ", price=" + price + ", quantity=" + quantity + ", topic=" + topic
				+ ", itemNumber=" + itemNumber + ", message=" + message + "]";
	}

	private String title;
	private int price;
	private int quantity;
	private String topic;
	private int itemNumber;
	private String message;
	
	public Book(String title, int price, int quantity, String topic, int itemNumber,String message) {
		
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



	public String toJson() {
		Gson parseJson = new GsonBuilder().
                setPrettyPrinting().
                create(); 

return parseJson.toJson(this, getClass());
	}
	
	

}
