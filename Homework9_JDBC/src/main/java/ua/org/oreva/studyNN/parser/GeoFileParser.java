package ua.org.oreva.studyNN.parser;

import ua.org.oreva.studyNN.exception.GeoItemFormatException;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 5/9/13
 * Time: 2:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class GeoFileParser implements Closeable {
	private BufferedReader reader;

	public GeoFileParser(BufferedReader reader) {
		this.reader = reader;
	}

	/**
	 *
	 * @return array of String values from the next line, or null if the end if the stream has reached
	 */
	public String[] parseNextLine() {
		try {
			String line = reader.readLine();
			if (line != null) {
				String[] values = line.trim().split("\\t");
				return values;
			}
			return null;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public GeoItem parseNextLineAsGeoItem() throws GeoItemFormatException {
		String[] values = parseNextLine();
		if (values != null) { //TODO: pererobiti oce!!!
			return new GeoItem(values);
		}
		return null;
	}

	@Override
	public void close() throws IOException {
		reader.close();
	}
}
