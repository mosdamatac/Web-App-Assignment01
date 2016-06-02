package a00973641.database.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import a00973641.database.Database;
import a00973641.database.DbConstants;
import a00973641.util.ServletUtilities;

/**
 * Servlet implementation class MemberAddServlet
 */
@WebServlet("/MemberAddServlet")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Pattern VALID_PHONE = Pattern.compile("^[0-9]{3}-[0-9]{3}-[0-9]{4}$");
	private static final Pattern VALID_EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String code = request.getParameter("code");
		String country = request.getParameter("country");
		String phoneNumber = request.getParameter("phoneNumber");
		String email = request.getParameter("email");

		StringBuffer errorMsg = null;

		if (firstName == null || firstName.trim().equals("")) {
			errorMsg.append("First name can't be null or empty");
		}
		if (lastName == null || lastName.trim().equals("")) {
			errorMsg.append("Last name can't be null or empty");
		}
		if (address == null || address.trim().equals("")) {
			errorMsg.append("Address can't be null or empty");
		}
		if (city == null || city.trim().equals("")) {
			errorMsg.append("City can't be null or empty");
		}
		if (code == null || code.trim().equals("")) {
			errorMsg.append("Code can't be null or empty");
		}
		if (country == null || country.trim().equals("")) {
			errorMsg.append("Country can't be null or empty");
		}
		if (phoneNumber == null || ServletUtilities.isValid(phoneNumber.trim(), VALID_PHONE)) {
			errorMsg.append("Invalid phone number (e.g. 111-111-1234)");
		}
		if (email == null || ServletUtilities.isValid(email.trim(), VALID_EMAIL)) {
			errorMsg.append("Invalid email (e.g. me@organization.com)");
		}

		if (errorMsg == null) {
			// TODO error page
		} else {
			Database db = Database.getInstance();
			Connection dbConn = null;
			PreparedStatement ps = null;
			String insertSQL = String.format("INSERT INTO %s VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
					DbConstants.MEMBER_TABLE_NAME);
			try {
				dbConn = db.getConnection();
				dbConn.prepareStatement(insertSQL);
				ps.setInt(1, 001);
				ps.setString(2, ServletUtilities.filter(firstName));
				ps.setString(3, ServletUtilities.filter(lastName));
				ps.setString(4, ServletUtilities.filter(address));
				ps.setString(5, ServletUtilities.filter(city));
				ps.setString(6, ServletUtilities.filter(code));
				ps.setString(7, ServletUtilities.filter(country));
				ps.setString(8, ServletUtilities.filter(phoneNumber));
				ps.setString(9, ServletUtilities.filter(email));

				ps.executeQuery();
			} catch (SQLException e) {
				// TODO error
			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException ex) {
						// TODO error
					}
				}
			}
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
