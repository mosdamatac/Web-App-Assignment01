package a00973641.database.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import a00973641.database.DBConnectionManager;
import a00973641.database.DbConstants;

/**
 * Servlet implementation class UpdateServlet
 */
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Reached UpdateServlet");
		DBConnectionManager db = DBConnectionManager.getInstance();
		Connection dbConn = null;
		PreparedStatement ps = null;
		String updateSQL = String.format("UPDATE %s SET %s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?" + "WHERE %s=?",
				DbConstants.MEMBER_TABLE_NAME, "firstName", "lastName", "Address", "City", "Code", "Country",
				"PhoneNumber", "EMail");
		try {
			System.out.println("Attempting to update data...");
			dbConn = db.getConnection();
			ps = dbConn.prepareStatement(updateSQL);

			ps.setString(1, request.getParameter("firstName"));
			ps.setString(2, request.getParameter("lastName"));
			ps.setString(3, request.getParameter("address"));
			ps.setString(4, request.getParameter("city"));
			ps.setString(5, request.getParameter("code"));
			ps.setString(6, request.getParameter("country"));
			ps.setString(7, request.getParameter("phoneNumber"));
			ps.setString(8, request.getParameter("email"));

			System.out.println("Executing: " + updateSQL);
			int count = ps.executeUpdate();
			System.out.println("Successfully updated row: " + count);
		} catch (SQLException e) {
			// TODO error
			System.out.println(e.getMessage());
		} finally {
			DBUtil.closeStatement(ps);
			db.shutdown();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
