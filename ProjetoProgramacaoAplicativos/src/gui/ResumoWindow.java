package gui;

import java.awt.EventQueue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.MaskFormatter;

import service.ResumoService;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ResumoWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableMensal;
	private JTable tableAnual;
	private JScrollPane scrollPaneAnual;
	private JLabel lblResumoAnual;
	private JScrollPane scrollPaneMensal;
	private JLabel lblResumoMensal;
	private JFormattedTextField txtDataMes;
	private JFormattedTextField txtDataAno;
	private MaskFormatter mascaraAno;
	private MaskFormatter mascaraMes;
	private JButton btnAtualizarAno;
	private List<Double> resultMes;
	private List<String> resultAno;
	private List<String> descricaoMes;
	private List<String> descricaoAno;
	private JButton btnAtualizarMes;
	private JLabel lblAno;
	private JLabel lblDataReferentemmaaaa;

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
		descricaoAno.add("Fundo para Despesas Ocasionais (+ Excedente Meses)");
		descricaoAno.add("Total Disponível para Despesas Durante o Ano");
		descricaoAno.add("Total Despesas Orçadas (12 meses)");
		descricaoAno.add("Total Despesas Ocasionais para o Ano");
		descricaoAno.add("Valor Total Restante ao Final do Ano");

	}

	private void criarMascaraData() {
		try {
			this.mascaraMes = new MaskFormatter("##/####");
			this.mascaraAno = new MaskFormatter("####");

		} catch (ParseException e) {
			System.out.println("ERRO: " + e.getMessage());
		}

	}

	private void initComponents() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 820, 387);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblResumoMensal = new JLabel("Resumo Mensal");
		lblResumoMensal.setBounds(12, 12, 128, 15);
		contentPane.add(lblResumoMensal);

		scrollPaneMensal = new JScrollPane();
		scrollPaneMensal.setBounds(12, 39, 796, 118);
		contentPane.add(scrollPaneMensal);

		tableMensal = new JTable();
		tableMensal.setFont(new Font("Dialog", Font.BOLD, 14));
		tableMensal.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Descrição", "Total Mensal" }));
		scrollPaneMensal.setViewportView(tableMensal);
		lblResumoAnual = new JLabel("Resumo Anual");
		lblResumoAnual.setBounds(12, 172, 111, 15);
		contentPane.add(lblResumoAnual);

		scrollPaneAnual = new JScrollPane();
		scrollPaneAnual.setBounds(12, 199, 796, 134);
		contentPane.add(scrollPaneAnual);

		tableAnual = new JTable();
		tableAnual.setFont(new Font("Dialog", Font.BOLD, 14));
		tableAnual.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Descrição", "Mensal(x12)", "Ocasional", "Total Anual" }));
		TableColumn column = tableAnual.getColumnModel().getColumn(0);

		int preferredWidth = 400;
		column.setPreferredWidth(preferredWidth);
		scrollPaneAnual.setViewportView(tableAnual);

		lblDataReferentemmaaaa = new JLabel("Data referente (MM/AAAA):");
		lblDataReferentemmaaaa.setBounds(376, 12, 201, 15);
		contentPane.add(lblDataReferentemmaaaa);

		txtDataMes = new JFormattedTextField(mascaraMes);
		txtDataMes.setBounds(565, 10, 114, 19);
		contentPane.add(txtDataMes);
		txtDataMes.setColumns(10);

		lblAno = new JLabel("Ano:");
		lblAno.setBounds(532, 172, 38, 15);
		contentPane.add(lblAno);

		txtDataAno = new JFormattedTextField(mascaraAno);
		txtDataAno.setFont(new Font("Dialog", Font.PLAIN, 12));
		txtDataAno.setBounds(565, 170, 114, 19);
		contentPane.add(txtDataAno);
		txtDataAno.setColumns(10);

		btnAtualizarMes = new JButton("Atualizar");
		btnAtualizarMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAtualizarMesAction();
			}
		});
		btnAtualizarMes.setBounds(691, 7, 117, 25);
		contentPane.add(btnAtualizarMes);

		btnAtualizarAno = new JButton("Atualizar");
		btnAtualizarAno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAtualizarAnoAction();
			}
		});
		btnAtualizarAno.setBounds(691, 167, 117, 25);
		contentPane.add(btnAtualizarAno);
	}

	private static boolean isDateValid(String dataString) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
		sdf.setLenient(false);

		try {
			Date data = sdf.parse(dataString);

			Calendar cal = Calendar.getInstance();
			cal.setTime(data);
			int mes = cal.get(Calendar.MONTH);
			int ano = cal.get(Calendar.YEAR);

			if (mes >= 0 && mes <= 11 && ano >= 1900 && ano <= 9999) {
				return true;
			} else {
				return false;
			}
		} catch (ParseException e) {
			return false;
		}
	}

	private void btnAtualizarMesAction() {
		try {
			DefaultTableModel model = (DefaultTableModel) tableMensal.getModel();
			model.fireTableDataChanged();
			model.setRowCount(0);
			if (isDateValid(txtDataMes.getText())) {
				resultMes = new ResumoService().atualizarMes(txtDataMes.getText());

				int tamanhoLista = Math.min(descricaoMes.size(), resultMes.size());
				for (int i = 0; i < tamanhoLista; i++) {
					String descricao = descricaoMes.get(i);
					Double resultado = resultMes.get(i);
					model.addRow(new Object[] { descricao, "R$ " + resultado });
				}
			} else
				JOptionPane.showMessageDialog(this, "Por favor insira uma data válida.", "Aviso",
						JOptionPane.WARNING_MESSAGE);
		} catch (SQLException | IOException e) {
			JOptionPane.showMessageDialog(this, "Erro ao atualizar resumo.", "Erro", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	private void btnAtualizarAnoAction() {

		try {
			DefaultTableModel model = (DefaultTableModel) tableAnual.getModel();
			model.fireTableDataChanged();
			model.setRowCount(0);
			resultAno = new ResumoService().atualizarAno(txtDataAno.getText());
			String descricao;
			String mensal;
			String ocasional;
			String totalAnual;

			int aux = 0;
			for (int i = 0; i < 21; i += 3) {
				descricao = descricaoAno.get(aux);
				mensal = resultAno.get(i);
				ocasional = resultAno.get(i + 1);
				totalAnual = resultAno.get(i + 2);
				if (aux < 3)
					model.addRow(new Object[] { descricao, "R$ " + mensal, "R$ " + ocasional, "R$ " + totalAnual });
				else
					model.addRow(new Object[] { descricao, mensal, ocasional, "R$ " + totalAnual });
				aux++;
			}

		} catch (SQLException | IOException e) {
			JOptionPane.showMessageDialog(this, "Erro ao atualizar resumo.", "Erro", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
