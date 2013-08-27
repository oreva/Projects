package ua.org.oreva.studyNN;

import ua.org.oreva.studyNN.core.Producer;
import ua.org.oreva.studyNN.core.TaskQueue;
import ua.org.oreva.studyNN.image.ImageFile;
import ua.org.oreva.studyNN.image.ImageResizeController;
import ua.org.oreva.studyNN.image.ImageResizeProducer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.image.*;
import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class App
{
	private static Rectangle[] dimensions;
	static {
		int size = 5;
		dimensions = new Rectangle[size];
		for (int i = 0; i < size; i++) {
			int width = (int)Math.round(Math.random() * 150);
			int height = (int)Math.round(Math.random() * 100);
			Rectangle nextDimension = new Rectangle(0, 0, width, height);
			dimensions[i] = nextDimension;
		}
	}

    public static void main( String[] args )  {
	    try {
		    System.out.println("Please set the source directory (were your images are):");
		    Scanner scanner = new Scanner(System.in);
		    String sourceDir = "d:/tmp/img"; //System.getProperty("user.home");
		    String resultDir = sourceDir;
		    if (scanner.hasNextLine() && !scanner.nextLine().isEmpty()) {
			    sourceDir = scanner.nextLine();
			    resultDir = sourceDir;
		    }
		    File dir = new File(sourceDir);
		    ImageResizeController controller = new ImageResizeController(10);
		    for (File f: dir.listFiles()) {
			    controller.resize(new ImageFile(sourceDir, f.getName(), resultDir),
					    new LinkedList<Rectangle>(Arrays.asList(dimensions)));
		    }

	    } catch (Exception e) {
		    e.printStackTrace();
	    }
    }
}
