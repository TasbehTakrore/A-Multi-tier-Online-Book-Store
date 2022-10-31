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
	String updateResponse ="Update doesn't succeeded!";
	static String CSVFileURL=System.getProperty("user.dir")+"\\\\src\\\\main\\\\java\\\\catalogDatabase.CSV";
	int f = 0;

	public List<String[]> getallData() throws IOException, CsvException {
		
		  try (CSVReader reader = new CSVReader(new FileReader(CSVFileURL))) {
			  r = reader.readAll();
			  reader.close();
		  }	
		  return r;
	}
	
	public String updateIteamQuantityInDatadase(String iteamNumber,int quantity, int price) throws IOException, CsvException {
		updateResponse ="Update doesn't succeeded!";
		r = getallData();
			 
		try (CSVWriter writer = new CSVWriter(new FileWriter(CSVFileURL))) {
				  
			for(String[] arry: r) {
				if(arry[4].equals(iteamNumber)){
					if(quantity>=0) {
					arry[2]=Integer.toString(quantity);
					updateResponse = "Update succeeded!";
					}
					if(price>0) {
					arry[1]=Integer.toString(price);					
					updateResponse = "Update succeeded!";
					}
					}
				writer.writeNext(arry);				  
			}	

		} 	
		catch (IOException e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
			System.out.println("**"+e);
		} 	
		return updateResponse;
		
			 
	}
		
		
}



