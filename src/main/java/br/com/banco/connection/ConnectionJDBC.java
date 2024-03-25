package br.com.banco.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {
	public static Connection abrir() {
		Connection conexao = null;
	try {
		conexao = DriverManager.getConnection("jdbc:h2:file:C:\\Users\\vmontezani\\Documents\\Projetos\\(BancoEquipe1)2\\BancoDigitalDB", "sa", "");
	
	}
	catch(SQLException e) {
		System.out.println("Erro ao abrir conexão: "+ e.getMessage());
	}
	return conexao;
}
}
