package calc.math;

import ru.ifmo.ctddev.reva.calc.exceptions.MathOperationException;

public class Const extends AbstractOperation {
	private int value;

	public Const(int value) {
		this.value = value;
		this.priority = OperationPriority.CONST;
	}
	public int evaluate(int arg) {
		return value;
	}

	@Override
	public boolean canAddInnerOperation() {
		return false;
	}

	@Override
	public void addInnerOperation(AbstractOperation op) throws MathOperationException {
		throw new MathOperationException("Const cannot have inner operations");
	}

	@Override
	public void removeLastInnerOperation() throws MathOperationException {
		throw new MathOperationException("Const cannot have inner operations");
	}

	@Override
	public AbstractOperation getInnerOperation(int operationIndex) throws MathOperationException {
		throw new MathOperationException("Const cannot have inner operations");
	}

	@Override
	public AbstractOperation getLastInnerOperation() throws MathOperationException {
		throw new MathOperationException("Const cannot have inner operations");
	}
}
