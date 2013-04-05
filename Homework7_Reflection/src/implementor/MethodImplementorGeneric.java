package implementor;

import util.TypeUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 4/3/13
 * Time: 3:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class MethodImplementorGeneric implements IImplementor {
	private Method source;

	public MethodImplementorGeneric(Method source) {
		this.source = source;
	}
	@Override
	public String implement() {
		String result = source.toGenericString();
		String methodName = source.getName();
		int i = result.indexOf(methodName);
		// Modifiers added
		result = result.substring(0, i);
		// Name
		String className = source.getDeclaringClass().getCanonicalName();
		if (result.indexOf(className) >= 0) {
			// Remove class name before the method name
			result = result.replaceAll(className, "");
			i = result.lastIndexOf(".");
			result = result.substring(0, i) + result.substring(i + 1);
		}
		result = result.replaceAll("abstract", "");
		result += methodName + "(";
		// Parameters
		i = 0;
		//Class[] paramTypes = source.getParameterTypes();
		Type[] paramTypes = source.getGenericParameterTypes();
		for (Type t: paramTypes) {
			//TODO: generics here?
			i++;
			String paramName = "param" + String.valueOf(i);    //any param name
			result += TypeUtils.stringValueOf(t) + " " + paramName;
			if (i < paramTypes.length) {
				result += ", ";
			}
		}
		if (source.getReturnType() == Void.TYPE) {
			result += ") {}";
		} else {
			result += ") { return ";
			if (source.getReturnType().isPrimitive()) {
				Class wrapper = TypeUtils.getWrapperType(source.getReturnType());
				result += "(" + wrapper.getCanonicalName() + ")"; //cast to wrapper type to return default value Object
			}
			// Return default value
			result += String.valueOf(source.getDefaultValue());
			result += "; }";
		}

		return result;
	}
}
