package dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import entities.CategoriaOrganizada;
import entities.Despesa;
import entities.FundoOcasional;
import entities.Investimento;
import entities.Rendimento;
import service.RelatorioService;

public class RelatorioDAOTeste {

	public static void buscarRendimentoAnoTeste() throws SQLException, IOException {

		List<Rendimento> listaRendimentosAno = new RelatorioService().buscarRendimentoAno("2023");

		System.out.println("Lista de rendimentos do ano: " + listaRendimentosAno);
	}

	public static void buscarDespesaAnoTeste() throws SQLException, IOException {

		List<Despesa> listaDespesaAno = new RelatorioService().buscarDespesaAno("2023");

		System.out.println("Lista de despesas do ano: " + listaDespesaAno);
	}

	public static void buscarInvestimentoAnoTeste() throws SQLException, IOException {

		List<Investimento> listaInvestimentoAno = new RelatorioService().buscarInvestimentoAno("2023");

		System.out.println("Lista de investimentos do ano: " + listaInvestimentoAno);
	}

	public static void buscarFundoAnoTeste() throws SQLException, IOException {

		List<FundoOcasional> listaFundosAno = new RelatorioService().buscarFundoAno("2023");

		System.out.println("Lista de fundos do ano: " + listaFundosAno);
	}
	
	public static void buscarRendimentoMesTeste() throws SQLException, IOException {

		List<Rendimento> listaRendimentosMes = new RelatorioService().buscarRendimentoMes("06/2023");

		System.out.println("Lista de rendimentos do mes: " + listaRendimentosMes);
	}

	public static void buscarDespesaMesTeste() throws SQLException, IOException {

		List<Despesa> listaDespesaMes = new RelatorioService().buscarDespesaMes("06/2023");

		System.out.println("Lista de despesas do mes: " + listaDespesaMes);
	}
	
	public static void buscarInvestimentoMesTeste() throws SQLException, IOException {

		List<Investimento> listaMes = new RelatorioService().buscarInvestimentoMes("06/2023");

		System.out.println("Lista de investimentos do mes: " + listaMes);
	}
	
	public static void buscarFundoMesTeste() throws SQLException, IOException {

		List<FundoOcasional> listaMes = new RelatorioService().buscarFundoMes("06/2023");

		System.out.println("Lista de fundos do mes: " + listaMes);
	}
	
	public static void buscarRendimentoMesOrganizadoTeste() throws SQLException, IOException {

		List<CategoriaOrganizada> listaMes = new RelatorioService().buscarRendimentoMesOrganizado("06/2023");

		System.out.println("Lista de categorias organizadas do mes: " + listaMes);
	}
	
	public static void buscarDespesaMesOrganizadoTeste() throws SQLException, IOException {

		List<CategoriaOrganizada> listaMes = new RelatorioService().buscarDespesaMesOrganizado("06/2023");

		System.out.println("Lista de categorias organizadas do mes: " + listaMes);
	}
	
	public static void buscarRendimentoAnoOrganizadoTeste() throws SQLException, IOException {

		List<CategoriaOrganizada> listaMes = new RelatorioService().buscarRendimentoAnoOrganizado("2023");

		System.out.println("Lista de categorias organizadas do ano: " + listaMes);
	}
	
	public static void buscarDespesaAnoOrganizadoTeste() throws SQLException, IOException {

		List<CategoriaOrganizada> listaMes = new RelatorioService().buscarDespesaAnoOrganizado("2023");

		System.out.println("Lista de categorias organizadas do ano: " + listaMes);
	}
	
	public static void main(String[] args) {
		try {
//	        buscarRendimentoAnoTeste();
//	        buscarDespesaAnoTeste();
//	        buscarInvestimentoAnoTeste();
//	        buscarFundoAnoTeste();
//	        buscarRendimentoMesTeste();
//	        buscarDespesaMesTeste();
//	        buscarInvestimentoMesTeste();
//	        buscarFundoMesTeste();
	        buscarRendimentoMesOrganizadoTeste();
	        buscarDespesaMesOrganizadoTeste();
//	        buscarRendimentoAnoOrganizadoTeste();
//	        buscarDespesaAnoOrganizadoTeste();
	    } catch (SQLException | IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
