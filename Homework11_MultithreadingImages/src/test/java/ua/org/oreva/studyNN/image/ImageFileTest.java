package ua.org.oreva.studyNN.image;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 8/22/13
 * Time: 1:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class ImageFileTest {
	private ImageFile image = new ImageFile("d:\\tmp\\img", "pic.gif", "d:\\tmp\\img");
	private String dirPath = "d:\\tmp\\img";
	private String dirPathFixed = "d:/tmp/img";

	@Test
	public void testFixPathSeparators() throws Exception {
		assertEquals(dirPathFixed, image.fixPathSeparators(dirPath));
	}

	@Test
	public void testGetSourceDirectoryPath() throws Exception {
		assertEquals(dirPathFixed, image.getSourceDirectoryPath());
	}

	@Test
	public void testGetSourceFileName() throws Exception {
		assertEquals("pic.gif", image.getSourceFileName());
	}

	@Test
	public void testGetResultDirectoryPath() throws Exception {
		assertEquals(dirPathFixed, image.getResultDirectoryPath());
	}

	@Test
	public void testGetAbsoluteSourcePath() throws Exception {
		String absolutePath = dirPathFixed + "/pic.gif";
		assertEquals(absolutePath, image.getAbsoluteSourcePath());
	}

	@Test
	public void testGetResultFileNameWithoutExtension() throws Exception {
		Rectangle rect = new Rectangle(0, 0, 100, 200);
		String resultName = "pic_100x200";
		assertEquals(resultName, image.getResultFileNameWithoutExtension(rect));
	}

	@Test
	public void testGetResultFileNameWithExtension() throws Exception {
		Rectangle rect = new Rectangle(0, 0, 100, 200);
		String resultName = "pic_100x200.png";
		assertEquals(resultName, image.getResultFileNameWithExtension(rect, "png"));
	}

	@Test
	public void testGetSourceFileNameWithoutExtension() throws Exception {
		assertEquals("pic", image.getSourceFileNameWithoutExtension());
	}

	@Test
	public void testGetAbsoluteResultPath() throws Exception {
		Rectangle rect = new Rectangle(0, 0, 100, 200);
		String resultName = "pic_100x200.png";
		String absolutePath = dirPathFixed + "/" + resultName;
		assertEquals(absolutePath, image.getAbsoluteResultPath(rect, "png"));
	}
}
