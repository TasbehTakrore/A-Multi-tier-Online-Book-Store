import static spark.Spark.get;
import static spark.Spark.patch;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import static spark.Spark.port;

public class adminServer {

	public static services handleRequest = new services();
	public static String ORDER_IP_ADDRESS="localhost";
	public static String ORDER_PORT="4100";
	public static String CATALOG_IP_ADDRESS="localhost";
	public static String CATALOG_PORT="4567";

	public static void main(String[] args) {

		port(4700);
		
        get("orders", (req,res)->{
        	
        	String allOrders = handleRequest.getAllOrders();
        	res.type("application/json");
            return allOrders;   
  
        });
        
        // admin can update quantity or price or both
        patch("update/:itemNumber", (req,res)->{
           res.type("application/json");
           System.out.println(req.body());
           Book book = jsonTransformer.convertGsonToObj(req.body());
           
           book.setItemNumber(Integer.parseInt(req.params(":itemNumber")));

           book = handleRequest.updateIteam(book);
           System.out.println(book.getMessage());
           
           return jsonTransformer.convertObjToGson(book,1);
        });
	}

}