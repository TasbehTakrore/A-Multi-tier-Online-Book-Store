package frontEnd;



public interface UserService {

	public  String search(String topic);
	
	public String info(int itemNumber);
	
	public void purchase(int itemNumber);
	
}
