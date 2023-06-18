package gui;

import java.awt.EventQueue;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import entities.Despesa;
import service.DespesaService;
import service.ResumoService;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ResumoWindow extends JFrame {

	private JPanel contentPane;
	private JTable tableMensal;
	private JTable tableAnual;
	private JScrollPane scrollPaneAnual;
	private JLabel lblResumoAnual;
	private JScrollPane scrollPaneMensal;
	private JLabel lblResumoMensal;
	private JFormattedTextField txtDataMes;
	private JFormattedTextField txtAno;
	private MaskFormatter mascaraAno;
	private MaskFormatter mascaraMes;
	private JButton btnAtualizarAno;
	private List<Double> resultResumo;
	private List<String> descricaoMes;
	private List<String> descricaoAno;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResumoWindow frame = new ResumoWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ResumoWindow() {
		this.criarMascaraData();
		this.initComponents();
		this.criarDescricoes();
	}
	private void criarDescricoes() {
		descricaoMes = new ArrayList<>();
		descricaoMes.add("Rendimento");
		descricaoMes.add("Investimento a Longo Prazo");
		descricaoMes.add("Fundo para Despesas Ocasionais");
		descricaoMes.add("Valor Total Disponível por mês para Despesas");
		descricaoMes.add("Valor Total das Despesas orçadas para o mês");
		descricaoMes.add("Valor Total");
		
		descricaoAno = new ArrayList<>();
		descricaoAno.add("Rendimento");
		descricaoAno.add("Investimento a Longo Prazo");
		descricaoAno.add("Fundo para Despesas Ocasionais");
		descricaoAno.add("Total Disponível para Despesas Durante o Ano");
		descricaoAno.add("Total Despesas Orçadas (12 meses)");
		descricaoAno.add("Total Despesas Ocasionais para o Ano");
		descricaoAno.add("Valor Total Restante ao Final do Ano");
		
	}

	private void criarMascaraData() {
		try {
			this.mascaraMes = new MaskFormatter("##/####");
			this.mascaraAno = new MaskFormatter("####");
			
		} catch(ParseException e) {
			System.out.println("ERRO: " + e.getMessage());
		}
		
	}

	private void initComponents() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 820, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblResumoMensal = new JLabel("Resumo Mensal");
		lblResumoMensal.setBounds(12, 12, 128, 15);
		contentPane.add(lblResumoMensal);

		scrollPaneMensal = new JScrollPane();
		scrollPaneMensal.setBounds(12, 39, 796, 143);
		contentPane.add(scrollPaneMensal);

		tableMensal = new JTable();
		tableMensal.setFont(new Font("Dialog", Font.PLAIN, 14));
		tableMensal.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Descrição", "Total Mensal"
			}
		));
		scrollPaneMensal.setViewportView(tableMensal);
		lblResumoAnual = new JLabel("Resumo Anual");
		lblResumoAnual.setBounds(12, 194, 111, 15);
		contentPane.add(lblResumoAnual);

		scrollPaneAnual = new JScrollPane();
		scrollPaneAnual.setBounds(12, 221, 796, 207);
		contentPane.add(scrollPaneAnual);

		tableAnual = new JTable();
		tableAnual.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Descrição", "Mensal(x12)", "Ocasional", "Total Anual" }));
		scrollPaneAnual.setViewportView(tableAnual);
		
		JLabel lblDataReferentemmaaaa = new JLabel("Data referente (MM/AAAA):");
		lblDataReferentemmaaaa.setBounds(376, 12, 201, 15);
		contentPane.add(lblDataReferentemmaaaa);
		
		txtDataMes = new JFormattedTextField(mascaraMes);
		txtDataMes.setBounds(565, 10, 114, 19);
		contentPane.add(txtDataMes);
		txtDataMes.setColumns(10);
		
		JLabel lblAno = new JLabel("Ano:");
		lblAno.setBounds(532, 194, 38, 15);
		contentPane.add(lblAno);
		
		txtAno = new JFormattedTextField(mascaraAno);
		txtAno.setBounds(565, 192, 114, 19);
		contentPane.add(txtAno);
		txtAno.setColumns(10);
		
		JButton btnAtualizarMes = new JButton("Atualizar");
		btnAtualizarMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAtualizarMesActionPerformed();
			}
		});
		btnAtualizarMes.setBounds(691, 7, 117, 25);
		contentPane.add(btnAtualizarMes);
		
		btnAtualizarAno = new JButton("Atualizar");
		btnAtualizarAno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAtualizarAnoActionPerformed();
			}
		});
		btnAtualizarAno.setBounds(691, 189, 117, 25);
		contentPane.add(btnAtualizarAno);
	}

	protected void btnAtualizarMesActionPerformed() {
		try {
			DefaultTableModel model = (DefaultTableModel) tableMensal.getModel();
			model.fireTableDataChanged();
			model.setRowCount(0);
			resultResumo = new ResumoService().atualizarMes(txtDataMes.getText());
			
			int tamanhoLista = Math.min(descricaoMes.size(), resultResumo.size());
			for (int i = 0; i < tamanhoLista; i++) {
				String descricao = descricaoMes.get(i);
				Double resultado = resultResumo.get(i);
				model.addRow(new Object[]{descricao, "R$ "+resultado});
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void btnAtualizarAnoActionPerformed() {
		
	}
}
