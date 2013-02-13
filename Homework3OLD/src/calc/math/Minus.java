package calc.math;

public class Minus extends BinaryOperation {
	public Minus(AbstractOperation op1, AbstractOperation op2) {
		super(op1, op2, OperationPriority.ADDITION);
	}

	public Minus() {
		super();
		priority = OperationPriority.ADDITION;
	}

	public int operate(int value1, int value2) {
		return value1 - value2;
	}
}
