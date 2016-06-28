/**
 * Project: A00973641_assignment1
 * File: ResourceBundleUtility.java
 * Date: Jun 20, 2016
 * Time: 11:19:12 PM
 */
package a00973641.internationalization.util;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mara
 *
 */
public class ResourceBundleUtility {

	private static ResourceBundleUtility rbInstance = new ResourceBundleUtility();
	private HttpServletRequest request;
	private ResourceBundle resourceBundle;

	public void init(HttpServletRequest request) {
		this.request = request;
	}

	public static ResourceBundleUtility getInstance() {
		return rbInstance;
	}

	public void updateString(Locale locale) {
		resourceBundle = ResourceBundle.getBundle("a00973641.internationalization.resourcebundle.MyResource", locale);

		request.setAttribute(RbConstants.CHOOSE_LANG, resourceBundle.getString(RbConstants.CHOOSE_LANG));

		// About page Strings
		request.setAttribute(RbConstants.INTRO_HEAD, resourceBundle.getString(RbConstants.INTRO_HEAD));
		request.setAttribute(RbConstants.INTRO_BODY_1, resourceBundle.getString(RbConstants.INTRO_BODY_1));
		request.setAttribute(RbConstants.INTRO_BODY_2, resourceBundle.getString(RbConstants.INTRO_BODY_2));
		request.setAttribute(RbConstants.INTRO_AUTHOR_DETAILS,
				resourceBundle.getString(RbConstants.INTRO_AUTHOR_DETAILS));
		request.setAttribute(RbConstants.USE_APP_HEAD, resourceBundle.getString(RbConstants.USE_APP_HEAD));
		request.setAttribute(RbConstants.USE_APP_BODY_1, resourceBundle.getString(RbConstants.USE_APP_BODY_1));
		request.setAttribute(RbConstants.USE_APP_BODY_2, resourceBundle.getString(RbConstants.USE_APP_BODY_2));
		request.setAttribute(RbConstants.USE_APP_BODY_3, resourceBundle.getString(RbConstants.USE_APP_BODY_3));
		request.setAttribute(RbConstants.USE_DB_HEAD, resourceBundle.getString(RbConstants.USE_DB_HEAD));
		request.setAttribute(RbConstants.USE_DB_BODY_1, resourceBundle.getString(RbConstants.USE_DB_BODY_1));
		request.setAttribute(RbConstants.USE_DB_BODY_2, resourceBundle.getString(RbConstants.USE_DB_BODY_2));
		request.setAttribute(RbConstants.USE_DB_BODY_3, resourceBundle.getString(RbConstants.USE_DB_BODY_3));
		request.setAttribute(RbConstants.USE_DB_BODY_4, resourceBundle.getString(RbConstants.USE_DB_BODY_4));
		request.setAttribute(RbConstants.USE_DB_BODY_5, resourceBundle.getString(RbConstants.USE_DB_BODY_5));
		request.setAttribute(RbConstants.USE_DB_BODY_6, resourceBundle.getString(RbConstants.USE_DB_BODY_6));
		request.setAttribute(RbConstants.USE_DB_BODY_7, resourceBundle.getString(RbConstants.USE_DB_BODY_7));

		// Error page Strings
		request.setAttribute(RbConstants.ERROR, resourceBundle.getString(RbConstants.ERROR));

		// Home page Strings
		request.setAttribute(RbConstants.WELCOME, resourceBundle.getString(RbConstants.WELCOME));

		// Data Strings
		request.setAttribute(RbConstants.MEMBER_ID, resourceBundle.getString(RbConstants.MEMBER_ID));
		request.setAttribute(RbConstants.FIRST_NAME, resourceBundle.getString(RbConstants.FIRST_NAME));
		request.setAttribute(RbConstants.LAST_NAME, resourceBundle.getString(RbConstants.LAST_NAME));
		request.setAttribute(RbConstants.ADDRESS, resourceBundle.getString(RbConstants.ADDRESS));
		request.setAttribute(RbConstants.CITY, resourceBundle.getString(RbConstants.CITY));
		request.setAttribute(RbConstants.CODE, resourceBundle.getString(RbConstants.CODE));
		request.setAttribute(RbConstants.COUNTRY, resourceBundle.getString(RbConstants.COUNTRY));
		request.setAttribute(RbConstants.PHONE_NUMBER, resourceBundle.getString(RbConstants.PHONE_NUMBER));
		request.setAttribute(RbConstants.EMAIL, resourceBundle.getString(RbConstants.EMAIL));

		// Main page Strings
		request.setAttribute(RbConstants.ACTION, resourceBundle.getString(RbConstants.ACTION));
		request.setAttribute(RbConstants.AUTO_FILL, resourceBundle.getString(RbConstants.AUTO_FILL));
	}
}
