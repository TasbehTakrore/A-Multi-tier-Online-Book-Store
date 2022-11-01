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
	   
        //don't forget to set theses environment variables
		//comment the next 2 lines if catalog and order services are running on localhost
		public static String CATALOG_IP_ADDRESS=System.getenv("CATALOG_IP_ADDRESS");
		public static String ORDER_IP_ADDRESS=System.getenv("ORDER_IP_ADDRESS");
		
		//uncomment the next 2 lines if catalog and order services are running on localhost
		/*
		 public static String CATALOG_IP_ADDRESS="localhost";
		 public static String ORDER_IP_ADDRESS="localhost";
		 */
		
		
		//******** IMPORTANT NOTE:make sure to change the port if all the services are running on the localhost 
		//                        so that each service has a unique port number
		public static String ORDER_PORT="4000";
		public static String CATALOG_PORT="4000";
	
	
	public static void main(String[] args) {
		port(4000);
		
		get("search/:topic", (req,res)->{
			 res.type("application/json");
			 logMessages.printlogMessage(req,"search/"+req.params(":topic"));           
			 Book[] outputBooks=handleRequest.search(req.params(":topic"));
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
			 Book output=handleRequest.info(itemNumber);
			
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
		     Book output=handleRequest.purchase(itemNumber);
			 return StandardResponse.BookPurchaseResponse(output);
       });

	}

}
