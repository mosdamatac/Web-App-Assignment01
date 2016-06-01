/**
 * Project: A00973641_assignment1
 * File: Database.java
 * Date: May 28, 2016
 * Time: 7:06:01 PM
 */
package a00973641.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Mara
 *
 */
public class Database {

	private static Database dbInstance = new Database();
	private static Connection connection;
	private static Properties properties;

	public void init() throws IOException, SQLException {
		File file = new File(DbConstants.DB_PROPERTIES_FILENAME);
		if (!file.exists()) {
			// TODO error page
		}

		try {
			properties = new Properties();
			properties.load(new FileInputStream(file));
		} finally {
			if (connection != null)
				connection.close();
		}
	}

	public static Database getInstance() {
		return dbInstance;
	}

	public Connection getConnection() throws SQLException {
		if (connection != null) {
			return connection;
		}

		try {
			connect();
		} catch (ClassNotFoundException e) {
			// TODO Show error page
		}

		return connection;
	}

	private void connect() throws ClassNotFoundException, SQLException {
		Class.forName(properties.getProperty(DbConstants.DB_DRIVER_KEY));
		connection = DriverManager.getConnection(properties.getProperty(DbConstants.DB_URL_KEY),
				properties.getProperty(DbConstants.DB_USER_KEY), properties.getProperty(DbConstants.DB_PASSWORD_KEY));
	}

	public void shutdown() {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				// TODO Show error page
			}
		}
	}
}
