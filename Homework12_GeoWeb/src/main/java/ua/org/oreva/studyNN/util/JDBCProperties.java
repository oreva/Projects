package ua.org.oreva.studyNN.util;

import java.io.FileReader;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 5/9/13
 * Time: 12:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class JDBCProperties {
	private static final String propertyFile = "D:/Education/JavaAcademy/Projects/Homework9_JDBC/src/main/resources/jdbc.properties";

	public static final String JDBC_DRIVER = "JDBC_DRIVER";
	public static final String USER = "USER";
	public static final String PASSWORD = "PASSWORD";
	public static final String DRIVER_NAME = "DRIVER_NAME";
	public static final String HOST = "HOST";
	public static final String PORT = "PORT";
	public static final String DB_NAME = "DB_NAME";

	private Properties props;

	public JDBCProperties() {
		props = new Properties();
		try {
			FileReader reader = new FileReader(propertyFile);
			props.load(reader);
		} catch (Exception e) {
			// Did not find resource file or can't load it - dev problems
			throw new RuntimeException(e);
		}
	}

	public String getProperty(String property) {
		return props.getProperty(property);
	}
}
