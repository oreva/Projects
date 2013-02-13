package calc.math;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 2/9/13
 * Time: 1:46 PM
 * To change this template use File | Settings | File Templates.
 */
//TODO:delete class
public abstract class BinaryConst extends Times {
	public BinaryConst(int value) {
		super(new Const(value), new Const(1));
		priority = OperationPriority.CONST;
	}
}
