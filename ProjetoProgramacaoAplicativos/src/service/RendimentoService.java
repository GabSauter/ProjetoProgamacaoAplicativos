package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.Database;
import dao.RendimentoDAO;
import entities.Rendimento;

public class RendimentoService {
	
	public void cadastrar(Rendimento rendimento) throws SQLException, IOException {
		Connection conn = Database.conectar();
		new RendimentoDAO(conn).cadastrar(rendimento);
	}
}
