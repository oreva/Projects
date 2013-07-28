package ua.org.oreva.studyNN.runner;

import ua.org.oreva.studyNN.task.Task;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 5/31/13
 * Time: 11:48 AM
 * To change this template use File | Settings | File Templates.
 */
public interface TaskRunner {
	<X, Y> X run(Task<X, Y> task, Y value);
	void shutdownNow();
}
