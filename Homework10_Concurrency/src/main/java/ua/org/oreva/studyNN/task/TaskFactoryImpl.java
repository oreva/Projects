package ua.org.oreva.studyNN.task;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 6/1/13
 * Time: 11:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class TaskFactoryImpl<X, Y> implements TaskFactory<X, Y> {
	protected Class<X> resultType;
	protected Class<Y> inputType;

	public TaskFactoryImpl(Class<X> taskResultType, Class<Y> taskInputType) {
		resultType = taskResultType;
		inputType = taskInputType;
	}

	@Override
	public Task<X, Y> generateTask() {
		return new TaskImpl<X, Y>(resultType, inputType);
	}
}
