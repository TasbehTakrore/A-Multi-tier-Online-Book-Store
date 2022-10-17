package order;

import static spark.Spark.get;
import static spark.Spark.port;





public class OrderServer {
	
	public static Services handleRequest=new Services();
	public static String CATALOG_IP_ADDRESS="localhost";
	public static String CATALOG_PORT="4567";


	public static void main(String[] args) {
		port(4100);
		
		
		get("purchase/:itemNumber", (req,res)->{
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
