package collections;

import org.junit.Assert;
import org.junit.Ignore;
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
	@Test
	@Ignore
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

		bag.add(5);
		assertTrue(!bag.isEmpty());
		assertTrue(bag.add(5));
		assertTrue(!bag.isEmpty());
		assertEquals(2, bag.size());
	}

	@Test
	public void testIterator() throws Exception {
		Bag<Integer> bag = new Bag<Integer>();
		Iterator<Integer> iterator = bag.iterator();
		assertTrue(!iterator.hasNext());

		bag.add(5);
		iterator = bag.iterator();
		assertTrue(iterator.hasNext());
		assertEquals(5, iterator.next().intValue());
		iterator.remove();
		assertTrue(!iterator.hasNext());
		assertTrue(bag.isEmpty());
	}

	@Test
	public void testSize() throws Exception {
		Bag<Integer> bag = new Bag<Integer>();
		assertEquals(0, bag.size());
		bag.add(5);
		assertEquals(1, bag.size());
		bag.add(5);
		assertEquals(2, bag.size());
		bag.add(5);
		assertEquals(3, bag.size());
	}

	@Test
	public void testRemove() throws Exception {
		Bag<Integer> bag = new Bag<Integer>();
		assertTrue(bag.isEmpty());
		bag.add(5);
		assertTrue(!bag.isEmpty());
		bag.add(5);
		assertTrue(!bag.isEmpty());
		assertTrue(!bag.remove(0));
		assertTrue(bag.remove(5));
		assertTrue(!bag.isEmpty());
		assertTrue(bag.remove(5));
		assertTrue(!bag.remove(5));
	}

	@Test
	public void testContains() throws Exception {
		Bag<Integer> bag = new Bag<Integer>();
		assertTrue(!bag.contains(5));
		bag.add(5);
		assertTrue(bag.contains(5));
	}

	@Test
	public void testClear() throws Exception {
		Bag<Integer> bag = new Bag<Integer>();
		assertTrue(bag.isEmpty());
		bag.add(5);
		assertTrue(!bag.isEmpty());
		bag.clear();
		assertTrue(bag.isEmpty());
	}

	@Test
	public void testBagOrder() {
		Bag<Integer> bag = new Bag<Integer>();
		bag.add(2);
		bag.add(10);
		bag.add(2);
		Iterator<Integer> iterator = bag.iterator();
		int nextInt = iterator.next();
		assertEquals(2, nextInt);
		nextInt = iterator.next();
		assertEquals(2, nextInt);
		nextInt = iterator.next();
		assertEquals(10, nextInt);
	}
}
