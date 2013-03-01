package ru.ifmo.ctddev.reva.task1;

import ru.ifmo.ctddev.reva.task1.io.MatrixReader;
import ru.ifmo.ctddev.reva.task1.io.MatrixWriter;
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
		MatrixReader reader = null;
		MatrixWriter writer = null;
		try {
			reader = new MatrixReader(new BufferedReader(new FileReader(args[0])));
			writer = new MatrixWriter(new BufferedWriter(new FileWriter(args[1])));

			Matrix m1 = reader.read();
			Matrix m2 = reader.read();
			Matrix result = m1.multiply(m2);

			writer.write(result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException fe) {/*Ignoring*/}
		}
	}
}
