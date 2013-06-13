package ua.org.oreva.studyNN;

import ua.org.oreva.studyNN.client.Client;
import ua.org.oreva.studyNN.runner.TaskRunner;
import ua.org.oreva.studyNN.runner.TaskRunnerImpl;
import ua.org.oreva.studyNN.task.IntToStringTaskFactory;
import ua.org.oreva.studyNN.task.TaskFactory;
import ua.org.oreva.studyNN.task.TaskFactoryImpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
	    int numberOfThreads = 2;
	    int numerOfClients = 4;
	    TaskFactory taskFactory = new IntToStringTaskFactory();
	    TaskRunner runner = new TaskRunnerImpl(numberOfThreads);
	    Client[] clients = new Client[numerOfClients];
	    for (int i = 0; i < numerOfClients; i++) {
		    clients[i] = new Client(taskFactory, runner, "Client#" + i);
	    }

	    ExecutorService s = Executors.newCachedThreadPool();
	    for (int i = 0; i < numerOfClients; i++) {
		    s.submit(clients[i]);
	    }
	    s.shutdown();
	    runner.shutdown();
    }
}
