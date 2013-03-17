package collections;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 3/14/13
 * Time: 9:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class LinkedBag<E> extends AbstractCollection<E> {
	protected LinkedList<E> dataList;

	public LinkedBag() {
		dataList = new LinkedList<E>();
	}

	@Override
	public Iterator<E> iterator() {
		return dataList.iterator();
	}

	@Override
	public int size() {
		return dataList.size();
	}

	@Override
	public boolean add(E e) {
		return dataList.add(e);
	}
}
