package dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import entities.Categoria;
import entities.Despesa;
import service.DespesaService;

public class DespesaDAOTeste {

	public static void cadastrarDespesaTeste() throws SQLException, IOException {

		Categoria categoria = new Categoria();
		categoria.setId(27);
		categoria.setNome("Casa");

		Despesa despesa = new Despesa();
		despesa.setCategoria(categoria);
		despesa.setDespesa("Pagar aluguel");
		despesa.setMensal(10000.00);
		despesa.setOcasional(0.00);
		despesa.setData("05/2023");

		if (despesa.getMensal() == 0.00)
			despesa.setTotalAno(despesa.getOcasional());
		else if (despesa.getOcasional() == 0.00)
			despesa.setTotalAno(despesa.getMensal() * 12);

		new DespesaService().cadastrar(despesa);
		System.out.println("Cadastro de despesa feito com sucesso.");
	}

	public static void atualizarDespesaTeste() throws SQLException, IOException {
		Categoria categoria = new Categoria();
		categoria.setId(27);
		categoria.setNome("Casa");

		Despesa despesa = new Despesa();
		despesa.setId(3);
		despesa.setCategoria(categoria);
		despesa.setDespesa("Salário Mensal");
		despesa.setMensal(90000.00);
		despesa.setOcasional(0.00);
		despesa.setData("06/2006");

		if (despesa.getMensal() == 0.00)
			despesa.setTotalAno(despesa.getOcasional());
		else if (despesa.getOcasional() == 0.00)
			despesa.setTotalAno(despesa.getMensal() * 12);

		new DespesaService().atualizar(despesa);
		System.out.println("Atualização de despesa feito com sucesso.");
	}

	public static void excluirDespesaTeste() throws SQLException, IOException {

		Despesa despesa = new Despesa();
		despesa.setId(3);

		int linhasManipuladas = new DespesaService().excluir(despesa);
		if (linhasManipuladas == 0)
			System.out.println("Nenhum despesa encontrado para exclusão");
		else
			System.out.println("Exclusão de despesa feito com sucesso.");
	}

	public static void buscarTodosDespesaTeste() throws SQLException, IOException {

		List<Despesa> listaRendimentos = new DespesaService().buscarTodos();

		System.out.println("Lista de despesas: " + listaRendimentos);
	}

	public static void main(String[] args) {

		try {
			// cadastrarDespesaTeste();
			// atualizarDespesaTeste();
			// excluirDespesaTeste();
			buscarTodosDespesaTeste();

		} catch (SQLException | IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
