package dao;

import java.io.IOException;
import java.sql.SQLException;

import entities.Categoria;
import entities.Rendimento;
import service.RendimentoService;

public class RendimentoDAOTeste {
	
	public static void cadastrarRendimentoTeste() throws SQLException, IOException {
		
		Categoria categoria = new Categoria();
		categoria.setId(2);
		categoria.setNome("Salário");
		
		Rendimento rendimento = new Rendimento();
		rendimento.setCategoria(categoria);
		rendimento.setRendimento("Salário Mensal");
		rendimento.setMensal(10000.00);
		rendimento.setOcasional(0.00);
		
		if(rendimento.getMensal() == 0.00)
			rendimento.setTotalAno(rendimento.getOcasional());
		else if(rendimento.getOcasional() == 0.00)
			rendimento.setTotalAno(rendimento.getMensal() * 12);
		
		new RendimentoService().cadastrar(rendimento);
		System.out.println("Cadastro de rendimento feito com sucesso.");
	}

	public static void main(String[] args) {
		
		try {
			
			cadastrarRendimentoTeste();
			
		} catch (SQLException | IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
