package ua.org.oreva.studyNN.controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.tutorial.util.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.org.oreva.studyNN.beans.Message;
import ua.org.oreva.studyNN.dao.MessageDAO;

import java.util.Date;
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
	@Autowired
	private MessageDAO dao;
	@Autowired
	private SessionFactory sessionFactory;

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
		Transaction tx = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();

			Message m = new Message();
			m.setMessage("test " + new Date().getTime());
			dao.setSession(session);
			dao.createItem(m);

			tx.commit();

			return "submitResultPage";

		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}
	}
}
