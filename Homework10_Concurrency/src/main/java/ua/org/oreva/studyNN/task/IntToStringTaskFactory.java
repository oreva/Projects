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
		IntToStringTask t = new IntToStringTask(generateRandomTaskName());
		return t;
	}

	@Override
	public Task<String, Integer> generateTaskWithName(String name) {
		IntToStringTask t = new IntToStringTask(name);
		return t;
	}

	public String generateRandomTaskName() {
		return "Task " + String.valueOf((int)(Math.random() * 100));
	}
}
