package collections;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 3/16/13
 * Time: 12:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class LinkedBagTest {
	@Test
	public void testAdd() throws Exception {
		LinkedBag<Integer> bag = new LinkedBag<Integer>();
		bag.add(5);
		bag.remove(5);
		assertTrue(!bag.contains(5));
	}

	@Test
	public void testRemove() throws Exception {
		LinkedBag<Integer> bag = new LinkedBag<Integer>();
		assertTrue(!bag.remove(5));
	}
}
