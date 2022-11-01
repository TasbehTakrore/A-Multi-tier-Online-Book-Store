package order;

import static spark.Spark.*;
import static spark.Spark.port;

import com.google.gson.Gson;







public class OrderServer {
	
	public static Services handleRequest=new Services();
	
    //don't forget to set this environment variable
	//comment the next  line if catalog  service are running on localhost
	public static String CATALOG_IP_ADDRESS=System.getenv("CATALOG_IP_ADDRESS");
	
	//uncomment the next  line if catalog service are running on localhost
	/*
	 public static String CATALOG_IP_ADDRESS="localhost";
	*/
	
	
	//******** IMPORTANT NOTE:make sure to change the port if all the services are running on the localhost 
	//                        so that each service has a unique port number
	public static String CATALOG_PORT="4000";




	public static void main(String[] args) {
		port(4000);
		
		  
		post("purchase/:itemNumber", (req,res)->{
			 res.type("application/json");
			 logMessages.printlogMessage(req,"purchase/"+req.params(":itemNumber"));           
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
		   logMessages.printlogMessage(req,"orders");           			 
		   Order[] orders=handleRequest.getAllOrders();
           return StandardResponse.getAllOrdersFormattToJson(orders);
       });

	}

}
