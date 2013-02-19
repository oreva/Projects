package ru.ifmo.ctddev.reva.calc.math;

import ru.ifmo.ctddev.reva.calc.exceptions.MathOperationException;

public class Variable extends AbstractOperation {
	private String varName;

	private Variable() {
		super();
		this.priority = OperationPriority.CONST;
	}

	public Variable(String varName) {
		this();
		this.varName = varName;
	}

	public long evaluate(long arg) {
		return arg;
	}

	@Override
	public boolean canAddInnerOperation() {
		return false;
	}

	@Override
	public void addInnerOperation(AbstractOperation op) {
		throw new MathOperationException("Variable cannot have inner operations");
	}

	@Override
	public void removeLastInnerOperation() {
		throw new MathOperationException("Variable cannot have inner operations");
	}

	@Override
	public AbstractOperation getInnerOperation(int operationIndex) {
		throw new MathOperationException("Variable cannot have inner operations");
	}

	@Override
	public AbstractOperation getLastInnerOperation() {
		throw new MathOperationException("Variable cannot have inner operations");
	}
}
