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
			
			st = conn.prepareStatement("SELECT * FROM rendimento "
					+ "WHERE SUBSTRING(data, 4, 7) = ? OR (SUBSTRING(data, 4, 7) = ? AND SUBSTRING(data, 1, 2) != 01)");
			st.setString(1, data);
			st.setString(2, String.valueOf(Integer.parseInt(data) - 1));

			rs = st.executeQuery();

			while (rs.next()) {

				dataDiv = rs.getString("data").split("/", 2);

				if (rs.getDouble("mensal") > 0) {
					if (Integer.parseInt(dataDiv[1]) == Integer.parseInt(data))
						rendimentoMensal += (rs.getDouble("mensal") * (13 - Integer.parseInt(dataDiv[0])));
					else
						rendimentoMensal += (rs.getDouble("mensal") * (Integer.parseInt(dataDiv[0]) - 1));
				} else
					rendimentoOcasional += rs.getDouble("ocasional");
			}
			listaResumo.add(rendimentoMensal);
			listaResumo.add(rendimentoOcasional);

			rendimentoTotal = rendimentoMensal + rendimentoOcasional;
			listaResumo.add(rendimentoTotal);

			Database.finalizarStatement(st);
			Database.finalizarResultSet(rs);

			st = conn.prepareStatement("SELECT * FROM investimentos "
					+ "WHERE SUBSTRING(data, 4, 7) = ? OR (SUBSTRING(data, 4, 7) = ? AND SUBSTRING(data, 1, 2) != 01)");
			st.setString(1, data);
			st.setString(2, String.valueOf(Integer.parseInt(data) - 1));
			
			rs = st.executeQuery();

			while (rs.next()) {

				dataDiv = rs.getString("data").split("/", 2);

				if (rs.getDouble("mensal") > 0) {
					if (Integer.parseInt(dataDiv[1]) == Integer.parseInt(data))
						investimentoMensal += (rs.getDouble("mensal") * (13 - Integer.parseInt(dataDiv[0])));
					else
						investimentoMensal += (rs.getDouble("mensal") * (Integer.parseInt(dataDiv[0]) - 1));
				} else
					investimentoOcasional += rs.getDouble("ocasional");
			}
			listaResumo.add(investimentoMensal);
			listaResumo.add(investimentoOcasional);

			investimentoTotal = investimentoMensal + investimentoOcasional;
			listaResumo.add(investimentoTotal);

			Database.finalizarStatement(st);
			Database.finalizarResultSet(rs);

			st = conn.prepareStatement("SELECT * FROM fundo "
					+ "WHERE SUBSTRING(data, 4, 7) = ? OR (SUBSTRING(data, 4, 7) = ? AND SUBSTRING(data, 1, 2) != 01)");
			st.setString(1, data);
			st.setString(2, String.valueOf(Integer.parseInt(data) - 1));
			
			rs = st.executeQuery();

			while (rs.next()) {

				dataDiv = rs.getString("data").split("/", 2);

				if (rs.getDouble("mensal") > 0) {
					if (Integer.parseInt(dataDiv[1]) == Integer.parseInt(data))
						fundoMensal += (rs.getDouble("mensal") * (13 - Integer.parseInt(dataDiv[0])));
					else
						fundoMensal += (rs.getDouble("mensal") * (Integer.parseInt(dataDiv[0]) - 1));
				} else
					fundoOcasional += rs.getDouble("ocasional");
			}
			listaResumo.add(fundoMensal);
			listaResumo.add(fundoOcasional);

			fundoTotal = fundoMensal + fundoOcasional;
			listaResumo.add(fundoTotal);

			st = conn.prepareStatement("SELECT * FROM despesas "
					+ "WHERE SUBSTRING(data, 4, 7) = ? OR (SUBSTRING(data, 4, 7) = ? AND SUBSTRING(data, 1, 2) != 01)");
			st.setString(1, data);
			st.setString(2, String.valueOf(Integer.parseInt(data) - 1));
			
			rs = st.executeQuery();

			while (rs.next()) {

				dataDiv = rs.getString("data").split("/", 2);

				if (rs.getDouble("mensal") > 0) {
					if (Integer.parseInt(dataDiv[1]) == Integer.parseInt(data))
						despesaMensal += (rs.getDouble("mensal") * (13 - Integer.parseInt(dataDiv[0])));
					else
						despesaMensal += (rs.getDouble("mensal") * (Integer.parseInt(dataDiv[0]) - 1));
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

		double rendimento = 0;
		double investimentos = 0;
		double fundoOcasional = 0;
		double despesas = 0;

		List<Double> listaResumo = new ArrayList<Double>();

		try {

			st = conn.prepareStatement("SELECT * FROM rendimento "
					+ "WHERE (SUBSTRING(data, 4, 7) = ? AND SUBSTRING(data, 1, 2) <= ? AND mensal > 0) "
					+ "OR (YEAR(STR_TO_DATE(data, '%m/%Y')) = ? AND MONTH(STR_TO_DATE(data, '%m/%Y')) > ? AND mensal > 0)");
			st.setString(1, dataDiv[1]);
			st.setString(2, dataDiv[0]);
			st.setString(3, String.valueOf(Integer.parseInt(dataDiv[1]) - 1));
			st.setString(4, dataDiv[0]);

			rs = st.executeQuery();

			while (rs.next()) {
				rendimento += rs.getDouble("mensal");
			}

			listaResumo.add(rendimento);

			Database.finalizarStatement(st);
			Database.finalizarResultSet(rs);

			st = conn.prepareStatement("SELECT * FROM investimentos "
					+ "WHERE (SUBSTRING(data, 4, 7) = ? AND SUBSTRING(data, 1, 2) <= ? AND mensal > 0) OR "
					+ "(YEAR(STR_TO_DATE(data, '%m/%Y')) = ? AND MONTH(STR_TO_DATE(data, '%m/%Y')) > ? AND mensal > 0)");
			st.setString(1, dataDiv[1]);
			st.setString(2, dataDiv[0]);
			st.setString(3, String.valueOf(Integer.parseInt(dataDiv[1]) - 1));
			st.setString(4, dataDiv[0]);

			rs = st.executeQuery();

			while (rs.next()) {
				investimentos += rs.getDouble("mensal");
			}

			listaResumo.add(investimentos);

			Database.finalizarStatement(st);
			Database.finalizarResultSet(rs);

			st = conn.prepareStatement("SELECT * FROM fundo "
					+ "WHERE (SUBSTRING(data, 4, 7) = ? AND SUBSTRING(data, 1, 2) <= ? AND mensal > 0) OR "
					+ "(YEAR(STR_TO_DATE(data, '%m/%Y')) = ? AND MONTH(STR_TO_DATE(data, '%m/%Y')) > ? AND mensal > 0)");
			st.setString(1, dataDiv[1]);
			st.setString(2, dataDiv[0]);
			st.setString(3, String.valueOf(Integer.parseInt(dataDiv[1]) - 1));
			st.setString(4, dataDiv[0]);

			rs = st.executeQuery();

			while (rs.next()) {
				fundoOcasional += rs.getDouble("mensal");
			}

			listaResumo.add(fundoOcasional);

			Database.finalizarStatement(st);
			Database.finalizarResultSet(rs);

			st = conn.prepareStatement("SELECT * FROM despesas "
					+ "WHERE (SUBSTRING(data, 4, 7) = ? AND SUBSTRING(data, 1, 2) <= ? AND mensal > 0) OR "
					+ "(YEAR(STR_TO_DATE(data, '%m/%Y')) = ? AND MONTH(STR_TO_DATE(data, '%m/%Y')) > ? AND mensal > 0)");
			st.setString(1, dataDiv[1]);
			st.setString(2, dataDiv[0]);
			st.setString(3, String.valueOf(Integer.parseInt(dataDiv[1]) - 1));
			st.setString(4, dataDiv[0]);

			rs = st.executeQuery();

			while (rs.next()) {
				despesas += rs.getDouble("mensal");

			}

			listaResumo.add(despesas);

			return listaResumo;

		} finally {

			Database.finalizarStatement(st);
			Database.finalizarResultSet(rs);
			Database.desconectar();

		}

	}
}