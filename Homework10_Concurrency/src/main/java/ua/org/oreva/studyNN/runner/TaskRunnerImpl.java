package ua.org.oreva.studyNN.runner;

import ua.org.oreva.studyNN.task.Task;

import java.sql.Time;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 5/31/13
 * Time: 12:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class TaskRunnerImpl implements TaskRunner {
	private int numOfThreads;
	private LinkedList<TaskCall> taskQueue;
	private LinkedList<Thread> threads;

	public TaskRunnerImpl(int numberOfThreads) {
		this.numOfThreads = numberOfThreads;
		taskQueue = new LinkedList<TaskCall>();
		threads = new LinkedList<Thread>();
	}

	public void shutdownNow() {
		for (Thread t: threads) {
			t.interrupt();
		}
	}

	@Override
	public <X, Y> X run(Task<X, Y> task, Y value) {
		// Reject all threads that we cannot handle
		synchronized (threads) {
			if (threads.size() >= numOfThreads) {
				//throw new TaskRunnerRejectException();
			}
		}
		TaskCall<X, Y> taskCall = new TaskCall<X, Y>(task, value);
		addTaskToQueue(taskCall);
		runQueuedTask();
		return getTaskCallResult(taskCall);
	}

	private synchronized <X, Y> void addTaskToQueue(TaskCall<X, Y> task) {
		if (threads.size() < numOfThreads) {
			taskQueue.add(task);
		}
	}

	private synchronized void runQueuedTask() {
		if (!taskQueue.isEmpty() && threads.size() < numOfThreads) {
			Thread t = new Thread(taskQueue.remove());
			threads.add(t);
			t.start();
		}
	}

	private <X, Y> X getTaskCallResult(TaskCall<X, Y> taskCall) {
		synchronized (taskCall) {
			while (!taskCall.hasResult()) {
				try {
					TimeUnit.SECONDS.sleep(1);
					Thread.yield();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return taskCall.getResult();
	}

	private class TaskCall<A, B> implements Runnable {
		private Task<A, B> task;
		private B value;
		private A result;
		private boolean hasResult;

		public TaskCall(Task<A, B> task, B value) {
			this.task = task;
			this.value = value;
		}

		public A getResult() {
			return result;
		}

		public boolean hasResult() {
			return this.hasResult;
		}

		@Override
		public void run() {
			while (!hasResult && !Thread.interrupted()) {
				result = task.run(value);
				hasResult = true;
			}

		}
	}
}
