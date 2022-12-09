
import static spark.Spark.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.json.*;  


public class catalogServer {
	
	public static String CATALOG_2_IP_ADDRESS="localhost";
	public static String FRONTEND_IP_ADDRESS="localhost";

	public static String CATALOG_2_PORT="4568";
	public static String FRONTEND_PORT="4000";

	
	public static services catSer = new services();
			
	    public static void main(String[] args) throws IOException, CsvException {
	    	port(4567);
	    	
	        get("query/topic/:topic", (req,res)->{
	            res.type("application/json");
	            logMessages.printlogMessage(req,"query/topic/"+req.params(":topic"));
	            Book[] outputBooks = catSer.searchForTopic(URLDecoder.decode(req.params(":topic").toLowerCase(), StandardCharsets.UTF_8));
	            return jsonTransformer.convertObjToGson(outputBooks);            
	        });

	        get("query/itemNumber/:itemNumber", (req,res)->{
	            res.type("application/json");
	            logMessages.printlogMessage(req,"query/itemNumber/"+req.params(":itemNumber"));
	            Book outputBooks = catSer.searchForItem(URLDecoder.decode(req.params(":itemNumber"), StandardCharsets.UTF_8));
	            return  jsonTransformer.convertObjToGson(outputBooks);

	        });


	        patch("update/:itemNumber", (req,res)->{
	        	res.type("application/json");
	            logMessages.printlogMessage(req,"update/"+req.params(":itemNumber"));
	        	Book book = jsonTransformer.convertGsonToObj(req.body());
	        	
	        	// send ACK to frontEnd server to remove data..
	        	consistency.ACKtoRemoveFromCach(req.params(":itemNumber"));

	        	
//	        	//This is true statement.. when we create real IP, replace text with catalog2 IP..
//	        	if( ! req.ip().equals("catalog 2 IP"))  
//	        		consistency.UpdateOtherServer(book);
	        	
	        	consistency.UpdateOtherServer(book); // we need to remove it.. now this here only to test consistency class.
	        	
	        	book.setItemNumber(Integer.parseInt(req.params(":itemNumber")));
	        	book = catSer.updateIteamQuantity(book);
	           
	           return jsonTransformer.convertObjToGson(book);
	        });
	        
	    }


	    
}

