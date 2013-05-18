package ua.org.oreva.studyNN.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 5/13/13
 * Time: 2:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class SmallContentReader implements Closeable {
	private BufferedReader reader;

	public SmallContentReader(BufferedReader reader) {
		this.reader = reader;
	}

	public String readAll() throws IOException {
		StringBuilder sb = new StringBuilder();
		String line = reader.readLine();

		while (line != null) {
			sb.append(line);
			sb.append("\n");
			line = reader.readLine();
		}
		String everything = sb.toString();
		return everything;
	}

	@Override
	public void close() throws IOException {
		reader.close();
	}
}
