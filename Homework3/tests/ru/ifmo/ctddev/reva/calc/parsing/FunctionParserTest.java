package ru.ifmo.ctddev.reva.calc.parsing;

import org.junit.Test;
import ru.ifmo.ctddev.reva.calc.exceptions.IncorrectFuncBodyException;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 2/19/13
 * Time: 3:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class FunctionParserTest {
	@Test(expected = IncorrectFuncBodyException.class)
	public void testParseFunction() throws Exception {
		FunctionParser.parseFunction("2*y-5", "x");
	}
}
