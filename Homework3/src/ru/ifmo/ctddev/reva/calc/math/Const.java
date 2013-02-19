package ru.ifmo.ctddev.reva.calc.math;

import ru.ifmo.ctddev.reva.calc.exceptions.MathOperationException;

public class Const extends AbstractOperation {
	private int value;

	public Const(int value) {
		this.value = value;
		this.priority = OperationPriority.CONST;
	}
	public long evaluate(long arg) {
		return value;
	}

	@Override
	public boolean canAddInnerOperation() {
		return false;
	}

	@Override
	public void addInnerOperation(AbstractOperation op) {
		throw new MathOperationException("Const cannot have inner operations");
	}

	@Override
	public void removeLastInnerOperation() {
		throw new MathOperationException("Const cannot have inner operations");
	}

	@Override
	public AbstractOperation getInnerOperation(int operationIndex) {
		throw new MathOperationException("Const cannot have inner operations");
	}

	@Override
	public AbstractOperation getLastInnerOperation() {
		throw new MathOperationException("Const cannot have inner operations");
	}
}
