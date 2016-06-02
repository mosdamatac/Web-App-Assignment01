package a00973641.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
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
		try {
			db = DBConnectionManager.getInstance();
			InputStream input = ctx.getResourceAsStream(DbConstants.DB_PROPERTIES_FILENAME);
			db.init(input);
			Connection dbConn = db.getConnection();
			if (dbConn != null)
				System.out.println("Successfully connected to db..." + dbConn.toString());
			ctx.setAttribute("DBConnection", dbConn);

			// MemberDao md = new MemberDao(dbConn);
			// md.drop();
			// md.create();
			// System.out.println("Successfully created Member table");
		} catch (IOException ioe) {
			// TODO Error
		} catch (SQLException sqle) {

		} finally {
			db.shutdown();
		}
	}

}
