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
	
	public static String CATALOG_IP_ADDRESS;
	public static String CATALOG_PORT;
	public static String ORDER_IP_ADDRESS;
	public static String ORDER_PORT;
	public static LRUcacheInfo RLUcacheForInfo = new LRUcacheInfo(5);
	public static LRUcacheSearch RLUcacheForSearch = new LRUcacheSearch(1);
	
	public static void main(String[] args) {
		port(4000);
		
		get("search/:topic", (req,res)->{
			 res.type("application/json");
			 logMessages.printlogMessage(req,"search/"+req.params(":topic"));
			 Book[] outputBooks;
			 outputBooks= RLUcacheForSearch.get(req.params(":topic")); // // get books from RUL Search cache.
			 if(outputBooks==null) { // If books is not in the cache, get it from catalog server, and set in cache.
				 loadBalance.setCatalogServerIPAndPort(); ///////////////////////////////////***********
				 outputBooks=handleRequest.search(req.params(":topic"));
				 RLUcacheForSearch.set(req.params(":topic"),outputBooks);
				 }
			return StandardResponse.BookSearchResponse(outputBooks);
           
        });
		
		get("info/:itemNumber", (req,res)->{
			 res.type("application/json");
			 logMessages.printlogMessage(req,"info/"+req.params(":itemNumber"));           
			 int itemNumber;
			 try {
			 itemNumber=Integer.parseInt(req.params(":itemNumber"));}
			 catch(NumberFormatException e) {
				 return new StandardResponse("you should enter item id as an integer").MessageResponse();
			 }
			 Book output;
			 output= RLUcacheForInfo.get(itemNumber); // get book from RUL Info cache.
			 if(output == null) { // If book is not in the cache, get it from catalog server, and set in cache.
				 loadBalance.setCatalogServerIPAndPort(); 
				 output=handleRequest.info(itemNumber);
			 	RLUcacheForInfo.set(itemNumber,output);
			 }
			
           return StandardResponse.BookInfoResponse(output);
       });
		
		post("purchase/:itemNumber", (req,res)->{
			 res.type("application/json");
			 logMessages.printlogMessage(req,"purchase/"+req.params(":itemNumber"));           

			 int itemNumber;
			 try {
			 itemNumber=Integer.parseInt(req.params(":itemNumber"));}
			 catch(NumberFormatException e) {
				 
				 return new StandardResponse("you should enter item id as an integer").MessageResponse();
			 }
			 //setOrderServerIPAndPort
			 loadBalance.setOrderServerIPAndPort();
		     Book output=handleRequest.purchase(itemNumber);
			 return StandardResponse.BookPurchaseResponse(output);
       });
		
		
		delete("ACK/:itemNumber", (req,res)->{
			Book result;
			result = RLUcacheForInfo.get(Integer.parseInt(req.params(":itemNumber")));
			if(result != null) {
				// if result != null , this mean the cache has this value, so we need to remove it,
				// because we send (get) to RLU cache, now the node in head
				RLUcacheForInfo.removeHead(Integer.parseInt(req.params(":itemNumber")));
			}
			return "";
      });
		

	}

}
