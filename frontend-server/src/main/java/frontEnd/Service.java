package frontEnd;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.Vector;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



public class Service implements UserService {

	public Book[] search(String topic) {
		
		URI searchURI=null;
		try {
			searchURI = new URI("http://"+frontEndServer.CATALOG_IP_ADDRESS+":"+frontEndServer.CATALOG_PORT+"/query/topic/"+URLEncoder.encode(topic, StandardCharsets.UTF_8));
			
			
		} catch (URISyntaxException e) {
			
			e.printStackTrace();
		} 
		String responseData=getResponseData(searchURI);
		Gson parseJson = new GsonBuilder().
		                     setPrettyPrinting().
		                     setExclusionStrategies(new BookSearchExclusionStrategy()).
		                     create(); 
		
		Book[] resultBooks = parseJson.fromJson(responseData, Book[].class) ;
		
		return resultBooks;
	}

	public Book info(int itemNumber) {
		URI searchURI=null;
		try {
			searchURI = new URI("http://"+frontEndServer.CATALOG_IP_ADDRESS+":"+frontEndServer.CATALOG_PORT+"/query/iteamNumber/"+URLEncoder.encode(Integer.toString(itemNumber), StandardCharsets.UTF_8));
		
			
		} catch (URISyntaxException e) {
			
			e.printStackTrace();
		} 
		String responseData=getResponseData(searchURI);
		Gson parseJson = new GsonBuilder().
                setPrettyPrinting().
                setExclusionStrategies(new BookSearchExclusionStrategy()).
                create(); 

      Book resultBook = parseJson.fromJson(responseData, Book.class) ;
		return resultBook;
	}

	public void purchase(int itemNumber) {
		// TODO Auto-generated method stub

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

}
