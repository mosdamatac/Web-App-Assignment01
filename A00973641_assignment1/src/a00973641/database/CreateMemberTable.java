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
public class CreateMemberTable {

	private final Connection dbConn;

	public CreateMemberTable(Connection connection) {
		dbConn = connection;
		System.out.println(dbConn.toString());
	}

	public void drop() throws SQLException {
		PreparedStatement ps = null;
		try {
			if (DBUtil.tableExists(dbConn, DbConstants.MEMBER_TABLE_NAME)) {
				System.out.println("Dropping table...");
				String dropSQL = "DROP TABLE " + DbConstants.MEMBER_TABLE_NAME;
				ps = dbConn.prepareStatement(dropSQL);
				ps.executeUpdate();
				System.out.println("Table dropped");
			}
		} finally {
			DBUtil.closeStatement(ps);
		}
	}

	public void create() throws SQLException {
		PreparedStatement ps = null;
		try {
			System.out.println("Creating table...");
			String createSQL = String.format(
					"CREATE TABLE %s (MemberID INT IDENTITY(1,1) PRIMARY KEY, " + //
							"firstName VARCHAR(15), lastName VARCHAR(15), Address VARCHAR(25), City VARCHAR(15), " + //
							"Code CHAR(6), Country VARCHAR(15), PhoneNumber CHAR(12), EMail VARCHAR(30))", //
					DbConstants.MEMBER_TABLE_NAME);
			System.out.println("Create SQL: " + createSQL);
			ps = dbConn.prepareStatement(createSQL);
			int count = ps.executeUpdate();
			System.out.println("Table created " + count);
		} finally {
			DBUtil.closeStatement(ps);
		}
	}
}
