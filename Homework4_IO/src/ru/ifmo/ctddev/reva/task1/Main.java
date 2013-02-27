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
		try {
			BufferedReader f = new BufferedReader(new FileReader(args[0]));
			BufferedWriter g = new BufferedWriter(new FileWriter(args[1]));
			/*BufferedReader f = new BufferedReader(new FileReader("d:/Education/JavaAcademy/Projects/Homework4_IO/input.txt"));
			BufferedWriter g = new BufferedWriter(new FileWriter("d:/Education/JavaAcademy/Projects/Homework4_IO/output.txt"));
            */

			Matrix m1 = new Matrix(f);
			Matrix m2 = new Matrix(f);
			Matrix result = m1.multiply(m2);
			result.write(g);
			f.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
