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
	String updateResponse ="Update not succeeded! This item may not exist, or quantity less than 0.";
	static String CSVFileURL=System.getProperty("user.dir")+"\\\\src\\\\main\\\\java\\\\catalogDatabase.CSV";
	int f = 0;

	public List<String[]> getallData() throws IOException, CsvException {
		
		  try (CSVReader reader = new CSVReader(new FileReader(CSVFileURL))) {
			  r = reader.readAll();
			  reader.close();
		  }	
		  return r;
	}
	
	public String updateIteamQuantityInDatadase(String iteamNumber,String quantity) throws IOException, CsvException {
		
		if (Integer.parseInt(quantity) < 0) { return updateResponse;}
		f=0;
		r = getallData();
			 
		try (CSVWriter writer = new CSVWriter(new FileWriter(CSVFileURL))) {
				  
			for(String[] arry: r) {
				if(arry[4].equals(iteamNumber))  {
					arry[2]=quantity;
					f=1;
					}
				writer.writeNext(arry);				  
			}	
			if(f==1)
			updateResponse = "Update succeeded!";

		} 	
		catch (IOException e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
			System.out.println("**"+e);
		} 	
	//	System.out.println("&&&"+updateResponse);
		return updateResponse;
		
			 
	}
		
		
}



