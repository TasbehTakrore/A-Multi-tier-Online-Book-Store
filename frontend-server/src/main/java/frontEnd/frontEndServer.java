package frontEnd;
import static spark.Spark.*;

import java.io.StringReader;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class frontEndServer {

	public static Service handleRequest=new Service();
	public static String CATALOG_IP_ADDRESS="localhost";
	public static String CATALOG_PORT="4567";
	
	public static void main(String[] args) {
		port(4000);
		
		get("search/:topic", (req,res)->{
			 res.type("application/json");
			 String output=handleRequest.search(req.params(":topic"));
			
            return output;
        });
		
		get("info/:itemNumber", (req,res)->{
			 res.type("application/json");
			 String output=handleRequest.info(Integer.parseInt(req.params(":itemNumber")));
			
           return output;
       });

	}

}
