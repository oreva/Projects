package ua.org.oreva.studyNN.core;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 8/14/13
 * Time: 12:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class Worker implements Runnable {
	private TaskQueue taskQueue;
	private TaskQueue resultQueue;

	public Worker(TaskQueue taskQueue, TaskQueue resultQueue) {
		this.taskQueue = taskQueue;
		this.resultQueue = resultQueue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Task task = taskQueue.get();
				task.run(null);
				resultQueue.put(task);
				Thread.yield();
			}
		} catch (InterruptedException e) {
			System.out.println("Worker " + this.toString() + " stopped via interruption");
		}
	}
}
