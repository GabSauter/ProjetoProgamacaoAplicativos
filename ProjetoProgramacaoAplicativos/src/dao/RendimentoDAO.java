package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import entities.Rendimento;

public class RendimentoDAO {
	
	private Connection conn;
	
	public RendimentoDAO(Connection conn){
		this.conn = conn;
	}
	
	public void cadastrar(Rendimento rendimento) throws SQLException {
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("insert into rendimento (categoria_id, rendimento, mensal, ocasional, total_anual) values(?,?,?,?,?)");
			
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
			
			st = conn.prepareStatement("update rendimento set categoria_id = ?, rendimento = ?, mensal = ?, ocasional = ?, total_anual = ? where id = ?");
			
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
			
			st = conn.prepareStatement("delete from rendimento where id = ?");
			
			st.setInt(1, rendimento.getId());
			
			int linhasManipuladas = st.executeUpdate();
			
			return linhasManipuladas;
			
		}finally {
			Database.finalizarStatement(st);
			Database.desconectar();
		}
		
	}
}
