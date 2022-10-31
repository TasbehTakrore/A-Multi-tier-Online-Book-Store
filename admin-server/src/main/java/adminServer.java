import static spark.Spark.get;
import static spark.Spark.patch;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import static spark.Spark.port;

public class adminServer {

	public static services handleRequest = new services();
	public static String CATALOG_IP_ADDRESS=System.getenv("CATALOG_IP_ADDRESS");
	public static String CATALOG_PORT="4000";
	public static String ORDER_IP_ADDRESS=System.getenv("ORDER_IP_ADDRESS");
	public static String ORDER_PORT="4000";

	public static void main(String[] args) {

		port(4000);
		 get("orders", (req,res)->{
        	
        	String allOrders = handleRequest.getAllOrders();
        	res.type("application/json");
            logMessages.printlogMessage(req,"orders");
            return allOrders;   
  
        });
        
        // admin can update quantity or price or both
        patch("update/:itemNumber", (req,res)->{
           res.type("application/json");
           Book book = jsonTransformer.convertGsonToObj(req.body());           
           book.setItemNumber(Integer.parseInt(req.params(":itemNumber")));
           book = handleRequest.updateIteam(book);
           logMessages.printlogMessage(req,"update/"+req.params(":itemNumber"));           
           return jsonTransformer.convertObjToGson(book,1);
        });
	}

}
