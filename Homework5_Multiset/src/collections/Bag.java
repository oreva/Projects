package collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 3/14/13
 * Time: 9:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class Bag<E> extends AbstractBag<E> {
	public Bag() {
		innerMap = new TreeMap<E, ArrayList<E>>();
	}
}
