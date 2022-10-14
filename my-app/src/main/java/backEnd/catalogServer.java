package backEnd;

import static spark.Spark.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class catalogServer {
	
	static String searchResponse="";
	static String comma="";
	static int f = 0; 
	
	static void ifTopicFound(String[] x, String topic){
		if ( x[4].equals(topic))
			searchResponse += comma+"\n  {\n     \"id\":" + x[0]+ ",\n     \"title\":\""+ x[1]+"\"\n  }";
			if (f==0) {
				f=1;
				comma += ",";}
	}
	
	
	 static String searchForTopic(String topic) throws IOException, CsvException {
		 searchResponse ="[";
		 comma="";
		 f = 0;
		  try (CSVReader reader = new CSVReader(new FileReader("C:\\Users\\USER\\git\\A-Multi-tier-Online-Book-Store\\my-app\\src\\main\\java\\backEnd\\catalogDatabase.CSV"))) {
			   List<String[]> r = reader.readAll();
			      r.forEach(x -> ifTopicFound(x,topic)); } 
		  searchResponse += "\n]"; 
		
		return searchResponse;
	}


	    public static void main(String[] args) throws IOException, CsvException {

	        get("/search/:topic", (req,res)->{
	            res.type("application/json");
	            return  searchForTopic(req.params(":topic").toLowerCase());
	        });

	    }
	

}

