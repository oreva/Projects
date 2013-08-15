package ua.org.oreva.studyNN.core;

import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 8/14/13
 * Time: 12:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class TaskQueue {
	private LinkedList<Task> queue;
	private int limit;
	private boolean unlimitedQueue;

	public TaskQueue() {
		queue = new LinkedList<Task>();
		unlimitedQueue = true;
	}

	public TaskQueue(int limit) {
		if (limit <= 0) {
			throw new IllegalArgumentException("TaskQueue limit should be greater than zero.");
		}
		queue = new LinkedList<Task>();
		this.limit = limit;
		unlimitedQueue = false;
	}

	public synchronized void put(Task task) throws InterruptedException {
		//System.out.println("Want to put");
		while (limitReached()) {
			//System.out.println("Put is waiting (limit reached). Tasks in queue: " + queue.size());
			wait();
		}
		//System.out.println("Put and notify");
		queue.add(task);
		notifyAll(); //Notify all to put and to get if we were waiting in the put() method because of the limit or in the get() because of the emptiness
	}

	public synchronized Task get() throws InterruptedException {
		//System.out.println("Want to get");
		while (queue.isEmpty()) {
			//System.out.println("Get is waiting (queue is empty)");
			wait();
		}
		//System.out.println("Get and notify");
		Task task = queue.pollFirst();
		if (!unlimitedQueue) {
			notifyAll(); //Notify all to put and to get if we were waiting in the put() method because of the limit or in the get() because of the emptiness
		}
		return task;
	}

	public synchronized int getSize() {
		return queue.size();
	}

	private synchronized boolean limitReached() {
		return !unlimitedQueue && queue.size() >= limit;
	}
}
