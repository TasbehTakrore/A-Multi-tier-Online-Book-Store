package frontEnd;

import java.util.Vector;

public interface UserService {

	public  Book[] search(String topic);
	
	public Book info(int itemNumber);
	
	public Book purchase(int itemNumber);
	
}
