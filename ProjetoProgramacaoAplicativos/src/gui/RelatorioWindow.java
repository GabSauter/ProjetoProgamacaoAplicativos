package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.MaskFormatter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import entities.CategoriaOrganizada;
import entities.Despesa;
import entities.FundoOcasional;
import entities.Investimento;
import entities.Rendimento;
import service.RelatorioService;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class RelatorioWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JCheckBox chkOrganizacao;
	private JCheckBox chkRelatorioMensal;
	private JCheckBox chkRelatorioAnual;
	private JButton btnGerarRelatorio;

	private List<Rendimento> rendimentos;
	private List<Despesa> despesas;
	private List<FundoOcasional> fundos;
	private List<Investimento> investimentos;

	private List<CategoriaOrganizada> categoriasRendimentoOrganizadas;
	private List<CategoriaOrganizada> categoriasDespesaOrganizadas;

	private JFormattedTextField txtFieldData;
	private MaskFormatter mascaraData;

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
		this.criarMascaraData();
		initComponents();
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		setResizable(false);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		getContentPane().add(panel);
		panel.setLayout(null);

		chkRelatorioAnual = new JCheckBox("Relatório Anual");
		chkRelatorioAnual.setBounds(10, 11, 150, 23);
		chkRelatorioAnual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chkRelatorioMensal.setSelected(false);
			}
		});
		panel.add(chkRelatorioAnual);

		chkRelatorioMensal = new JCheckBox("Relatório Mensal");
		chkRelatorioMensal.setBounds(10, 45, 150, 23);
		chkRelatorioMensal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chkRelatorioAnual.setSelected(false);
			}
		});
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

		txtFieldData = new JFormattedTextField(mascaraData);
		txtFieldData.setBounds(195, 12, 93, 20);
		panel.add(txtFieldData);

		JLabel lblData = new JLabel("Ano:");
		lblData.setBounds(166, 15, 46, 14);
		panel.add(lblData);
	}

	private void btnGerarRelatorioAction() {
		boolean relatorioAnual = chkRelatorioAnual.isSelected();
		boolean relatorioMensal = chkRelatorioMensal.isSelected();
		boolean organizacao = chkOrganizacao.isSelected();

		if (relatorioAnual && !organizacao && !relatorioMensal) {
			gerarRelatorioAnual();
		} else if (relatorioMensal && !organizacao && !relatorioAnual) {
			gerarRelatorioMensal();
		} else if (organizacao && relatorioMensal && !relatorioAnual) {
			gerarRelatorioMensalOrganizado();
		} else if (organizacao && relatorioAnual && !relatorioMensal) {
			gerarRelatorioAnualOrganizado();
		} else if (organizacao && !relatorioAnual && !relatorioMensal) {
			JOptionPane.showMessageDialog(this, "Por favor selecione mensal ou anual para gerar relatório organizado.",
					"Aviso", JOptionPane.WARNING_MESSAGE);
		} else if (!relatorioAnual && !relatorioMensal) {
			JOptionPane.showMessageDialog(this, "Por favor selecione mensal ou anual.", "Aviso",
					JOptionPane.WARNING_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Por favor selecione apenas um: mensal ou anual.", "Aviso",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private void gerarRelatorioAnual() {
		if (isDateValid(txtFieldData.getText())) {
			try {

				rendimentos = new RelatorioService().buscarRendimentoAno(txtFieldData.getText().split("/")[1]);
				despesas = new RelatorioService().buscarDespesaAno(txtFieldData.getText().split("/")[1]);
				investimentos = new RelatorioService().buscarInvestimentoAno(txtFieldData.getText().split("/")[1]);
				fundos = new RelatorioService().buscarFundoAno(txtFieldData.getText().split("/")[1]);

			} catch (SQLException | IOException e) {

				e.printStackTrace();
			}

			criarExcel();
		} else {
			JOptionPane.showMessageDialog(this, "Data inválida.", "Aviso", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void gerarRelatorioMensal() {
		if (isDateValid(txtFieldData.getText())) {
			try {

				rendimentos = new RelatorioService().buscarRendimentoMes(txtFieldData.getText());
				despesas = new RelatorioService().buscarDespesaMes(txtFieldData.getText());
				investimentos = new RelatorioService().buscarInvestimentoMes(txtFieldData.getText());
				fundos = new RelatorioService().buscarFundoMes(txtFieldData.getText());

			} catch (SQLException | IOException e) {
				JOptionPane.showMessageDialog(this, "Houve um erro ao buscar listas.", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}

			criarExcel();

		} else {
			JOptionPane.showMessageDialog(this, "Data inválida.", "Aviso", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void gerarRelatorioMensalOrganizado() {
		if (isDateValid(txtFieldData.getText())) {
			try {

				categoriasRendimentoOrganizadas = new RelatorioService()
						.buscarRendimentoMesOrganizado(txtFieldData.getText());
				categoriasDespesaOrganizadas = new RelatorioService()
						.buscarDespesaAnoOrganizado(txtFieldData.getText());

			} catch (SQLException | IOException e) {
				JOptionPane.showMessageDialog(this, "Houve um erro ao buscar listas.", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}

			criarExcelOrganizado();

		} else {
			JOptionPane.showMessageDialog(this, "Data inválida.", "Aviso", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void gerarRelatorioAnualOrganizado() {
		if (isDateValid(txtFieldData.getText())) {
			try {

				categoriasRendimentoOrganizadas = new RelatorioService()
						.buscarRendimentoAnoOrganizado(txtFieldData.getText().split("/")[1]);
				categoriasDespesaOrganizadas = new RelatorioService()
						.buscarDespesaAnoOrganizado(txtFieldData.getText().split("/")[1]);

			} catch (SQLException | IOException e) {
				JOptionPane.showMessageDialog(this, "Houve um erro ao buscar listas.", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}

			criarExcelOrganizado();
		} else {
			JOptionPane.showMessageDialog(this, "Data inválida.", "Aviso", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void criarExcel() {

		try (Workbook workbook = new HSSFWorkbook()) {
			Sheet rendimentoSheet = workbook.createSheet("Rendimentos");

			Row rendimentoHeaderRow = rendimentoSheet.createRow(0);
			rendimentoHeaderRow.createCell(0).setCellValue("ID");
			rendimentoHeaderRow.createCell(1).setCellValue("Categoria");
			rendimentoHeaderRow.createCell(2).setCellValue("Rendimento");
			rendimentoHeaderRow.createCell(3).setCellValue("Mensal");
			rendimentoHeaderRow.createCell(4).setCellValue("Ocasional");
			rendimentoHeaderRow.createCell(5).setCellValue("Total Ano");
			rendimentoHeaderRow.createCell(6).setCellValue("Data");

			int rendimentoRowNum = 1;
			for (Rendimento rendimento : rendimentos) {
				Row row = rendimentoSheet.createRow(rendimentoRowNum++);
				row.createCell(0).setCellValue(rendimento.getId());
				row.createCell(1).setCellValue(rendimento.getCategoria().toString());
				row.createCell(2).setCellValue(rendimento.getRendimento());
				row.createCell(3).setCellValue(rendimento.getMensal());
				row.createCell(4).setCellValue(rendimento.getOcasional());
				row.createCell(5).setCellValue(rendimento.getTotalAno());
				row.createCell(6).setCellValue(rendimento.getData());
			}

			Sheet despesaSheet = workbook.createSheet("Despesas");

			Row despesaHeaderRow = despesaSheet.createRow(0);
			despesaHeaderRow.createCell(0).setCellValue("ID");
			despesaHeaderRow.createCell(1).setCellValue("Categoria");
			despesaHeaderRow.createCell(2).setCellValue("Despesa");
			despesaHeaderRow.createCell(3).setCellValue("Mensal");
			despesaHeaderRow.createCell(4).setCellValue("Ocasional");
			despesaHeaderRow.createCell(5).setCellValue("Total Ano");
			despesaHeaderRow.createCell(6).setCellValue("Data");

			int despesaRowNum = 1;
			for (Despesa despesa : despesas) {
				Row row = despesaSheet.createRow(despesaRowNum++);
				row.createCell(0).setCellValue(despesa.getId());
				row.createCell(1).setCellValue(despesa.getCategoria().toString());
				row.createCell(2).setCellValue(despesa.getDespesa());
				row.createCell(3).setCellValue(despesa.getMensal());
				row.createCell(4).setCellValue(despesa.getOcasional());
				row.createCell(5).setCellValue(despesa.getTotalAno());
				row.createCell(6).setCellValue(despesa.getData());
			}

			Sheet investimentoSheet = workbook.createSheet("Investimentos");

			Row investimentoHeaderRow = investimentoSheet.createRow(0);
			investimentoHeaderRow.createCell(0).setCellValue("ID");
			investimentoHeaderRow.createCell(1).setCellValue("Investimento");
			investimentoHeaderRow.createCell(2).setCellValue("Mensal");
			investimentoHeaderRow.createCell(3).setCellValue("Ocasional");
			investimentoHeaderRow.createCell(4).setCellValue("Total Ano");
			investimentoHeaderRow.createCell(5).setCellValue("Data");

			int investimentoRowNum = 1;
			for (Investimento investimento : investimentos) {
				Row row = investimentoSheet.createRow(investimentoRowNum++);
				row.createCell(0).setCellValue(investimento.getId());
				row.createCell(1).setCellValue(investimento.getInvestimento());
				row.createCell(2).setCellValue(investimento.getMensal());
				row.createCell(3).setCellValue(investimento.getOcasional());
				row.createCell(4).setCellValue(investimento.getTotalAno());
				row.createCell(5).setCellValue(investimento.getData());
			}

			Sheet fundoOcasionalSheet = workbook.createSheet("Fundos Ocasionais");

			Row fundoOcasionalHeaderRow = fundoOcasionalSheet.createRow(0);
			fundoOcasionalHeaderRow.createCell(0).setCellValue("ID");
			fundoOcasionalHeaderRow.createCell(1).setCellValue("Fundo Ocasional");
			fundoOcasionalHeaderRow.createCell(2).setCellValue("Mensal");
			fundoOcasionalHeaderRow.createCell(3).setCellValue("Ocasional");
			fundoOcasionalHeaderRow.createCell(4).setCellValue("Total Ano");
			fundoOcasionalHeaderRow.createCell(5).setCellValue("Data");

			int fundoOcasionalRowNum = 1;
			for (FundoOcasional fundoOcasional : fundos) {
				Row row = fundoOcasionalSheet.createRow(fundoOcasionalRowNum++);
				row.createCell(0).setCellValue(fundoOcasional.getId());
				row.createCell(1).setCellValue(fundoOcasional.getFundoOcasional());
				row.createCell(2).setCellValue(fundoOcasional.getMensal());
				row.createCell(3).setCellValue(fundoOcasional.getOcasional());
				row.createCell(4).setCellValue(fundoOcasional.getTotalAno());
				row.createCell(5).setCellValue(fundoOcasional.getData());
			}

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Salvar arquivo");

			FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos do Excel", "xls");
			fileChooser.setFileFilter(filter);

			int userSelection = fileChooser.showSaveDialog(null);

			if (userSelection == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				if (!selectedFile.getName().endsWith(".xls")) {
					selectedFile = new File(selectedFile.getAbsolutePath() + ".xls");
				}

				try (FileOutputStream fileOut = new FileOutputStream(selectedFile)) {
					workbook.write(fileOut);
					System.out.println("Arquivo criado com sucesso!");
				} catch (IOException e) {
					JOptionPane.showMessageDialog(this, "Erro durante a criação do arquivo.", "Erro",
							JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void criarExcelOrganizado() {

		try (Workbook workbook = new HSSFWorkbook()) {
			Sheet rendimentoSheet = workbook.createSheet("Categorias de Rendimento");

			Row rendimentoHeaderRow = rendimentoSheet.createRow(0);
			rendimentoHeaderRow.createCell(0).setCellValue("Categoria");
			rendimentoHeaderRow.createCell(1).setCellValue("Total Mensal");
			rendimentoHeaderRow.createCell(2).setCellValue("Total Ocasional");
			rendimentoHeaderRow.createCell(3).setCellValue("Total Anual");

			int rendimentoRowNum = 1;
			for (CategoriaOrganizada categoriaRendimentoOrganizada : categoriasRendimentoOrganizadas) {
				Row row = rendimentoSheet.createRow(rendimentoRowNum++);
				row.createCell(0).setCellValue(categoriaRendimentoOrganizada.getCategoria());
				row.createCell(1).setCellValue(categoriaRendimentoOrganizada.getTotalMensal());
				row.createCell(2).setCellValue(categoriaRendimentoOrganizada.getTotalOcasional());
				row.createCell(3).setCellValue(categoriaRendimentoOrganizada.getTotalAnual());
			}

			Sheet despesaSheet = workbook.createSheet("Categorias de Despesa");

			Row despesaHeaderRow = despesaSheet.createRow(0);
			despesaHeaderRow.createCell(0).setCellValue("Categoria");
			despesaHeaderRow.createCell(1).setCellValue("Total Mensal");
			despesaHeaderRow.createCell(2).setCellValue("Total Ocasional");
			despesaHeaderRow.createCell(3).setCellValue("Total Anual");

			int despesaRowNum = 1;
			for (CategoriaOrganizada categoriaDespesaOrganizada : categoriasDespesaOrganizadas) {
				Row row = despesaSheet.createRow(despesaRowNum++);
				row.createCell(0).setCellValue(categoriaDespesaOrganizada.getCategoria());
				row.createCell(1).setCellValue(categoriaDespesaOrganizada.getTotalMensal());
				row.createCell(2).setCellValue(categoriaDespesaOrganizada.getTotalOcasional());
				row.createCell(3).setCellValue(categoriaDespesaOrganizada.getTotalAnual());
			}

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Salvar arquivo");

			FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos do Excel", "xls");
			fileChooser.setFileFilter(filter);

			int userSelection = fileChooser.showSaveDialog(null);

			if (userSelection == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				if (!selectedFile.getName().endsWith(".xls")) {
					selectedFile = new File(selectedFile.getAbsolutePath() + ".xls");
				}

				try (FileOutputStream fileOut = new FileOutputStream(selectedFile)) {
					workbook.write(fileOut);
					System.out.println("Arquivo criado com sucesso!");
				} catch (IOException e) {
					JOptionPane.showMessageDialog(this, "Erro durante a criação do arquivo.", "Erro",
							JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Erro durante a criação do arquivo.", "Erro",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	private void criarMascaraData() {
		try {
			this.mascaraData = new MaskFormatter("##/####");
		} catch (ParseException e) {
			System.out.println("ERRO: " + e.getMessage());
		}
	}

	public static boolean isDateValid(String dataString) {
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
}
