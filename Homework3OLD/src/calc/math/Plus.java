package calc.math;

public class Plus extends BinaryOperation {
	public Plus(AbstractOperation op1, AbstractOperation op2) {
		super(op1, op2, OperationPriority.ADDITION);
	}

	public Plus() {
		super();
		priority = OperationPriority.ADDITION;
	}

	public int operate(int value1, int value2) {
		return value1 + value2;
	}
}
