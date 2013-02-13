package ru.ifmo.ctddev.reva.calc.math;

import ru.ifmo.ctddev.reva.calc.exceptions.EvaluationException;

public class Minus extends BinaryOperation {
	public Minus(AbstractOperation op1, AbstractOperation op2) {
		super(op1, op2, OperationPriority.ADDITION);
	}

	public Minus() {
		super();
		priority = OperationPriority.ADDITION;
	}

	public long operate(long value1, long value2) throws EvaluationException {
		return value1 - value2;
	}
}
