package ua.org.oreva.studyNN.core;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 8/14/13
 * Time: 12:55 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Task<X, Y> {
	X run(Y value);
}
