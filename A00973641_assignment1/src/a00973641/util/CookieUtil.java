/**
 * Project: a00973641_lab07
 * File: CookieUtil.java
 * Date: Jun 8, 2016
 * Time: 1:12:40 AM
 */
package a00973641.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Mara
 *
 */
public class CookieUtil {

	/**
	 * Find the cookie with the given cookie name.
	 * 
	 * @param request
	 * @param cookieName
	 * @return Cookie is returned if it matches name otherwise, null.
	 */
	public static Cookie getCookie(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookieName.equals(cookie.getName())) {
					return cookie;
				}
			}
		}
		return null;
	}

	/**
	 * Find the cookie value with the given cookie value.
	 * 
	 * @param request
	 * @param cookieName
	 * @param defaultValue
	 * @return Cookie value is returned if it matches name otherwise, default
	 *         value.
	 */
	public static String getCookieValue(HttpServletRequest request, String cookieName, String defaultValue) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookieName.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return defaultValue;
	}
}
