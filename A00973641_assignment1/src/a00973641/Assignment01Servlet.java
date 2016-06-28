package a00973641;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		} else if (request.getParameter("continueBtn") != null) {
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
