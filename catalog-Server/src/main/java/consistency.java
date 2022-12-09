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
			
			URI catalogUpdateQuantityURI=new URI("http://"+catalogServer.CATALOG_2_IP_ADDRESS+":"+catalogServer.CATALOG_2_PORT+"/update/"+orderedBook.getItemNumber());
			   
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
	}
	


