package ua.org.oreva.studyNN.client;

import ua.org.oreva.studyNN.runner.TaskRunner;
import ua.org.oreva.studyNN.task.Task;
import ua.org.oreva.studyNN.task.TaskFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 5/31/13
 * Time: 11:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class Client implements Runnable {
	private TaskRunner runner;
	private TaskFactory taskFactory;
	private String name;

	public Client(TaskFactory taskFactory, TaskRunner runner, String clientName){
		this.taskFactory = taskFactory;
		this.runner = runner;
		this.name = clientName;
	}

	public void setTaskRunner(TaskRunner runner) {
		this.runner = runner;
	}

	public void setTaskFactory(TaskFactory factory) {
		taskFactory = factory;
	}

	@Override
	public void run() {
		if (runner == null) {
			throw new NoTaskRunnerException();
		}
		if (taskFactory == null) {
			throw new NoTaskFactoryException();
		}
		int i = 0;
		while (true) {
			Task task = taskFactory.generateTask();
			Object result = runner.run(task, task.generateRandomInput());
			System.out.println("Client " + name + ", task#" + i + ", result: " + result);
			i++;
			Thread.yield();
		}
	}
}
