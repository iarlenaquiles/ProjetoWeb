package dsweb.dao;

import java.util.List;

import dsweb.model.Pessoa;

public interface PessoaDao {

	public void adiciona(Pessoa pessoa);

	public List<Pessoa> getLista();

	public Pessoa getPessoa(Integer id);

	public void altera(Pessoa pessoa);

	public void remove(Pessoa pessoa);

}