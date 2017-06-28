package dsweb.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dsweb.model.Pessoa;

public class PessoaMapDao implements PessoaDao {
	private Map<Integer, Pessoa> pessoas = new HashMap<Integer, Pessoa>();
	private Integer proximoId = 1;
	
	@Override
	public void adiciona(Pessoa pessoa) {
		if (pessoa.getId() == null) {
			pessoa.setId(proximoId++);
		}
		pessoas.put(pessoa.getId(), pessoa);
	}

	@Override
	public List<Pessoa> getLista() {
		return new ArrayList<Pessoa>(pessoas.values());
	}

	@Override
	public Pessoa getPessoa(Integer id) {
		return pessoas.get(id);
	}

	@Override
	public void altera(Pessoa pessoa) {
		pessoas.put(pessoa.getId(), pessoa);
	}

	@Override
	public void remove(Pessoa pessoa) {
		pessoas.remove(pessoa.getId());
	}

}
