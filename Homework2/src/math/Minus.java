package math;

public class Minus extends BinaryOperation {
	public Minus(IExpression expr1, IExpression expr2) {
		super(expr1, expr2);
	}

	public int operate(int value1, int value2) {
		return value1 - value2;
	}
}
