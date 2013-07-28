package ua.org.oreva.studyNN.client;

import ua.org.oreva.studyNN.runner.TaskRunner;
import ua.org.oreva.studyNN.task.Task;
import ua.org.oreva.studyNN.task.TaskFactory;

import java.util.concurrent.TimeUnit;

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
		try {
			while (!Thread.interrupted()) {
				Task task = taskFactory.generateTaskWithName("Task " + i + " of the Client " + name);
				runner.run(task, task.generateRandomInput());
				i++;
				Thread.yield();
				TimeUnit.SECONDS.sleep(5);
			}
		} catch (InterruptedException e) {
			System.out.println("Client " + name + " stopped via interruption");
		}
	}
}
