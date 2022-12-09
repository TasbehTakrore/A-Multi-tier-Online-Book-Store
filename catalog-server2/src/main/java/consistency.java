import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;



public class consistency {
	
	
	public static void UpdateOtherServer(Book orderedBook) throws URISyntaxException  {
			HttpRequest request=null;
			HttpClient client=null;
			HttpResponse<String> response=null;
			
			URI catalogUpdateQuantityURI=new URI("http://"+catalogServer.CATALOG_1_IP_ADDRESS+":"+catalogServer.CATALOG_1_PORT+"/update/"+orderedBook.getItemNumber());
			   
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

		}
	
	
	public static void ACKtoRemoveFromCach(String key) throws URISyntaxException {
		
		HttpRequest request=null;
		HttpClient client=null;
		HttpResponse<String> response=null;
		
		URI frontEndURI=new URI("http://"+catalogServer.FRONTEND_IP_ADDRESS+":"+catalogServer.FRONTEND_PORT+"/ACK/"+key);
		   
		try {
		
			request = HttpRequest.newBuilder()
					  .uri(frontEndURI)
					  .DELETE()
					  .build();
			
			
			client=HttpClient.newHttpClient();
			
			response = client.send(request, BodyHandlers.ofString());
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}

	}

	
	}
	


