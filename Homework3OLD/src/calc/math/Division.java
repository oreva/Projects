package calc.math;

public class Division extends BinaryOperation {
	public Division(AbstractOperation op1, AbstractOperation op2) {
		super(op1, op2, OperationPriority.MULTIPLICATION);
	}

	public Division() {
		super();
		priority = OperationPriority.MULTIPLICATION;
	}

	public int operate(int value1, int value2) {
		return value1 / value2;
	}
}
