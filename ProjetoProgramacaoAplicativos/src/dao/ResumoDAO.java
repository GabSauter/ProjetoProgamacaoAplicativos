package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResumoDAO {
	Connection conn;

	public ResumoDAO(Connection conn) {
		this.conn = conn;
	}

	public List<Double> atualizarMes(String data) throws SQLException {
		
		String[] date = data.split("/", 2);
		PreparedStatement st = null;
		ResultSet rs = null;
		
		double rendimento;
		double investimentos;
		double ocasional;
		double totalDispDespesas;
		double despesas;
		double valorTotal;
		double total = 0;
		
		List<Double> listaResumo = new ArrayList<Double>();
		
		try {

			st = conn.prepareStatement(
					"SELECT * FROM rendimento WHERE (YEAR(STR_TO_DATE(data, '%m/%Y')) = ? AND MONTH(STR_TO_DATE(data, '%m/%Y')) <= ? AND mensal > 0) "
							+ "OR (YEAR(STR_TO_DATE(data, '%m/%Y')) < ? AND MONTH(STR_TO_DATE(data, '%m/%Y')) >= ? AND mensal > 0) OR STR_TO_DATE(data, '%m/%Y') = STR_TO_DATE(?, '%m/%Y')");
			
			st.setInt(1, Integer.parseInt(date[1]));
			st.setInt(2, Integer.parseInt(date[0]));
			st.setInt(3, Integer.parseInt(date[1]));
			st.setInt(4, Integer.parseInt(date[0]));
			st.setString(5, data);

			rs = st.executeQuery();

			while (rs.next()) {
				total += rs.getDouble("mensal");
			}
			
			rendimento = total;
			listaResumo.add(total);
			total = 0;

			st = conn.prepareStatement(
					"SELECT * FROM investimentos WHERE (YEAR(STR_TO_DATE(data, '%m/%Y')) = ? AND MONTH(STR_TO_DATE(data, '%m/%Y')) <= ? AND mensal > 0) "
							+ "OR (YEAR(STR_TO_DATE(data, '%m/%Y')) < ? AND MONTH(STR_TO_DATE(data, '%m/%Y')) >= ? AND mensal > 0) OR (STR_TO_DATE(data, '%m/%Y') = STR_TO_DATE(?, '%m/%Y') AND mensal > 0)");
			st.setInt(1, Integer.parseInt(date[1]));
			st.setInt(2, Integer.parseInt(date[0]));
			st.setInt(3, Integer.parseInt(date[1]));
			st.setInt(4, Integer.parseInt(date[0]));
			st.setString(5, data);

			rs = st.executeQuery();

			while (rs.next()) {
				total += rs.getDouble("mensal");
			}
			investimentos = total;
			listaResumo.add(total);
			total = 0;

			st = conn.prepareStatement(
					"SELECT * FROM fundo WHERE (YEAR(STR_TO_DATE(data, '%m/%Y')) = ? AND MONTH(STR_TO_DATE(data, '%m/%Y')) <= ? AND mensal > 0) "
							+ "OR (YEAR(STR_TO_DATE(data, '%m/%Y')) < ? AND MONTH(STR_TO_DATE(data, '%m/%Y')) >= ? AND mensal > 0) OR (STR_TO_DATE(data, '%m/%Y') = STR_TO_DATE(?, '%m/%Y') AND mensal > 0)");
			st.setInt(1, Integer.parseInt(date[1]));
			st.setInt(2, Integer.parseInt(date[0]));
			st.setInt(3, Integer.parseInt(date[1]));
			st.setInt(4, Integer.parseInt(date[0]));
			st.setString(5, data);

			rs = st.executeQuery();

			while (rs.next()) {
				total += rs.getDouble("mensal") + rs.getDouble("ocasional");
			}
			ocasional = total;
			listaResumo.add(total);

			totalDispDespesas = rendimento - investimentos - ocasional;
			listaResumo.add(totalDispDespesas);
			total = 0;

			st = conn.prepareStatement(
					"SELECT * FROM despesas WHERE (YEAR(STR_TO_DATE(data, '%m/%Y')) = ? AND MONTH(STR_TO_DATE(data, '%m/%Y')) <= ? AND mensal > 0) "
							+ "OR (YEAR(STR_TO_DATE(data, '%m/%Y')) < ? AND MONTH(STR_TO_DATE(data, '%m/%Y')) >= ? AND mensal > 0) OR (STR_TO_DATE(data, '%m/%Y') = STR_TO_DATE(?, '%m/%Y') AND mensal > 0)");
			st.setInt(1, Integer.parseInt(date[1]));
			st.setInt(2, Integer.parseInt(date[0]));
			st.setInt(3, Integer.parseInt(date[1]));
			st.setInt(4, Integer.parseInt(date[0]));
			st.setString(5, data);

			rs = st.executeQuery();

			while (rs.next()) {
				total += rs.getDouble("mensal");
				System.out.println(total + " " + rs.getString("despesa"));
			}
			System.out.println(total);
			despesas = total;
			listaResumo.add(total);

			valorTotal = totalDispDespesas - despesas;
			listaResumo.add(valorTotal);

			return listaResumo;

		} finally {

		}

	}
}