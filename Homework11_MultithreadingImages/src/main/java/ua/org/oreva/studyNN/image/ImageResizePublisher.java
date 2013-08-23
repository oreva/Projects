package ua.org.oreva.studyNN.image;

import ua.org.oreva.studyNN.core.Publisher;
import ua.org.oreva.studyNN.core.Task;
import ua.org.oreva.studyNN.core.TaskQueue;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 8/15/13
 * Time: 1:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class ImageResizePublisher extends Publisher {

	public ImageResizePublisher(TaskQueue resultQueue) {
		super(resultQueue);
	}

	@Override
	public void publish(Task task) {
		ImageResizeTask t = (ImageResizeTask)task;
		System.out.println(t.getResult());
	}
}
