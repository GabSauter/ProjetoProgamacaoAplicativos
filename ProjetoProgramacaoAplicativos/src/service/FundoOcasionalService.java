package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.Database;
import dao.FundoOcasionalDAO;
import entities.FundoOcasional;

public class FundoOcasionalService {

	public void cadastrar(FundoOcasional fundo) throws SQLException, IOException {
		Connection conn = Database.conectar();
		new FundoOcasionalDAO(conn).cadastrar(fundo);
	}
	
	public List<FundoOcasional> buscarTodos() throws SQLException, IOException {
		Connection conn = Database.conectar();
		return new FundoOcasionalDAO(conn).buscarTodos();
	}

	public void atualizar(FundoOcasional fundoEditado) throws SQLException, IOException {
		Connection conn = Database.conectar();
		new FundoOcasionalDAO(conn).atualizar(fundoEditado);
		
	}

	public void excluir(FundoOcasional fundo) throws SQLException, IOException {
		Connection conn = Database.conectar();
		new FundoOcasionalDAO(conn).excluir(fundo);
		
	}
}
