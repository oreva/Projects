package calc.math;

import ru.ifmo.ctddev.reva.calc.exceptions.MathOperationException;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 2/7/13
 * Time: 7:15 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractOperation implements IExpression {
	public OperationPriority priority;

	public abstract boolean canAddInnerOperation();
	public abstract void addInnerOperation(AbstractOperation op) throws MathOperationException;
	public abstract void removeLastInnerOperation() throws MathOperationException;
	public abstract AbstractOperation getInnerOperation(int operationIndex) throws MathOperationException;
	public abstract AbstractOperation getLastInnerOperation() throws MathOperationException;
}
