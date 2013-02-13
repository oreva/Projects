package calc;

import ru.ifmo.ctddev.reva.calc.exceptions.IncorrectFuncBodyException;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 2/7/13
 * Time: 2:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class Calc {
	public static void main(String[] args) {
		Func f = new Func(args[0], "x");
		// Parse f
		try {
			f.parse();
		} catch (IncorrectFuncBodyException e) {
			System.out.println(e.getMessage());
		}
		// Calculate
		calculate(f, 0, 10);
	}

	private static void calculate(Func f, int fromX, int toX) {
		for (int x = fromX; x <= toX; x++) {
			String resultMessage;
			try {
				int result = f.evaluate(x);
				resultMessage = String.valueOf(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
