package ru.ifmo.ctddev.reva.task1.matrix;

import ru.ifmo.ctddev.reva.task1.matrix.exceptions.MatrixIncorrectFormatException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 2/27/13
 * Time: 12:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class MatrixReader {
	public static Matrix read(BufferedReader reader) throws MatrixIncorrectFormatException {
		try {
			int rowCount;
			int columnCount;

			String dimensionsLine = reader.readLine();
			MatrixDimension dim = readMatrixDimensions(dimensionsLine);
			rowCount = dim.rowCount();
			columnCount = dim.columnCount();

			double[][] contentMatrix = new double[rowCount][columnCount];
			for (int i = 0; i < rowCount; i++) {
				contentMatrix[i] = readMatrixLine(reader.readLine(), columnCount);
			}
			return new Matrix(rowCount, columnCount, contentMatrix);

		} catch (Exception e) {
			throw new MatrixIncorrectFormatException("Incorrect matrix format in the file", e);
		}
	}

	public static MatrixDimension readMatrixDimensions(String str) throws MatrixIncorrectFormatException {
		Scanner scanner = new Scanner(str);
		scanner.useDelimiter("\\s+");
		int rowCount;
		int columnCount;
		try {
			if (scanner.hasNextInt()) {
				rowCount = scanner.nextInt();
			} else {
				throw new MatrixIncorrectFormatException("Cannot read matrix. Dimensions should be properly specified");
			}
			if (scanner.hasNextInt()) {
				columnCount = scanner.nextInt();
			} else {
				throw new MatrixIncorrectFormatException("Cannot read matrix. Dimensions should be properly specified");
			}
			if (scanner.hasNext()) {
				throw new MatrixIncorrectFormatException("Cannot read matrix. Dimensions should be properly specified");
			}
		} catch (Exception e) {
			throw new MatrixIncorrectFormatException("Cannot read matrix", e);
		} finally {
			scanner.close();
		}
		return new MatrixDimension(rowCount, columnCount);
	}

	private static double[] readMatrixLine(String strLine, int columnCount) throws MatrixIncorrectFormatException {
		Scanner scanner = new Scanner(strLine);
		scanner.useDelimiter("\\s+");
		double[] result = new double[columnCount];
		try {
			for (int i = 0; i < columnCount; i++) {
				if (scanner.hasNextDouble()) {
					result[i] = scanner.nextDouble();
				} else {
					throw new MatrixIncorrectFormatException("Cannot read matrix. Matrix line #" + String.valueOf(i + 1) + " should be properly specified");
				}
			}
			if (scanner.hasNext()) {
				throw new MatrixIncorrectFormatException("Cannot read matrix. Matrix line #" + String.valueOf(columnCount) + " should be properly specified");
			}
		} catch (Exception e) {
			throw new MatrixIncorrectFormatException("Cannot read matrix line", e);
		} finally {
			scanner.close();
		}
		return result;
	}

	private static class MatrixDimension {
		private int rowCount;
		private int columnCount;

		public MatrixDimension(int rowCount, int columnCount) {
			this.rowCount= rowCount;
			this.columnCount = columnCount;
		}

		public int rowCount() {
			return rowCount;
		}

		public int columnCount() {
			return columnCount;
		}
	}
}
