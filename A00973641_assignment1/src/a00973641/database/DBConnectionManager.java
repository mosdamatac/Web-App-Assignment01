/**
 * Project: A00973641_assignment1
 * File: Database.java
 * Date: May 28, 2016
 * Time: 7:06:01 PM
 */
package a00973641.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Mara
 *
 */
public class DBConnectionManager {

	private static DBConnectionManager dbInstance = new DBConnectionManager();
	private static Connection connection;
	private static Properties properties;

	public void init(InputStream input) throws IOException, SQLException {
		try {
			System.out.println("Test reading... " + input.read());
			properties = new Properties();
			properties.load(input);
			System.out.println("Property file loaded...");
		} finally {
			if (connection != null)
				connection.close();
		}
	}

	public static DBConnectionManager getInstance() {
		return dbInstance;
	}

	public Connection getConnection() throws SQLException {
		if (connection != null) {
			System.out.println(connection.toString());
			return connection;
		}

		try {
			connect();
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		return connection;
	}

	private void connect() throws ClassNotFoundException, SQLException {
		String url = properties.getProperty(DbConstants.DB_URL_KEY) + "/"
				+ properties.getProperty(DbConstants.DB_NAME_KEY);
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		System.out.println("Driver loaded");
		connection = DriverManager.getConnection("jdbc:sqlserver://Beangrinder.bcit.ca/jspweb", "javastudent",
				"compjava");

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
