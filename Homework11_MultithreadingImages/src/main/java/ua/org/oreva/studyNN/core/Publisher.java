package ua.org.oreva.studyNN.core;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 8/14/13
 * Time: 12:56 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Publisher implements Runnable {
	private TaskQueue resultQueue;

	public Publisher(TaskQueue resultQueue) {
		this.resultQueue = resultQueue;
	}

	public abstract void publish(Task task);

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Task task = resultQueue.get();
				publish(task);
				Thread.yield();
			}
		} catch (InterruptedException e) {
			System.out.println("Publisher " + this.toString() + " stopped via interruption");
		}
	}
}
