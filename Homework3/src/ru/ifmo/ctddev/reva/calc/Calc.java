package ru.ifmo.ctddev.reva.calc;

import ru.ifmo.ctddev.reva.calc.exceptions.EvaluationException;
import ru.ifmo.ctddev.reva.calc.exceptions.IncorrectFuncBodyException;
import ru.ifmo.ctddev.reva.calc.math.AbstractOperation;
import ru.ifmo.ctddev.reva.calc.parsing.FunctionParser;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 2/7/13
 * Time: 2:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class Calc {
	public static void main(String[] args) {
		try {
			AbstractOperation f = FunctionParser.parseFunction(args[0], "x");
			calculate(f, 0, 10);
		} catch (IncorrectFuncBodyException e) {
			e.printStackTrace();

		}
	}

	private static void calculate(AbstractOperation f, int fromX, int toX) {
		System.out.println("x    f");
		for (int x = fromX; x <= toX; x++) {
			String result = "";
			try {
				result = String.valueOf(f.evaluate(x));
			} catch (EvaluationException e) {
				result = e.getMessage();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				System.out.println(String.valueOf(x) + "    " + result);
			}
		}
	}
}
