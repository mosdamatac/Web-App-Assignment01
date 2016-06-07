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

import a00973641.data.Member;
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
			request.setAttribute("operation", "deleted");

			// Set member field values
			Member member = setMember(request);
			request.setAttribute("member", member);

			// Delete member
			System.out.println("Attempting to delete data...");
			dbConn = db.getConnection();

			// Prepare statement
			ps = dbConn.prepareStatement(deleteSQL);
			ps.setInt(1, member.getMemberID());

			int count = ps.executeUpdate();
			System.out.println("Successfully deleted row: " + count);
			rd = ctx.getRequestDispatcher("/result.jsp");
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
		request.setAttribute("operation", "updated");

		RequestDispatcher rd = null;

		// Set member field values
		Member member = setMember(request);
		request.setAttribute("member", member);

		// Validate input values
		StringBuffer errorMsg = validate(member);

		if (errorMsg.toString().trim().length() != 0) {
			System.out.println(errorMsg);
			request.setAttribute("errorMsg", errorMsg);
			rd = ctx.getRequestDispatcher("/error");
			rd.forward(request, response);
		} else {
			// Update member data
			DBConnectionManager db = DBConnectionManager.getInstance();
			Connection dbConn = null;
			PreparedStatement ps = null;
			String updateSQL = String.format("UPDATE %s SET %s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=? WHERE %s=?",
					DbConstants.MEMBER_TABLE_NAME, "firstName", "lastName", "Address", "City", "Code", "Country",
					"PhoneNumber", "EMail", "MemberID");
			try {
				System.out.println("Attempting to update data...");
				dbConn = db.getConnection();

				// Prepare statement
				ps = dbConn.prepareStatement(updateSQL);
				ps.setString(1, member.getFirstName());
				ps.setString(2, member.getLastName());
				ps.setString(3, member.getAddress());
				ps.setString(4, member.getCity());
				ps.setString(5, member.getCode());
				ps.setString(6, member.getCountry());
				ps.setString(7, member.getPhoneNumber());
				ps.setString(8, member.getEmail());
				ps.setInt(9, member.getMemberID());

				int count = ps.executeUpdate();
				System.out.println("Successfully updated row: " + count);
				rd = ctx.getRequestDispatcher("/result.jsp");
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
		request.setAttribute("operation", "added");

		RequestDispatcher rd = null;

		// Set member field values
		Member member = setMember(request);
		request.setAttribute("member", member);

		// Validate input values
		StringBuffer errorMsg = validate(member);

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

				// Prepare statement
				ps = dbConn.prepareStatement(insertSQL);
				ps.setString(1, ServletUtilities.filter(member.getFirstName()));
				ps.setString(2, ServletUtilities.filter(member.getLastName()));
				ps.setString(3, ServletUtilities.filter(member.getAddress()));
				ps.setString(4, ServletUtilities.filter(member.getCity()));
				ps.setString(5, ServletUtilities.filter(member.getCode()));
				ps.setString(6, ServletUtilities.filter(member.getCountry()));
				ps.setString(7, ServletUtilities.filter(member.getPhoneNumber()));
				ps.setString(8, ServletUtilities.filter(member.getEmail()));

				int count = ps.executeUpdate();
				System.out.println("Successfully added row: " + count);
				rd = ctx.getRequestDispatcher("/result.jsp");
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

	private static StringBuffer validate(Member member) {
		StringBuffer errorMsg = new StringBuffer();

		if (member.getFirstName() == null || member.getFirstName().trim().equals("")) {
			errorMsg.append("First name can't be empty<br>");
		}
		if (member.getLastName() == null || member.getLastName().trim().equals("")) {
			errorMsg.append("Last name can't be empty<br>");
		}
		if (member.getAddress() == null || member.getAddress().trim().equals("")) {
			errorMsg.append("Address can't be empty<br>");
		}
		if (member.getCity() == null || member.getCity().trim().equals("")) {
			errorMsg.append("City can't be empty<br>");
		}
		if (member.getCode() == null || member.getCode().trim().equals("")) {
			errorMsg.append("Code can't be empty<br>");
		}
		if (member.getCountry() == null || member.getCountry().trim().equals("")) {
			errorMsg.append("Country can't be empty<br>");
		}
		if (member.getPhoneNumber() == null || !ServletUtilities.isValid(member.getPhoneNumber().trim(), VALID_PHONE)) {
			errorMsg.append("Invalid phone number (e.g. 111-111-1234)<br>");
		}
		if (member.getEmail() == null || !ServletUtilities.isValid(member.getEmail().trim(), VALID_EMAIL)) {
			errorMsg.append("Invalid email (e.g. me@organization.com)<br>");
		}

		return errorMsg;
	}

	private static Member setMember(HttpServletRequest request) {
		int memberID = 0;
		if (request.getParameter("memberID") != null) {
			memberID = Integer.parseInt(request.getParameter("memberID"));
		}
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String code = request.getParameter("code");
		String country = request.getParameter("country");
		String phoneNumber = request.getParameter("phoneNumber");
		String email = request.getParameter("email");

		Member member = new Member();
		member.setMemberID(memberID);
		member.setFirstName(firstName);
		member.setLastName(lastName);
		member.setAddress(address);
		member.setCity(city);
		member.setCode(code);
		member.setCountry(country);
		member.setPhoneNumber(phoneNumber);
		member.setEmail(email);
		request.setAttribute("member", member);

		return member;
	}
}
