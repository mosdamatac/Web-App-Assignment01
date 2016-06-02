/**
 * Project: A00973641_assignment1
 * File: MemberDao.java
 * Date: Jun 1, 2016
 * Time: 8:54:20 PM
 */
package a00973641.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import a00973641.database.util.DBUtil;

/**
 * @author Mara
 *
 */
public class MemberDao {

	private final DBConnectionManager db;

	public MemberDao() {
		db = DBConnectionManager.getInstance();
	}

	public void drop() throws SQLException {
		PreparedStatement ps = null;
		try {
			Connection dbConn = db.getConnection();
			if (DBUtil.tableExists(dbConn, DbConstants.MEMBER_TABLE_NAME)) {
				String dropSQL = "DROP TABLE " + DbConstants.MEMBER_TABLE_NAME;
				ps = dbConn.prepareStatement(dropSQL);
				ps.executeUpdate();
			}
		} finally {
			DBUtil.closeStatement(ps);
		}
	}

	public void create() throws SQLException {
		PreparedStatement ps = null;
		try {
			Connection dbConn = db.getConnection();
			String createSQL = String.format(
					"CREATE TABLE %s (MemberID int NOT NULL AUTO_INCREMENT, " + //
							"firstName VARCHAR(15), lastName VARCHAR(15), Address VARCHAR(25), City VARCHAR(15), " + //
							"Code CHAR(6), Country VARCHAR(15), PhoneNumber CHAR(12), EMail VARCHAR(30)", //
					DbConstants.MEMBER_TABLE_NAME);
			ps = dbConn.prepareStatement(createSQL);
			ps.executeUpdate();
		} finally {
			DBUtil.closeStatement(ps);
		}
	}
}
