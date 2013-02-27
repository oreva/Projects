package ru.ifmo.ctddev.reva.task1.matrix;

import ru.ifmo.ctddev.reva.task1.matrix.exceptions.MatrixIncorrectFormatException;
import ru.ifmo.ctddev.reva.task1.matrix.exceptions.MatrixOperationException;

import java.io.*;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 2/25/13
 * Time: 11:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class Matrix {
	//private Dimension dim;
	private int numRows;
	private int numColumns;
	private double[][] content;

	public Matrix(BufferedReader f) throws MatrixIncorrectFormatException {
		Matrix source = MatrixReader.read(f);
		numRows = source.numRows();
		numColumns = source.numColumns();
		content = source.content();
	}

	public Matrix(int rowCount, int columnCount, double[][] content) throws MatrixIncorrectFormatException {
		numRows = rowCount;
		numColumns = columnCount;
		this.content = new double[numRows][numColumns];
		try {
			for (int i = 0; i < numRows; i++) {
				for (int j = 0; j < numColumns; j++) {
					this.content[i][j] = content[i][j];
				}
			}
		} catch (Exception e) {
			//If we came here then content dimensions don't match with dimension
			throw new MatrixIncorrectFormatException("Cannot create matrix. Incorrect number of rows or columns specified.");
		}
	}

	public boolean canAddOrRemove(Matrix matrix) {
		return (numRows == matrix.numRows() && numColumns == matrix.numColumns());
	}

	public Matrix add(Matrix matrix) throws MatrixOperationException, MatrixIncorrectFormatException {
		//throws exception if matrix has not the same dimensions as this
		if (!canAddOrRemove(matrix)) {
			throw new MatrixOperationException("Cannot add two matrix with different dimensions");
		}
		double[][] c = new double[numRows][numColumns];
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				c[i][j] = content[i][j] + matrix.content()[i][j];
			}
		}
		return new Matrix(numRows, numColumns, c);
	}

	public Matrix subtract(Matrix matrix) throws MatrixOperationException, MatrixIncorrectFormatException {
		//throws exception if matrix has not the same dimensions as this
		if (!canAddOrRemove(matrix)) {
			throw new MatrixOperationException("Cannot subtract two matrix with different dimensions");
		}
		double[][] c = new double[numRows][numColumns];
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				c[i][j] = content[i][j] - matrix.content()[i][j];
			}
		}
		return new Matrix(numRows, numColumns, c);
	}

	public boolean canMultiply(Matrix matrix) {
		return (numColumns == matrix.numRows());
	}

	public Matrix multiply(Matrix matrix) throws MatrixOperationException, MatrixIncorrectFormatException {
		if (!canMultiply(matrix)) {
			throw new MatrixOperationException("Cannot multiply matrix! Number of columns of first matrix should be the same as number of rows of second matrix.");
		}
		double[][] c = new double[numRows][matrix.numColumns()];
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < matrix.numColumns(); j++) {
				double result = 0;
				for (int k = 0; k < numColumns; k++) {
					result += content[i][k] * matrix.content()[k][j];
				}
				c[i][j] = result;
			}
		}
		return new Matrix(numRows, matrix.numColumns(), c);
	}

	public Matrix scale(int number) throws MatrixIncorrectFormatException {
		double[][] c = new double[numRows][numColumns];
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				c[i][j] = content[i][j] * number;
			}
		}
		return new Matrix(numRows, numColumns, c);
	}

	public void write(BufferedWriter bf) throws IOException {
		String delimiter = " ";

		bf.write(String.valueOf(numRows));
		bf.write(delimiter);
		bf.write(String.valueOf(numColumns));
		bf.newLine();

		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				bf.write(String.valueOf(content[i][j]));
				bf.write(delimiter);
			}
			bf.newLine();
		}
		bf.close();
	}

	public double[][] content() {
		return this.content;
	}

	public int numRows() {
		return numRows;
	}

	public int numColumns() {
		return numColumns;
	}
}
