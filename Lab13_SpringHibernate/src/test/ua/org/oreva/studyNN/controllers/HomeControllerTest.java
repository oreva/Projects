package ua.org.oreva.studyNN.controllers;

import org.junit.Test;

import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 4/28/14
 * Time: 9:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class HomeControllerTest {
	@Test
	public void testShowHomePage() throws Exception {
		HomeController controller = new HomeController();

		String homepage = controller.showHomePage(new HashMap<String, Object>());

		assertEquals(homepage, "home");
	}
}
