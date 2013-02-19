package ru.ifmo.ctddev.reva.calc.parsing;

import ru.ifmo.ctddev.reva.calc.exceptions.IncorrectFuncBodyException;
import ru.ifmo.ctddev.reva.calc.math.*;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 2/19/13
 * Time: 1:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class FunctionParser {
	public static AbstractOperation parseFunction(String functionBody, String variableName) throws IncorrectFuncBodyException {
		AbstractOperation result = null;
		String newBody = functionBody.replaceAll("\\s+", "");
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
					result = updateInnerOperation(new Const(Integer.valueOf(number)), result);
				}
				break;
			}
			if (!number.isEmpty()) {
				result = updateInnerOperation(new Const(Integer.valueOf(number)), result);
				number = "";
			}
			//Handle variable
			if (s.matches("[a-zA-Z]")) {
				variable += s;
				if (i < bodyArray.length - 1) {
					i++;
					continue;
				} else {
					result = aggregateVariable(variable, variableName, result);
				}
				break;
			}
			if (!variable.isEmpty()) {
				result = aggregateVariable(variable, variableName, result);
				variable = "";
			}
			//Handle operations
			switch (ch) {
				case '+':
					result = updateInnerOperation(new Plus(), result);
					break;
				case '-':
					result = updateInnerOperation(new Minus(), result);
					break;
				case '*':
					result = updateInnerOperation(new Times(), result);
					break;
				case '/':
					result = updateInnerOperation(new Division(), result);
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
								result = updateInnerOperation(new Brackets(parseFunction(innerBodyString, variableName)), result);
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
		return result;
	}

	private static AbstractOperation aggregateVariable(String varName, String actualVarName, AbstractOperation targetOperation)
			throws IncorrectFuncBodyException {
		if (varName.equals(actualVarName)) {
			targetOperation = updateInnerOperation(new Variable(actualVarName), targetOperation);
		} else {
			throw new IncorrectFuncBodyException("Unknown variable name " + varName);
		}
		return targetOperation;
	}

	private static AbstractOperation updateInnerOperation(AbstractOperation source, AbstractOperation target) {
		if (target != null) {
			target = doAggregateOperation(source, target);
		} else {
			target = source;
		}
		return target;
	}

	private static AbstractOperation doAggregateOperation(AbstractOperation source, AbstractOperation target) {
		AbstractOperation newTarget = target;
		AbstractOperation newSource = source;
		if (target.priority().ordinal() >= source.priority().ordinal()) {
			newTarget = source;
			newSource = target;
		}

		if (newTarget.canAddInnerOperation()) {
			newTarget.addInnerOperation(newSource);
		} else {
			AbstractOperation inner = newTarget.getLastInnerOperation();
			newTarget.removeLastInnerOperation();
			newTarget.addInnerOperation(doAggregateOperation(newSource, inner));
		}
		return newTarget;
	}
}
