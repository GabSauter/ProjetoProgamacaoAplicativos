package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.Database;
import dao.InvestimentoDAO;
import entities.Investimento;

public class InvestimentoService {
	public void cadastrar(Investimento investimento) throws SQLException, IOException {
		Connection conn = Database.conectar();
		new InvestimentoDAO(conn).cadastrar(investimento);
	}
	
	public void atualizar(Investimento investimento) throws SQLException, IOException {
		Connection conn = Database.conectar();
		new InvestimentoDAO(conn).atualizar(investimento);
	}
	
	public int excluir(Investimento investimento) throws SQLException, IOException {
		Connection conn = Database.conectar();
		return new InvestimentoDAO(conn).excluir(investimento);
	}
	
	public List<Investimento> buscarTodos() throws SQLException, IOException {
		Connection conn = Database.conectar();
		return new InvestimentoDAO(conn).buscarTodos();
	}
}
