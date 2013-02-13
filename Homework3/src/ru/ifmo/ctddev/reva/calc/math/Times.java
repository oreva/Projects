package ru.ifmo.ctddev.reva.calc.math;

import ru.ifmo.ctddev.reva.calc.exceptions.EvaluationException;

public class Times extends BinaryOperation {
	public Times(AbstractOperation op1, AbstractOperation op2) {
		super(op1, op2, OperationPriority.MULTIPLICATION);
	}

	public Times() {
		super();
		priority = OperationPriority.MULTIPLICATION;
	}

	public long operate(long value1, long value2) throws EvaluationException {
		return value1 * value2;
	}
}
