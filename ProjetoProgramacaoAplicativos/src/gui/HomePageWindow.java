package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomePageWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JButton btnRelatorio;
	private JButton btnResumo;
	private JButton btnFundoDespesas;
	private JButton btnInvestimentos;
	private JButton btnDespesas;
	private JButton btnRendimentos;
	private JLabel lblTitle;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePageWindow window = new HomePageWindow();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public HomePageWindow() {
		initComponents();
	}

	private void initComponents() {
		frame = new JFrame();
		frame.setBounds(100, 100, 420, 470);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		lblTitle = new JLabel("GGE Finances");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitle.setBounds(149, 27, 152, 20);
		frame.getContentPane().add(lblTitle);

		btnRendimentos = new JButton("Rendimentos");
		btnRendimentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRendimentoAction();
			}
		});
		btnRendimentos.setBounds(60, 207, 286, 30);
		frame.getContentPane().add(btnRendimentos);

		btnDespesas = new JButton("Despesas");
		btnDespesas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDespesasAction();
			}
		});
		btnDespesas.setBounds(60, 261, 286, 30);
		frame.getContentPane().add(btnDespesas);

		btnInvestimentos = new JButton("Investimentos a Longo Prazo");
		btnInvestimentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnInvestimentosLongoPrazo();
			}
		});
		btnInvestimentos.setBounds(60, 314, 286, 30);
		frame.getContentPane().add(btnInvestimentos);

		btnFundoDespesas = new JButton("Fundo de Despesas Ocasionais");
		btnFundoDespesas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFundoDespesas();
			}
		});
		btnFundoDespesas.setBounds(60, 365, 286, 30);
		frame.getContentPane().add(btnFundoDespesas);

		btnResumo = new JButton("Resumo");
		btnResumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnResumoAction();
			}
		});
		btnResumo.setBounds(60, 92, 286, 30);
		frame.getContentPane().add(btnResumo);

		btnRelatorio = new JButton("Relatório");
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRelatorioAction();
			}
		});
		btnRelatorio.setBounds(60, 149, 286, 30);
		frame.getContentPane().add(btnRelatorio);
	}

	private void btnFundoDespesas() {
		FundoOcasionalWindow fundoOcasionalWindow = new FundoOcasionalWindow();
		fundoOcasionalWindow.setLocationRelativeTo(null);
		fundoOcasionalWindow.setVisible(true);
	}

	private void btnInvestimentosLongoPrazo() {
		InvestimentoLongoPrazoWindow investimentoLongoPrazoWindow = new InvestimentoLongoPrazoWindow();
		investimentoLongoPrazoWindow.setVisible(true);
		investimentoLongoPrazoWindow.setLocationRelativeTo(null);
	}

	private void btnDespesasAction() {
		DespesasWindow despesasWindow = new DespesasWindow();
		despesasWindow.setVisible(true);
		despesasWindow.setLocationRelativeTo(null);
	}

	private void btnRendimentoAction() {
		RendimentoWindow rendimentoWindow = new RendimentoWindow();
		rendimentoWindow.setVisible(true);
		rendimentoWindow.setLocationRelativeTo(null);
	}

	private void btnRelatorioAction() {
		RelatorioWindow relatorioWindow = new RelatorioWindow();
		relatorioWindow.setLocationRelativeTo(null);
		relatorioWindow.setVisible(true);
	}

	private void btnResumoAction() {
		ResumoWindow resumoWindow = new ResumoWindow();
		resumoWindow.setLocationRelativeTo(null);
		resumoWindow.setVisible(true);
	}
}
