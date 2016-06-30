package a00973641.database;

import java.sql.Connection;
import java.sql.SQLException;

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

	}

}
