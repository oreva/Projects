package ru.ifmo.ctddev.reva.calc.math;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 2/14/13
 * Time: 2:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class DivisionTest {
	@Test
	public void testEvaluate() throws Exception {
		Const c = new Const(2);
		Variable v = new Variable("x");
		assertTrue(new Division(c, v).evaluate(2) == 1);
	}
}
