package frontEnd;
import static spark.Spark.*;

import java.io.StringReader;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class frontEndServer {

	public static Service handleRequest=new Service();
	public static String CATALOG_IP_ADDRESS=System.getenv("CATALOG_IP_ADDRESS");
	public static String CATALOG_PORT="4000";
	public static String ORDER_IP_ADDRESS=System.getenv("ORDER_IP_ADDRESS");
	public static String ORDER_PORT="4000";
	
	
	public static void main(String[] args) {
		port(4000);
		
		get("search/:topic", (req,res)->{
			 res.type("application/json");
		 
			 Book[] outputBooks=handleRequest.search(req.params(":topic"));
			
			return StandardResponse.BookSearchResponse(outputBooks);
			
           
        });
		
		get("info/:itemNumber", (req,res)->{
			 res.type("application/json");
			 int itemNumber;
			 try {
			 itemNumber=Integer.parseInt(req.params(":itemNumber"));}
			 catch(NumberFormatException e) {
				 
				 return new StandardResponse("you should enter item id as an integer").MessageResponse();
			 }
			 Book output=handleRequest.info(itemNumber);
			
           return StandardResponse.BookInfoResponse(output);
       });
		
		post("purchase/:itemNumber", (req,res)->{
			 res.type("application/json");
			 int itemNumber;
			 try {
			 itemNumber=Integer.parseInt(req.params(":itemNumber"));}
			 catch(NumberFormatException e) {
				 
				 return new StandardResponse("you should enter item id as an integer").MessageResponse();
			 }
		     Book output=handleRequest.purchase(itemNumber);
			 return StandardResponse.BookPurchaseResponse(output);
       });

	}

}
