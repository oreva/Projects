package ua.org.oreva.studyNN.task;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 5/31/13
 * Time: 11:57 AM
 * To change this template use File | Settings | File Templates.
 */
public interface TaskFactory<X, Y> {
	Task<X, Y> generateTask();
	Task<X, Y> generateTaskWithName(String name);
}
