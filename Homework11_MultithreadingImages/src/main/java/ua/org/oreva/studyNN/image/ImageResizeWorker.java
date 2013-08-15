package ua.org.oreva.studyNN.image;

import ua.org.oreva.studyNN.core.TaskQueue;
import ua.org.oreva.studyNN.core.Worker;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 8/15/13
 * Time: 1:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class ImageResizeWorker extends Worker {
	public ImageResizeWorker(TaskQueue taskQueue, TaskQueue resultQueue) {
		super(taskQueue, resultQueue);
	}
}
