package frontEnd;

import java.util.Vector;

public interface UserService {

	public  Vector<Book> search(String topic);
	
	public Book info(int itemNumber);
	
	public void purchase(int itemNumber);
	
}
