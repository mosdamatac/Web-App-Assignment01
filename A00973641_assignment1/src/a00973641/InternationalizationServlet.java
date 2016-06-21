package a00973641;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import a00973641.util.ResourceBundleUtility;

/**
 * Servlet implementation class InternationalizationServlet
 */
public class InternationalizationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ResourceBundleUtility rbUtil = ResourceBundleUtility.getInstance();
		rbUtil.init(request);
		if (request.getParameter("jpBtn") != null) {
			System.out.println("Translating to japanese..");
			rbUtil.updateString(new Locale("ja", "JP"));
		} else {
			System.out.println("Translating to english..");
			rbUtil.updateString(new Locale("en", "CA"));
		}

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
