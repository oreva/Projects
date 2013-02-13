package math;

public class Const implements IExpression {
	private int value;

	public Const(int value) {
		this.value = value;
	}
	public int evaluate(int arg) {
		return value;
	}
}
