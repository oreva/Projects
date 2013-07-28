package ua.org.oreva.studyNN.runner;

import ua.org.oreva.studyNN.task.Task;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 7/27/13
 * Time: 8:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class TaskRunnerImpl2 implements TaskRunner {
	private TaskQueue taskQueue;
	private ArrayList<Thread> taskQueueRunners;
	private int numOfRunners;

	public TaskRunnerImpl2(int numberOfThreads) {
		numOfRunners = numberOfThreads;
		taskQueueRunners = new ArrayList<Thread>(numOfRunners);
		taskQueue = new TaskQueue();
	}

	@Override
	public <X, Y> X run(Task<X, Y> task, Y value) {
		// Add new runner if possible
		synchronized (taskQueueRunners) {
			if (taskQueueRunners.size() < numOfRunners) {
				TaskQueueRunner runner = new TaskQueueRunner(taskQueue);
				Thread t = new Thread(runner);
				taskQueueRunners.add(t);
				t.start();
			}
		}
		// Add task to queue
		taskQueue.put(task);
		return null;  //TODO: don't return null!
	}

	@Override
	public void shutdownNow() {
		for (Thread t: taskQueueRunners) {
			t.interrupt();
		}
	}
}
