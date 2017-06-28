package dsweb.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dsweb.dao.PessoaDao;
import dsweb.dao.PessoaJdbcDao;
import dsweb.model.Pessoa;

public class PessoaController {

	private PessoaDao pessoaDao = new PessoaJdbcDao();

	public String lista(HttpServletRequest request, HttpServletResponse response) {
		List<Pessoa> pessoas = pessoaDao.getLista();
		request.setAttribute("pessoas", pessoas);
		return "lista_pessoas";
	}

	public String insereForm(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("pessoa", new Pessoa());
		request.setAttribute("acao", "insere");
		return "insere_pessoa";
	}

	public String insere(HttpServletRequest request, HttpServletResponse response) {
		Pessoa pessoa = getPessoaFromParams(request);
		pessoaDao.adiciona(pessoa);
		Cookie cookie = new Cookie("id_pessoa", "" + pessoa.getId());
		cookie.setMaxAge(60 * 60 * 60);
		response.addCookie(cookie);
		return "redirect:mvc?controller=PessoaController&action=lista";
	}

	private Pessoa getPessoaFromParams(HttpServletRequest request) {
		Integer id;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			id = null;
		}
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String endereco = request.getParameter("endereco");

		return new Pessoa(id, nome, email, endereco);
	}

	public String deleta(HttpServletRequest request, HttpServletResponse response) {
		Pessoa pessoa = getPessoaFromParams(request);
		pessoaDao.remove(pessoa);
		return "redirect:mvc?controller=PessoaController&action=lista";
	}

	public String atualizaForm(HttpServletRequest request, HttpServletResponse response) {
		Pessoa pessoa = getPessoaFromParams(request);
		Pessoa pessoaaux = pessoaDao.getPessoa(pessoa.getId());
		request.setAttribute("pessoa", pessoaaux);
		request.setAttribute("acao", "edita");
		return "edita_pessoa";
	}

	public String edita(HttpServletRequest request, HttpServletResponse response) {
		Pessoa pessoa = getPessoaFromParams(request);
		pessoaDao.altera(pessoa);
		return "redirect:mvc?controller=PessoaController&action=lista";
	}

	public String verPessoaCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie ck[] = request.getCookies();
		Pessoa pessoa = null;
		for (int i = 0; i < ck.length; i++) {
			if (ck[i].getName().equals("id_pessoa")) {
				pessoa = pessoaDao.getPessoa(Integer.parseInt(ck[i].getValue()));
			}
		}
		request.setAttribute("pessoa", pessoa);
		return "view_pessoa";
	}

}
