package ua.org.oreva.studyNN.task;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 6/13/13
 * Time: 5:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class IntToStringTask extends TaskImpl<String, Integer> {
	public IntToStringTask() {
		super(String.class, Integer.class);
	}

	@Override
	public Integer generateRandomInput() {
		return (int)(Math.random() * 100);
	}

	@Override
	public String run(Integer value) {
		return value.toString();
	}
}
