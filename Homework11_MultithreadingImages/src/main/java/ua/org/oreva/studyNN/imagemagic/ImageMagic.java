package ua.org.oreva.studyNN.imagemagic;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 8/14/13
 * Time: 5:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class ImageMagic {
	public static BufferedImage resizeImage(BufferedImage source, Rectangle newBounds) {
		BufferedImage result = new BufferedImage(newBounds.width, newBounds.height, source.getType());
		Graphics2D g = (Graphics2D) result.getGraphics();
		float[] scales = {2f, 3f, 1f};
		float[] offsets = new float[3];
		AffineTransform transform = AffineTransform.getScaleInstance(2, 3);
		g.drawImage(source, new AffineTransformOp(transform, AffineTransformOp.TYPE_BICUBIC), 0, 0);

		//test
		//System.out.println("Image resized with bounds: " + newBounds.width + "x" + newBounds.height);
		//test

		return result;
	}
}
