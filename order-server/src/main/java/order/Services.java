package order;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;



public class Services implements UserServices {
	orderDatabase orders=new orderDatabase();

	@Override
	public Order purchase(int itemNumber) {
		URI catalogQueryRequest=null;
		Order newOrder=null;
		try {
			catalogQueryRequest = new URI("http://"+OrderServer.CATALOG_IP_ADDRESS+":"+OrderServer.CATALOG_PORT+"/query/itemNumber/"+URLEncoder.encode(Integer.toString(itemNumber), StandardCharsets.UTF_8));
		} catch (URISyntaxException e) {
		   e.printStackTrace();
		}
		
		String orderedBookInfo=getResponseData(catalogQueryRequest );
		Gson parseJson = new Gson();  
		Book orderedBook = parseJson.fromJson(orderedBookInfo, Book.class) ;
		orderedBook.setItemNumber(itemNumber);
		
		//book doesn't exist
		if(orderedBook.getMessage()!= null && orderedBook.getMessage().equals("This item does not exist!")) {
			 newOrder=new Order(orderedBook,-1 ,"This item does not exist!");	
		}
		
		//book out of stock
		else if(orderedBook.getQuantity()<1) {
		
			 newOrder=new Order(orderedBook,-1 ,"This item is out of stock currently! come back soon.");	
		}
		
		//the book exists in the stock
		else {
			
			int newOrderNumber=orders.getOrdersCount()+1;
			 newOrder=new Order(orderedBook, newOrderNumber ,"success");
			
			 //update quantity 
			 orderedBook.setQuantity(orderedBook.getQuantity() -1);
			 
			 try {
					String updateBook=getUpdateQuantityResponseData(orderedBook);
					Book b=new Gson().fromJson(updateBook, Book.class);
					if(!b.getMessage().equals("Update succeeded!")) {
						newOrder.setMessage("something went wrong!");	
						return newOrder;
					}
					} catch (URISyntaxException e) {
					  e.printStackTrace();
					  newOrder.setMessage("something went wrong!");
					  return newOrder;
					  
					}
				   
				 //store order in database
			       orders.addRecord(newOrder);
			       
			}
			
			return newOrder;
		}

	private String getResponseData(URI uri) {
		HttpRequest request=null;
		HttpClient client=null;
		HttpResponse<String> response=null;
		try {
			request = HttpRequest.newBuilder()
					  .uri(uri)
					  .GET()
					  .build();
			
			client=HttpClient.newHttpClient();
			
			response = client.send(request, BodyHandlers.ofString());
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		return response.body();
	}
	
	private String getUpdateQuantityResponseData(Book orderedBook) throws URISyntaxException {
		HttpRequest request=null;
		HttpClient client=null;
		HttpResponse<String> response=null;
		
		URI catalogUpdateQuantityURI=new URI("http://"+OrderServer.CATALOG_IP_ADDRESS+":"+OrderServer.CATALOG_PORT+"/update/"+orderedBook.getItemNumber());
		   
		try {
		
			request = HttpRequest.newBuilder()
					  .uri(catalogUpdateQuantityURI)
					  .method("patch",BodyPublishers.ofString(orderedBook.toJson()))
					  .build();
			
			
			client=HttpClient.newHttpClient();
			
			response = client.send(request, BodyHandlers.ofString());
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	
		return response.body();
	}

	@Override
	public Order[] getAllOrders() {
		
		return orders.getAllOrders();
	}
}
