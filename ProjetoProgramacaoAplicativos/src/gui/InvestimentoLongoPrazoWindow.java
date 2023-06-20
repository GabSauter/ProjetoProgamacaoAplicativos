package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import entities.Investimento;
import service.InvestimentoService;

public class InvestimentoLongoPrazoWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTable table;
	private JTextField txtInvestimento;
	private JTextField txtValor;
	private JButton btnCadastrarInvestimento;
	private JButton btnLimparCampos;
	private JRadioButton rdbtnOcasional;
	private JRadioButton rdbtnMensal;
	private JButton btnEditarInvestimento;
	private JButton btnExcluirInvestimento;
	
	private List<Investimento> investimentos;
	
	private JFormattedTextField txtData;
	private MaskFormatter mascaraData;

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

	public InvestimentoLongoPrazoWindow() {
		this.criarMascaraData();
		this.initComponents();
		this.carregaTabela();
	}

	private void initComponents() {
		setBounds(100, 100, 570, 390);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false);

		JPanel panelCadastro = new JPanel();
		panelCadastro.setBorder(
				new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Cadastrar  investimento a longo prazo",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panelCadastro.setLayout(null);
		panelCadastro.setBounds(10, 12, 548, 145);
		getContentPane().add(panelCadastro);

		JLabel lblInvestimento = new JLabel("Investimento:");
		lblInvestimento.setBounds(12, 28, 99, 14);
		panelCadastro.add(lblInvestimento);

		txtInvestimento = new JTextField();
		txtInvestimento.setColumns(10);
		txtInvestimento.setBounds(109, 26, 150, 20);
		panelCadastro.add(txtInvestimento);

		btnCadastrarInvestimento = new JButton("Cadastrar");
		btnCadastrarInvestimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCadastrarAction();
			}
		});
		btnCadastrarInvestimento.setBounds(269, 110, 105, 23);
		panelCadastro.add(btnCadastrarInvestimento);

		btnLimparCampos = new JButton("Limpar campos");
		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLimparCamposAction();
			}
		});
		btnLimparCampos.setBounds(386, 110, 150, 23);
		panelCadastro.add(btnLimparCampos);

		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(12, 54, 70, 15);
		panelCadastro.add(lblValor);

		txtValor = new JTextField();
		txtValor.setColumns(10);
		txtValor.setBounds(109, 52, 150, 20);
		panelCadastro.add(txtValor);

		JPanel panelTipoInvestimento = new JPanel();

		panelTipoInvestimento.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),
				"Tipo de Investimento", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panelTipoInvestimento.setBounds(12, 83, 247, 50);
		panelCadastro.add(panelTipoInvestimento);
		panelTipoInvestimento.setLayout(null);

		rdbtnOcasional = new JRadioButton("Ocasional");
		rdbtnOcasional.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnMensal.setSelected(false);
			}
		});

		rdbtnOcasional.setBounds(8, 19, 95, 23);
		panelTipoInvestimento.add(rdbtnOcasional);

		rdbtnMensal = new JRadioButton("Mensal");
		rdbtnMensal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnOcasional.setSelected(false);
			}
		});
		rdbtnMensal.setBounds(136, 19, 89, 23);
		panelTipoInvestimento.add(rdbtnMensal);

		JLabel lblDatammaaaa = new JLabel("Data (MM/AAAA):");
		lblDatammaaaa.setBounds(269, 54, 117, 15);
		panelCadastro.add(lblDatammaaaa);

		txtData = new JFormattedTextField(mascaraData);
		txtData.setBounds(396, 52, 140, 19);
		panelCadastro.add(txtData);
		txtData.setColumns(10);

		JPanel panelInvestimento = new JPanel();
		panelInvestimento.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),
				"Investimentos Cadastrados", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panelInvestimento.setBounds(10, 169, 548, 186);
		getContentPane().add(panelInvestimento);
		panelInvestimento.setLayout(null);

		btnEditarInvestimento = new JButton("Editar");
		btnEditarInvestimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEditarAction();
			}
		});
		btnEditarInvestimento.setBounds(333, 162, 100, 23);
		panelInvestimento.add(btnEditarInvestimento);

		btnExcluirInvestimento = new JButton("Excluir");
		btnExcluirInvestimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnExcluirInvestimentoAction();
			}
		});
		btnExcluirInvestimento.setBounds(438, 162, 100, 23);
		panelInvestimento.add(btnExcluirInvestimento);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 14, 526, 136);
		panelInvestimento.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tableMouseClickedAction();
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Investimento", "Data inicial", "Mensal", "Ocasional", "Total Anual" }));
		scrollPane.setViewportView(table);
	}

	private void btnExcluirInvestimentoAction() {
		int coluna = table.getSelectedRow();

		if (coluna == -1) {
			JOptionPane.showMessageDialog(this, "Selecione um investimento para excluir.", "Aviso",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		Investimento investimento = investimentos.get(coluna);

		int resposta = JOptionPane.showConfirmDialog(this,
				"Deseja realmente excluir o investimento: " + investimento.getInvestimento() + "?", "Confirmação",
				JOptionPane.YES_NO_OPTION);
		if (resposta == JOptionPane.YES_OPTION) {
			try {
				new InvestimentoService().excluir(investimento);
				JOptionPane.showMessageDialog(this, "Investimento excluido.", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
				carregaTabela();
			} catch (SQLException | IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Erro ao excluir investimento.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	private void btnEditarAction() {
		int coluna = table.getSelectedRow();

		if (coluna == -1) {
			JOptionPane.showMessageDialog(this, "Selecione um investimento para editar.", "Aviso",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		Investimento investimento = investimentos.get(coluna);

		int resposta = JOptionPane.showConfirmDialog(this,
				"Deseja realmente editar o investimento: " + investimento.getInvestimento() + "?", "Confirmação",
				JOptionPane.YES_NO_OPTION);
		if (resposta == JOptionPane.YES_OPTION) {
			if (!(txtInvestimento.getText().toString().equals("") || txtValor.getText().toString().equals(""))) {
				if (isDateValid(txtData.getText())) {
					Investimento investimentoEditado = new Investimento();
					investimentoEditado.setId(investimento.getId());
					investimentoEditado.setInvestimento(txtInvestimento.getText());
					investimentoEditado.setData(txtData.getText());
					try {
						if (rdbtnMensal.isSelected()) {
							investimentoEditado.setMensal(Double.parseDouble(txtValor.getText()));
							investimentoEditado.setOcasional(0.0);
							investimentoEditado.setTotalAno(Double.parseDouble(txtValor.getText()) * 12);
						}
						if (rdbtnOcasional.isSelected()) {
							investimentoEditado.setOcasional(Double.parseDouble(txtValor.getText()));
							investimentoEditado.setMensal(0.0);
							investimentoEditado.setTotalAno(Double.parseDouble(txtValor.getText()));
						}

						new InvestimentoService().atualizar(investimentoEditado);
						JOptionPane.showMessageDialog(this, "Investimento editado.", "Sucesso",
								JOptionPane.INFORMATION_MESSAGE);
						carregaTabela();
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(this, "Valor inválido. Insira um valor numérico.", "Erro",
								JOptionPane.ERROR_MESSAGE);
					} catch (SQLException | IOException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(this, "Erro ao editar investimento.", "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
				} else
					JOptionPane.showMessageDialog(this, "Por favor insira uma data válida.", "Aviso",
							JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this,
						"Por favor insira valores em todos os campos para editar um investimento.", "Aviso",
						JOptionPane.WARNING_MESSAGE);
			}

		}
	}

	private void btnLimparCamposAction() {
		txtInvestimento.setText("");
		txtValor.setText("");
		txtData.setText("");
	}

	private void tableMouseClickedAction() {
		int coluna = table.getSelectedRow();
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		txtInvestimento.setText(model.getValueAt(coluna, 0).toString());

		Boolean isMensal = (Double.parseDouble(model.getValueAt(coluna, 2).toString()) > Double
				.parseDouble(model.getValueAt(coluna, 3).toString()));
		String valor = isMensal ? model.getValueAt(coluna, 2).toString() : model.getValueAt(coluna, 3).toString();
		txtValor.setText(valor);
		txtData.setText(model.getValueAt(coluna, 1).toString());

		if (isMensal) {
			rdbtnMensal.setSelected(true);
			rdbtnOcasional.setSelected(false);
		} else {
			rdbtnMensal.setSelected(false);
			rdbtnOcasional.setSelected(true);
		}

	}

	private void btnCadastrarAction() {
		Investimento investimento = new Investimento();
		investimento.setInvestimento(this.txtInvestimento.getText());
		if (!(txtInvestimento.getText().toString().equals("") || txtValor.getText().toString().equals(""))) {
			if (isDateValid(txtData.getText())) {
				investimento.setData(txtData.getText());
				try {
					if (this.rdbtnMensal.isSelected()) {
						investimento.setMensal(Double.parseDouble(txtValor.getText()));
						investimento.setOcasional(0.0);
						investimento.setTotalAno(Double.parseDouble(txtValor.getText()) * 12);
					}
					if (this.rdbtnOcasional.isSelected()) {
						investimento.setOcasional(Double.parseDouble(txtValor.getText()));
						investimento.setMensal(0.0);
						investimento.setTotalAno(Double.parseDouble(txtValor.getText()));
					}
					new InvestimentoService().cadastrar(investimento);
					this.carregaTabela();
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(this, "Valor inválido. Insira um valor numérico.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} catch (SQLException | IOException e) {
					JOptionPane.showMessageDialog(this, "Erro ao cadastrar investimento.", "Erro",
							JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			} else
				JOptionPane.showMessageDialog(this, "Por favor insira uma data válida.", "Aviso",
						JOptionPane.WARNING_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this,
					"Por favor insira valores em todos os campos para cadastrar um investimento.", "Aviso",
					JOptionPane.WARNING_MESSAGE);
		}

	}

	private void carregaTabela() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.fireTableDataChanged();
		model.setRowCount(0);

		investimentos = new ArrayList<Investimento>();
		try {
			investimentos = new InvestimentoService().buscarTodos();
			for (Investimento investimento : investimentos) {
				model.addRow(new Object[] { investimento.getInvestimento(), investimento.getData(),
						investimento.getMensal(), investimento.getOcasional(), investimento.getTotalAno() });
			}
		} catch (SQLException | IOException e) {
			JOptionPane.showMessageDialog(this, "Erro ao carregar investimentos a longo prazo.", "Erro",
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
}