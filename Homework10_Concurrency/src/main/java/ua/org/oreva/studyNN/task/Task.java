package ua.org.oreva.studyNN.task;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 5/31/13
 * Time: 11:47 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Task<X, Y> {
	X run(Y value);

	X runWithRandomInput();
	Y generateRandomInput();

	String getName();
	void setName(String name);
}
