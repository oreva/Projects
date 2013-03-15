package collections;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 3/14/13
 * Time: 9:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class LinkedBag<E> extends LinkedList<E> {
	/*protected LinkedList<E> innerMap;

	public LinkedBag() {
		innerMap = new LinkedList<E>();
	}

	@Override
	public Iterator<E> iterator() {
		return new BagIterator<E>();
	}

	private class BagIterator<T> implements Iterator<T> {
		private Iterator<E> innerMapIterator;

		public BagIterator() {
			innerMapIterator = innerMap().keySet().iterator();
		}

		@Override
		public boolean hasNext() {
			return innerMapIterator().hasNext();
		}

		@Override
		@SuppressWarnings("unchecked")
		public T next() {
			return (T)innerMapIterator().next();
		}

		@Override
		public void remove() {
			innerMapIterator().remove();
		}

		private Iterator<E> innerMapIterator() {
			return innerMapIterator;
		}
	}

	@Override
	public int size() {
		return innerMap().keySet().size();
	}

	protected AbstractMap<E, Integer> innerMap() {
		return innerMap;
	}

	@Override
	public boolean add(E e) {
		innerMap().put(e, 1);
		return true;
	} */
}
