package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Database;
import dao.ResumoDAO;

public class ResumoService {
	Connection conn = null;

	public List<Double> atualizarMes(String data) throws SQLException, IOException {
		Double despesas;

		conn = Database.conectar();

		List<Double> result = new ResumoDAO(conn).atualizarMes(data);
		despesas = result.get(3);
		result.set(3, result.get(0) - result.get(1) - result.get(2));
		result.add(despesas);
		result.add(result.get(3) - despesas);

		return result;
	}

	public List<String> atualizarAno(String data) throws SQLException, IOException {

		//retorna um List tipo String apenas para fins estéticos na exibição da tabela
		
		List<String> novo;
		double excedente = 0;
		double despesasMensais;
		double despesasOcasionais;
		double totalDispDespesas;

		conn = Database.conectar();

		List<Double> result = new ResumoDAO(conn).atualizarAno(data);

		for (int i = 0; i < 12; i++) {
			List<Double> totalAno = new ResumoService().atualizarMes(i + "/" + data);
			excedente += totalAno.get(5);
		}

		result.set(6, result.get(6) + excedente);
		result.set(8, result.get(8) + excedente);

		despesasOcasionais = result.get(10);
		despesasMensais = result.get(9);
		totalDispDespesas = result.get(2) - result.get(5);
		novo = new ArrayList<>();

		for (Double res : result)
			novo.add(res.toString());

		novo.set(9, "");
		novo.set(10, "");
		novo.add("" + totalDispDespesas);
		novo.add("");
		novo.add("");
		novo.add("" + despesasMensais);
		novo.add("");
		novo.add("");
		novo.add("" + despesasOcasionais);
		novo.add("");
		novo.add("");
		novo.add("" + (totalDispDespesas - despesasMensais - despesasOcasionais));

		return novo;
	}
}
