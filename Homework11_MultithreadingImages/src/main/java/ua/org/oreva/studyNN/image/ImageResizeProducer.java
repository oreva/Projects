package ua.org.oreva.studyNN.image;

import ua.org.oreva.studyNN.core.Producer;
import ua.org.oreva.studyNN.core.Task;
import ua.org.oreva.studyNN.core.TaskQueue;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 8/14/13
 * Time: 5:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class ImageResizeProducer extends Producer {
	private BufferedImage image;
	private LinkedList<Rectangle> listOfBounds;

	public ImageResizeProducer(BufferedImage image,
	                           LinkedList<Rectangle> listOfBounds,
	                           TaskQueue taskQueue) {
		super(taskQueue);
		this.image = image;
		this.listOfBounds = (LinkedList)listOfBounds.clone();
	}

	@Override
	public boolean canGenerateTask() {
		return !listOfBounds.isEmpty();
	}

	@Override
	public Task generateTask() {
		Rectangle bounds = listOfBounds.pollFirst();
		return new ImageResizeTask(image, bounds);
	}
}
