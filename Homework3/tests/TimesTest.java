
import org.junit.Test;
import ru.ifmo.ctddev.reva.calc.math.Const;
import ru.ifmo.ctddev.reva.calc.math.Times;
import ru.ifmo.ctddev.reva.calc.math.Variable;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 2/14/13
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class TimesTest {
	@Test
	public void testEvaluate() throws Exception {
		Const c = new Const(2);
		Variable v = new Variable("x");
		assertTrue(new Times(c, v).evaluate(2) == 4);
	}
}
