/**
 * Project: A00973641_assignment1
 * File: MemberDao.java
 * Date: Jun 6, 2016
 * Time: 1:01:52 PM
 */
package a00973641.database.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import a00973641.database.DBConnectionManager;
import a00973641.database.DbConstants;
import a00973641.database.util.DBUtil;
import a00973641.util.ServletUtilities;

/**
 * @author Mara
 *
 */
public class MemberDao {

	private static final Pattern VALID_PHONE = Pattern.compile("^\\d{3}-\\d{3}-\\d{4}$");
	private static final Pattern VALID_EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	public MemberDao() {

	}

	public static void delete(HttpServletRequest request, HttpServletResponse response, ServletContext ctx)
			throws ServletException, IOException {
		RequestDispatcher rd = null;

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
			rd = ctx.getRequestDispatcher("/main.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			DBUtil.closeStatement(ps);
			db.shutdown();
		}
	}

	public static void update(HttpServletRequest request, HttpServletResponse response, ServletContext ctx)
			throws ServletException, IOException {
		RequestDispatcher rd = null;

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String code = request.getParameter("code");
		String country = request.getParameter("country");
		String phoneNumber = request.getParameter("phoneNumber");
		String email = request.getParameter("email");

		StringBuffer errorMsg = new StringBuffer();

		if (firstName == null || firstName.trim().equals("")) {
			errorMsg.append("First name can't be null or empty<br>");
		}
		if (lastName == null || lastName.trim().equals("")) {
			errorMsg.append("Last name can't be null or empty<br>");
		}
		if (address == null || address.trim().equals("")) {
			errorMsg.append("Address can't be null or empty<br>");
		}
		if (city == null || city.trim().equals("")) {
			errorMsg.append("City can't be null or empty<br>");
		}
		if (code == null || code.trim().equals("")) {
			errorMsg.append("Code can't be null or empty<br>");
		}
		if (country == null || country.trim().equals("")) {
			errorMsg.append("Country can't be null or empty<br>");
		}
		if (phoneNumber == null || !ServletUtilities.isValid(phoneNumber.trim(), VALID_PHONE)) {
			errorMsg.append("Invalid phone number (e.g. 111-111-1234)<br>");
		}
		if (email == null || !ServletUtilities.isValid(email.trim(), VALID_EMAIL)) {
			errorMsg.append("Invalid email (e.g. me@organization.com)<br>");
		}

		if (errorMsg.toString().trim().length() != 0) {
			System.out.println(errorMsg);
			request.setAttribute("errorMsg", errorMsg);
			rd = ctx.getRequestDispatcher("/error");
			rd.forward(request, response);
		} else {
			DBConnectionManager db = DBConnectionManager.getInstance();
			Connection dbConn = null;
			PreparedStatement ps = null;
			String updateSQL = String.format("UPDATE %s SET %s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=? WHERE %s=?",
					DbConstants.MEMBER_TABLE_NAME, "firstName", "lastName", "Address", "City", "Code", "Country",
					"PhoneNumber", "EMail", "MemberID");
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
				ps.setInt(9, Integer.parseInt(request.getParameter("memberID")));

				System.out.println("Executing: " + updateSQL);
				int count = ps.executeUpdate();
				System.out.println("Successfully updated row: " + count);
				rd = ctx.getRequestDispatcher("/main.jsp");
				rd.forward(request, response);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} catch (ServletException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} finally {
				DBUtil.closeStatement(ps);
				db.shutdown();
			}
		}
	}

	public static void insert(HttpServletRequest request, HttpServletResponse response, ServletContext ctx)
			throws ServletException, IOException {
		RequestDispatcher rd = null;

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String code = request.getParameter("code");
		String country = request.getParameter("country");
		String phoneNumber = request.getParameter("phoneNumber");
		String email = request.getParameter("email");

		StringBuffer errorMsg = new StringBuffer();

		if (firstName == null || firstName.trim().equals("")) {
			errorMsg.append("First name can't be empty<br>");
		}
		if (lastName == null || lastName.trim().equals("")) {
			errorMsg.append("Last name can't be empty<br>");
		}
		if (address == null || address.trim().equals("")) {
			errorMsg.append("Address can't be empty<br>");
		}
		if (city == null || city.trim().equals("")) {
			errorMsg.append("City can't be empty<br>");
		}
		if (code == null || code.trim().equals("")) {
			errorMsg.append("Code can't be empty<br>");
		}
		if (country == null || country.trim().equals("")) {
			errorMsg.append("Country can't be empty<br>");
		}
		if (phoneNumber == null || !ServletUtilities.isValid(phoneNumber.trim(), VALID_PHONE)) {
			errorMsg.append("Invalid phone number (e.g. 111-111-1234)<br>");
		}
		if (email == null || !ServletUtilities.isValid(email.trim(), VALID_EMAIL)) {
			errorMsg.append("Invalid email (e.g. me@organization.com)<br>");
		}

		if (errorMsg.toString().trim().length() != 0) {
			System.out.println(errorMsg);
			request.setAttribute("errorMsg", errorMsg);
			rd = ctx.getRequestDispatcher("/error");
			rd.forward(request, response);
		} else {
			DBConnectionManager db = DBConnectionManager.getInstance();
			Connection dbConn = null;
			PreparedStatement ps = null;
			String insertSQL = String.format("INSERT INTO %s VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
					DbConstants.MEMBER_TABLE_NAME);
			try {
				System.out.println("Attempting to add data...");
				dbConn = db.getConnection();
				ps = dbConn.prepareStatement(insertSQL);

				ps.setString(1, ServletUtilities.filter(firstName));
				ps.setString(2, ServletUtilities.filter(lastName));
				ps.setString(3, ServletUtilities.filter(address));
				ps.setString(4, ServletUtilities.filter(city));
				ps.setString(5, ServletUtilities.filter(code));
				ps.setString(6, ServletUtilities.filter(country));
				ps.setString(7, ServletUtilities.filter(phoneNumber));
				ps.setString(8, ServletUtilities.filter(email));

				System.out.println("Executing: " + insertSQL);
				int count = ps.executeUpdate();
				System.out.println("Successfully added row: " + count);
				rd = ctx.getRequestDispatcher("/main.jsp");
				rd.forward(request, response);
			} catch (SQLException e) {
				// TODO error
				System.out.println(e.getMessage());
			} finally {
				DBUtil.closeStatement(ps);
				db.shutdown();
			}
		}
	}
}
