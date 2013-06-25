package ua.org.oreva.studyNN.runner;

import ua.org.oreva.studyNN.task.Task;

import java.util.LinkedList;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 5/31/13
 * Time: 12:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class TaskRunnerImplOLD implements TaskRunner {
	private int numOfThreads;
	private ExecutorService exec;
	private LinkedList<TaskCall> taskQueue;

	public TaskRunnerImplOLD(int numberOfThreads) {
		this.numOfThreads = numberOfThreads;
		taskQueue = new LinkedList<TaskCall>();
	}

	public void shutdown() {
		executorService().shutdown();
		exec = null;
	}

	private ExecutorService executorService() {
		if (exec == null) {
			exec = Executors.newFixedThreadPool(numOfThreads);
		}
		return exec;
	}

	@Override
	public <X, Y> X run(Task<X, Y> task, Y value) {
		try {
			addTaskToQueue(new TaskCall<X, Y>(task, value));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			Future<X> future = runQueuedTask();
			try {
				return future.get();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	private synchronized <X, Y> void addTaskToQueue(TaskCall<X, Y> task) throws InterruptedException {
		while (task == null) {
			wait();
		}
		taskQueue.add(task);
		notify();
	}

	private synchronized <X> Future<X> runQueuedTask() throws InterruptedException {
		while (taskQueue.isEmpty()) {
			wait();
		}
		Future<X> future = executorService().submit(taskQueue.remove());
		return future;
	}

	private class TaskCall<A, B> implements Callable<A> {
		private Task<A, B> task;
		private B value;
		public TaskCall(Task<A, B> task, B value) {
			this.task = task;
			this.value = value;
		}

		@Override
		public A call() throws Exception {
			return task.run(value);
		}
	}
}
