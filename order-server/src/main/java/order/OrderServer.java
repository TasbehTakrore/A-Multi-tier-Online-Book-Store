package order;

import static spark.Spark.*;
import static spark.Spark.port;

import com.google.gson.Gson;





public class OrderServer {
	
	public static Services handleRequest=new Services();
	public static String CATALOG_IP_ADDRESS="localhost";
	public static String CATALOG_PORT="4567";


	public static void main(String[] args) {
		port(4100);
		
		  
		post("purchase/:itemNumber", (req,res)->{
			 res.type("application/json");
			 Order outputOrder=handleRequest.purchase(Integer.parseInt(req.params(":itemNumber")));
			 return StandardResponse.FormatPurchaseResponse(outputOrder);
        });
		
		get("Orders", (req,res)->{
			 res.type("application/json");
			 String output=null;
			
           return output;
       });

	}

}
