package ua.org.oreva.studyNN.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 4/28/14
 * Time: 6:29 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class HomeController {
	@RequestMapping({"/", "/home"})
	public String showHomePage(Map<String, Object> model) {
		return "home";
	}
}
