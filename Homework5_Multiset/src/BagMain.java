import collections.Bag;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 3/14/13
 * Time: 2:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class BagMain {
	public static void main(String[] args) {
		Collection<Integer> bag = new Bag<Integer>();
		bag.add(1);
		bag.add(5);
		bag.add(7);
		bag.add(8);
		bag.add(1);
		bag.add(12);
		bag.add(1);
		for (Integer i : bag) {
			System.out.print(i + ",");
		}
		// output: 1,1,1,5,7,8,12,
		System.out.println();
		for (Integer i : bag) {
			System.out.print(i + ",");
		}
		// output: 1,1,1,5,7,8,12,
	}
}
