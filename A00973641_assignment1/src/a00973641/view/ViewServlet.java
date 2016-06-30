package a00973641.view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import a00973641.data.Member;
import a00973641.database.DBConnectionManager;
import a00973641.database.util.DBUtil;
import a00973641.database.util.DbConstants;

/**
 * Servlet implementation class Assignment01Servlet
 */
public class ViewServlet extends HttpServlet {
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
		String selectSQL = String.format("SELECT * FROM %s", DbConstants.MEMBER_TABLE_NAME);
		try {
			dbConn = db.getConnection();
			System.out.println("Excecuting " + selectSQL);
			ps = dbConn.prepareStatement(selectSQL);
			Member member;
			List<Member> memberList = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				member = new Member();
				member.setMemberID(rs.getInt(1));
				member.setFirstName(rs.getString(2));
				member.setLastName(rs.getString(3));
				member.setAddress(rs.getString(4));
				member.setCity(rs.getString(5));
				member.setCode(rs.getString(6));
				member.setCountry(rs.getString(7));
				member.setPhoneNumber(rs.getString(8));
				member.setEmail(rs.getString(9));

				memberList.add(member);
			}

			request.setAttribute("members", memberList);
		} catch (SQLException e) {
			// TODO error
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
