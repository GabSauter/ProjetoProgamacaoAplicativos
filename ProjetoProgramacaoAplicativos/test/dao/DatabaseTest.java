package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseTest {

	public static void main(String[] args) {
		try {
			
			Connection conn = Database.conectar();
			System.out.println("Conexão estabelecida.");
			
			Database.desconectar();
			System.out.println("Conexão finalizada.");
			
		} catch (SQLException | IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
