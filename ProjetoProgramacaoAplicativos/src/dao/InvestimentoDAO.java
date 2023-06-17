package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Investimento;

public class InvestimentoDAO {
	
	private Connection conn;
	
	public InvestimentoDAO(Connection conn){
		this.conn = conn;
	}
	
	public void cadastrar(Investimento investimento) throws SQLException {
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("INSERT INTO investimentos (nome, mensal, ocasional, total_anual, data) VALUES(?,?,?,?,?)");
			
			st.setString(1, investimento.getInvestimento());
			st.setDouble(2, investimento.getMensal());
			st.setDouble(3, investimento.getOcasional());
			st.setDouble(4, investimento.getTotalAno());
			st.setString(5, investimento.getData());
			
			st.executeUpdate();
			
		} finally {
			Database.finalizarStatement(st);
			Database.desconectar();
		}
	}
	
	public void atualizar(Investimento investimento) throws SQLException {
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("UPDATE investimentos SET nome = ?, mensal = ?, ocasional = ?, total_anual = ?, data = ? WHERE id = ?");
			
			st.setString(1, investimento.getInvestimento());
			st.setDouble(2, investimento.getMensal());
			st.setDouble(3, investimento.getOcasional());
			st.setDouble(4, investimento.getTotalAno());
			st.setString(5, investimento.getData());
			st.setInt(6, investimento.getId());
			
			st.executeUpdate();
		} finally {
			Database.finalizarStatement(st);
			Database.desconectar();
		}
	}
	
	public int excluir(Investimento investimento) throws SQLException {
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("DELETE FROM investimentos WHERE id = ?");
			
			st.setInt(1, investimento.getId());
			
			int linhasManipuladas = st.executeUpdate();
			
			return linhasManipuladas;
			
		}finally {
			Database.finalizarStatement(st);
			Database.desconectar();
		}
	}
	
	public List<Investimento> buscarTodos() throws SQLException{
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("SELECT id, nome, mensal, ocasional, total_anual, data FROM investimentos ORDER BY nome");
			
			rs = st.executeQuery();
			
			List<Investimento> listaInvestimentos = new ArrayList<>();
			
			while (rs.next()) {
				
	            Investimento investimento = new Investimento();
	            
	            investimento.setId(rs.getInt("id"));
	            investimento.setInvestimento(rs.getString("nome"));
	            investimento.setMensal(rs.getDouble("mensal"));
	            investimento.setOcasional(rs.getDouble("ocasional"));
	            investimento.setTotalAno(rs.getDouble("total_anual"));
	            investimento.setData(rs.getString("data"));
	            
	            listaInvestimentos.add(investimento);
			}
			
			return listaInvestimentos;
			
		}finally {
			Database.finalizarStatement(st);
			Database.finalizarResultSet(rs);
			Database.desconectar();
		}
	}
}
