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
	static String iteamResponse="";
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
		 
		  if(searchResponse.equals("[\n]")) {
			  searchResponse = "\n  {\n     \"message\": \"This topic does not exist!\" \n  }";
		  }
		return searchResponse;
	}

	static void ifIteamFound(String[] x, String iteam){
			if ( x[0].equals(iteam))
				{iteamResponse = "\n  {\n     \"title\": \"" + x[1]+ "\",\n     \"quantity\":"+ x[3]+ ",\n     \"price\":"+ x[2]+"\n  }";
					f = 1;
				}}
			 
	 
	 static String searchForIteam(String iteam) throws IOException, CsvException {
		 iteamResponse="\n  {\n     \"message\": \"This item does not exist!"+"\"\n  }";
		  try (CSVReader reader = new CSVReader(new FileReader("C:\\Users\\USER\\git\\A-Multi-tier-Online-Book-Store\\my-app\\src\\main\\java\\backEnd\\catalogDatabase.CSV"))) {
			   List<String[]> r = reader.readAll();
			   f = 0;
			   for( String[] arry: r) {
				   ifIteamFound(arry,iteam.toString());
		    		  if(f==1) break;
			   }}
			   
		return iteamResponse;
	}

	 

	    public static void main(String[] args) throws IOException, CsvException {

	        get("query/search/:topic", (req,res)->{
	            res.type("application/json");
	            return  searchForTopic(req.params(":topic").toLowerCase());
	        });

	        get("query/info/:iteam", (req,res)->{
	            res.type("application/json");
	            return  searchForIteam(req.params(":iteam").toLowerCase());
	        });
	    }
	

}

