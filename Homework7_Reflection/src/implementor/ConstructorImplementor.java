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
		StringBuilder result = new StringBuilder(oldConstructor.substring(0, i));
		// Name
		result.append(implementationName);
		result.append("(");
		//Parameters
		String body = "";
		Type[] paramTypes = source.getGenericParameterTypes();
		if (paramTypes.length == 0 || Modifier.isPrivate(source.getModifiers())) {
			result.append(")");
		} else {
			i = 0;
			body = "super(";
			for (Type t: paramTypes) {
				i++;
				String paramName = "param" + String.valueOf(i);    //any param name
				result.append(TypeUtils.stringValueOf(t) + " " + paramName);
				body += paramName;
				if (i < paramTypes.length) {
					result.append(", ");
					body += ", ";
				}
			}
			body += ");";
			result.append(")");
		}
		//Exceptions
		Type[] exceptions = source.getGenericExceptionTypes();
		if (exceptions.length > 0) {
			result.append(" throws ");
			for (Type e: exceptions) {
				result.append(TypeUtils.stringValueOf(e));
				if (e != exceptions[exceptions.length - 1]) {
					result.append(", ");
				}
			}
		}
		result.append("{ " + body + " }");
		return result.toString().trim();
	}
}
