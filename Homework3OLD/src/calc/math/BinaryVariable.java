package calc.math;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 2/9/13
 * Time: 5:17 PM
 * To change this template use File | Settings | File Templates.
 */
//TODO:delete class
public abstract class BinaryVariable extends Times {
	public BinaryVariable(String varName) {
		super(new Variable(varName), new Const(1));
		priority = OperationPriority.CONST;
	}
}
