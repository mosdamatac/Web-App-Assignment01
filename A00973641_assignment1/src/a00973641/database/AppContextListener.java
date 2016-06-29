package a00973641.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import a00973641.database.dao.DbDao;
import a00973641.database.util.DBUtil;

/**
 * Application Lifecycle Listener implementation class AppContextListener
 *
 */
@WebListener
public class AppContextListener implements ServletContextListener {

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		Connection dbConn = (Connection) servletContextEvent.getServletContext().getAttribute("DBConnection");
		try {
			dbConn.close();
			dbConn = null;
		} catch (SQLException e) {
			// TODO error
		}
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext ctx = servletContextEvent.getServletContext();
		DBConnectionManager db = null;

		try {
			db = DBConnectionManager.getInstance();
			InputStream input = ctx.getResourceAsStream(DbConstants.DB_PROPERTIES_FILENAME);
			db.init(input);
			ctx.setAttribute("database", db);

			// Create tables on first run. Comment out when table is created.
			// initMemberTable();

			// Test member select
			// testMemberSelect();

			// Check table columns
			DbDao.getData(ctx);
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
