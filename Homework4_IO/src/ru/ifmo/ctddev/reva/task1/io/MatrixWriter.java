package ru.ifmo.ctddev.reva.task1.io;

import ru.ifmo.ctddev.reva.task1.matrix.Matrix;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 2/27/13
 * Time: 1:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class MatrixWriter implements Closeable {
	private BufferedWriter writer;

	public MatrixWriter(BufferedWriter writer) {
		this.writer = writer;
	}

	public void write(Matrix matrix) throws IOException {
		String delimiter = " ";

		writer.write(String.valueOf(matrix.numRows()) + delimiter + String.valueOf(matrix.numColumns()));
		writer.newLine();

		for (int i = 0; i < matrix.numRows(); i++) {
			for (int j = 0; j < matrix.numColumns(); j++) {
				writer.write(String.valueOf(matrix.content()[i][j]));
				writer.write(delimiter);
			}
			writer.newLine();
		}
	}

	@Override
	public void close() throws IOException {
		writer.close();
	}
}
