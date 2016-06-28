package a00973641;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import a00973641.internationalization.util.ResourceBundleUtility;
import a00973641.util.CookieUtil;

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

		// Check cookie for language preference
		if (request.getParameter("langPreference") == null && CookieUtil.getCookie(request, LANG_COOKIE) != null) {
			cookieStr = CookieUtil.getCookieValue(request, LANG_COOKIE, "en");
			System.out.println(cookieStr);
		}

		// Check button clicked: ENG, DUT or FRA
		// Set language to Dutch and store in cookie
		if (request.getParameter("nlBtn") != null || cookieStr.equals("nl")) {
			System.out.println("Translating to dutch..");
			rbUtil.updateString(new Locale("nl", "BE"));
			cookie = new Cookie(LANG_COOKIE, "nl");
		}
		// Set language to French and store in cookie
		else if (request.getParameter("frBtn") != null || cookieStr.equals("fr")) {
			rbUtil.updateString(new Locale("fr", "FR"));
			cookie = new Cookie(LANG_COOKIE, "fr");
		}
		// Set language to English and store in cookie
		else {
			System.out.println("Translating to english..");
			rbUtil.updateString(new Locale("en", "CA"));
			cookie = new Cookie(LANG_COOKIE, "en");

		}

		cookie.setMaxAge(60 * 60 * 24 * 7);
		response.addCookie(cookie);

		String referer = request.getHeader("Referer");
		response.sendRedirect(referer);
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
