package dsweb.controller;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mvc")
public class FrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String controller = request.getParameter("controller");
		String nomeDaClasse = "dsweb.controller." + controller;
		String nomeDaAcao = request.getParameter("action");
		
		System.out.println("controller: " + nomeDaClasse);
		System.out.println("action: " + nomeDaAcao);
		
		try {
			Class<?> classe = Class.forName(nomeDaClasse);
			Object obj = classe.newInstance();
			
			Method method = obj.getClass().getMethod(nomeDaAcao, HttpServletRequest.class, HttpServletResponse.class);
			String pagina = (String)method.invoke(obj, request, response);
			if (pagina.startsWith("redirect:")) {
				String acao = pagina.replace("redirect:", "");
				response.sendRedirect(acao);
			} else if (pagina.startsWith("forward:")) {
				String acao = pagina.replace("forward:", "");
				request.getRequestDispatcher(acao).forward(request, response);
			} else {
				request.getRequestDispatcher("/WEB-INF/view/" + pagina + ".jsp").forward(request, response);
			}
			
		} catch (Exception e) {
			throw new ServletException("A lógica de negócios causou uma exceção", e);
		}
	}
}
