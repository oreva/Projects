package calc.math;

import ru.ifmo.ctddev.reva.calc.exceptions.MathOperationException;

public abstract class BinaryOperation extends AbstractOperation {
	private static int NUM_OF_OPERANDS = 2;
	protected AbstractOperation[] operands = new AbstractOperation[NUM_OF_OPERANDS];

	public BinaryOperation() {
		super();
	}

	public BinaryOperation(AbstractOperation op1, AbstractOperation op2) {
		addInnerOperation(op1);
		addInnerOperation(op2);
	}

	public BinaryOperation(AbstractOperation op1, AbstractOperation op2, OperationPriority priority) {
		this(op1, op2);
		this.priority = priority;
	}

	public int evaluate(int arg) {
		return operate(getInnerOperation(0).evaluate(arg), getInnerOperation(1).evaluate(arg));
	}

	protected abstract int operate(int value1, int value2);

	@Override
	public boolean canAddInnerOperation() {
		return (operands.length < NUM_OF_OPERANDS);
	}

	@Override
	public void addInnerOperation(AbstractOperation op) throws MathOperationException {
		if (!canAddInnerOperation()) {
			throw new MathOperationException("BinaryOperation cannot have more than two operands");
		}
		operands[operands.length - 1] = op;
	}

	@Override
	public void removeLastInnerOperation() throws MathOperationException {
		if (operands.length > 0) {
			AbstractOperation[] newOperands = new AbstractOperation[NUM_OF_OPERANDS];
			for (int i = 0; i < operands.length - 1; i++) {
				newOperands[i] = operands[i];
			}
			operands = newOperands;
		}
	}

	@Override
	public AbstractOperation getInnerOperation(int operationIndex) throws MathOperationException {
		if (!canAddInnerOperation() || operationIndex < 0) {
			throw new MathOperationException("Illegal operation index " + String.valueOf(operationIndex) + "for BinaryOperation");
		}
		return operands[operationIndex];
	}

	@Override
	public AbstractOperation getLastInnerOperation() throws MathOperationException {
		return getInnerOperation(operands.length - 1);
	}
}
