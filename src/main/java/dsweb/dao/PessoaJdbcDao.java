package dsweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dsweb.model.Pessoa;

public class PessoaJdbcDao implements PessoaDao {

	private Connection connection;

	public PessoaJdbcDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	@Override
	public void adiciona(Pessoa pessoa) {
		String sql = "insert into pessoas " + "(nome,email,endereco)" + " values (?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, pessoa.getNome());
			stmt.setString(2, pessoa.getEmail());
			stmt.setString(3, pessoa.getEndereco());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				pessoa.setId(rs.getInt(1));
			}
			System.out.println("pessoa id: " + pessoa.getId());
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private Pessoa mapRow(ResultSet rs) throws SQLException {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(rs.getInt("id"));
		pessoa.setNome(rs.getString("nome"));
		pessoa.setEmail(rs.getString("email"));
		pessoa.setEndereco(rs.getString("endereco"));
		return pessoa;
	}

	@Override
	public List<Pessoa> getLista() {
		try {
			List<Pessoa> pessoas = new ArrayList<Pessoa>();
			String sql = "select * from pessoas";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Pessoa
				Pessoa pessoa = mapRow(rs);
				// adicionando o objeto à lista
				pessoas.add(pessoa);
			}
			rs.close();
			stmt.close();
			return pessoas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void altera(Pessoa pessoa) {
		try {
			String sql = "update pessoas set nome= ?, email = ?, endereco = ? where id = ?";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, pessoa.getNome());
			stmt.setString(2, pessoa.getEmail());
			stmt.setString(3, pessoa.getEndereco());
			stmt.setInt(4, pessoa.getId());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void remove(Pessoa pessoa) {
		try {
			String sql = "delete from pessoas where id = ?";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, pessoa.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Pessoa getPessoa(Integer id) {
		try {
			Pessoa result = null;
			String sql = "select * from pessoas where id = ?";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				result = mapRow(rs);
			}
			return result;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
