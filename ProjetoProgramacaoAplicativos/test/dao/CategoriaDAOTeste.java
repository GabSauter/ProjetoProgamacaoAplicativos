package dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import entities.Categoria;
import service.CategoriaService;

public class CategoriaDAOTeste {
	
	public static void cadastrarCategoriaTeste() throws SQLException, IOException {
		Categoria categoria = new Categoria();
		categoria.setNome("Venda");
		
		new CategoriaService().cadastrar(categoria);
		System.out.println("Cadastro de categoria feito com sucesso.");
	}
	
	public static void atualizarCategoriaTeste() throws SQLException, IOException {
		Categoria categoria = new Categoria();
		categoria.setNome("Venda");
		String novoNome = "Salário";
		
		new CategoriaService().atualizar(novoNome, categoria);
		System.out.println("Atualização de categoria feito com sucesso.");
	}
	
	public static void excluirCategoriaTeste() throws SQLException, IOException {
		Categoria categoria = new Categoria();
		categoria.setNome("Salário");
		
		int linhasManipuladas = new CategoriaService().excluir(categoria);
		if(linhasManipuladas == 0)
			System.out.println("Nenhuma categoria encontrada para exclusão");
		else
			System.out.println("Exclusão de categoria feito com sucesso.");
	}
	
	public static void buscarTodosCategoriaTeste() throws SQLException, IOException {
		
		List<Categoria> listaCategoria = new CategoriaService().buscarTodos();
		
		System.out.println("Lista de categorias: " + listaCategoria);
	}

	public static void main(String[] args) {
		
		try {
			//cadastrarCategoriaTeste();
			//atualizarCategoriaTeste();
			//excluirCategoriaTeste();
			buscarTodosCategoriaTeste();
		} catch (SQLException | IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
