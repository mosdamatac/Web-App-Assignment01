package a00973641;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			System.out.println("Redirecting to InsertServlet...");
			rd = ctx.getRequestDispatcher("/insert");
			rd.forward(request, response);
		} else if (request.getParameter("delete") != null) {
			System.out.println("Redirecting to DeleteServlet...");
			rd = ctx.getRequestDispatcher("/delete");
			rd.forward(request, response);
		} else if (request.getParameter("update") != null) {
			System.out.println("Redirecting to UpdateServlet...");
			rd = ctx.getRequestDispatcher("/update");
			rd.forward(request, response);
		} else if (request.getParameter("backBtn") != null) {
			System.out.println("Redirecting to index.jsp...");
			rd = ctx.getRequestDispatcher("/index.jsp");
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
