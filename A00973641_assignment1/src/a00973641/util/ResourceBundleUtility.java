/**
 * Project: A00973641_assignment1
 * File: ResourceBundleUtility.java
 * Date: Jun 20, 2016
 * Time: 11:19:12 PM
 */
package a00973641.util;

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
		resourceBundle = ResourceBundle.getBundle("a00973641.resourcebundle.MyResource", locale);

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
	}
}
