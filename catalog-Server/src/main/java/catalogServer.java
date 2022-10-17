
import static spark.Spark.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class catalogServer {
	

	public static services catSer = new services();
	 

	    public static void main(String[] args) throws IOException, CsvException {

	        get("query/topic/:topic", (req,res)->{
	            res.type("application/json");
	            return  catSer.searchForTopic(URLDecoder.decode(req.params(":topic").toLowerCase(), StandardCharsets.UTF_8));
	        });

	        get("query/iteamNumber/:iteamNumber", (req,res)->{
	            res.type("application/json");
	            
	            return  catSer.searchForIteam(URLDecoder.decode(req.params(":iteamNumber").toLowerCase(), StandardCharsets.UTF_8));
	        });


	        put("update/:iteamNumber/:count", (req,res)->{
	            res.type("application/json");
	            
	            return catSer.updateIteamCount(req.params(":iteamNumber"),req.params(":count"));
	        });
	        
		    //System.out.print(catSer.updateIteamCount("1","1600"));

	    }


	    
}

