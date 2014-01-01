package ua.org.oreva.studyNN.db;

import ua.org.oreva.studyNN.util.JDBCProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 5/9/13
 * Time: 1:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class DBManager {
	private JDBCProperties properties;
	private static DBManager instance;

	private DBManager() {
		properties = new JDBCProperties();
		try {
			Class.forName(properties.getProperty(JDBCProperties.JDBC_DRIVER));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public static DBManager instance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(
				url(),
				properties.getProperty(JDBCProperties.USER),
				properties.getProperty(JDBCProperties.PASSWORD)
		);
	}

	private String url() {
		String host = properties.getProperty(JDBCProperties.HOST);
		if (!host.isEmpty()) {
			host = "//" + host;
		}
		String port = properties.getProperty(JDBCProperties.PORT);
		if (!port.isEmpty()) {
			port = ":" + port + "/";
		}
		return "jdbc:" +
				properties.getProperty(JDBCProperties.DRIVER_NAME) + ":" +
				host +
				port +
				properties.getProperty(JDBCProperties.DB_NAME);
	}
}
