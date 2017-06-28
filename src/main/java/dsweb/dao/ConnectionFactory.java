package dsweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    public Connection getConnection() {
        try {
        		Class.forName("org.postgresql.Driver");
        		
            return DriverManager.getConnection(
            		"jdbc:postgresql://localhost/dsweb_ap2", 
            		"prova_web", "123");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
