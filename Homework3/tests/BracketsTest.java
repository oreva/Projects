import org.junit.Test;
import ru.ifmo.ctddev.reva.calc.math.Brackets;

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
		assertTrue(new Brackets("x", "x*(2+2)").evaluate(2) == 8);
	}
}
