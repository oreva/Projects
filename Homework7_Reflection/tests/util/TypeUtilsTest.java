package util;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 4/5/13
 * Time: 3:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class TypeUtilsTest {
	@Test
	public void testGetWrapperType() throws Exception {
		assertEquals(Integer.class, TypeUtils.getWrapperType(int.class));
		assertEquals(Boolean.class, TypeUtils.getWrapperType(boolean.class));
		assertEquals(Character.class, TypeUtils.getWrapperType(char.class));
		assertEquals(Float.class, TypeUtils.getWrapperType(float.class));
		assertEquals(Double.class, TypeUtils.getWrapperType(double.class));
		assertEquals(Long.class, TypeUtils.getWrapperType(long.class));
	}

	@Test
	public void testStringValueOf() throws Exception {
		Class c = Object.class;
		assertEquals("java.lang.Object", TypeUtils.stringValueOf(c));
		c = int.class;
		assertEquals("int", TypeUtils.stringValueOf(c));
		c = TestClass.class;
		Constructor constructor = c.getConstructors()[0];
		Type[] t = constructor.getGenericParameterTypes();
		assertEquals("java.util.List<? super java.lang.String>".replaceAll(" ", "").replaceAll("\\n", ""),
				TypeUtils.stringValueOf(t[0]).replaceAll(" ", "").replaceAll("\\n", ""));
	}

	class TestClass {
		public TestClass(List<? super String> s) {}
	}

}
