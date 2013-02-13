package calc.math;

import ru.ifmo.ctddev.reva.calc.exceptions.MathOperationException;

public class Variable extends AbstractOperation {
	private String varName;

	private Variable() {
		super();
		this.priority = OperationPriority.CONST;
	}

	public Variable(String varName) {
		new Variable();
		this.varName = varName;
	}

	public int evaluate(int arg) {
		return arg;
	}

	@Override
	public boolean canAddInnerOperation() {
		return false;
	}

	@Override
	public void addInnerOperation(AbstractOperation op) throws MathOperationException {
		throw new MathOperationException("Variable cannot have inner operations");
	}

	@Override
	public void removeLastInnerOperation() throws MathOperationException {
		throw new MathOperationException("Variable cannot have inner operations");
	}

	@Override
	public AbstractOperation getInnerOperation(int operationIndex) throws MathOperationException {
		throw new MathOperationException("Variable cannot have inner operations");
	}

	@Override
	public AbstractOperation getLastInnerOperation() throws MathOperationException {
		throw new MathOperationException("Variable cannot have inner operations");
	}
}
