package ru.ifmo.ctddev.reva.task1.matrix;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 2/25/13
 * Time: 11:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class MatrixTest {
	private Matrix source;
	private Matrix operandForAdd;
	private Matrix operandForMultiply;

	@Before
	public void setup() throws Exception {
		double[][] content = new double[2][3];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				content[i][j] = 1.5;
			}
		}
		source = new Matrix(2, 3, content);

		double[][] content2 = new double[2][3];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				content2[i][j] = 1;
			}
		}
		operandForAdd = new Matrix(2, 3, content2);

		double[][] content3 = new double[3][4];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				content3[i][j] = 2;
			}
		}
		operandForMultiply = new Matrix(3, 4, content3);
	}

	@Test
	public void testAdd() throws Exception {
		assertTrue(source.add(operandForAdd).content()[0][0] == 2.5);
	}

	@Test
	public void testSubtract() throws Exception {
		assertTrue(source.subtract(operandForAdd).content()[0][0] == 0.5);
	}

	@Test
	public void testMultiply() throws Exception {
		assertTrue(source.multiply(operandForMultiply).content()[0][0] == 9);
	}

	@Test
	public void testScale() throws Exception {
		assertTrue(source.scale(5).content()[0][0] == 7.5);
	}
}
