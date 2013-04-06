package implementor;

import util.TypeUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 4/3/13
 * Time: 3:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class MethodImplementor implements IImplementor {
	private Method source;

	public MethodImplementor(Method source) {
		this.source = source;
	}
	@Override
	public String implement() {
		//do not implement final methods
		if (Modifier.isFinal(source.getModifiers())) {
			return "";
		}
		String result = "";
		String methodName = source.getName();

		// Modifiers
		result += Modifier.toString(source.getModifiers()) + " ";
		result = result.replace("abstract", "");
		//Type parameters
		TypeVariable[] tv = source.getTypeParameters();
		if (tv.length > 0) {
			result += "<";
			for (TypeVariable v: tv) {
				result += TypeUtils.stringValueOf(v);
				if (v != tv[tv.length - 1]) {
					result += ", ";
				}
			}
			result += "> ";
		}
		//Return type
		result += TypeUtils.stringValueOf(source.getGenericReturnType()) + " ";
		// Name
		result += methodName + "(";
		// Parameters
		int i = 0;
		Type[] paramTypes = source.getGenericParameterTypes();
		for (Type t: paramTypes) {
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
