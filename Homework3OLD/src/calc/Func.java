package calc;

import ru.ifmo.ctddev.reva.calc.exceptions.IncorrectFuncBodyException;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 2/7/13
 * Time: 2:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class Func {
	private String body;
	private String varName;
	private BinaryOperation bodyOperation;

	public Func(String body, String varName) {
		this.body = body;
		this.varName = varName;
	}

	public void parse() throws IncorrectFuncBodyException {
		bodyOperation = parseStringBody(body);
	}

	private BinaryOperation parseStringBody(String body) throws IncorrectFuncBodyException {
		BinaryOperation result = null;
		char[] bodyArray = body.toCharArray();
		String number = "";
		String variable = "";

		int i = 0;
		while (i < bodyArray.length) {
			char ch = bodyArray[i];
			//Skip spaces
			if (ch == ' ') {
				i++;
				continue;
			}
			String s = String.valueOf(ch);
			// Handle number
			if (s.matches("[0-9]")) {
				number += s;
				if (i < bodyArray.length - 1) {
					i++;
					continue;
				} else {
					result = updateBodyOperation(new BinaryConst(Integer.valueOf(number)), result);
				}
				break;
			}
			if (!number.isEmpty()) {
				result = updateBodyOperation(new BinaryConst(Integer.valueOf(number)), result);
				number = "";
			}
			//Handle variable
			if (s.matches("[a-zA-Z]")) {
				variable += s;
				if (i < bodyArray.length - 1) {
					i++;
					continue;
				} else {
					if (varName.equals(variable)) {
						result = updateBodyOperation(new BinaryVariable(varName), result);
					} else {
						throw new IncorrectFuncBodyException("Unknown variable name " + variable);
					}
				}
				break;
			}
			if (!variable.isEmpty()) {
				if (varName.equals(variable)) {
					result = updateBodyOperation(new BinaryVariable(varName), result);
				} else {
					throw new IncorrectFuncBodyException("Unknown variable name " + variable);
				}
			}
			//Handle operations
			switch (ch) {
				case '+':
					result = updateBodyOperation(new Plus(), result);
					break;
				case '-':
					result = updateBodyOperation(new Minus(), result);
					break;
				case '*':
					result = updateBodyOperation(new Times(), result);
					break;
				case '/':
					result = updateBodyOperation(new Division(), result);
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
								result = updateBodyOperation(new Brackets(parseStringBody(innerBodyString)), result);
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
					throw new IncorrectFuncBodyException("Closed bracket at position " + String.valueOf(i) + " should have the opened bracket before it");
				default:
					throw new IncorrectFuncBodyException("Illegal symbol " + s + " at position " + String.valueOf(i));
			}
			i++;
		}
		return result;
	}

	private BinaryOperation updateBodyOperation(BinaryOperation source, BinaryOperation target) {
		if (target != null) {
			return aggregateOperation(source, target);
		}
		return source;
	}
	private BinaryOperation aggregateOperation(BinaryOperation source, BinaryOperation target) {
		if (target.priority.ordinal() < source.priority.ordinal()) {
			//TODO: re-do with oop without instanceof
			if (target.operand2() != null && target.operand2() instanceof BinaryOperation) {
				target.setOperand2(aggregateOperation(source, (BinaryOperation) target.operand2()));
			} else {
				target.setOperand2(source);
			}
			return target;
		}
		if (source.operand1() != null && source.operand1() instanceof BinaryOperation) {
			source.setOperand1(aggregateOperation(target, (BinaryOperation) source.operand1()));
		} else {
			source.setOperand1(target);
		}
		return source;
	}

	public int evaluate(int arg)  {
		return bodyOperation.evaluate(arg);
	}
}
