package ua.org.oreva.studyNN.image;

import ua.org.oreva.studyNN.core.Producer;
import ua.org.oreva.studyNN.core.TaskQueue;
import ua.org.oreva.studyNN.core.Worker;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 8/14/13
 * Time: 6:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class ImageResizeController {
	private TaskQueue taskQueue;
	private TaskQueue resultQueue;

	private LinkedList<Thread> producerThreads = new LinkedList<Thread>();
	private LinkedList<Thread> workerThreads = new LinkedList<Thread>();
	private LinkedList<Thread> publisherThreads = new LinkedList<Thread>();

	public ImageResizeController() {
		this(100, 10);
	}

	public ImageResizeController(int taskQueueLimit) {
		this(taskQueueLimit, 10);
	}

	public ImageResizeController(int taskQueueLimit, int numOfWorkers) {
		//Task queues
		taskQueue = new TaskQueue(taskQueueLimit);
		resultQueue = new TaskQueue();

		//Workers
		for (int i = 0; i < numOfWorkers; i++) {
			ImageResizeWorker w = new ImageResizeWorker(taskQueue, resultQueue);
			Thread t = new Thread(w);
			t.start();
			workerThreads.add(t);

			ImageResizePublisher p = new ImageResizePublisher(resultQueue);
			t = new Thread(p);
			t.start();
			publisherThreads.add(t);
		}
	}

	public void resize(BufferedImage image, LinkedList<Rectangle> listOfBounds) {
		ImageResizeProducer p = new ImageResizeProducer(image, listOfBounds, taskQueue);
		Thread t = new Thread(p);
		producerThreads.add(t);
		t.start();
		Thread.yield();
	}

	public void interruptNow() {
		for (Thread t: producerThreads) {
			t.interrupt();
		}
		for (Thread t: workerThreads) {
			t.interrupt();
		}
		for (Thread t: publisherThreads) {
			t.interrupt();
		}
	}
}
