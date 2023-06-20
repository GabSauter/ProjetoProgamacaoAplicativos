package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Categoria;
import entities.CategoriaOrganizada;
import entities.Despesa;
import entities.FundoOcasional;
import entities.Investimento;
import entities.Rendimento;

public class RelatorioDAO {

	private Connection conn;

	public RelatorioDAO(Connection conn) {
		this.conn = conn;
	}

	public List<Rendimento> buscarRendimentoAno(String ano) throws SQLException {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement(
					"SELECT r.id, r.categoria_id, c.nome, r.rendimento, r.mensal, r.ocasional, r.total_anual, r.data FROM rendimento r INNER JOIN categoria c ON r.categoria_id = c.id WHERE SUBSTRING(data, 4, 7) = ?");

			st.setString(1, ano);

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
				rendimento.setData(rs.getString("data"));

				listaRendimentos.add(rendimento);
			}

			return listaRendimentos;

		} finally {
			Database.finalizarStatement(st);
			Database.finalizarResultSet(rs);
			Database.desconectar();
		}
	}

	public List<Despesa> buscarDespesaAno(String ano) throws SQLException {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement(
					"SELECT d.id, d.categoria_id, c.nome, d.despesa, d.mensal, d.ocasional, d.total_anual, d.data FROM despesas d INNER JOIN categoria c ON d.categoria_id = c.id WHERE SUBSTRING(data, 4, 7) = ?");

			st.setString(1, ano);

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

		} finally {
			Database.finalizarStatement(st);
			Database.finalizarResultSet(rs);
			Database.desconectar();
		}
	}

	public List<Investimento> buscarInvestimentoAno(String ano) throws SQLException {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("SELECT * FROM investimentos WHERE SUBSTRING(data, 4, 7) = ?");

			st.setString(1, ano);

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

		} finally {
			Database.finalizarStatement(st);
			Database.finalizarResultSet(rs);
			Database.desconectar();
		}
	}

	public List<FundoOcasional> buscarFundoAno(String ano) throws SQLException {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("SELECT * FROM fundo WHERE SUBSTRING(data, 4, 7) = ?");

			st.setString(1, ano);

			rs = st.executeQuery();

			List<FundoOcasional> listaFundos = new ArrayList<>();

			while (rs.next()) {

				FundoOcasional fundos = new FundoOcasional();

				fundos.setId(rs.getInt("id"));
				fundos.setFundoOcasional(rs.getString("nome"));
				fundos.setMensal(rs.getDouble("mensal"));
				fundos.setOcasional(rs.getDouble("ocasional"));
				fundos.setTotalAno(rs.getDouble("total_anual"));
				fundos.setData(rs.getString("data"));

				listaFundos.add(fundos);
			}

			return listaFundos;

		} finally {
			Database.finalizarStatement(st);
			Database.finalizarResultSet(rs);
			Database.desconectar();
		}
	}

	public List<Rendimento> buscarRendimentoMes(String data) throws SQLException {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement(
					"SELECT r.id, r.categoria_id, c.nome, r.rendimento, r.mensal, r.ocasional, r.total_anual, r.data FROM rendimento r INNER JOIN categoria c ON r.categoria_id = c.id WHERE data = ?");

			st.setString(1, data);

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
				rendimento.setData(rs.getString("data"));

				listaRendimentos.add(rendimento);
			}

			return listaRendimentos;

		} finally {
			Database.finalizarStatement(st);
			Database.finalizarResultSet(rs);
			Database.desconectar();
		}
	}

	public List<Despesa> buscarDespesaMes(String data) throws SQLException {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement(
					"SELECT d.id, d.categoria_id, c.nome, d.despesa, d.mensal, d.ocasional, d.total_anual, d.data FROM despesas d INNER JOIN categoria c ON d.categoria_id = c.id WHERE data = ?");

			st.setString(1, data);

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

		} finally {
			Database.finalizarStatement(st);
			Database.finalizarResultSet(rs);
			Database.desconectar();
		}
	}

	public List<Investimento> buscarInvestimentoMes(String data) throws SQLException {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("SELECT * FROM investimentos WHERE data = ?");

			st.setString(1, data);

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

		} finally {
			Database.finalizarStatement(st);
			Database.finalizarResultSet(rs);
			Database.desconectar();
		}
	}

	public List<FundoOcasional> buscarFundoMes(String data) throws SQLException {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("SELECT * FROM fundo WHERE data = ?");

			st.setString(1, data);

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

		} finally {
			Database.finalizarStatement(st);
			Database.finalizarResultSet(rs);
			Database.desconectar();
		}
	}

	public List<CategoriaOrganizada> buscarRendimentoMesOrganizado(String ano) throws SQLException {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement(
					"SELECT c.nome AS categoria, SUM(d.mensal) AS total_mensal, SUM(d.ocasional) AS total_ocasional, SUM(d.total_anual) AS total_anual FROM rendimento d JOIN categoria c ON d.categoria_id = c.id WHERE d.data = ? GROUP BY c.nome;");

			st.setString(1, ano);

			rs = st.executeQuery();

			List<CategoriaOrganizada> listaCategoriasOrganizadas = new ArrayList<>();

			while (rs.next()) {

				CategoriaOrganizada categoriaOrganizada = new CategoriaOrganizada();

				categoriaOrganizada.setCategoria(rs.getString("categoria"));
				categoriaOrganizada.setTotalMensal(rs.getDouble("total_mensal"));
				categoriaOrganizada.setTotalOcasional(rs.getDouble("total_ocasional"));
				categoriaOrganizada.setTotalAnual(rs.getDouble("total_anual"));

				listaCategoriasOrganizadas.add(categoriaOrganizada);
			}

			return listaCategoriasOrganizadas;

		} finally {
			Database.finalizarStatement(st);
			Database.finalizarResultSet(rs);
			Database.desconectar();
		}
	}

	public List<CategoriaOrganizada> buscarDespesaMesOrganizado(String ano) throws SQLException {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement(
					"SELECT c.nome AS categoria, SUM(d.mensal) AS total_mensal, SUM(d.ocasional) AS total_ocasional, SUM(d.total_anual) AS total_anual FROM despesas d JOIN categoria c ON d.categoria_id = c.id WHERE d.data = ? GROUP BY c.nome;");

			st.setString(1, ano);

			rs = st.executeQuery();

			List<CategoriaOrganizada> listaCategoriasOrganizadas = new ArrayList<>();

			while (rs.next()) {

				CategoriaOrganizada categoriaOrganizada = new CategoriaOrganizada();

				categoriaOrganizada.setCategoria(rs.getString("categoria"));
				categoriaOrganizada.setTotalMensal(rs.getDouble("total_mensal"));
				categoriaOrganizada.setTotalOcasional(rs.getDouble("total_ocasional"));
				categoriaOrganizada.setTotalAnual(rs.getDouble("total_anual"));

				listaCategoriasOrganizadas.add(categoriaOrganizada);
			}

			return listaCategoriasOrganizadas;

		} finally {
			Database.finalizarStatement(st);
			Database.finalizarResultSet(rs);
			Database.desconectar();
		}
	}

	public List<CategoriaOrganizada> buscarRendimentoAnoOrganizado(String ano) throws SQLException {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement(
					"SELECT c.nome AS categoria, SUM(d.mensal) AS total_mensal, SUM(d.ocasional) AS total_ocasional, SUM(d.total_anual) AS total_anual FROM rendimento d JOIN categoria c ON d.categoria_id = c.id WHERE SUBSTRING(data, 4, 7) = ? GROUP BY c.nome;");

			st.setString(1, ano);

			rs = st.executeQuery();

			List<CategoriaOrganizada> listaCategoriasOrganizadas = new ArrayList<>();

			while (rs.next()) {

				CategoriaOrganizada categoriaOrganizada = new CategoriaOrganizada();

				categoriaOrganizada.setCategoria(rs.getString("categoria"));
				categoriaOrganizada.setTotalMensal(rs.getDouble("total_mensal"));
				categoriaOrganizada.setTotalOcasional(rs.getDouble("total_ocasional"));
				categoriaOrganizada.setTotalAnual(rs.getDouble("total_anual"));

				listaCategoriasOrganizadas.add(categoriaOrganizada);
			}

			return listaCategoriasOrganizadas;

		} finally {
			Database.finalizarStatement(st);
			Database.finalizarResultSet(rs);
			Database.desconectar();
		}
	}

	public List<CategoriaOrganizada> buscarDespesaAnoOrganizado(String ano) throws SQLException {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement(
					"SELECT c.nome AS categoria, SUM(d.mensal) AS total_mensal, SUM(d.ocasional) AS total_ocasional, SUM(d.total_anual) AS total_anual FROM despesas d JOIN categoria c ON d.categoria_id = c.id WHERE SUBSTRING(data, 4, 7) = ? GROUP BY c.nome;");

			st.setString(1, ano);

			rs = st.executeQuery();

			List<CategoriaOrganizada> listaCategoriasOrganizadas = new ArrayList<>();

			while (rs.next()) {

				CategoriaOrganizada categoriaOrganizada = new CategoriaOrganizada();

				categoriaOrganizada.setCategoria(rs.getString("categoria"));
				categoriaOrganizada.setTotalMensal(rs.getDouble("total_mensal"));
				categoriaOrganizada.setTotalOcasional(rs.getDouble("total_ocasional"));
				categoriaOrganizada.setTotalAnual(rs.getDouble("total_anual"));

				listaCategoriasOrganizadas.add(categoriaOrganizada);
			}

			return listaCategoriasOrganizadas;

		} finally {
			Database.finalizarStatement(st);
			Database.finalizarResultSet(rs);
			Database.desconectar();
		}
	}
}
