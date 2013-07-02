package ua.org.oreva.studyNN.runner;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 7/2/13
 * Time: 11:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class TaskRunnerRejectException extends Exception {
	public TaskRunnerRejectException() {
		super("Number of available threads has exceeded");
	}
}
