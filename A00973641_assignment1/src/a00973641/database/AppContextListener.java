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
		// Test member select
		PreparedStatement ps = null;
		String selectSQL = "SELECT * FROM " + DbConstants.MEMBER_TABLE_NAME;
		try {
			db = DBConnectionManager.getInstance();
			InputStream input = ctx.getResourceAsStream(DbConstants.DB_PROPERTIES_FILENAME);
			db.init(input);
			Connection dbConn = db.getConnection();
			ctx.setAttribute("DBConnection", dbConn);

			// Create tables on first run. Comment out when table is created.
			// CreateMemberTable md = new CreateMemberTable(dbConn);
			// md.drop();
			// md.create();

			// Test member select
			System.out.println("Executing: " + selectSQL);
			ps = dbConn.prepareStatement(selectSQL);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString(2));
			}
		} catch (IOException ioe) {
			// TODO Error
		} catch (SQLException sqle) {

		} finally {
			// DBUtil.closeStatement(ps);
			db.shutdown();
		}
	}

}
