package ua.org.oreva.studyNN.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.tutorial.util.HibernateUtil;
import org.springframework.stereotype.Component;
import ua.org.oreva.studyNN.beans.Message;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 6/3/14
 * Time: 4:25 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class MessageDAO extends GenericItemDAO<Message, Long> {
	@Override
	public Message createItem(Message item) {
		String queryString = "insert into message (phone, email, message)" +
				" values (:phone, :email, :message)";
		Query q = getSession().createQuery(queryString)
				.setParameter("email", item.getEmail())
				.setParameter("message", item.getMessage())
				.setParameter("phone", item.getPhone());
		q.executeUpdate();
		return item;
	}
}
