
import org.junit.Test;
import ru.ifmo.ctddev.reva.calc.math.Const;
import ru.ifmo.ctddev.reva.calc.math.Minus;
import ru.ifmo.ctddev.reva.calc.math.Variable;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 2/14/13
 * Time: 2:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class MinusTest {
	@Test
	public void testEvaluate() throws Exception {
		Const c = new Const(2);
		Variable v = new Variable("x");
		assertTrue(new Minus(c, v).evaluate(2) == 0);
	}
}
