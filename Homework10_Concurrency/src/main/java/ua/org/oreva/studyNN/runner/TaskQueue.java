package ua.org.oreva.studyNN.runner;

import ua.org.oreva.studyNN.task.Task;

import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 7/27/13
 * Time: 7:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class TaskQueue<X, Y> {
	private LinkedList<Task<X, Y>> queue;

	public TaskQueue() {
		queue = new LinkedList<Task<X, Y>>();
	}

	public synchronized void put(Task<X, Y> task) {
		queue.add(task);
	}

	public synchronized Task<X, Y> get() throws InterruptedException {
		while (queue.isEmpty()) {
			wait();
		}
		notifyAll();
		return queue.pollFirst();
	}
}
