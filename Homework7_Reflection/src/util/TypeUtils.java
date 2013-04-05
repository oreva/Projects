package util;

import java.lang.reflect.*;
import java.util.HashMap;

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

	public static String stringValueOf(Type type) {
		if (type instanceof ParameterizedType) {
			return stringValueOf((ParameterizedType)type);
		} else if (type instanceof WildcardType) {
			return stringValueOf((WildcardType)type);
		} else if (type instanceof TypeVariable) {
			return ((TypeVariable) type).getName();
		} else if (type instanceof Class) {
			return ((Class) type).getCanonicalName();
		}
		return type.toString();
	}

	public static String stringValueOf(ParameterizedType type) {
		String result = "";
		result += stringValueOf(type.getRawType());
		result += "<";
		Type[] typeArgs = type.getActualTypeArguments();
		if (typeArgs.length > 0) {
			for (Type arg: typeArgs) {
				result += stringValueOf(arg);
				if (arg != typeArgs[typeArgs.length - 1]) {
					result += ", ";
				}
			}
		} else {
			throw new RuntimeException("No parametrized types!");
		}
		result += ">";
		return result;
	}

	public static String stringValueOf(WildcardType type) {
		String result = "";
		Type[] bounds = type.getLowerBounds();
		int len = bounds.length;
		if (len > 0) {
			result += "? super ";
		} else {
			bounds = type.getUpperBounds();
			len = bounds.length;
			if (len > 0) {
				result += "? extends ";
			}
		}
		if (len > 0) {
			for (int i = 0; i < len; i++) {
				result += stringValueOf(bounds[i]);
				if (i < len - 1) {
					result += ", ";
				}
			}
		} else {
			result += "?";
		}
		return result;
	}
}
