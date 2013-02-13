package ru.ifmo.ctddev.reva.calc.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 2/11/13
 * Time: 5:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class MathOperationException extends RuntimeException {
	public MathOperationException(String message) {
		super(message);
	}
}
