package ru.ifmo.ctddev.reva.calc.math;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 2/14/13
 * Time: 2:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class BracketsTest {
	@Test
	public void testEvaluate() throws Exception {
		assertTrue(new Brackets(new Plus(new Const(1), new Variable("x"))).evaluate(2) == 3);
	}
}
