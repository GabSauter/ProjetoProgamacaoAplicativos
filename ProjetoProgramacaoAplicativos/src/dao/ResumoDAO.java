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

	// O processo de coleta no DB requer muitas verificações, porém tentei enviar
	// apenas o necessário para a service, que será a responsável
	// pelos demais cálculos

	public List<Double> atualizarAno(String data) throws SQLException {

		List<Double> listaResumo = new ArrayList<>();
		String[] dataDiv;
		PreparedStatement st = null;
		ResultSet rs = null;
		double rendimentoMensal = 0;
		double rendimentoOcasional = 0;
		double rendimentoTotal;
		double investimentoMensal = 0;
		double investimentoOcasional = 0;
		double investimentoTotal;
		double fundoMensal = 0;
		double fundoOcasional = 0;
		double fundoTotal;
		double despesaMensal = 0;
		double despesaOcasional = 0;

		try {
			st = conn.prepareStatement(
					"SELECT * " + "FROM rendimento " + "WHERE YEAR(STR_TO_DATE(data, '%m/%Y')) = ? OR "
							+ "(YEAR(STR_TO_DATE(data, '%m/%Y')) = ? AND MONTH(STR_TO_DATE(data, '%m/%Y')) != 01);");
			st.setInt(1, Integer.parseInt(data));
			st.setInt(2, Integer.parseInt(data) - 1);
			rs = st.executeQuery();

			while (rs.next()) {

				dataDiv = rs.getString("data").split("/", 2);

				if (rs.getDouble("mensal") > 0) {
					if (Integer.parseInt(dataDiv[1]) == Integer.parseInt(data))
						rendimentoMensal += (rs.getDouble("mensal") * (13 - Double.parseDouble(dataDiv[0])));
					else
						rendimentoMensal += (rs.getDouble("mensal") * (Double.parseDouble(dataDiv[0]) - 1));
				} else
					rendimentoOcasional += rs.getDouble("ocasional");
			}
			listaResumo.add(rendimentoMensal);
			listaResumo.add(rendimentoOcasional);

			rendimentoTotal = rendimentoMensal + rendimentoOcasional;
			listaResumo.add(rendimentoTotal);

			Database.finalizarStatement(st);
			Database.finalizarResultSet(rs);

			st = conn.prepareStatement(
					"SELECT * " + "FROM investimentos " + "WHERE YEAR(STR_TO_DATE(data, '%m/%Y')) = ? OR "
							+ "(YEAR(STR_TO_DATE(data, '%m/%Y')) = ? AND MONTH(STR_TO_DATE(data, '%m/%Y')) != 01);");
			st.setInt(1, Integer.parseInt(data));
			st.setInt(2, Integer.parseInt(data) - 1);
			rs = st.executeQuery();

			while (rs.next()) {

				dataDiv = rs.getString("data").split("/", 2);

				if (rs.getDouble("mensal") > 0) {
					if (Integer.parseInt(dataDiv[1]) == Integer.parseInt(data))
						investimentoMensal += (rs.getDouble("mensal") * (13 - Double.parseDouble(dataDiv[0])));
					else
						investimentoMensal += (rs.getDouble("mensal") * (Double.parseDouble(dataDiv[0]) - 1));
				} else
					investimentoOcasional += rs.getDouble("ocasional");
			}
			listaResumo.add(investimentoMensal);
			listaResumo.add(investimentoOcasional);

			investimentoTotal = investimentoMensal + investimentoOcasional;
			listaResumo.add(investimentoTotal);

			Database.finalizarStatement(st);
			Database.finalizarResultSet(rs);

			st = conn.prepareStatement("SELECT * " + "FROM fundo " + "WHERE YEAR(STR_TO_DATE(data, '%m/%Y')) = ? OR "
					+ "(YEAR(STR_TO_DATE(data, '%m/%Y')) = ? AND MONTH(STR_TO_DATE(data, '%m/%Y')) != 01);");
			st.setInt(1, Integer.parseInt(data));
			st.setInt(2, Integer.parseInt(data) - 1);
			rs = st.executeQuery();

			while (rs.next()) {

				dataDiv = rs.getString("data").split("/", 2);

				if (rs.getDouble("mensal") > 0) {
					if (Integer.parseInt(dataDiv[1]) == Integer.parseInt(data))
						fundoMensal += (rs.getDouble("mensal") * (13 - Double.parseDouble(dataDiv[0])));
					else
						fundoMensal += (rs.getDouble("mensal") * (Double.parseDouble(dataDiv[0]) - 1));
				} else
					fundoOcasional += rs.getDouble("ocasional");
			}
			listaResumo.add(fundoMensal);
			listaResumo.add(fundoOcasional);

			fundoTotal = fundoMensal + fundoOcasional;
			listaResumo.add(fundoTotal);

			st = conn.prepareStatement("SELECT * " + "FROM despesas " + "WHERE YEAR(STR_TO_DATE(data, '%m/%Y')) = ? OR "
					+ "(YEAR(STR_TO_DATE(data, '%m/%Y')) = ? AND MONTH(STR_TO_DATE(data, '%m/%Y')) != 01);");
			st.setInt(1, Integer.parseInt(data));
			st.setInt(2, Integer.parseInt(data) - 1);
			rs = st.executeQuery();

			while (rs.next()) {

				dataDiv = rs.getString("data").split("/", 2);

				if (rs.getDouble("mensal") > 0) {
					if (Integer.parseInt(dataDiv[1]) == Integer.parseInt(data))
						despesaMensal += (rs.getDouble("mensal") * (13 - Double.parseDouble(dataDiv[0])));
					else
						despesaMensal += (rs.getDouble("mensal") * (Double.parseDouble(dataDiv[0]) - 1));
				} else
					despesaOcasional += rs.getDouble("ocasional");
			}

			listaResumo.add(despesaMensal);
			listaResumo.add(despesaOcasional);

			Database.finalizarStatement(st);
			Database.finalizarResultSet(rs);

			return listaResumo;
		} finally {
			Database.finalizarStatement(st);
			Database.finalizarResultSet(rs);
			Database.desconectar();
		}

	}

	public List<Double> atualizarMes(String data) throws SQLException {
		String[] dataDiv = data.split("/", 2);
		PreparedStatement st = null;
		ResultSet rs = null;
		double rendimento;
		double investimentos;
		double ocasional;
		double totalDispDespesas;
		double despesas;
		double valorTotal;
		double total = 0;
		List<Double> listaResumo = new ArrayList<>();
		try {

			st = conn.prepareStatement("SELECT * FROM rendimento "
					+ "WHERE (YEAR(STR_TO_DATE(data, '%m/%Y')) = ? AND MONTH(STR_TO_DATE(data, '%m/%Y')) <= ? AND mensal > 0) "
					+ "OR (YEAR(STR_TO_DATE(data, '%m/%Y')) < ? AND MONTH(STR_TO_DATE(data, '%m/%Y')) >= ? AND mensal > 0) "
					+ "OR STR_TO_DATE(data, '%m/%Y') = STR_TO_DATE(?, '%m/%Y')");
			st.setInt(1, Integer.parseInt(dataDiv[1]));
			st.setInt(2, Integer.parseInt(dataDiv[0]));
			st.setInt(3, Integer.parseInt(dataDiv[1]));
			st.setInt(4, Integer.parseInt(dataDiv[0]));
			st.setString(5, data);

			rs = st.executeQuery();

			while (rs.next()) {
				total += rs.getDouble("mensal");
			}
			rendimento = total;
			listaResumo.add(total);
			total = 0;
			Database.finalizarStatement(st);
			Database.finalizarResultSet(rs);

			st = conn.prepareStatement("SELECT * FROM investimentos "
					+ "WHERE (YEAR(STR_TO_DATE(data, '%m/%Y')) = ? AND MONTH(STR_TO_DATE(data, '%m/%Y')) <= ? AND mensal > 0) "
					+ "OR (YEAR(STR_TO_DATE(data, '%m/%Y')) < ? AND MONTH(STR_TO_DATE(data, '%m/%Y')) >= ? AND mensal > 0) "
					+ "OR (STR_TO_DATE(data, '%m/%Y') = STR_TO_DATE(?, '%m/%Y') AND mensal > 0)");
			st.setInt(1, Integer.parseInt(dataDiv[1]));
			st.setInt(2, Integer.parseInt(dataDiv[0]));
			st.setInt(3, Integer.parseInt(dataDiv[1]));
			st.setInt(4, Integer.parseInt(dataDiv[0]));
			st.setString(5, data);

			rs = st.executeQuery();

			while (rs.next()) {
				total += rs.getDouble("mensal");
			}
			investimentos = total;
			listaResumo.add(total);
			total = 0;
			Database.finalizarStatement(st);
			Database.finalizarResultSet(rs);

			st = conn.prepareStatement("SELECT * FROM fundo "
					+ "WHERE (YEAR(STR_TO_DATE(data, '%m/%Y')) = ? AND MONTH(STR_TO_DATE(data, '%m/%Y')) <= ? AND mensal > 0) "
					+ "OR (YEAR(STR_TO_DATE(data, '%m/%Y')) < ? AND MONTH(STR_TO_DATE(data, '%m/%Y')) >= ? AND mensal > 0) "
					+ "OR (STR_TO_DATE(data, '%m/%Y') = STR_TO_DATE(?, '%m/%Y') AND mensal > 0)");
			st.setInt(1, Integer.parseInt(dataDiv[1]));
			st.setInt(2, Integer.parseInt(dataDiv[0]));
			st.setInt(3, Integer.parseInt(dataDiv[1]));
			st.setInt(4, Integer.parseInt(dataDiv[0]));
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

			Database.finalizarStatement(st);
			Database.finalizarResultSet(rs);

			st = conn.prepareStatement("SELECT * FROM despesas "
					+ "WHERE (YEAR(STR_TO_DATE(data, '%m/%Y')) = ? AND MONTH(STR_TO_DATE(data, '%m/%Y')) <= ? AND mensal > 0) "
					+ "OR (YEAR(STR_TO_DATE(data, '%m/%Y')) < ? AND MONTH(STR_TO_DATE(data, '%m/%Y')) >= ? AND mensal > 0) "
					+ "OR (STR_TO_DATE(data, '%m/%Y') = STR_TO_DATE(?, '%m/%Y') AND mensal > 0)");
			st.setInt(1, Integer.parseInt(dataDiv[1]));
			st.setInt(2, Integer.parseInt(dataDiv[0]));
			st.setInt(3, Integer.parseInt(dataDiv[1]));
			st.setInt(4, Integer.parseInt(dataDiv[0]));
			st.setString(5, data);

			rs = st.executeQuery();

			while (rs.next()) {
				total += rs.getDouble("mensal");
			}

			despesas = total;
			listaResumo.add(total);

			valorTotal = totalDispDespesas - despesas;
			listaResumo.add(valorTotal);

			return listaResumo;

		} finally {
			Database.finalizarStatement(st);
			Database.finalizarResultSet(rs);
			Database.desconectar();
		}

	}
}