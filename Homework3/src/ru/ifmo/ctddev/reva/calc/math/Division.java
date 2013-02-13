package ru.ifmo.ctddev.reva.calc.math;

import ru.ifmo.ctddev.reva.calc.exceptions.EvaluationException;

public class Division extends BinaryOperation {
	public Division(AbstractOperation op1, AbstractOperation op2) {
		super(op1, op2, OperationPriority.MULTIPLICATION);
	}

	public Division() {
		super();
		priority = OperationPriority.MULTIPLICATION;
	}

	public long operate(long value1, long value2) throws EvaluationException {
		if (value2 == 0) {
			throw new EvaluationException("division by 0");
		}
		return value1 / value2;
	}
}
