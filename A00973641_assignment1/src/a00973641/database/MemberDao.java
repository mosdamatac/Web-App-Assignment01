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

	private final Database db;

	public MemberDao() {
		db = Database.getInstance();
	}

	public void drop() throws SQLException {
		PreparedStatement ps = null;
		try {
			Connection dbConn = db.getConnection();
			if (DBUtil.tableExists(dbConn, DbConstants.MEMBER_TABLE_NAME)) {
				String dropSQL = "DROP TABLE " + DbConstants.MEMBER_TABLE_NAME;
				ps = dbConn.prepareStatement(dropSQL);
				DBUtil.executeUpdate(ps, dropSQL);
			}
		} finally {
			DBUtil.closeStatement(ps);
		}
	}
}
