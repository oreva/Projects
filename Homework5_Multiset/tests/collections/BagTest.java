package collections;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 3/16/13
 * Time: 12:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class BagTest {
	@org.junit.Test
	public void testAdd() throws Exception {
		Bag<Integer> bag = new Bag<Integer>();
		bag.add(5);
		bag.remove(5);
		assertTrue(bag.isEmpty());
	}

	@org.junit.Test
	public void testRemove() throws Exception {
		Bag<Integer> bag = new Bag<Integer>();
		assertTrue(!bag.remove(5));
	}
}
