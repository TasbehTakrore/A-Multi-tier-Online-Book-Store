package order;

import static spark.Spark.*;
import static spark.Spark.port;

import com.google.gson.Gson;

public class OrderServer {
	
	public static Services handleRequest=new Services();
	public static String CATALOG_IP_ADDRESS="localhost";
	public static String CATALOG_PORT="4567";


	public static void main(String[] args) {
		port(4101);
		
		  
		post("purchase/:itemNumber", (req,res)->{
			 res.type("application/json");
			 logMessages.printlogMessage(req,"purchase/"+req.params(":itemNumber"));           
			 int itemNumber;
			 try {
			 itemNumber=Integer.parseInt(req.params(":itemNumber"));}
			 catch(NumberFormatException e) {
				 
				 return new StandardResponse("you should enter item id as an integer").MessageResponse();
			 }
			 loadBalance.setCatalogServerIPAndPort();
			 Order outputOrder=handleRequest.purchase(itemNumber);
			 return StandardResponse.FormatPurchaseResponse(outputOrder);
        });
		
		get("orders", (req,res)->{
		   res.type("application/json");
		   logMessages.printlogMessage(req,"orders");           			 
		   Order[] orders=handleRequest.getAllOrders();
           return StandardResponse.getAllOrdersFormattToJson(orders);
       });

	}

}
