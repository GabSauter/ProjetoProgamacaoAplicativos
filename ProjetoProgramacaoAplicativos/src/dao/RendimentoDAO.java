package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Categoria;
import entities.Rendimento;

public class RendimentoDAO {
	
	private Connection conn;
	
	public RendimentoDAO(Connection conn){
		this.conn = conn;
	}
	
	public void cadastrar(Rendimento rendimento) throws SQLException {
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("INSERT INTO rendimento (categoria_id, rendimento, mensal, ocasional, total_anual) VALUES(?,?,?,?,?)");
			
			st.setInt(1, rendimento.getCategoria().getId());
			st.setString(2, rendimento.getRendimento());
			st.setDouble(3, rendimento.getMensal());
			st.setDouble(4, rendimento.getOcasional());
			st.setDouble(5, rendimento.getTotalAno());
			
			st.executeUpdate();
			
		} finally {
			Database.finalizarStatement(st);
			Database.desconectar();
		}
	}
	
	public void atualizar(Rendimento rendimento) throws SQLException {
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("UPDATE rendimento SET categoria_id = ?, rendimento = ?, mensal = ?, ocasional = ?, total_anual = ? WHERE id = ?");
			
			st.setInt(1, rendimento.getCategoria().getId());
			st.setString(2, rendimento.getRendimento());
			st.setDouble(3, rendimento.getMensal());
			st.setDouble(4, rendimento.getOcasional());
			st.setDouble(5, rendimento.getTotalAno());
			st.setInt(6, rendimento.getId());
			
			st.executeUpdate();
		} finally {
			Database.finalizarStatement(st);
			Database.desconectar();
		}
	}
	
	public int excluir(Rendimento rendimento) throws SQLException {
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("DELETE FROM rendimento WHERE id = ?");
			
			st.setInt(1, rendimento.getId());
			
			int linhasManipuladas = st.executeUpdate();
			
			return linhasManipuladas;
			
		}finally {
			Database.finalizarStatement(st);
			Database.desconectar();
		}
	}
	
	public List<Rendimento> buscarTodos() throws SQLException{
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("SELECT r.id, r.categoria_id, c.nome, r.rendimento, r.mensal, r.ocasional, r.total_anual FROM rendimento r INNER JOIN categoria c ON r.categoria_id = c.id ORDER BY r.rendimento");
			
			rs = st.executeQuery();
			
			List<Rendimento> listaRendimentos = new ArrayList<>();
			
			while (rs.next()) {
				
				Rendimento rendimento = new Rendimento();
	            Categoria categoria = new Categoria();
	            
	            categoria.setId(rs.getInt("categoria_id"));
	            categoria.setNome(rs.getString("nome"));
	            
	            rendimento.setId(rs.getInt("id"));
	            rendimento.setCategoria(categoria);
	            rendimento.setRendimento(rs.getString("rendimento"));
	            rendimento.setMensal(rs.getDouble("mensal"));
	            rendimento.setOcasional(rs.getDouble("ocasional"));
	            rendimento.setTotalAno(rs.getDouble("total_anual"));
	            
	            listaRendimentos.add(rendimento);
			}
			
			return listaRendimentos;
			
		}finally {
			Database.finalizarStatement(st);
			Database.finalizarResultSet(rs);
			Database.desconectar();
		}
	}
}
