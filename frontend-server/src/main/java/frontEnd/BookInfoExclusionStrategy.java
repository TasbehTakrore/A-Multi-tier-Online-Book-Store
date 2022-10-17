package frontEnd;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class BookInfoExclusionStrategy implements ExclusionStrategy {

	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		
	if ("itemNumber".equals(f.getName())) {
            return true;
		}
	if ("topic".equals(f.getName())) {
        return true;
    }
	if ("message".equals(f.getName())) {
        return true;
    }
	return false;

	}

	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

}
