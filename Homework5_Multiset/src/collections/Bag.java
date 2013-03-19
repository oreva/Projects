package collections;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 3/14/13
 * Time: 9:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class Bag<E> extends AbstractCollection<E> {
	protected AbstractMap<E, ArrayList<E>> innerMap;

	public Bag() {
		innerMap = new HashMap<E, ArrayList<E>>();
	}

	@Override
	public Iterator<E> iterator() {
		return new BagIterator<E>();
	}

	private class BagIterator<T> implements Iterator<T> {
		private Iterator<E> innerMapIterator = innerMap().keySet().iterator();
		private Iterator<T> innerListIterator = null;
		private ArrayList<E> currentInnerList = null;

		@Override
		public boolean hasNext() {
			return (innerListIterator() != null) && innerListIterator().hasNext();
		}

		@Override
		public T next() {
			return (innerListIterator() != null) ? innerListIterator().next() : null;
		}

		@Override
		public void remove() {
			innerListIterator().remove();
			if (currentInnerList.isEmpty()) {
				innerMapIterator().remove();
				innerListIterator = null;
			}
		}

		private Iterator<E> innerMapIterator() {
			return innerMapIterator;
		}

		@SuppressWarnings("unchecked")
		private Iterator<T> innerListIterator() {
			if ((innerListIterator == null || !innerListIterator.hasNext()) && innerMapIterator().hasNext()) {
				currentInnerList = innerMap().get(innerMapIterator().next());
				innerListIterator = (Iterator<T>)currentInnerList.iterator();
			}
			return innerListIterator;
		}
	}

	@Override
	public int size() {
		int size = 0;
		for (Map.Entry<E, ArrayList<E>> entry: innerMap().entrySet()) {
			size += entry.getValue().size();
		}
		return size;
	}

	protected AbstractMap<E, ArrayList<E>> innerMap() {
		return innerMap;
	}

	@Override
	public boolean add(E e) {
		if (!innerMap().containsKey(e)) {
			innerMap().put(e, new ArrayList<E>());
		}
		return innerMap().get(e).add(e);
	}

	@Override
	public boolean remove(Object o) {
		if (innerMap().containsKey(o)) {
			ArrayList<E> valueList = innerMap().get(o);
			valueList.remove(0);
			if (valueList.isEmpty()) {
				innerMap().remove(o);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean contains(Object o) {
		return innerMap().containsKey(o);
	}

	@Override
	public void clear() {
		innerMap().clear();
	}
}
