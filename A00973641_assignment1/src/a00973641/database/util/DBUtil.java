/**
 * Project: A00973641Gis
 * File: DBUtil.java
 * Date: Mar 20, 2016
 * Time: 5:40:58 PM
 */
package a00973641.database.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Mara
 *
 */
public class DBUtil {

	private DBUtil() {

	}

	/**
	 * Execute DML statements.
	 * 
	 * @param statement
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public static int executeUpdate(PreparedStatement statement, String sql) throws SQLException {
		int count = statement.executeUpdate();
		return count;
	}

	/**
	 * Execute query statements.
	 * 
	 * @param statement
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public static ResultSet executeQuery(PreparedStatement statement, String sql) throws SQLException {
		ResultSet resultset = statement.executeQuery();

		return resultset;
	}

	/**
	 * Determine if table already exists in the database.
	 * 
	 * @param connection
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public static boolean tableExists(Connection connection, String tableName) throws SQLException {
		DatabaseMetaData databaseMetaData = connection.getMetaData();
		ResultSet resultset = null;
		String rsTableName = null;

		try {
			resultset = databaseMetaData.getTables(connection.getCatalog(), "%", "%", null);
			while (resultset.next()) {
				rsTableName = resultset.getString("TABLE_NAME");
				if (rsTableName.equalsIgnoreCase(tableName)) {
					return true;
				}
			}
		} finally {
			resultset.close();
		}

		return false;
	}

	/**
	 * Close statement resource.
	 * 
	 * @param statement
	 */
	public static void closeStatement(PreparedStatement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			// TODO error
		}
	}
}
