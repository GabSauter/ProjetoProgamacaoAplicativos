package gui;

import java.awt.EventQueue;

public class InvestimentoLongoPrazoWindowTeste {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				InvestimentoLongoPrazoWindow window = new InvestimentoLongoPrazoWindow();
				window.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
