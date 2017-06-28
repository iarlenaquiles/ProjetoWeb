package dsweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CriaTabelaPessoas {

	public static void main(String[] args) throws SQLException {
		Connection c = new ConnectionFactory().getConnection();
		String sql = "create table pessoas (id serial, " +  
				"nome VARCHAR(255), email VARCHAR(255), " + 
				"endereco VARCHAR(255), " +  
				"primary key (id))";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.executeUpdate();
		System.out.println("Tabela criada com sucesso!");
		c.close();
	}

}
