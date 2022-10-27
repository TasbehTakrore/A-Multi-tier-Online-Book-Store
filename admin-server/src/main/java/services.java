import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.google.gson.Gson;
 

public class services {
	
	jsonTransformer transformer = new jsonTransformer();
	
	String getAllOrders(){
		URI orderQueryRequest=null;
	//	Order newOrder=null;
		try {
			orderQueryRequest = new URI("http://"+adminServer.ORDER_IP_ADDRESS+":"+adminServer.ORDER_PORT+"/orders");
		} catch (URISyntaxException e) {
		   e.printStackTrace();
		}
		
		String orderedBookInfo=getResponseData(orderQueryRequest);
		System.out.println(orderedBookInfo);
		return orderedBookInfo;
		
	}


	  
	  Book updateIteam(Book book) throws IOException{
			URI catalogQueryRequest=null;
			URI updateQueryRequest=null;
			
			Book returnBook;
			//	Order newOrder=null;
			try {
				catalogQueryRequest = new URI("http://"+adminServer.CATALOG_IP_ADDRESS+":"+adminServer.CATALOG_PORT+"/query/itemNumber/"+URLEncoder.encode(Integer.toString(book.getItemNumber()), StandardCharsets.UTF_8));
			} catch (URISyntaxException e) {
			   e.printStackTrace();
			   System.out.println("********");
			}
			
			String orderedBookInfo=getResponseData(catalogQueryRequest);
			Gson parseJson = new Gson();  
			Book orderedBook = parseJson.fromJson(orderedBookInfo, Book.class) ;
			orderedBook.setItemNumber(book.getItemNumber());
			
			//book doesn't exist
			if(orderedBook.getMessage()!= null && orderedBook.getMessage().equals("This item does not exist!")) {
				System.out.println("This item does not exist!");
				return orderedBook;
			}
			
			
				try {
					updateQueryRequest = new URI("http://"+adminServer.CATALOG_IP_ADDRESS+":"+adminServer.CATALOG_PORT+"/update/"+URLEncoder.encode(Integer.toString(book.getItemNumber()), StandardCharsets.UTF_8));
				} catch (URISyntaxException e) {
				   e.printStackTrace();
				   System.out.println("********");

				}
				
				
				String updateResponse=patchResponseData(updateQueryRequest,book);
				System.out.println(updateResponse);
				returnBook = transformer.convertGsonToObj(updateResponse);
				
				return returnBook;
		  
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
		

		private String patchResponseData(URI uri, Book book) {
			HttpRequest request=null;
			HttpClient client=null;
			HttpResponse<String> response=null;
			try {
				request = HttpRequest.newBuilder()
						  .uri(uri)
						  .method("patch",BodyPublishers.ofString(jsonTransformer.convertObjToGson(book)))
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

}
