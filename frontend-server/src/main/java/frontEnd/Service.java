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

public class Service implements UserService {

	public String search(String topic) {
		
		URI searchURI=null;
		try {
			searchURI = new URI("http://"+frontEndServer.CATALOG_IP_ADDRESS+":"+frontEndServer.CATALOG_PORT+"/query/search/"+URLEncoder.encode(topic, StandardCharsets.UTF_8));
			System.out.println("*********"+searchURI +"*********");
			
		} catch (URISyntaxException e) {
			
			e.printStackTrace();
		} 
		String responseData=getResponseData(searchURI);
		return responseData;
	}

	public String info(int itemNumber) {
		URI searchURI=null;
		try {
			searchURI = new URI("http://"+frontEndServer.CATALOG_IP_ADDRESS+":"+frontEndServer.CATALOG_PORT+"/query/info/"+URLEncoder.encode(Integer.toString(itemNumber), StandardCharsets.UTF_8));
			System.out.println("*********"+searchURI +"*********");
			
		} catch (URISyntaxException e) {
			
			e.printStackTrace();
		} 
		String responseData=getResponseData(searchURI);
		return responseData;
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
