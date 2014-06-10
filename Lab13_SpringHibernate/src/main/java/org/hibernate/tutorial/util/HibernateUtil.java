package org.hibernate.tutorial.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 2/13/14
 * Time: 2:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class HibernateUtil {
	@Autowired
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
