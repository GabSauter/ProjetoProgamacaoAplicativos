package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.Database;
import dao.RendimentoDAO;
import entities.Rendimento;

public class RendimentoService {
	
	public void cadastrar(Rendimento rendimento) throws SQLException, IOException {
		Connection conn = Database.conectar();
		new RendimentoDAO(conn).cadastrar(rendimento);
	}
	
	public void atualizar(Rendimento rendimento) throws SQLException, IOException {
		Connection conn = Database.conectar();
		new RendimentoDAO(conn).atualizar(rendimento);
	}
	
	public int excluir(Rendimento rendimento) throws SQLException, IOException {
		Connection conn = Database.conectar();
		return new RendimentoDAO(conn).excluir(rendimento);
	}
	
	public List<Rendimento> buscarTodos() throws SQLException, IOException {
		Connection conn = Database.conectar();
		return new RendimentoDAO(conn).buscarTodos();
	}
}
