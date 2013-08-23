package ua.org.oreva.studyNN.image;

import org.junit.Test;
import ua.org.oreva.studyNN.core.Task;
import ua.org.oreva.studyNN.core.TaskQueue;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 8/22/13
 * Time: 1:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class ImageResizeProducerTest {
	@Test
	public void testCanGenerateTask() throws Exception {
		ImageFile image = new ImageFile("/usr", "img.gif", "/usr");
		Rectangle[] rects = {new Rectangle(0, 0, 100, 200), new Rectangle(0, 0, 200, 300)};
		TaskQueue taskQueue = new TaskQueue();
		LinkedList<Rectangle> dimensions = new LinkedList<Rectangle>(Arrays.asList(rects));
		ImageResizeProducer producer = new ImageResizeProducer(image, dimensions, taskQueue);

		assertTrue(producer.canGenerateTask());

		dimensions = new LinkedList<Rectangle>();
		producer = new ImageResizeProducer(image, dimensions, taskQueue);

		assertFalse(producer.canGenerateTask());
	}

	@Test
	public void testGenerateTask() throws Exception {
		ImageFile image = new ImageFile("/usr", "img.gif", "/usr");
		Rectangle[] rects = {new Rectangle(0, 0, 100, 200), new Rectangle(0, 0, 200, 300)};
		TaskQueue taskQueue = new TaskQueue();
		LinkedList<Rectangle> dimensions = new LinkedList<Rectangle>(Arrays.asList(rects));
		ImageResizeProducer producer = new ImageResizeProducer(image, dimensions, taskQueue);

		Task task = producer.generateTask();

		assertTrue(task instanceof ImageResizeTask);
		assertNull(((ImageResizeTask) task).getResult());
		((ImageResizeTask) task).run(rects[0]);
		assertNotNull(((ImageResizeTask) task).getResult());
	}
}
