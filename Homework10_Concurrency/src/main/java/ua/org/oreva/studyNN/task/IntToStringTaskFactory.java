package ua.org.oreva.studyNN.task;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 6/13/13
 * Time: 6:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class IntToStringTaskFactory implements TaskFactory<String, Integer> {
	@Override
	public Task<String, Integer> generateTask() {
		return new IntToStringTask();
	}
}
