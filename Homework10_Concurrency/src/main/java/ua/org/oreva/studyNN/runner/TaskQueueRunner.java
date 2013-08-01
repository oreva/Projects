package ua.org.oreva.studyNN.runner;

import ua.org.oreva.studyNN.task.Task;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 7/27/13
 * Time: 8:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class TaskQueueRunner implements Runnable {
	private TaskQueue taskQueue;
	private String name;

	public TaskQueueRunner(TaskQueue taskQueue, String name) {
		this.taskQueue = taskQueue;
		this.name = name;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Task task = taskQueue.get();
				Object taskResult = task.runWithRandomInput();
				System.out.println(task.getName() + " run with result " + taskResult + " by " + name);
				//TimeUnit.SECONDS.sleep(3);
			}
		} catch (InterruptedException e) {
			System.out.println("Task Queue stopped via interruption");
		}
	}
}
