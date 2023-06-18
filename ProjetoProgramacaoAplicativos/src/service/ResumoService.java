package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.Database;
import dao.ResumoDAO;

public class ResumoService {
	Connection conn = null;
	
	public List<Double> atualizarMes(String data) throws SQLException, IOException{
		conn = Database.conectar();
		return new ResumoDAO(conn).atualizarMes(data);
		
	}
}
