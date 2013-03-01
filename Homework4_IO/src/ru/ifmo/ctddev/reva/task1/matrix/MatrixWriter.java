package ru.ifmo.ctddev.reva.task1.matrix;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 2/27/13
 * Time: 1:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class MatrixWriter {
	public static void write(BufferedWriter bf, Matrix matrix) throws IOException {
		String delimiter = " ";

		bf.write(String.valueOf(matrix.numRows()) + delimiter + String.valueOf(matrix.numColumns()));
		bf.newLine();

		for (int i = 0; i < matrix.numRows(); i++) {
			for (int j = 0; j < matrix.numColumns(); j++) {
				bf.write(String.valueOf(matrix.content()[i][j]));
				bf.write(delimiter);
			}
			bf.newLine();
		}
	}
}
