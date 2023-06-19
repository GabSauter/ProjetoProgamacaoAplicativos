package gui;

import java.awt.EventQueue;

public class DespesaWindowTeste {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				DespesasWindow window = new DespesasWindow();
				window.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
}
