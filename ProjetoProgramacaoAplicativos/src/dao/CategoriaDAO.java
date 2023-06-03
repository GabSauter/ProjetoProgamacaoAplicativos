package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Categoria;

public class CategoriaDAO {
	
	private Connection conn;
	
	public CategoriaDAO(Connection conn) {
		this.conn = conn;
	}
	
	public void cadastrar(Categoria categoria) throws SQLException {
		
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("insert into categoria (nome) values (?)");
			
			st.setString(1, categoria.getNome());;
			
			st.executeUpdate();
		} finally {
			Database.finalizarStatement(st);
			Database.desconectar();
		}
	}
	
	public void atualizar(String novoNome,Categoria categoria) throws SQLException {
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("update categoria set nome = ? where nome = ?");
			
			st.setString(1, novoNome);
			st.setString(2, categoria.getNome());
			
			st.executeUpdate();
		} finally {
			Database.finalizarStatement(st);
			Database.desconectar();
		}
	}
	
	public int excluir(Categoria categoria) throws SQLException {
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("delete from categoria where nome = ?");
			
			st.setString(1, categoria.getNome());
			
			int linhasManipuladas = st.executeUpdate();
			
			return linhasManipuladas;
			
		}finally {
			Database.finalizarStatement(st);
			Database.desconectar();
		}
	}
	
	public List<Categoria> buscarTodos() throws SQLException{
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("select * from categoria order by nome");
			
			rs = st.executeQuery();
			
			List<Categoria> listaCategorias = new ArrayList<>();
			
			while (rs.next()) {
				
				Categoria categoria = new Categoria();
				categoria.setNome(rs.getString("nome"));
				
				listaCategorias.add(categoria);
			}
			
			return listaCategorias;
			
		}finally {
			Database.finalizarStatement(st);
			Database.finalizarResultSet(rs);
			Database.desconectar();
		}
	}
}
