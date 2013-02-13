import math.*;

public class Main {
	public static void main(String[] args) {
		//x^2-2x+1
		int x = Integer.valueOf(args[0]);
		int result = new Plus(new Minus(new Times(new Variable("x"), new Variable("x")),
				new Times(new Const(2), new Variable("x"))), new Const(1)).evaluate(x);
		System.out.println("x^2-2x+1 = " + result);
	}
}
