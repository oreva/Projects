package math;

public class Plus extends BinaryOperation {
	public Plus(IExpression expr1, IExpression expr2) {
		super(expr1, expr2);
	}

	public int operate(int value1, int value2) {
		return value1 + value2;
	}
}
