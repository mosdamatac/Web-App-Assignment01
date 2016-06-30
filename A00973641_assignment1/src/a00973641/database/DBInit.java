/**
 * Project: A00973641_assignment1
 * File: DBInit.java
 * Date: Jun 29, 2016
 * Time: 1:51:40 PM
 */
package a00973641.database;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import a00973641.database.decode.Decoder;
import a00973641.database.util.DBUtil;
import a00973641.database.util.DbConstants;

/**
 * @author Mara
 *
 */
public class DBInit {

	private static DBInit dbinit = new DBInit();

	public static DBInit getInstance() {
		return dbinit;
	}

	public void init(HttpServletRequest request, ServletContext ctx, String password) {
		DBConnectionManager db = null;

		// Decrypt dbproperties
		Decoder decoder = new Decoder();
		byte[] decryptedText = decoder.readAndDecrypt(ctx, password);

		try {
			db = DBConnectionManager.getInstance();
			InputStream input = new ByteArrayInputStream(decryptedText);
			db.init(input);

			// Create tables on first run. Comment out when table is created.
			// initMemberTable();

			// Test member select
			// testMemberSelect();

			// Check table columns
			// DbDao.getData(ctx);
		} catch (IOException ioe) {
			// TODO Error
		} catch (SQLException sqle) {

		} finally {
			db.shutdown();
		}
	}

	private void initMemberTable() {
		DBConnectionManager db = DBConnectionManager.getInstance();
		Connection dbConn;
		try {
			dbConn = db.getConnection();
			CreateMemberTable md = new CreateMemberTable(dbConn);
			md.drop();
			md.create();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void testMemberSelect() {
		PreparedStatement ps = null;
		String selectSQL = "SELECT * FROM " + DbConstants.MEMBER_TABLE_NAME;
		DBConnectionManager db = DBConnectionManager.getInstance();
		Connection dbConn;

		try {
			dbConn = db.getConnection();
			System.out.println("Executing: " + selectSQL);
			ps = dbConn.prepareStatement(selectSQL);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString(2));
			}
		} catch (SQLException sqle) {

		} finally {
			DBUtil.closeStatement(ps);
			db.shutdown();
		}
	}
}
