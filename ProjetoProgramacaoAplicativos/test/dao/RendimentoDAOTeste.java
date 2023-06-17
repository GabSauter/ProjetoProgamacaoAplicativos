package dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import entities.Categoria;
import entities.Rendimento;
import service.RendimentoService;

public class RendimentoDAOTeste {
	
	public static void cadastrarRendimentoTeste() throws SQLException, IOException {
		
		Categoria categoria = new Categoria();
		categoria.setId(27);
		categoria.setNome("Casa");
		
		Rendimento rendimento = new Rendimento();
		rendimento.setCategoria(categoria);
		rendimento.setRendimento("Salário Mensal");
		rendimento.setMensal(10000.00);
		rendimento.setOcasional(0.00);
		rendimento.setData("05/2023");
		
		if(rendimento.getMensal() == 0.00)
			rendimento.setTotalAno(rendimento.getOcasional());
		else if(rendimento.getOcasional() == 0.00)
			rendimento.setTotalAno(rendimento.getMensal() * 12);
		
		new RendimentoService().cadastrar(rendimento);
		System.out.println("Cadastro de rendimento feito com sucesso.");
	}
	
	public static void atualizarRendimentoTeste() throws SQLException, IOException {
		Categoria categoria = new Categoria();
		categoria.setId(27);
		categoria.setNome("Casa");
		
		Rendimento rendimento = new Rendimento();
		rendimento.setId(42);
		rendimento.setCategoria(categoria);
		rendimento.setRendimento("Salário Mensal");
		rendimento.setMensal(90000.00);
		rendimento.setOcasional(0.00);
		rendimento.setData("06/2006");
		
		if(rendimento.getMensal() == 0.00)
			rendimento.setTotalAno(rendimento.getOcasional());
		else if(rendimento.getOcasional() == 0.00)
			rendimento.setTotalAno(rendimento.getMensal() * 12);
		
		new RendimentoService().atualizar(rendimento);
		System.out.println("Atualização de rendimento feito com sucesso.");
	}
	
	public static void excluirRendimentoTeste() throws SQLException, IOException {
		
		Rendimento rendimento = new Rendimento();
		rendimento.setId(42);
		
		int linhasManipuladas = new RendimentoService().excluir(rendimento);
		if(linhasManipuladas == 0)
			System.out.println("Nenhum rendimento encontrado para exclusão");
		else
			System.out.println("Exclusão de rendimento feito com sucesso.");
	}
	
	public static void buscarTodosRendimentoTeste() throws SQLException, IOException {
		
		List<Rendimento> listaRendimentos = new RendimentoService().buscarTodos();
		
		System.out.println("Lista de rendimentos: " + listaRendimentos);
	}

	public static void main(String[] args) {
		
		try {
			//cadastrarRendimentoTeste();
			//atualizarRendimentoTeste();
			//excluirRendimentoTeste();
			buscarTodosRendimentoTeste();
			
		} catch (SQLException | IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
