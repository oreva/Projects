package util;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 4/3/13
 * Time: 2:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class TypeUtils {
	private static final HashMap<Class, Class> WRAPPER_TYPES = getWrapperTypes();

	public static Class getWrapperType(Class primitiveClass)
	{
		return WRAPPER_TYPES.get(primitiveClass);
	}

	private static HashMap<Class, Class> getWrapperTypes()
	{
		HashMap<Class, Class> ret = new HashMap<Class, Class>();
		ret.put(boolean.class, Boolean.class);
		ret.put(char.class, Character.class);
		ret.put(byte.class, Byte.class);
		ret.put(short.class, Short.class);
		ret.put(int.class, Integer.class);
		ret.put(long.class, Long.class);
		ret.put(float.class, Float.class);
		ret.put(double.class, Double.class);
		ret.put(void.class, Void.class);
		return ret;
	}
}
