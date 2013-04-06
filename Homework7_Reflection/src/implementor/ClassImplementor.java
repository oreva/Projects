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
public class ClassImplementor implements IImplementor {
	private Class source;
	private String name;
	private Package pack;

	public ClassImplementor(Class source, String implementationName, Package implementationPackage) {
		this.source = source;
		this.name = implementationName;
		this.pack = implementationPackage;
	}

	/**
	 * Creates new realization of the source with the given name
	 */
	public String implement() {
		//Package
		StringBuilder result = new StringBuilder();
		if (pack != null) {
			result.append("package ");
			result.append(pack.getName());
			result.append(";\n");
		}
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
		result.append("public class ");
		result.append(name);
		result.append(genericTypeString);
		if (source.isInterface()) {
			result.append(" implements ");
		} else {
			result.append(" extends ");
		}
		result.append(source.getName());
		result.append(genericTypeString);
		result.append(" {\n");
		//Constructors
		result.append(implementConstructors());
		//Methods
		result.append(implementMethods());
		//End of the class
		result.append("}");
		return result.toString().trim();
	}

	private String implementConstructors() {
		StringBuilder result = new StringBuilder();
		Constructor[] constructors = source.getDeclaredConstructors();
		for (Constructor c: constructors) {
			result.append(new ConstructorImplementor(c, name).implement());
			result.append("\n");
		}
		result.append("\n");
		return result.toString().trim();
	}

	private String implementMethods() {
		StringBuilder result = new StringBuilder();
		Method[] methods = source.getDeclaredMethods();
		for (Method m: methods) {
			result.append(new MethodImplementor(m).implement());
			result.append("\n");
		}
		result.append("\n");
		return result.toString().trim();
	}
}
