package ua.org.oreva.studyNN.util;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 5/19/13
 * Time: 9:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class SmallContentReaderTest {
	@Test
	public void testReadAll() throws Exception {
		StringReader strReader = new StringReader(
				"test \n string \n reader"
		);
		SmallContentReader reader = new SmallContentReader(new BufferedReader(strReader));
		assertEquals("test \n string \n reader", reader.readAll());
	}
}
