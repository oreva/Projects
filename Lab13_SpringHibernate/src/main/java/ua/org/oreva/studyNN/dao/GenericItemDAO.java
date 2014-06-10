package ua.org.oreva.studyNN.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.tutorial.util.HibernateUtil;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 6/8/14
 * Time: 8:58 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class GenericItemDAO<T, ID extends Serializable> implements ItemDAO<T, ID> {
	private Session session;
	private Class<T> persistentClass;

	public GenericItemDAO() {
		this.persistentClass = (Class<T>)((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	protected Session getSession() {
		/*if (null == session) {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
		} */
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public T findByID(ID itemId) {
		T item = (T) getSession().get(getPersistentClass(), itemId);
		return item;
	}

	public void flush() {
		getSession().flush();
	}

	public void clear() {
		getSession().clear();
	}
}
