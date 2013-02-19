package ru.ifmo.ctddev.reva.calc.math;


/**
 * Created with IntelliJ IDEA.  dgfdgdgdf
 * User: Olga Reva
 * Date: 2/7/13
 * Time: 7:15 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractOperation implements IExpression {
	protected OperationPriority priority;
	public OperationPriority priority() {
		return this.priority;
	}

	public abstract boolean canAddInnerOperation();
	public abstract void addInnerOperation(AbstractOperation op);
	public abstract void removeLastInnerOperation();
	public abstract AbstractOperation getInnerOperation(int operationIndex);
	public abstract AbstractOperation getLastInnerOperation();
}
