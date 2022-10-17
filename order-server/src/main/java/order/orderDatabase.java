package order;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

public class orderDatabase {

	List<String[]> orders;
	private int ordersCount;
	String CSVFileURL;
	
	public orderDatabase() {
		
	CSVFileURL=System.getProperty("user.dir")+"/src/main/resources/orderDatabase.CSV";
    orders=getallData();
    ordersCount=orders.size();
    System.out.println(ordersCount);
	}
	
	public int getOrdersCount() {
		return ordersCount;
	}
	public List<String[]> getallData(){
		
		  try (CSVReader reader = new CSVReader(new FileReader(CSVFileURL))) {
			  orders = reader.readAll();
			  reader.close();
		  } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CsvException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		  return orders;
	}
	
	public void addRecord(Order newOrder) {
		try {
			CSVWriter writer = new CSVWriter(new FileWriter(CSVFileURL));
			String [] order=newOrder.toString().split(",");
			for(int i=0;i<order.length;i++) {
				order[i]=order[i].split("=")[1];
			}
			orders.add(order);
			ordersCount++;
			
			writer.writeAll(orders);
			writer.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
