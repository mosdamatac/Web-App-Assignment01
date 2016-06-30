package a00973641;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import a00973641.database.DBInit;
import a00973641.database.dao.MemberDao;

/**
 * Servlet implementation class Assignment01Servlet
 */
public class Assignment01Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext ctx = getServletContext();
		RequestDispatcher rd = null;
		if (request.getParameter("insert") != null) {
			System.out.println("Redirecting to MemberDao...");
			MemberDao.insert(request, response, ctx);
		} else if (request.getParameter("delete") != null) {
			System.out.println("Redirecting to MemberDao...");
			MemberDao.delete(request, response, ctx);
		} else if (request.getParameter("update") != null) {
			System.out.println("Redirecting to MemberDao...");
			MemberDao.update(request, response, ctx);
		} else if (request.getParameter("backBtn") != null) {
			System.out.println("Redirecting to main.jsp...");
			rd = ctx.getRequestDispatcher("/main.jsp");
			rd.forward(request, response);
		} else if (request.getParameter("loginBtn") != null) {
			// Decrypt and initialize database properties
			String password = request.getParameter("passwordTb");
			DBInit dbinit = DBInit.getInstance();
			dbinit.init(request, ctx, password);

			// Create a summary arraylist session attribute if successful login
			List<String> dbSummary = new ArrayList<>();
			HttpSession session = request.getSession();
			session.setAttribute("dbSummary", dbSummary);

			System.out.println("Redirecting to main.jsp...");
			rd = ctx.getRequestDispatcher("/main.jsp");
			rd.forward(request, response);
		} else if (request.getParameter("homeBtn") != null) {
			System.out.println("Redirecting to index.jsp...");
			rd = ctx.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		} else if (request.getParameter("aboutBtn") != null) {
			System.out.println("Redirecting to about.jsp...");
			rd = ctx.getRequestDispatcher("/about");
			rd.forward(request, response);
		} else if (request.getParameter("summaryBtn") != null) {
			System.out.println("Redirecting to summary.jsp...");
			rd = ctx.getRequestDispatcher("/summary");
			rd.forward(request, response);
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
