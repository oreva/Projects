package ua.org.oreva.studyNN.imagemagic;

import ua.org.oreva.studyNN.image.ImageFile;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;

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

	/**
	 *
	 * @param image
	 * @param newBounds
	 * @return status string
	 */
	public static String resizeWithImageMagic(ImageFile image, Rectangle newBounds) {
		Runtime runtime = Runtime.getRuntime();
		StringBuilder command = new StringBuilder();
		command.append("convert '");
		command.append(escapePathForImageMagic(image.getAbsoluteSourcePath()));
		command.append("' -resize ");
		String w = String.valueOf(newBounds.width);
		String h = String.valueOf(newBounds.height);
		command.append(w + "x" + h);
		command.append("\\! "); //ignore aspect ratio
		String resultPath = escapePathForImageMagic(image.getAbsoluteResultPath(newBounds, "gif"));
		command.append("'" + resultPath + "'");

		System.out.println("ImageMagic command: " + command.toString());

		String status;
		try {
			Process p = runtime.exec(command.toString());
			ProcessInputStreamReader inputReader = new ProcessInputStreamReader(p.getInputStream());
			new Thread(inputReader).start();
			ProcessInputStreamReader errorReader = new ProcessInputStreamReader(p.getErrorStream());
			new Thread(errorReader).start();
			try {
				p.waitFor();
				System.out.println("Debug info: ImageMagic process returned " + p.exitValue());
				System.out.println("InputStream result info:" + inputReader.getResultInfo());
				System.out.println("ErrorStream result info:" + errorReader.getResultInfo());
			} catch (InterruptedException e) {
				status = "Creation of image " + resultPath + " failed due to the IO exception: " + e.getMessage();
				e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
			}
			status = "Image " + resultPath + " created successfully";
		} catch (IOException e) {
			status = "Creation of image " + resultPath + " failed due to the IO exception: " + e.getMessage();
		}
		return status;
	}

	public static String escapePathForImageMagic(String path) {
		String result = path.replace(":", "\\:");
		return result;
	}

	private static class ProcessInputStreamReader implements Runnable {
		private BufferedInputStream inputStream;
		private StringBuilder resultInfo;

		public ProcessInputStreamReader(InputStream inputStream) {
			this.inputStream = new BufferedInputStream(inputStream);
			resultInfo = new StringBuilder();
		}

		@Override
		public void run() {
			try {
				int c;
				while ((c = inputStream.read()) != -1) {
					resultInfo.append((char)c);
				}
			} catch (IOException o) {
				System.out.println("ProcessInputStreamReader got IOException:");
				o.printStackTrace();
			}
		}

		public String getResultInfo() {
			return resultInfo.toString();
		}
	}
}
