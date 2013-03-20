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
	public void testSameElementsOrder() {
		Bag<String> bag = new Bag<String>();
		String first = "first";
		String second = "second";
		String third = "third";
		bag.add(first);
		bag.add(second);
		bag.add(third);
		bag.add(first);
		bag.add(third);
		bag.add(second);
		bag.add(second);
		Iterator<String> iterator = bag.iterator();
		System.out.println("testSameElementsOrder:: order of the elements:");
		while (iterator.hasNext()) {
			String next = iterator.next();
			System.out.println(next);
			if (next.equals("first")) {
				assertEquals(next, iterator.next());
			} else if (next.equals("second")) {
				assertEquals(next, iterator.next());
				assertEquals(next, iterator.next());
			} else if (next.equals("third")) {
				assertEquals(next, iterator.next());
			}
		}
	}

	@Test
	public void testEqualElementsOrder() {
		class BagElement {
			private int i;

			public BagElement(int i) {
				this.i = i;
			}

			public int i() {
				return i;
			}

			@Override
			public boolean equals(Object obj) {
				return (obj instanceof BagElement) && ((BagElement) obj).i() == i;
			}

			@Override
			public int hashCode() {
				return i;
			}
		}

		Bag<BagElement> bag = new Bag<BagElement>();
		BagElement element1 = new BagElement(1);
		BagElement element2 = new BagElement(1);
		BagElement element3 = new BagElement(2);
		bag.add(element1);
		bag.add(element3);
		bag.add(element2);
		Iterator<BagElement> iterator = bag.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().equals(element1)) {
				assertTrue(element1.equals(iterator.next()));
				break;
			}
		}
	}
}
