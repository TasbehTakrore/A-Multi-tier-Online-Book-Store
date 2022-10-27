
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
	

	public static services catSer = new services();
	//public static jsonTransformer transToJson = new jsonTransformer();
		
	    public static void main(String[] args) throws IOException, CsvException {

	    	
	        get("query/topic/:topic", (req,res)->{
	            res.type("application/json");
	            System.out.println("Search service..");
	            Book[] outputBooks = catSer.searchForTopic(URLDecoder.decode(req.params(":topic").toLowerCase(), StandardCharsets.UTF_8));
	            return jsonTransformer.convertObjToGson(outputBooks);            
	        });

	        get("query/itemNumber/:itemNumber", (req,res)->{
	            res.type("application/json");
	            System.out.println("info service..");
	            Book outputBooks = catSer.searchForItem(URLDecoder.decode(req.params(":itemNumber"), StandardCharsets.UTF_8));
	            return  jsonTransformer.convertObjToGson(outputBooks);

	        });


	        patch("update/:itemNumber", (req,res)->{
	            res.type("application/json");
	           System.out.println(req.body());	           
	           Book book = jsonTransformer.convertGsonToObj(req.body());
	           book.setItemNumber(Integer.parseInt(req.params(":itemNumber")));
	           book = catSer.updateIteamQuantity(book);
	           System.out.println("-----------------"+book.getMessage());
	           
	           return jsonTransformer.convertObjToGson(book);
	        });
	        
	    }


	    
}

