import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


//import spark.ResponseTransformer;

public class jsonTransformer {

	Gson gson = new Gson();
    String json ="";

    public static String convertObjToGson(Book []  book) {
    	
    	try {
		 Gson parseJson = new GsonBuilder().
                 setPrettyPrinting().
                 create(); 

		return parseJson.toJson(book, Book[].class);
    	}
    	catch(Exception e) {
    	      System.out.println("--"+e);  
    	      return "";

    	}

        }
    
    public static String convertObjToGson(Book  book) {
    	

      	try {
  		 Gson parseJson = new GsonBuilder().
                   setPrettyPrinting().
                   create(); 

  		return parseJson.toJson(book, Book.class);
  		
      	}
      	catch(Exception e) {
      	      System.out.println("--"+e);  
      	      return "";

      	}

          }
    
    public static Book convertGsonToObj(String  body) {
      	try {
		Gson parseJson = new GsonBuilder().
                setPrettyPrinting().
                create(); 

        return  parseJson.fromJson(body, Book.class) ;
      	}
      	catch(Exception e) {
      	      System.out.println("--"+e);  
      	      return null;

      	}
    }
    

    public static String convertObjToGson(Book  book, int f) {
    	

      	try {
      		 Gson parseJson = new GsonBuilder().
      	             setPrettyPrinting().
      	             setExclusionStrategies(new ResponseExclusionStrategy()).
      	             create(); 
      		return parseJson.toJson(book, Book.class);
  		
      	}
      	catch(Exception e) {
      	      System.out.println("--"+e);  
      	      return "";

      	}

          }
    

    
}