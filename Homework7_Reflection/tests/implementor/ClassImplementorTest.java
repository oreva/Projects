package implementor;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 4/1/13
 * Time: 6:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClassImplementorTest {


	@Test
	public void testImplement() throws Exception {
		Class c = Class.forName("examples.UnboundedWildcards");
		ClassImplementorGeneric ci = new ClassImplementorGeneric(c, "UnboundedWildcards", c.getPackage());
		//ci.callGenericMethods();
		String strImplementation = ci.implement();
		System.out.println(strImplementation);
	}
}
