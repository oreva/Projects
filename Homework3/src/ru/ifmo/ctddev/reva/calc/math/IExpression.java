package ru.ifmo.ctddev.reva.calc.math;

import ru.ifmo.ctddev.reva.calc.exceptions.EvaluationException;

public interface IExpression {
	long evaluate(long arg) throws EvaluationException;
}
