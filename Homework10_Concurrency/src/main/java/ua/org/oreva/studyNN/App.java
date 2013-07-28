package ua.org.oreva.studyNN;

import ua.org.oreva.studyNN.client.Client;
import ua.org.oreva.studyNN.runner.TaskRunner;
import ua.org.oreva.studyNN.runner.TaskRunnerImpl2;
import ua.org.oreva.studyNN.task.IntToStringTaskFactory;
import ua.org.oreva.studyNN.task.TaskFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException {
	    int numberOfThreads = 5;
	    int numerOfClients = 4;
	    TaskFactory taskFactory = new IntToStringTaskFactory();
	    TaskRunner runner = new TaskRunnerImpl2(numberOfThreads);
	    Client[] clients = new Client[numerOfClients];
	    for (int i = 0; i < numerOfClients; i++) {
		    clients[i] = new Client(taskFactory, runner, String.valueOf(i));
	    }

	    ExecutorService s = Executors.newCachedThreadPool();
	    for (int i = 0; i < numerOfClients; i++) {
		    s.submit(clients[i]);
	    }
		TimeUnit.SECONDS.sleep(15);
	    s.shutdownNow();
	    runner.shutdownNow();
    }
}
