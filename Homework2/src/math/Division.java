package math;

public class Division extends BinaryOperation {
	public Division(IExpression expr1, IExpression expr2) {
		super(expr1, expr2);
	}

	public int operate(int value1, int value2) {
		return value1 / value2;
	}
}
