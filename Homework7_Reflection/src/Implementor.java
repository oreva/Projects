import implementor.ClassImplementor;

import java.io.FileWriter;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 3/31/13
 * Time: 6:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class Implementor {
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Class or interface name is required to implement it.");
			System.exit(0);
		}
		//Directory to save files
		String dir = System.getProperty("user.dir");
		if (args.length > 1) {
			dir = args[1];
		}
		String className = args[0];
		try {
			Class c = Class.forName(className);
			String newClassCode = new ClassImplementor(c, c.getSimpleName() + "ImplGeneric", c.getPackage()).implement();

			try {
				FileWriter writer = new FileWriter(dir + "\\" + c.getSimpleName() + "ImplGeneric.java");
				writer.write(newClassCode);
				writer.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}


}
