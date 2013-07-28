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

	public TaskQueueRunner(TaskQueue taskQueue) {
		this.taskQueue = taskQueue;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				//TODO: return result of tasks (store into a map?)
				Task task = taskQueue.get();
				Object taskResult = task.runWithRandomInput();
				System.out.println("Task " + task.getName() + " run with result " + taskResult);
				TimeUnit.SECONDS.sleep(3);
			}
		} catch (InterruptedException e) {
			System.out.println("Task Queue stopped via interruption");
		}
	}
}
