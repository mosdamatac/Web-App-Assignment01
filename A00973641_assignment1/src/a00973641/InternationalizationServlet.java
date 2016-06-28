package a00973641;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import a00973641.util.CookieUtil;
import a00973641.util.ResourceBundleUtility;

/**
 * Servlet implementation class InternationalizationServlet
 */
public class InternationalizationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String LANG_COOKIE = "langChoice";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ResourceBundleUtility rbUtil = ResourceBundleUtility.getInstance();
		rbUtil.init(request);
		Cookie cookie;
		String cookieStr = "";

		// If request is from another page, check for cookie
		if (request.getParameter("aboutBtn") != null && CookieUtil.getCookie(request, LANG_COOKIE) != null) {
			cookieStr = CookieUtil.getCookieValue(request, LANG_COOKIE, "en");
			System.out.println(cookieStr);
		}

		// Check button clicked: ENG or DUT
		if (request.getParameter("nlBtn") != null || cookieStr.equals("nl")) {
			System.out.println("Translating to dutch..");
			rbUtil.updateString(new Locale("nl", "BE"));
			cookie = new Cookie(LANG_COOKIE, "nl");
		} else {
			System.out.println("Translating to english..");
			rbUtil.updateString(new Locale("en", "CA"));
			cookie = new Cookie(LANG_COOKIE, "en");

		}

		cookie.setMaxAge(60 * 60 * 24 * 7);
		response.addCookie(cookie);

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/about");
		rd.forward(request, response);
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
