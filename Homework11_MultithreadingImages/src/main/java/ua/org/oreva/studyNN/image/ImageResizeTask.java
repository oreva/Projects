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
public class ImageResizeTask implements Task<String, Rectangle> {
	private ImageFile image;
	private Rectangle bounds;
	private String result;

	public ImageResizeTask(ImageFile image, Rectangle newBounds) {
		this.image = image;
		this.bounds = newBounds;
	}

	public String getResult() {
		return result;
	}

	@Override
	public String run(Rectangle value) {
		if (null == value) {
			value = bounds;
		}
		result = ImageMagic.resizeWithImageMagic(image, value);
		return result;
	}
}
