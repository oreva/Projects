package calc.math;

public class Times extends BinaryOperation {
	public Times(AbstractOperation op1, AbstractOperation op2) {
		super(op1, op2, OperationPriority.MULTIPLICATION);
	}

	public Times() {
		super();
		priority = OperationPriority.MULTIPLICATION;
	}

	public int operate(int value1, int value2) {
		return value1 * value2;
	}
}
