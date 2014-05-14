package ua.org.oreva.studyNN.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 5/14/14
 * Time: 9:47 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class MessageController {
	@RequestMapping("/message")
	public String showMessagePage(Map<String, Object> model) {
		return "messagePage";
	}
}
