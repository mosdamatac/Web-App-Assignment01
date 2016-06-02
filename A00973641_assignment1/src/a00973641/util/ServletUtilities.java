/**
 * Project: A00973641_assignment1
 * File: ServletUtilities.java
 * Date: May 4, 2016
 * Time: 11:38:56 PM
 */
package a00973641.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Mara
 */
public class ServletUtilities {

	/**
	 * Determine if the String input and pattern input.
	 * 
	 * @param input
	 * @param pattern
	 * @return True if input and pattern match. Otherwise, return false.
	 */
	public static boolean isValid(String input, Pattern pattern) {
		Matcher match = pattern.matcher(input);
		return match.matches();
	}

	public static String filter(String input) {
		StringBuffer filtered = new StringBuffer(input.length());
		char c;
		for (int i = 0; i < input.length(); i++) {
			c = input.charAt(i);
			switch (c) {
			case '<':
				filtered.append("&lt;");
				break;
			case '>':
				filtered.append("&gt;");
				break;
			case '"':
				filtered.append("&quot;");
				break;
			case '&':
				filtered.append("&amp;");
				break;
			default:
				filtered.append(c);
			}
		}
		return filtered.toString();
	}
}
