package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;


//um breve esboço do que pode ser feito de interfac


public class MainWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		this.initComponents();
	}
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGgeFinances = new JLabel("GGE Finances");
		lblGgeFinances.setFont(new Font("Dialog", Font.BOLD, 34));
		lblGgeFinances.setBounds(113, 12, 273, 35);
		contentPane.add(lblGgeFinances);
		
		JLabel lblBemVindoUser = new JLabel("Bem vindo, User!");
		lblBemVindoUser.setBounds(186, 50, 128, 15);
		contentPane.add(lblBemVindoUser);
		
		JButton btnRendimentos = new JButton("Rendimentos");
		btnRendimentos.setBounds(143, 82, 214, 44);
		contentPane.add(btnRendimentos);
		
		JButton btnDespesas = new JButton("Despesas");
		btnDespesas.setBounds(143, 155, 214, 44);
		contentPane.add(btnDespesas);
		
		JButton btnInvestimentos = new JButton("Investimentos");
		btnInvestimentos.setBounds(143, 234, 214, 44);
		contentPane.add(btnInvestimentos);
		
		JButton btnDespesasOcasionais = new JButton("Despesas Ocasionais");
		btnDespesasOcasionais.setBounds(143, 305, 214, 44);
		contentPane.add(btnDespesasOcasionais);
		
		JButton btnResumo = new JButton("Resumo");
		btnResumo.setBounds(143, 385, 214, 44);
		contentPane.add(btnResumo);
	}
}
