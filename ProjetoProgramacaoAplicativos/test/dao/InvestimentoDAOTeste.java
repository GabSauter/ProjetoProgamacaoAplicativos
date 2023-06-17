package dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import entities.Investimento;
import service.InvestimentoService;

public class InvestimentoDAOTeste {
	public static void cadastrarInvestimentoTeste() throws SQLException, IOException {
		
		Investimento investimento = new Investimento();
		investimento.setInvestimento("Pagar aluguel");
		investimento.setMensal(10000.00);
		investimento.setOcasional(0.00);
		investimento.setData("05/2023");
		
		if(investimento.getMensal() == 0.00)
			investimento.setTotalAno(investimento.getOcasional());
		else if(investimento.getOcasional() == 0.00)
			investimento.setTotalAno(investimento.getMensal() * 12);
		
		new InvestimentoService().cadastrar(investimento);
		System.out.println("Cadastro de investimento feito com sucesso.");
	}
	
	public static void atualizarInvestimentoTeste() throws SQLException, IOException {
		
		Investimento investimento = new Investimento();
		investimento.setId(4);
		investimento.setInvestimento("Salário Mensal");
		investimento.setMensal(90000.00);
		investimento.setOcasional(0.00);
		investimento.setData("06/2006");
		
		if(investimento.getMensal() == 0.00)
			investimento.setTotalAno(investimento.getOcasional());
		else if(investimento.getOcasional() == 0.00)
			investimento.setTotalAno(investimento.getMensal() * 12);
		
		new InvestimentoService().atualizar(investimento);
		System.out.println("Atualização de investimento feito com sucesso.");
	}
	
	public static void excluirInvestimentoTeste() throws SQLException, IOException {
		
		Investimento investimento = new Investimento();
		investimento.setId(4);
		
		int linhasManipuladas = new InvestimentoService().excluir(investimento);
		if(linhasManipuladas == 0)
			System.out.println("Nenhum investimento encontrado para exclusão");
		else
			System.out.println("Exclusão de investimento feito com sucesso.");
	}
	
	public static void buscarTodosInvestimentoTeste() throws SQLException, IOException {
		
		List<Investimento> listaRendimentos = new InvestimentoService().buscarTodos();
		
		System.out.println("Lista de investimentos: " + listaRendimentos);
	}

	public static void main(String[] args) {
		
		try {
			//cadastrarInvestimentoTeste();
			//atualizarInvestimentoTeste();
			excluirInvestimentoTeste();
			//buscarTodosInvestimentoTeste();
			
		} catch (SQLException | IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
