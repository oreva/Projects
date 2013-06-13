package ua.org.oreva.studyNN.client;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 6/3/13
 * Time: 5:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class NoTaskFactoryException extends RuntimeException {
	public NoTaskFactoryException() {
		super("Task factory was not set");
	}
}
