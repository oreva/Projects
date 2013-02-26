package ru.ifmo.ctddev.reva.task1;

import ru.ifmo.ctddev.reva.task1.matrix.Matrix;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
			//FileReader f = new FileReader(args[0]);
			//FileReader g = new FileReader(args[1]);
			FileReader f = new FileReader("d:/Education/JavaAcademy/Projects/Homework4_IO/input.txt");
			FileWriter g = new FileWriter("d:/Education/JavaAcademy/Projects/Homework4_IO/output.txt");

			Matrix m = new Matrix(f);
			Matrix result = m.multiply(m);
			result.write(g);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
