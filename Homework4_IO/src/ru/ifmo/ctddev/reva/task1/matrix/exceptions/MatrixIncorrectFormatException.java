package ru.ifmo.ctddev.reva.task1.matrix.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 2/25/13
 * Time: 2:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class MatrixIncorrectFormatException extends Exception {
	public MatrixIncorrectFormatException(String message) {
		super(message);
	}

	public MatrixIncorrectFormatException(String message, Throwable cause) {
		this(message);
		this.initCause(cause);
	}
}
