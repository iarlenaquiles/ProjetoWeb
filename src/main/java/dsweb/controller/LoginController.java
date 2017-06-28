package dsweb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController {

	public String loginForm(HttpServletRequest request, HttpServletResponse response) {
		return "login";
	}

	public String login(HttpServletRequest request, HttpServletResponse response) {
		String usuario = request.getParameter("usuario");
		String senha = request.getParameter("senha");

		if (usuario.equals("admin") && senha.equals("teste1234")) {
			HttpSession session = request.getSession();
			session.setAttribute("usuario", "admin");
			return "redirect:mvc?controller=PessoaController&action=lista";
		} else {
			return "redirect:mvc?controller=LoginController&action=loginForm";
		}
	}

	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);

		session.removeAttribute("usuario");
		session.invalidate();

		return "redirect:mvc?controller=LoginController&action=loginForm";
	}

}
