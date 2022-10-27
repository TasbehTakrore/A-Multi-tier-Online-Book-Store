package order;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class StandardResponse {
	
	
	private String message;

	public StandardResponse(String message) {
		this.message = message;
	}
	
	public String MessageResponse() {
		
		 Gson parseJson = new GsonBuilder().
                              setPrettyPrinting().
                              create(); 
		 
		 return parseJson.toJson(this, getClass());
	}
	
	
	public static String FormatPurchaseResponse(Order outputOrder) {
		
		 Gson parseJson;
		
		 if(outputOrder.getMessage().equals("success")) {
			 parseJson =  new GsonBuilder().
					          setPrettyPrinting().
					          setExclusionStrategies(new SuccessOrderExclusionStrategy()).
					          create(); 
			 }
			 
			 else {
				 parseJson =  new GsonBuilder().
			                      setPrettyPrinting().
			                      setExclusionStrategies(new  FailOrderExclusionStrategy()).
			                      create(); 
			 }
			
            return parseJson.toJson(outputOrder, Order.class);
	}
	
	public static Order FormatfFromStringtoOrder(String orderData) {
		
		 Gson parseJson;
		
		 parseJson =  new GsonBuilder().
					          setPrettyPrinting().
					          create(); 
		
			
          return parseJson.fromJson(orderData, Order.class);
	}

	
	public static String FormatfFromOrdertoJson(Order orderData) {
		
		 Gson parseJson;
		
		 parseJson =  new GsonBuilder().
					          setPrettyPrinting().
					          create(); 
		
			
         return parseJson.toJson(orderData, Order.class);
	}
	
	
	public static String getAllOrdersFormattToJson(Order[] orders) {
		 Gson parseJson;
			
		 parseJson =  new GsonBuilder().
				          excludeFieldsWithoutExposeAnnotation().
					      setPrettyPrinting().
					      create(); 
		
		return parseJson.toJson(orders, Order[].class);
	}
}
