package ru.ifmo.ctddev.reva.task1;

import ru.ifmo.ctddev.reva.task1.matrix.Matrix;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 2/26/13
 * Time: 5:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
	public static void main(String[] args) {
		BufferedReader f = null;
		BufferedWriter g = null;
		try {
			f = new BufferedReader(new FileReader(args[0]));
			g = new BufferedWriter(new FileWriter(args[1]));

			Matrix m1 = new Matrix(f);
			Matrix m2 = new Matrix(f);
			Matrix result = m1.multiply(m2);
			result.write(g);
			f.close();
			g.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				f.close();
				g.close();
			} catch (IOException fe) {/*Ignoring*/}
		}
	}
}
