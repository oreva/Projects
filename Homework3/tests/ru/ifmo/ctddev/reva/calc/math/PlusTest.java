package ru.ifmo.ctddev.reva.calc.math;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 2/14/13
 * Time: 1:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlusTest {
	@org.junit.Test
	public void testEvaluate() throws Exception {
		Const c = new Const(2);
		Variable v = new Variable("x");
		assertTrue(new Plus(c, v).evaluate(2) == 4);
	}
}
