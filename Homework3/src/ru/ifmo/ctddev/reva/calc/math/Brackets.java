package ru.ifmo.ctddev.reva.calc.math;

import ru.ifmo.ctddev.reva.calc.exceptions.EvaluationException;
import ru.ifmo.ctddev.reva.calc.exceptions.IncorrectFuncBodyException;
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
	private String varName;

	public Brackets(String varName) {
		super();
		this.varName = varName;
		this.priority = OperationPriority.BRACKETS;
	}
	public Brackets(String varName, String body) throws IncorrectFuncBodyException {
		this(varName);
		parse(body);
		if (innerOperation == null) {
			throw new IncorrectFuncBodyException("Brackets cannot be empty");
		}
	}

	@Override
	public boolean canAddInnerOperation() {
		return (innerOperation == null);
	}

	@Override
	public void addInnerOperation(AbstractOperation op) throws MathOperationException {
		if (canAddInnerOperation()) {
			setInnerOperation(op);
		} else {
			throw new MathOperationException("Brackets cannot have more than one inner operation");
		}
	}

	@Override
	public void removeLastInnerOperation() throws MathOperationException {
		setInnerOperation(null);
	}

	@Override
	public AbstractOperation getInnerOperation(int operationIndex) throws MathOperationException {
		if (operationIndex == 0) {
			return innerOperation;
		} else {
			throw new MathOperationException("Incorrect operationIndex " + String.valueOf(operationIndex) + " for Brackets");
		}
	}

	@Override
	public AbstractOperation getLastInnerOperation() throws MathOperationException {
		return getInnerOperation(0);
	}

	@Override
	public long evaluate(long arg) throws EvaluationException {
		return innerOperation.evaluate(arg);
	}

	public void setInnerOperation(AbstractOperation op) {
		innerOperation = op;
	}

	private void parse(String body) throws IncorrectFuncBodyException {
		String newBody = body.replaceAll("\\s+", "");
		char[] bodyArray = newBody.toCharArray();
		String number = "";
		String variable = "";

		int i = 0;
		while (i < bodyArray.length) {
			char ch = bodyArray[i];
			String s = String.valueOf(ch);
			// Handle number
			if (s.matches("[0-9]")) {
				number += s;
				if (i < bodyArray.length - 1) {
					i++;
					continue;
				} else {
					updateInnerOperation(new Const(Integer.valueOf(number)));
				}
				break;
			}
			if (!number.isEmpty()) {
				updateInnerOperation(new Const(Integer.valueOf(number)));
				number = "";
			}
			//Handle variable
			if (s.matches("[a-zA-Z]")) {
				variable += s;
				if (i < bodyArray.length - 1) {
					i++;
					continue;
				} else {
					updateInnerOperationWithVariable(variable);
				}
				break;
			}
			if (!variable.isEmpty()) {
				updateInnerOperationWithVariable(variable);
				variable = "";
			}
			//Handle operations
			switch (ch) {
				case '+':
					updateInnerOperation(new Plus());
					break;
				case '-':
					updateInnerOperation(new Minus());
					break;
				case '*':
					updateInnerOperation(new Times());
					break;
				case '/':
					updateInnerOperation(new Division());
					break;
				case '(':
					String innerBodyString = "";
					int numOfBrackets = 1;
					boolean innerFunctionProceeded = false;
					for (int j = i + 1; j < bodyArray.length; j++) {
						char nextCh = bodyArray[j];
						if (nextCh == '(') {
							numOfBrackets++;
						} else if (nextCh == ')') {
							numOfBrackets--;
							if (numOfBrackets == 0) {
								updateInnerOperation(new Brackets(varName, innerBodyString));
								i = j + 1;
								innerFunctionProceeded = true;
								break;
							}
						}
						innerBodyString += String.valueOf(nextCh);
					}
					if (innerFunctionProceeded) {
						continue;
					} else {
						throw new IncorrectFuncBodyException("Opened bracket at position " + String.valueOf(i) + " should be closed");
					}
				case ')':
					throw new IncorrectFuncBodyException("Closed bracket at position " + String.valueOf(i) + " should have an opened bracket before it");
				default:
					throw new IncorrectFuncBodyException("Illegal symbol " + s + " at position " + String.valueOf(i));
			}
			i++;
		}
	}

	private void updateInnerOperationWithVariable(String variableName) throws IncorrectFuncBodyException {
		if (varName.equals(variableName)) {
			updateInnerOperation(new Variable(varName));
		} else {
			throw new IncorrectFuncBodyException("Unknown variable name " + variableName);
		}
	}

	private void updateInnerOperation(AbstractOperation source) {
		if (innerOperation != null) {
			setInnerOperation(aggregateOperation(source, innerOperation));
		} else {
			setInnerOperation(source);
		}
	}

	private AbstractOperation aggregateOperation(AbstractOperation source, AbstractOperation target) {
		AbstractOperation newTarget = target;
		AbstractOperation newSource = source;
		if (target.priority.ordinal() >= source.priority.ordinal()) {
			newTarget = source;
			newSource = target;
		}

		if (newTarget.canAddInnerOperation()) {
			newTarget.addInnerOperation(newSource);
		} else {
			AbstractOperation inner = newTarget.getLastInnerOperation();
			newTarget.removeLastInnerOperation();
			newTarget.addInnerOperation(aggregateOperation(newSource, inner));
		}
		return newTarget;
	}
}
