package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RelatorioWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JCheckBox chkOrganizacao;
	private JCheckBox chkRelatorioMensal;
	private JCheckBox chkRelatorioAnual;
	private JButton btnGerarRelatorio;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RelatorioWindow window = new RelatorioWindow();
					window.setVisible(true);
					window.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RelatorioWindow() {
		initComponents();
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		getContentPane().add(panel);
		panel.setLayout(null);

		chkRelatorioAnual = new JCheckBox("Relatório Anual");
		chkRelatorioAnual.setBounds(10, 11, 150, 23);
		panel.add(chkRelatorioAnual);

		chkRelatorioMensal = new JCheckBox("Relatório Mensal");
		chkRelatorioMensal.setBounds(10, 45, 150, 23);
		panel.add(chkRelatorioMensal);

		chkOrganizacao = new JCheckBox("Organização");
		chkOrganizacao.setBounds(10, 79, 150, 23);
		panel.add(chkOrganizacao);

		btnGerarRelatorio = new JButton("Gerar Relatório");
		btnGerarRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnGerarRelatorioAction();
			}
		});
		btnGerarRelatorio.setBounds(10, 113, 150, 23);
		panel.add(btnGerarRelatorio);
	}

	private void btnGerarRelatorioAction() {
		boolean relatorioAnual = chkRelatorioAnual.isSelected();
		boolean relatorioMensal = chkRelatorioMensal.isSelected();
		boolean organizacao = chkOrganizacao.isSelected();

		if (relatorioAnual) {
			gerarRelatorioAnual();
		}

		if (relatorioMensal) {
			gerarRelatorioMensal();
		}

		if (organizacao) {
			gerarRelatorioOrganizado();
		}
	}

	private void gerarRelatorioAnual() {

	}

	private void gerarRelatorioMensal() {

	}

	private void gerarRelatorioOrganizado() {

	}
}
