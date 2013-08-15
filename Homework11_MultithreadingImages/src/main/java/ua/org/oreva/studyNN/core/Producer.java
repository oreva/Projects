package ua.org.oreva.studyNN.core;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 8/14/13
 * Time: 12:56 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Producer implements Runnable {
	private TaskQueue taskQueue;

	public Producer(TaskQueue taskQueue) {
		this.taskQueue = taskQueue;
	}

	public abstract boolean canGenerateTask();
	public abstract Task generateTask();

	@Override
	public void run() {
		try {
			while (!Thread.interrupted() && canGenerateTask()) {
				taskQueue.put(generateTask());
				Thread.yield();
			}
		} catch (InterruptedException e) {
			System.out.println("Producer " + this.toString() + " stopped via interruption");
		}
	}
}
