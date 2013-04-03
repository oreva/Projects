package implementor;

import java.lang.reflect.Constructor;

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
		String oldConstructor = source.toString();
		int i = oldConstructor.indexOf(source.getName());
		// Modifiers added
		String result = oldConstructor.substring(0, i);
		// Name
		result += implementationName + "(";
		//Parameters
		Class[] paramTypes = source.getParameterTypes();
		if (paramTypes.length == 0) {
			result += "){}";
		} else {
			i = 0;
			String body = "super(";
			for (Class t: paramTypes) {
				//TODO: generics here?
				i++;
				String paramName = "param" + String.valueOf(i);    //any param name
				result += t.getName() + " " + paramName;
				body += paramName;
				if (i < paramTypes.length) {
					result += ", ";
					body += ", ";
				}
			}
			body += ");";
			result += "){ " + body + " }";
		}
		return result;
	}
}
