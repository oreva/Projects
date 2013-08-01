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
	public <X, Y> X run(Task<X, Y> task, Y value) throws InterruptedException {
		// Add new runner if possible
		synchronized (taskQueueRunners) {
			if (taskQueueRunners.size() < numOfRunners) {
				String threadName = "Thread T" + String.valueOf(taskQueueRunners.size());
				TaskQueueRunner runner = new TaskQueueRunner(taskQueue, threadName);
				Thread t = new Thread(runner);
				taskQueueRunners.add(t);
				t.start();
			}
		}
		// Add task to queue
		taskQueue.put(task);
		return null; //Real task result we'll see after queue processing (TaskQueueRunner will write it to the screen).
	}

	@Override
	public void shutdownNow() {
		for (Thread t: taskQueueRunners) {
			t.interrupt();
		}
	}
}
