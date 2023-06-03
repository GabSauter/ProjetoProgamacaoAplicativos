package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.CategoriaDAO;
import dao.Database;
import entities.Categoria;

public class CategoriaService {
	
	public void cadastrar(Categoria categoria) throws SQLException, IOException {
		Connection conn = Database.conectar();
		new CategoriaDAO(conn).cadastrar(categoria);
	}
	
	public void atualizar(String novoNome, Categoria categoria) throws SQLException, IOException {
		Connection conn = Database.conectar();
		new CategoriaDAO(conn).atualizar(novoNome, categoria);
	}
	
	public int excluir(Categoria categoria) throws SQLException, IOException {
		Connection conn = Database.conectar();
		return new CategoriaDAO(conn).excluir(categoria);
	}
	
	public List<Categoria> buscarTodos() throws SQLException, IOException {
		Connection conn = Database.conectar();
		return new CategoriaDAO(conn).buscarTodos();
	}
}
