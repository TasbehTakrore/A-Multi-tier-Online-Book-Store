
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class ResponseExclusionStrategy implements ExclusionStrategy {

	@Override
	public boolean shouldSkipField(FieldAttributes f) {
	
		if ("title".equals(f.getName())) {
            return true;
        }	
		if ("price".equals(f.getName())) {
	            return true;
	        }
		if ("quantity".equals(f.getName())) {
            return true;
        }
		if ("topic".equals(f.getName())) {
            return true;
        }
//		if ("message".equals(f.getName())) {
//            return true;
//        }
		return false;
	}

	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

}
