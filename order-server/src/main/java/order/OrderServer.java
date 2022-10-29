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
			 int itemNumber;
			 try {
			 itemNumber=Integer.parseInt(req.params(":itemNumber"));}
			 catch(NumberFormatException e) {
				 
				 return new StandardResponse("you should enter item id as an integer").MessageResponse();
			 }
			 Order outputOrder=handleRequest.purchase(itemNumber);
			 return StandardResponse.FormatPurchaseResponse(outputOrder);
        });
		
		get("orders", (req,res)->{
		   res.type("application/json");
			 
		  Order[] orders=handleRequest.getAllOrders();
           return StandardResponse.getAllOrdersFormattToJson(orders);
       });

	}

}
