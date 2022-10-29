package order;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

public class orderDatabase {

	List<Order> orders=new Vector<Order>() ;
	private int ordersCount;
	String CSVFileURL;
	
	public orderDatabase() {
		
	CSVFileURL="orderDatabase.CSV";
    orders=getallData();
    ordersCount=orders.size();
    System.out.println(ordersCount);
	}
	
	public int getOrdersCount() {
		return ordersCount;
	}
	public List<Order> getallData(){
		List<String[]> data = null;
		  try (CSVReader reader = new CSVReader(new FileReader(CSVFileURL))) {
			  data = reader.readAll();
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
		  
		  for(int i=0;i<data.size();i++) {
			  orders.add(Order.fromDatabasetoOrder(data.get(i)));
		  }
		  return orders;
	}
	
	public void addRecord(Order newOrder) {
		try {
			CSVWriter writer = new CSVWriter(new FileWriter(CSVFileURL,true));
			String [] order=newOrder.toStringtoDatabase().split(",");
		    orders.add(newOrder);
			ordersCount++;
			
			
			writer.writeNext(order);
			writer.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public Order[] getAllOrders() {
		Order[] out= new Order[orders.size()];
		try
		{
		
		orders.toArray(out);}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return out;
	}
}
