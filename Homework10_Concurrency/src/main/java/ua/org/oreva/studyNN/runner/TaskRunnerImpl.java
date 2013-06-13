package ua.org.oreva.studyNN.runner;

import ua.org.oreva.studyNN.task.Task;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 5/31/13
 * Time: 12:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class TaskRunnerImpl implements TaskRunner {
	private int numOfThreads;
	private ExecutorService exec;
	private ArrayList<Task> taskQueue;

	public TaskRunnerImpl(int numberOfThreads) {
		this.numOfThreads = numberOfThreads;
		taskQueue = new ArrayList<Task>();
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
		taskQueue.add(task);
		Future<X> future = executorService().submit(new TaskCall<X, Y>(task, value));
		try {
			//sleep for test. TODO: remove sleep
			//TimeUnit.MILLISECONDS.sleep(1000);
			return future.get();
		} catch (InterruptedException e1) {
			System.out.println(e1);
		} catch (ExecutionException e2) {
			System.out.println(e2);
		}
		return null;
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
