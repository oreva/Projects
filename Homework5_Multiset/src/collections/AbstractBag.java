package collections;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 3/12/13
 * Time: 2:16 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractBag<E> extends AbstractCollection<E> {
	//protected AbstractMap<Integer, ArrayList<E>> innerMap;
	protected AbstractMap<E, ArrayList<E>> innerMap;

	@Override
	public Iterator<E> iterator() {
		return new BagIterator<E>();
	}

	private class BagIterator<T> implements Iterator<T> {
		//private Iterator<Integer> innerMapIterator;
		private Iterator<E> innerMapIterator;
		private Iterator<T> innerListIterator = null;

		public BagIterator() {
			innerMapIterator = innerMap().keySet().iterator();
		}

		@Override
		public boolean hasNext() {
			return innerListIterator().hasNext();
		}

		@Override
		public T next() {
			return innerListIterator().next();
		}

		@Override
		public void remove() {
			innerListIterator().remove();
		}

		private Iterator<E> innerMapIterator() {
			return innerMapIterator;
		}

		@SuppressWarnings("unchecked")
		private Iterator<T> innerListIterator() {
			if ((innerListIterator == null || !innerListIterator.hasNext()) && innerMapIterator().hasNext()) {
				innerListIterator = (Iterator<T>)innerMap().get(innerMapIterator().next()).iterator();
			}
			return innerListIterator;
		}
	}

	@Override
	public int size() {
		int size = 0;
		/*for (Map.Entry<Integer, ArrayList<E>> entry: innerMap().entrySet()) {
			size += entry.getValue().size();
		} */
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
		/*if (!innerMap().containsKey(e.hashCode())) {
			innerMap().put(e.hashCode(), new ArrayList<E>());
		}
		return innerMap().get(e.hashCode()).add(e);*/
		if (!innerMap().containsKey(e)) {
			innerMap().put(e, new ArrayList<E>());
		}
		return innerMap().get(e).add(e);
	}
}
