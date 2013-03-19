package collections;

import org.junit.Test;

import java.util.Iterator;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 3/16/13
 * Time: 12:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class BagTest {
	/*@org.junit.Test
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
	}   */

	@Test
	public void testMemoryLeak() {
		Bag<Integer> bag = new Bag<Integer>();
		assertEquals(0, bag.size());
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			bag.add(i);
			assertEquals(1, bag.size());
			Iterator<Integer> iterator = bag.iterator();
			assertTrue(iterator.hasNext());
			assertEquals(i, iterator.next().intValue());
			iterator.remove();
			assertEquals(0, bag.size());
		}
	}

	@Test
	public void testAdd() throws Exception {
		Bag<Integer> bag = new Bag<Integer>();
		bag.add(0);
		assertTrue(!bag.isEmpty());
		bag.remove(0);
		assertTrue(bag.isEmpty());
		bag.add(1);
		assertTrue(!bag.isEmpty());
		bag.remove(1);
		assertTrue(bag.isEmpty());
		bag.add(2);
		assertTrue(!bag.isEmpty());
		bag.remove(2);
		assertTrue(bag.isEmpty());
	}
}
