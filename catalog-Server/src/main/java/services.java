import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class services {
	
	
	static String searchResponse="";
	static String iteamResponse="";
	static String updateResponse="";
	static String comma="";
	static int f = 0; 
	public static database data = new database();
	static String CSVFileURL="C:\\\\Users\\\\USER\\\\git\\\\A-Multi-tier-Online-Book-Store\\\\catalog-Server\\\\src\\\\main\\\\java\\\\catalogDatabase.CSV";
	List<String[]> r;

	
	 void ifTopicFound(String[] x, String topic){
		if ( x[4].equals(topic))
			searchResponse += comma+"\n  {\n     \"id\":" + x[0]+ ",\n     \"title\":\""+ x[1]+"\"\n  }";
			if (f==0) {
				f=1;
				comma += ",";}
	}

	
	  String searchForTopic(String topic) throws IOException, CsvException {
		  
		 searchResponse ="[";
		 comma="";
		 f = 0;
		 
		 r =  data.getallData(CSVFileURL);
		 r.forEach(x -> ifTopicFound(x,topic)); 
		 
		 searchResponse += "\n]"; 
		 
		 //if topic not exist
		  if(searchResponse.equals("[\n]")) {
			  searchResponse = "\n  {\n     \"message\": \"This topic does not exist!\" \n  }";
		  }
		  return searchResponse;
	}

	 void ifIteamFound(String[] x, String iteam){
			if ( x[0].equals(iteam))
				{iteamResponse = "\n  {\n     \"title\": \"" + x[1]+ "\",\n     \"quantity\":"+ x[3]+ ",\n     \"price\":"+ x[2]+"\n  }";
					f = 1;
				}}
			 
	 
	  String searchForIteam(String iteam) throws IOException, CsvException {
		 iteamResponse="\n  {\n     \"message\": \"This item does not exist!"+"\"\n  }";
		 r =  data.getallData(CSVFileURL);

		 f = 0;
		 for( String[] arry: r) {
			 ifIteamFound(arry,iteam.toString());
			 if(f==1) break;
		 }
			   
		 return iteamResponse;
	}


	  
	  String updateIteamCount(String iteamNumber, String count) throws IOException, CsvException {
		  
		  //r =  data.getallData(CSVFileURL);
		  updateResponse = data.updateIteamCountInDatadase(iteamNumber,count,CSVFileURL);
		  return updateResponse;
		  
	  }

  
	  

}
