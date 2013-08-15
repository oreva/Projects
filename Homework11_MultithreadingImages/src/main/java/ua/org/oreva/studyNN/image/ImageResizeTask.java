package ua.org.oreva.studyNN.image;

import ua.org.oreva.studyNN.core.Task;
import ua.org.oreva.studyNN.imagemagic.ImageMagic;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 8/14/13
 * Time: 5:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class ImageResizeTask implements Task<BufferedImage, Rectangle> {
	private BufferedImage image;
	private Rectangle bounds;
	private BufferedImage result;

	public ImageResizeTask(BufferedImage image, Rectangle newBounds) {
		this.image = image;
		this.bounds = newBounds;
	}

	public BufferedImage getResult() {
		return result;
	}

	@Override
	public BufferedImage run(Rectangle value) {
		if (null == value) {
			value = bounds;
		}
		result = ImageMagic.resizeImage(image, value);
		return result;
	}
}
