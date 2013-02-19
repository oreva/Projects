package ru.ifmo.ctddev.reva.calc.math;

import ru.ifmo.ctddev.reva.calc.exceptions.EvaluationException;
import ru.ifmo.ctddev.reva.calc.exceptions.MathOperationException;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 2/11/13
 * Time: 3:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class Brackets extends AbstractOperation {
	private AbstractOperation innerOperation;

	public Brackets(AbstractOperation innerOperation) {
		super();
		this.innerOperation = innerOperation;
		this.priority = OperationPriority.BRACKETS;
	}

	@Override
	public boolean canAddInnerOperation() {
		return (innerOperation == null);
	}

	@Override
	public void addInnerOperation(AbstractOperation op) {
		if (canAddInnerOperation()) {
			setInnerOperation(op);
		} else {
			throw new MathOperationException("Brackets cannot have more than one inner operation");
		}
	}

	@Override
	public void removeLastInnerOperation() {
		setInnerOperation(null);
	}

	@Override
	public AbstractOperation getInnerOperation(int operationIndex) {
		if (operationIndex == 0) {
			return innerOperation;
		} else {
			throw new MathOperationException("Incorrect operationIndex " + String.valueOf(operationIndex) + " for Brackets");
		}
	}

	@Override
	public AbstractOperation getLastInnerOperation() {
		return getInnerOperation(0);
	}

	@Override
	public long evaluate(long arg) throws EvaluationException {
		return innerOperation.evaluate(arg);
	}

	public void setInnerOperation(AbstractOperation op) {
		innerOperation = op;
	}
}
