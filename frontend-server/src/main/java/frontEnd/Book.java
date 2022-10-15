package frontEnd;

public class Book {
	private String title;
	private int price;
	private int availableItems;
	private String topic;
	private int itemNumber;
	
	public Book(String title, int price, int availableItems, String topic, int itemNumber) {
		
		this.title = title;
		this.price = price;
		this.availableItems = availableItems;
		this.topic = topic;
		this.itemNumber = itemNumber;
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

	public int getAvailableItems() {
		return availableItems;
	}

	public void setAvailableItems(int availableItems) {
		this.availableItems = availableItems;
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
