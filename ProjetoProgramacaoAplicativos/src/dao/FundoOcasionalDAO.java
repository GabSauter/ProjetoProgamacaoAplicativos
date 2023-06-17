package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entities.FundoOcasional;

public class FundoOcasionalDAO {
	
	private Connection conn;

	public FundoOcasionalDAO(Connection conn) {
		this.conn = conn;
	}
	
	public void cadastrar(FundoOcasional fundoOcasional) throws SQLException {
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("INSERT INTO fundo (nome, mensal, ocasional, total_anual, data) VALUES(?,?,?,?,?)");
			
			st.setString(1, fundoOcasional.getFundoOcasional());
			st.setDouble(2, fundoOcasional.getMensal());
			st.setDouble(3, fundoOcasional.getOcasional());
			st.setDouble(4, fundoOcasional.getTotalAno());
			st.setString(5, fundoOcasional.getData());
			
			st.executeUpdate();
			
		} finally {
			Database.finalizarStatement(st);
			Database.desconectar();
		}
	}
	
	public List<FundoOcasional> buscarTodos() throws SQLException{
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("SELECT * from fundo order by id");
			
			rs = st.executeQuery();
			
			List<FundoOcasional> listaFundos = new ArrayList<>();
			
			while (rs.next()) {
				FundoOcasional fundo = new FundoOcasional();
				fundo.setId(rs.getInt("id"));
				fundo.setFundoOcasional(rs.getString("nome"));
				fundo.setMensal(rs.getDouble("mensal"));
				fundo.setOcasional(rs.getDouble("ocasional"));
				fundo.setTotalAno(rs.getDouble("total_anual"));
				fundo.setData(rs.getString("data"));
				
				listaFundos.add(fundo);
			}
			
			return listaFundos;
			
		}finally {
			Database.finalizarStatement(st);
			Database.finalizarResultSet(rs);
			Database.desconectar();
		}
	}

	public void atualizar(FundoOcasional fundo) throws SQLException {
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("UPDATE fundo SET nome = ?, mensal = ?, ocasional = ?, total_anual = ?, data = ? WHERE id = ?");
	
			st.setString(1, fundo.getFundoOcasional());
			st.setDouble(2, fundo.getMensal());
			st.setDouble(3, fundo.getOcasional());
			st.setDouble(4, fundo.getTotalAno());
			st.setString(5, fundo.getData());
			st.setInt(6, fundo.getId());
			
			st.executeUpdate();
		} finally {
			Database.finalizarStatement(st);
			Database.desconectar();
		}
	}

	public int excluir(FundoOcasional fundo) throws SQLException {
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("DELETE FROM fundo WHERE id = ?");
			
			st.setInt(1, fundo.getId());
			
			int linhasManipuladas = st.executeUpdate();
			
			return linhasManipuladas;
			
		}finally {
			Database.finalizarStatement(st);
			Database.desconectar();
		}
	}
	
}
