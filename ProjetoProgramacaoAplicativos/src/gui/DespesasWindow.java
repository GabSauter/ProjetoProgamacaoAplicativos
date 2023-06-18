
package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import entities.Categoria;
import entities.Despesa;
import service.CategoriaService;
import service.DespesaService;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class DespesasWindow extends JFrame {

	private JTable table;
	private JTextField txtDespesa;
	private JTextField txtValor;
	private JPanel panelCadastro;
	private JLabel lblDespesa;
	private JButton btnCadastrarDespesa;
	private JButton btnLimparCampos;
	private JLabel lblCategoria;
	private JComboBox<Categoria> cbCategoria;
	private JButton btnAddCategoria;
	private JLabel lblValor;
	private JPanel panelTipoDespesa;
	private JRadioButton rdbtnOcasional;
	private JRadioButton rdbtnMensal;
	private JPanel panelDespesas;
	private JButton btnEditarDespesa;
	private JButton btnExcluirDespesa;
	private JScrollPane scrollPane;
	private JLabel lblDatammaaaa;
	private JFormattedTextField txtData;
	private List<Despesa> despesas;
	private List<Categoria> categorias;
	private MaskFormatter mascaraData;

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

	/**
	 * Create the frame.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public DespesasWindow() {
		this.criarMascaraData();
		this.initComponents();
		this.carregarComboBox();
		this.carregaTabelaDespesa();
	}

	private void criarMascaraData() {
		try {
			this.mascaraData = new MaskFormatter("##/####");
		} catch (ParseException e) {
			System.out.println("ERRO: " + e.getMessage());
		}
	}

	private void initComponents() {

		setBounds(100, 100, 570, 433);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		panelCadastro = new JPanel();
		panelCadastro.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Cadastrar despesas",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panelCadastro.setLayout(null);
		panelCadastro.setBounds(10, 12, 548, 170);
		getContentPane().add(panelCadastro);

		lblDespesa = new JLabel("Despesa:");
		lblDespesa.setBounds(12, 53, 99, 14);
		panelCadastro.add(lblDespesa);

		txtDespesa = new JTextField();
		txtDespesa.setColumns(10);
		txtDespesa.setBounds(109, 51, 150, 20);
		panelCadastro.add(txtDespesa);

		btnCadastrarDespesa = new JButton("Cadastrar");
		btnCadastrarDespesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCadastrarDespesaActionPerformed();
			}
		});
		btnCadastrarDespesa.setBounds(269, 135, 105, 23);
		panelCadastro.add(btnCadastrarDespesa);

		btnLimparCampos = new JButton("Limpar campos");
		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLimparCamposActionPerformed();
			}
		});
		btnLimparCampos.setBounds(386, 135, 150, 23);
		panelCadastro.add(btnLimparCampos);

		lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(12, 26, 87, 15);
		panelCadastro.add(lblCategoria);

		cbCategoria = new JComboBox<Categoria>();
		cbCategoria.setBounds(109, 21, 150, 20);
		panelCadastro.add(cbCategoria);

		btnAddCategoria = new JButton("✎");
		btnAddCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddCategoriaAction();
			}
		});
		btnAddCategoria.setFont(new Font("Dialog", Font.BOLD, 12));
		btnAddCategoria.setBounds(269, 21, 44, 20);
		panelCadastro.add(btnAddCategoria);

		lblValor = new JLabel("Valor:");
		lblValor.setBounds(12, 79, 70, 15);
		panelCadastro.add(lblValor);

		txtValor = new JTextField();
		txtValor.setColumns(10);
		txtValor.setBounds(109, 77, 150, 20);
		panelCadastro.add(txtValor);

		panelTipoDespesa = new JPanel();

		panelTipoDespesa.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Tipo de despesa",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panelTipoDespesa.setBounds(12, 108, 247, 50);
		panelCadastro.add(panelTipoDespesa);
		panelTipoDespesa.setLayout(null);

		rdbtnOcasional = new JRadioButton("Ocasional");
		rdbtnOcasional.setBounds(8, 19, 95, 23);
		panelTipoDespesa.add(rdbtnOcasional);

		rdbtnMensal = new JRadioButton("Mensal");
		rdbtnMensal.setBounds(136, 19, 89, 23);
		panelTipoDespesa.add(rdbtnMensal);

		lblDatammaaaa = new JLabel("Data (MM/AAAA):");
		lblDatammaaaa.setBounds(277, 79, 117, 15);
		panelCadastro.add(lblDatammaaaa);

		txtData = new JFormattedTextField(mascaraData);
		txtData.setBounds(402, 77, 134, 19);
		panelCadastro.add(txtData);
		txtData.setColumns(10);

		panelDespesas = new JPanel();
		panelDespesas.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Despesas cadastradas",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panelDespesas.setBounds(10, 188, 548, 197);
		getContentPane().add(panelDespesas);
		panelDespesas.setLayout(null);

		btnEditarDespesa = new JButton("Editar");
		btnEditarDespesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEditarDespesaActionPerformed();
			}
		});
		btnEditarDespesa.setBounds(333, 162, 100, 23);
		panelDespesas.add(btnEditarDespesa);

		btnExcluirDespesa = new JButton("Excluir");
		btnExcluirDespesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnExcluirActionPerformed();
			}
		});
		btnExcluirDespesa.setBounds(438, 162, 100, 23);
		panelDespesas.add(btnExcluirDespesa);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 14, 526, 136);
		panelDespesas.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tableMouseClickedAction();
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Categoria", "Despesa", "Data inicial", "Mensal", "Ocasional", "Total Anual" }));
		scrollPane.setViewportView(table);
	}

	protected void btnExcluirActionPerformed() {
		int coluna = table.getSelectedRow();

		if (coluna == -1) {
			JOptionPane.showMessageDialog(this, "Selecione um fundo para excluir.", "Aviso",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		Despesa despesa = despesas.get(coluna);

		int resposta = JOptionPane.showConfirmDialog(this,
				"Deseja realmente excluir o fundo: " + despesa.getDespesa() + "?", "Confirmação",
				JOptionPane.YES_NO_OPTION);
		if (resposta == JOptionPane.YES_OPTION) {
			try {
				new DespesaService().excluir(despesa);
				JOptionPane.showMessageDialog(this, "Fundo Ocasional excluido.", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
				carregaTabelaDespesa();
			} catch (SQLException | IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Erro ao excluir Fundo Ocasional.", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	protected void btnEditarDespesaActionPerformed() {
		int coluna = table.getSelectedRow();

		if (coluna == -1) {
			JOptionPane.showMessageDialog(this, "Selecione uma despesa para editar.", "Aviso",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		Despesa despesa = despesas.get(coluna);

		int resposta = JOptionPane.showConfirmDialog(this,
				"Deseja realmente editar a despesa: " + despesa.getDespesa() + "?", "Confirmação",
				JOptionPane.YES_NO_OPTION);
		if (resposta == JOptionPane.YES_OPTION) {
			Categoria categoria = new Categoria();
			categoria = (Categoria) cbCategoria.getSelectedItem();

			if (categoria instanceof Categoria) {
				if (!(txtDespesa.getText().toString().equals("") || txtValor.getText().toString().equals(""))) {
					if (isDateValid(txtData.getText())) {
						Despesa despesaEditada = new Despesa();
						despesaEditada.setId(despesa.getId());
						despesaEditada.setCategoria(categoria);
						despesaEditada.setDespesa(txtDespesa.getText());
						despesaEditada.setData(txtData.getText());
						try {

							if (rdbtnMensal.isSelected()) {
								despesaEditada.setMensal(Double.parseDouble(txtValor.getText()));
								despesaEditada.setOcasional(0.0);
								despesaEditada.setTotalAno(Double.parseDouble(txtValor.getText()) * 12);
							}
							if (rdbtnOcasional.isSelected()) {
								despesaEditada.setOcasional(Double.parseDouble(txtValor.getText()));
								despesaEditada.setMensal(0.0);
								despesaEditada.setTotalAno(Double.parseDouble(txtValor.getText()));
							}

							new DespesaService().atualizar(despesaEditada);
							JOptionPane.showMessageDialog(this, "Rendimento editado.", "Sucesso",
									JOptionPane.INFORMATION_MESSAGE);
							carregaTabelaDespesa();
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(this, "Valor inválido. Insira um valor numérico.", "Erro",
									JOptionPane.ERROR_MESSAGE);
						} catch (SQLException | IOException e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(this, "Erro ao editar Rendimento.", "Erro",
									JOptionPane.ERROR_MESSAGE);
						}
					} else
						JOptionPane.showMessageDialog(this, "Por favor insira uma data válida.", "Aviso",
								JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this,
							"Por favor insira valores em todos os campos para editar uma categoria.", "Aviso",
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Nenhuma categoria encontrada, por favor cadastre uma categoria.",
						"Aviso", JOptionPane.WARNING_MESSAGE);
			}

		}

	}

	protected void btnLimparCamposActionPerformed() {
		txtDespesa.setText("");
		txtValor.setText("");
		txtData.setText("");

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

	private void carregarComboBox() {
		try {

			this.categorias = new CategoriaService().buscarTodos();

			this.cbCategoria.removeAllItems();
			for (Categoria categoria : categorias) {
				this.cbCategoria.addItem(categoria);
			}

		} catch (SQLException | IOException e) {
			JOptionPane.showMessageDialog(this, "Erro ao carregar categorias.", "Erro", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	private void btnCadastrarDespesaActionPerformed() {
		Categoria categoria = new Categoria();
		categoria = (Categoria) cbCategoria.getSelectedItem();

		if (categoria instanceof Categoria) {
			if (!(txtDespesa.getText().toString().equals("") || txtValor.getText().toString().equals(""))) {
				if (isDateValid(txtData.getText())) {
					Despesa despesa = new Despesa();
					despesa.setCategoria(categoria);
					despesa.setDespesa(txtDespesa.getText());
					despesa.setData(txtData.getText());
					try {
						if (rdbtnMensal.isSelected()) {
							despesa.setMensal(Double.parseDouble(txtValor.getText()));
							despesa.setOcasional(0.0);
							despesa.setTotalAno(Double.parseDouble(txtValor.getText()) * 12);
						}
						if (rdbtnOcasional.isSelected()) {
							despesa.setOcasional(Double.parseDouble(txtValor.getText()));
							despesa.setMensal(0.0);
							despesa.setTotalAno(Double.parseDouble(txtValor.getText()));
						}

						new DespesaService().cadastrar(despesa);

						// JOptionPane.showMessageDialog(this, "Rendimento cadastrado com sucesso.",
						// "Sucesso", JOptionPane.INFORMATION_MESSAGE);
						carregaTabelaDespesa();
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(this, "Valor inválido. Insira um valor numérico.", "Erro",
								JOptionPane.ERROR_MESSAGE);
					} catch (SQLException | IOException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(this, "Erro ao cadastrar Rendimento.", "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
				} else
					JOptionPane.showMessageDialog(this, "Por favor insira uma data válida.", "Aviso",
							JOptionPane.WARNING_MESSAGE);
			} else
				JOptionPane.showMessageDialog(this,
						"Por favor insira valores em todos os campos para cadastrar uma categoria.", "Aviso",
						JOptionPane.WARNING_MESSAGE);
		} else
			JOptionPane.showMessageDialog(this, "Nenhuma categoria encontrada, por favor cadastre uma categoria.",
					"Aviso", JOptionPane.WARNING_MESSAGE);
	}

	private void carregaTabelaDespesa() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.fireTableDataChanged();
		model.setRowCount(0);

		despesas = new ArrayList<Despesa>();
		try {
			despesas = new DespesaService().buscarTodos();
			for (Despesa despesa : despesas) {
				model.addRow(new Object[] { despesa.getCategoria().getNome(), despesa.getDespesa(), despesa.getData(),
						despesa.getMensal(), despesa.getOcasional(), despesa.getTotalAno() });
			}
		} catch (SQLException | IOException e) {
			JOptionPane.showMessageDialog(this, "Erro ao carregar rendimentos.", "Erro", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

	private void tableMouseClickedAction() {
		int coluna = table.getSelectedRow();
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		Categoria categoria = new Categoria();
		for (Categoria categoriaItem : categorias) {
			if (categoriaItem.getNome().equals(model.getValueAt(coluna, 0).toString()))
				categoria = categoriaItem;
		}

		cbCategoria.setSelectedItem(categoria);

		txtDespesa.setText(model.getValueAt(coluna, 1).toString());

		Boolean isMensal = (Double.parseDouble(model.getValueAt(coluna, 3).toString()) > Double
				.parseDouble(model.getValueAt(coluna, 4).toString()));
		String valor = isMensal ? model.getValueAt(coluna, 3).toString() : model.getValueAt(coluna, 4).toString();
		txtValor.setText(valor);
		txtData.setText(model.getValueAt(coluna, 2).toString());

		if (isMensal) {
			rdbtnMensal.setSelected(true);
			rdbtnOcasional.setSelected(false);
		} else {
			rdbtnMensal.setSelected(false);
			rdbtnOcasional.setSelected(true);
		}
	}

	private void btnAddCategoriaAction() {
		new CategoriaWindow().setVisible(true);
	}

}