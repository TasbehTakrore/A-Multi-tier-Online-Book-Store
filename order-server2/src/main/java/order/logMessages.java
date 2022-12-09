package order;
import spark.*;

public class logMessages {
	
	 static void printlogMessage(Request req, String url){
		 
		System.out.println("---------------------------------");
		java.util.Date date = new java.util.Date();    
		System.out.println(date);   
		System.out.println("Client IP:"+req.ip());
		System.out.println("The Request:"+url);
		System.out.println("---------------------------------");
		
	}

}
