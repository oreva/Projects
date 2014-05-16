package ua.org.oreva.studyNN.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.org.oreva.studyNN.beans.Message;

import java.util.LinkedList;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 5/14/14
 * Time: 9:47 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/message")
public class MessageController {
	@RequestMapping(method = RequestMethod.GET)
	public String showMessagePage(Model model) {
		model.addAttribute(new LinkedList<Message>());
		model.addAttribute(new Message());
		return "messagePage";
	}

	/*@RequestMapping(method = RequestMethod.GET, params="new")
	public String createMessage(Model model) {
		model.addAttribute(new Message());
		return "messagePage";
	}*/

	@RequestMapping(method = RequestMethod.POST)
	public String storeMessage(Model model) {
		return "submitResultPage";
	}
}
