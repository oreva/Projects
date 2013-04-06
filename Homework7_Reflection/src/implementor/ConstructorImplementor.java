package implementor;

import util.TypeUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 4/3/13
 * Time: 3:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConstructorImplementor implements IImplementor {
	private Constructor source;
	private String implementationName;

	public ConstructorImplementor(Constructor source, String implementationName) {
		this.source = source;
		this.implementationName = implementationName;
	}

	@Override
	public String implement() {
		String oldConstructor = source.toGenericString();
		int i = oldConstructor.indexOf(source.getName());
		// Modifiers added
		String result = oldConstructor.substring(0, i);
		// Name
		result += implementationName + "(";
		//Parameters
		String body = "";
		Type[] paramTypes = source.getGenericParameterTypes();
		if (paramTypes.length == 0 || Modifier.isPrivate(source.getModifiers())) {
			result += ")";
		} else {
			i = 0;
			body = "super(";
			for (Type t: paramTypes) {
				i++;
				String paramName = "param" + String.valueOf(i);    //any param name
				result += TypeUtils.stringValueOf(t) + " " + paramName;
				body += paramName;
				if (i < paramTypes.length) {
					result += ", ";
					body += ", ";
				}
			}
			body += ");";
			result += ")";
		}
		//Exceptions
		Type[] exceptions = source.getGenericExceptionTypes();
		if (exceptions.length > 0) {
			result += " throws ";
			for (Type e: exceptions) {
				result += e.getClass().getCanonicalName();
				if (e != exceptions[exceptions.length - 1]) {
					result += ", ";
				}
			}
		}
		result += "{ " + body + " }";
		return result;
	}
}
