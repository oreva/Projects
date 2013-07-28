package ua.org.oreva.studyNN.task;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 6/1/13
 * Time: 11:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class TaskImpl<X, Y> implements Task<X, Y> {
	protected Class<X> resultType;
	protected Class<Y> inputType;
	private String name;

	public TaskImpl(Class<X> resultType, Class<Y> inputType) {
		this.resultType = resultType;
		this.inputType = inputType;
	}

	public TaskImpl(Class<X> resultType, Class<Y> inputType, String name) {
		this(resultType, inputType);
		setName(name);
	}

	@Override
	/**
	 * @return default behaviour: just new instance of type X ignoring Y. This can be overridden in subclasses.
	 */
	public X run(Y value) {
		try {
			return resultType.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public X runWithRandomInput() {
		return run(generateRandomInput());
	}

	@Override
	public Y generateRandomInput() {
		try {
			return inputType.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}


}
