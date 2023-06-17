package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.Database;
import dao.DespesaDAO;
import entities.Despesa;

public class DespesaService {
	public void cadastrar(Despesa despesa) throws SQLException, IOException {
		Connection conn = Database.conectar();
		new DespesaDAO(conn).cadastrar(despesa);
	}
	
	public void atualizar(Despesa despesa) throws SQLException, IOException {
		Connection conn = Database.conectar();
		new DespesaDAO(conn).atualizar(despesa);
	}
	
	public int excluir(Despesa despesa) throws SQLException, IOException {
		Connection conn = Database.conectar();
		return new DespesaDAO(conn).excluir(despesa);
	}
	
	public List<Despesa> buscarTodos() throws SQLException, IOException {
		Connection conn = Database.conectar();
		return new DespesaDAO(conn).buscarTodos();
	}
}
