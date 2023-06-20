package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.Database;
import dao.RelatorioDAO;
import entities.CategoriaOrganizada;
import entities.Despesa;
import entities.FundoOcasional;
import entities.Investimento;
import entities.Rendimento;

public class RelatorioService {
	
	public List<Rendimento> buscarRendimentoAno(String data) throws SQLException, IOException {
		Connection conn = Database.conectar();
		return new RelatorioDAO(conn).buscarRendimentoAno(data);
	}
	
	public List<Despesa> buscarDespesaAno(String data) throws SQLException, IOException {
		Connection conn = Database.conectar();
		return new RelatorioDAO(conn).buscarDespesaAno(data);
	}
	
	public List<Investimento> buscarInvestimentoAno(String data) throws SQLException, IOException {
		Connection conn = Database.conectar();
		return new RelatorioDAO(conn).buscarInvestimentoAno(data);
	}
	
	public List<FundoOcasional> buscarFundoAno(String data) throws SQLException, IOException {
		Connection conn = Database.conectar();
		return new RelatorioDAO(conn).buscarFundoAno(data);
	}
	
	public List<Rendimento> buscarRendimentoMes(String data) throws SQLException, IOException {
		Connection conn = Database.conectar();
		return new RelatorioDAO(conn).buscarRendimentoMes(data);
	}
	
	public List<Despesa> buscarDespesaMes(String data) throws SQLException, IOException {
		Connection conn = Database.conectar();
		return new RelatorioDAO(conn).buscarDespesaMes(data);
	}
	
	public List<Investimento> buscarInvestimentoMes(String data) throws SQLException, IOException {
		Connection conn = Database.conectar();
		return new RelatorioDAO(conn).buscarInvestimentoMes(data);
	}
	
	public List<FundoOcasional> buscarFundoMes(String data) throws SQLException, IOException {
		Connection conn = Database.conectar();
		return new RelatorioDAO(conn).buscarFundoMes(data);
	}
	
	public List<CategoriaOrganizada> buscarRendimentoAnoOrganizado(String data) throws SQLException, IOException {
		Connection conn = Database.conectar();
		return new RelatorioDAO(conn).buscarRendimentoAnoOrganizado(data);
	}
	
	public List<CategoriaOrganizada> buscarDespesaAnoOrganizado(String data) throws SQLException, IOException {
		Connection conn = Database.conectar();
		return new RelatorioDAO(conn).buscarDespesaAnoOrganizado(data);
	}
	
	public List<CategoriaOrganizada> buscarRendimentoMesOrganizado(String data) throws SQLException, IOException {
		Connection conn = Database.conectar();
		return new RelatorioDAO(conn).buscarRendimentoMesOrganizado(data);
	}
	
	public List<CategoriaOrganizada> buscarDespesaMesOrganizado(String data) throws SQLException, IOException {
		Connection conn = Database.conectar();
		return new RelatorioDAO(conn).buscarDespesaMesOrganizado(data);
	}
}
