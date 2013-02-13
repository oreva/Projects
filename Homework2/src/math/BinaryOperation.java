package math;

public abstract class BinaryOperation implements IExpression {
	protected IExpression operand1;
	protected IExpression operand2;

	public BinaryOperation(IExpression expr1, IExpression expr2) {
		operand1 = expr1;
		operand2 = expr2;
	}

	public int evaluate(int arg) {
		return operate(operand1.evaluate(arg), operand2.evaluate(arg));
	}

	protected abstract int operate(int value1, int value2);
}
