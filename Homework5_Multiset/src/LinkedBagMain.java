import collections.LinkedBag;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 3/14/13
 * Time: 2:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class LinkedBagMain {
	public static void main(String[] args) {
		Collection<Integer> bag = new LinkedBag<Integer>();
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
		// output: 1,5,7,8,1,12,1,
		System.out.println();
		for (Integer i : bag) {
			System.out.print(i + ",");
		}
		// output: 1,5,7,8,1,12,1,
	}
}
