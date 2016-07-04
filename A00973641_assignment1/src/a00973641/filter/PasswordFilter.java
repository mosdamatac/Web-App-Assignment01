package a00973641.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PasswordFilter implements Filter {
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String ipAddress = req.getRemoteAddr();
		System.out.println("IP " + ipAddress + "access: " + req.getRequestURL() + " @ " +
				new Date().toString());
		
		HttpSession session = req.getSession();

        if (session == null) {
            res.sendRedirect(req.getContextPath() + "/resources/index.jsp");
        } else {
            chain.doFilter(req, res); // Logged-in user found, so just continue request.
        }
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("PasswordFilter initialized");

	}

}
