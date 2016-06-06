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
 * Servlet implementation class DeleteServlet
 */
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DBConnectionManager db = DBConnectionManager.getInstance();
		Connection dbConn = null;
		PreparedStatement ps = null;
		String deleteSQL = String.format("DELETE FROM %s WHERE MemberID=?", DbConstants.MEMBER_TABLE_NAME);
		try {
			System.out.println("Attempting to add data...");
			dbConn = db.getConnection();
			ps = dbConn.prepareStatement(deleteSQL);

			ps.setInt(1, Integer.parseInt(request.getParameter("memberID")));

			System.out.println("Executing: " + deleteSQL);
			int count = ps.executeUpdate();
			System.out.println("Successfully deleted row: " + count);
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
