package dsweb.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class LoginFilter implements javax.servlet.Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse rep = ((HttpServletResponse) response);
		HttpServletRequest req = ((HttpServletRequest) request);
		HttpSession session = req.getSession(false);
		String path = ((HttpServletRequest) request).getRequestURI();

		String login = req.getParameter("action");
		
		if (path.contains("login")) {
			chain.doFilter(request, response);
		} else if (session != null && session.getAttribute("usuario") != null) {
			chain.doFilter(request, response);
		} else {
			rep.sendRedirect("mvc?controller=LoginController&action=loginForm");
		}
	

	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
