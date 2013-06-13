package ua.org.oreva.studyNN.client;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 5/31/13
 * Time: 12:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class NoTaskRunnerException extends RuntimeException {
	public NoTaskRunnerException() {
		super("Task runner was not set");
	}
}
