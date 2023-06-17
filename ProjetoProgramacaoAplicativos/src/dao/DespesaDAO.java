package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Categoria;
import entities.Despesa;

public class DespesaDAO {
	
	private Connection conn;
	
	public DespesaDAO(Connection conn){
		this.conn = conn;
	}
	
	public void cadastrar(Despesa despesa) throws SQLException {
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("INSERT INTO despesas (categoria_id, despesa, mensal, ocasional, total_anual, data) VALUES(?,?,?,?,?,?)");
			
			st.setInt(1, despesa.getCategoria().getId());
			st.setString(2, despesa.getDespesa());
			st.setDouble(3, despesa.getMensal());
			st.setDouble(4, despesa.getOcasional());
			st.setDouble(5, despesa.getTotalAno());
			st.setString(6, despesa.getData());
			
			st.executeUpdate();
			
		} finally {
			Database.finalizarStatement(st);
			Database.desconectar();
		}
	}
	
	public void atualizar(Despesa despesa) throws SQLException {
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("UPDATE despesas SET categoria_id = ?, despesa = ?, mensal = ?, ocasional = ?, total_anual = ?, data = ? WHERE id = ?");
			
			st.setInt(1, despesa.getCategoria().getId());
			st.setString(2, despesa.getDespesa());
			st.setDouble(3, despesa.getMensal());
			st.setDouble(4, despesa.getOcasional());
			st.setDouble(5, despesa.getTotalAno());
			st.setString(6, despesa.getData());
			st.setInt(7, despesa.getId());
			
			st.executeUpdate();
		} finally {
			Database.finalizarStatement(st);
			Database.desconectar();
		}
	}
	
	public int excluir(Despesa despesa) throws SQLException {
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("DELETE FROM despesas WHERE id = ?");
			
			st.setInt(1, despesa.getId());
			
			int linhasManipuladas = st.executeUpdate();
			
			return linhasManipuladas;
			
		}finally {
			Database.finalizarStatement(st);
			Database.desconectar();
		}
	}
	
	public List<Despesa> buscarTodos() throws SQLException{
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("SELECT d.id, d.categoria_id, c.nome, d.despesa, d.mensal, d.ocasional, d.total_anual, d.data FROM despesas d INNER JOIN categoria c ON d.categoria_id = c.id ORDER BY d.despesa");
			
			rs = st.executeQuery();
			
			List<Despesa> listaDespesas = new ArrayList<>();
			
			while (rs.next()) {
				
				Despesa despesa = new Despesa();
	            Categoria categoria = new Categoria();
	            
	            categoria.setId(rs.getInt("categoria_id"));
	            categoria.setNome(rs.getString("nome"));
	            
	            despesa.setId(rs.getInt("id"));
	            despesa.setCategoria(categoria);
	            despesa.setDespesa(rs.getString("despesa"));
	            despesa.setMensal(rs.getDouble("mensal"));
	            despesa.setOcasional(rs.getDouble("ocasional"));
	            despesa.setTotalAno(rs.getDouble("total_anual"));
	            despesa.setData(rs.getString("data"));
	            
	            listaDespesas.add(despesa);
			}
			
			return listaDespesas;
			
		}finally {
			Database.finalizarStatement(st);
			Database.finalizarResultSet(rs);
			Database.desconectar();
		}
	}

}
