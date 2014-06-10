package ua.org.oreva.studyNN.dao;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 6/8/14
 * Time: 8:56 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ItemDAO<T, ID extends Serializable> {
	T findByID(ID itemId);
	T createItem(T item);
	void flush();
	void clear();
}
