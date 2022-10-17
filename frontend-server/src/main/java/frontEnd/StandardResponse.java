package frontEnd;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class StandardResponse {

	private String message;

	public StandardResponse(String message) {
		this.message = message;
	}
	
	public String MessageResponse() {
		
		 Gson parseJson = new GsonBuilder().
                 setPrettyPrinting().
                 create(); 
		 
		 return parseJson.toJson(this, getClass());
	}
	
	
	public static String BookSearchResponse(Book[] outputBooks) {
	if(outputBooks==null) {
			
			return new StandardResponse("This topic does not exist!").MessageResponse();
			
		}
		 Gson parseJson = new GsonBuilder().
                 setPrettyPrinting().
                 setExclusionStrategies(new BookSearchExclusionStrategy()).
                 create(); 
		return parseJson.toJson(outputBooks, Book[].class);
	}
	
	public static String BookInfoResponse(Book outputBook) {
		if(outputBook.getTitle()==null) {
			
			return new StandardResponse("This item does not exist!").MessageResponse();
			
		}
		 Gson parseJson = new GsonBuilder().
                setPrettyPrinting().
                setExclusionStrategies(new BookInfoExclusionStrategy()).
                create(); 
		return parseJson.toJson(outputBook, Book.class);
	}
}
