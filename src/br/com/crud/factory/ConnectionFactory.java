package br.com.crud.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	// Usuário do banco
	private static final String USERNAME = "root";
	
	//Senha do banco
	private static final String PASSWORD = "";
	
	//Caminho do banco - Exemplo valido para banco MySQL e pode ser adaptado para outro tipo de banco
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/Produtos";
	
	// Conexão com o banco de dados
	public static Connection createConnectionToMySQL() throws Exception {
		
		//Faz com que a classe carregada pela JVM
		Class.forName("com.mysql.jdbc.Driver");
		
		//Cria a conexão com o banco de dados
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		
		return connection;
		
	}
	
	public static void main(String[] args) throws Exception {
		
		//Verificar se já existe conexão com o banco de dados
		Connection connectionDB = createConnectionToMySQL();
		
		//Testa se a conexão esta OK
		if (connectionDB != null) {
			System.out.println("Conexão realizada com sucesso!\n");
			connectionDB.close();
		}
	}
	
}
