package gui;

import java.awt.EventQueue;

public class FundoOcasionalWindowTeste {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				FundoOcasionalWindow window = new FundoOcasionalWindow();
				window.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
}
