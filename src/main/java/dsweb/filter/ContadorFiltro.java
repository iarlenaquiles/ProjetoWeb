package dsweb.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class ContadorFiltro implements Filter {

	private FilterConfig config;
	int cont;

	public ContadorFiltro() {
		this.cont = 0;
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.config = fConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		ServletContext sc = config.getServletContext();

		chain.doFilter(request, response);
		sc.setAttribute("contador", cont++);

	}

	public void destroy() {
	}

}
