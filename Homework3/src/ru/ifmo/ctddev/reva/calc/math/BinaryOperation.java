package ru.ifmo.ctddev.reva.calc.math;

import ru.ifmo.ctddev.reva.calc.exceptions.EvaluationException;
import ru.ifmo.ctddev.reva.calc.exceptions.MathOperationException;

import java.util.ArrayList;

public abstract class BinaryOperation extends AbstractOperation {
	private static int NUM_OF_OPERANDS = 2;
	protected ArrayList<AbstractOperation> operands = new ArrayList<AbstractOperation>(NUM_OF_OPERANDS);

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

	public long evaluate(long arg) throws EvaluationException {
		long result = operate(getInnerOperation(0).evaluate(arg), getInnerOperation(1).evaluate(arg));
		String s = String.valueOf(result);
		int intResult;
		try {
			intResult = Integer.parseInt(s);
		} catch (Exception e) {
			EvaluationException ev = new EvaluationException("overflow");
			ev.initCause(e);
			throw ev;
		}
		return intResult;
	}

	protected abstract long operate(long value1, long value2) throws EvaluationException;

	@Override
	public boolean canAddInnerOperation() {
		return (operands.size() < NUM_OF_OPERANDS);
	}

	@Override
	public void addInnerOperation(AbstractOperation op) {
		if (!canAddInnerOperation()) {
			throw new MathOperationException("BinaryOperation cannot have more than two operands");
		}
		operands.add(op);
	}

	@Override
	public void removeLastInnerOperation() {
		if (operands.size() > 0) {
			operands.remove(operands.size() - 1);
		}
	}

	@Override
	public AbstractOperation getInnerOperation(int operationIndex) {
		if (operationIndex < 0 || operationIndex >= NUM_OF_OPERANDS) {
			throw new MathOperationException("Illegal operation index " + String.valueOf(operationIndex) + "for BinaryOperation");
		}
		return operands.get(operationIndex);
	}

	@Override
	public AbstractOperation getLastInnerOperation() {
		return getInnerOperation(operands.size() - 1);
	}
}
