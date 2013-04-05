package implementor;

import util.TypeUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 4/1/13
 * Time: 6:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClassImplementorGeneric implements IImplementor {
	private Class source;
	private String name;
	private Package pack;

	public ClassImplementorGeneric(Class source, String implementationName, Package implementationPackage) {
		this.source = source;
		this.name = implementationName;
		this.pack = implementationPackage;
	}

	/**
	 * Creates new realization of the source with the given name
	 */
	public String implement() {
		//Package
		String result = (pack != null) ? "package " + pack.getName() + ";\n" : "";
		//Class params
		TypeVariable[] tv = source.getTypeParameters();
		String genericTypeString = "";
		if (tv.length > 0) {
			genericTypeString += "<";
			for (int i = 0; i < tv.length; i++) {
				genericTypeString += TypeUtils.stringValueOf(tv[i]);
				if (i < tv.length - 1) {
					genericTypeString += ", ";
				}
			}
			genericTypeString += ">";
		}
		// Class name
		result += "public class " + name + genericTypeString;
		if (source.isInterface()) {
			result += " implements ";
		} else {
			result += " extends ";
		}
		result += source.getName() + genericTypeString + " {\n";
		//Constructors
		result += implementConstructors();
		//Methods
		result += implementMethods();
		//End of the class
		result += "}";
		return result;
	}

	private String implementConstructors() {
		String result = "";
		Constructor[] constructors = source.getDeclaredConstructors();
		for (Constructor c: constructors) {
			result += new ConstructorImplementorGeneric(c, name).implement() + "\n";
		}
		result += "\n";
		return result;
	}

	private String implementMethods() {
		String result = "";
		Method[] methods = source.getDeclaredMethods();
		for (Method m: methods) {
			result += new MethodImplementorGeneric(m).implement() + "\n";
		}
		result += "\n";
		return result;
	}
}
