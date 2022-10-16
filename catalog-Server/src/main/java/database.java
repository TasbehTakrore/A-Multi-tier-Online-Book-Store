import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

public class database {
	
	
	List<String[]> r;
	String updateResponse;
	
	public List<String[]> getallData(String CSVFileURL) throws IOException, CsvException {
		
		  try (CSVReader reader = new CSVReader(new FileReader(CSVFileURL))) {
			  r = reader.readAll();
			  reader.close();
		  }	
		  return r;
	}
	
	public String updateIteamCountInDatadase(String iteamNumber,String count, String CSVFileURL) throws IOException, CsvException {
		
		r = getallData(CSVFileURL);
			 
		try (CSVWriter writer = new CSVWriter(new FileWriter(CSVFileURL))) {
				  
			for(String[] arry: r) {
				 System.out.println(arry[0]+"**"+arry[3]+"**"+ iteamNumber);

				if(arry[0].equals(iteamNumber)) {arry[3]=count;}
				writer.writeNext(arry);				  
			}			    
		} 	
		catch (IOException e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
		} 
		
		updateResponse = "\n  {\n     \"message\": \"Update succeeded!\" \n  }";
		return updateResponse;
		
			 
	}
		
		
}



