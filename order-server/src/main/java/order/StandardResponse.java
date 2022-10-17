package order;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class StandardResponse {
	
	static Gson parseJson;
	
	public static String FormatPurchaseResponse(Order outputOrder) {
		
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
}
