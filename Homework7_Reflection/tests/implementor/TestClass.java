package implementor;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 4/3/13
 * Time: 3:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestClass extends AbstractTestClass implements TestInterface  {
	@Override
	public int one() {
		return 1;
	}

	protected String[] four() {
		return new String[4];
	}

	private static void five(String[] five) {
	}
}


interface TestInterface {
	int one();
	void two();
	String three(Object o);
}

abstract class AbstractTestClass implements TestInterface {

	@Override
	public abstract int one();

	@Override
	public void two() {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public String three(Object o) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	protected String[] four() {
		return new String[4];
	}
}