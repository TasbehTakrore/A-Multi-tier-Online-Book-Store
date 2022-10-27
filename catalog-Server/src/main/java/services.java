import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.google.gson.Gson;    

public class services {
	
	
	static String searchResponse="";
	static String iteamResponse="";
	static String updateResponse="";
	static String comma="";
	static int f = 0; 
	public static database data = new database();
	List<String[]> r;
	Book book = null;
	Book[]  books = null;
	int count=0;
	int index;

	
	void addBooktoArrayBooks(String[] x, String topic, int indexm){
		 
		 book = new Book(x[0],Integer.parseInt(x[1]),Integer.parseInt(x[2]),x[3],Integer.parseInt(x[4]),"");
		 books[index]=book;
	}
	
	  Book[] searchForTopic(String topic) throws IOException, CsvException {
		 
		  count=0;
		 r =  data.getallData();
		 
		 //to find count of books that have :topic
		 r.forEach(x -> {
			 if ( x[3].equals(topic))
				 count ++;}); 	 
				 
		 // If there is no book with :topic
		 if (count==0) {
			 books = new Book[1];
			 book = new Book();
			 book.setMessage("This topic does not exist!");
			 books[0]=book;
			 return books;
		 }
		 
		 index =0;
		 books = new Book[count];
		 r.forEach(x -> {
			 if ( x[3].equals(topic)) {
				 addBooktoArrayBooks(x, topic, index);
				 // System.out.println(books[index].getTitle());
				 index ++;}}); 
		 
		 return books;
	}
	 
	  Book searchForItem(String itemNumber) throws IOException, CsvException {
		 r =  data.getallData();

		 for( String[] x: r) {

			 if(x[4].equals(itemNumber)) { // if item exist
				 book = new Book(x[0],Integer.parseInt(x[1]),Integer.parseInt(x[2]),x[3],Integer.parseInt(x[4]),"");
				 System.out.println("info :\")");

				 return book;
			 }
		 			   
			 book = new Book();
			 book.setMessage("This item does not exist!");
			 System.out.println("no item!! :\")");
		 } 
		 return book;
	}


	  
	  Book updateIteamQuantity(Book bOok) throws IOException, CsvException {
		  
		  Book book = bOok;
		  //r =  data.getallData(CSVFileURL);
		  int quantity = book.getQuantity();
		  int price = book.getPrice();
		  System.out.println(price+"****");
		  updateResponse = data.updateIteamQuantityInDatadase(Integer.toString(book.getItemNumber()),quantity, price);

		  book.setMessage(updateResponse);
		  return book;
		  
	  }

  
	  

}
