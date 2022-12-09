package order;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class FailOrderExclusionStrategy implements ExclusionStrategy {

	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		if ("orderdBook".equals(f.getName())) {
            return true;
        }
		if ("orderNumber".equals(f.getName())) {
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
