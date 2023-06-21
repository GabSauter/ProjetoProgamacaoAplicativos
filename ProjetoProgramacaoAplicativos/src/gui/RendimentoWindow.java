package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import entities.Categoria;
import entities.Rendimento;
import service.CategoriaService;
import service.RendimentoService;

public class RendimentoWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JTable table;
	private JTextField txtRendimento;
	private JTextField txtValor;
	private JButton btnExcluirRendimento;
	private JButton btnEditarRendimento;
	private JButton btnCadastrarRendimento;
	private JButton btnLimparCampos;
	private JButton btnAddCategoria;
	private JRadioButton rdbtnOcasional;
	private JRadioButton rdbtnMensal;
	private JComboBox<Categoria> cbCategoria;
	
	private JFormattedTextField txtData;
	private MaskFormatter mascaraData;
	
	private List<Categoria> categorias;
	private List<Rendimento> rendimentos;
	private ButtonGroup buttonGroup;
	private JScrollPane scrollPane;
	private JPanel panelRendimento;
	private JLabel lblDatammaaaa;
	private JPanel panelTipoRendimento;
	private JLabel lblValor;
	private JLabel lblCategoria;
	private JLabel lblRendimento;
	private JPanel panelCadastro;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				RendimentoWindow window = new RendimentoWindow();
				window.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public RendimentoWindow() {
		this.criarMascaraData();
		this.initComponents();
		this.carregarComboBox();
		this.carregaTabelaRendimento();
	}

	private void initComponents() {

		setBounds(100, 100, 570, 433);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false);

		panelCadastro = new JPanel();
		panelCadastro.setBorder(
				new TitledBorder(null, "Cadastrar Rendimento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCadastro.setLayout(null);
		panelCadastro.setBounds(10, 12, 548, 170);
		getContentPane().add(panelCadastro);

		lblRendimento = new JLabel("Rendimento:");
		lblRendimento.setBounds(12, 53, 99, 14);
		panelCadastro.add(lblRendimento);

		txtRendimento = new JTextField();
		txtRendimento.setColumns(10);
		txtRendimento.setBounds(109, 51, 150, 20);
		panelCadastro.add(txtRendimento);

		btnCadastrarRendimento = new JButton("Cadastrar");
		btnCadastrarRendimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCadastrarRendimentoAction();
			}
		});
		btnCadastrarRendimento.setBounds(269, 135, 105, 23);
		panelCadastro.add(btnCadastrarRendimento);

		btnLimparCampos = new JButton("Limpar campos");
		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLimparCamposAction();
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

		panelTipoRendimento = new JPanel();

		panelTipoRendimento.setBorder(
				new TitledBorder(null, "Tipo de rendimento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTipoRendimento.setBounds(12, 108, 247, 50);
		panelCadastro.add(panelTipoRendimento);
		panelTipoRendimento.setLayout(null);

		buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnOcasional);
		buttonGroup.add(rdbtnMensal);

		rdbtnOcasional = new JRadioButton("Ocasional");
		rdbtnOcasional.setBounds(8, 19, 95, 23);
		panelTipoRendimento.add(rdbtnOcasional);

		rdbtnMensal = new JRadioButton("Mensal");
		rdbtnMensal.setBounds(136, 19, 89, 23);
		panelTipoRendimento.add(rdbtnMensal);

		rdbtnOcasional.setSelected(true);

		lblDatammaaaa = new JLabel("Data (MM/AAAA):");
		lblDatammaaaa.setBounds(269, 79, 123, 15);
		panelCadastro.add(lblDatammaaaa);

		txtData = new JFormattedTextField(mascaraData);
		txtData.setBounds(397, 77, 139, 19);
		panelCadastro.add(txtData);
		txtData.setColumns(10);

		rdbtnMensal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rdbtnOcasional.setSelected(false);
			}
		});

		rdbtnOcasional.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rdbtnMensal.setSelected(false);
			}
		});

		panelRendimento = new JPanel();
		panelRendimento.setBorder(
				new TitledBorder(null, "Rendimentos cadastrados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelRendimento.setBounds(10, 188, 548, 197);
		getContentPane().add(panelRendimento);
		panelRendimento.setLayout(null);

		btnEditarRendimento = new JButton("Editar");
		btnEditarRendimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEditarRendimentoAction();
			}
		});
		btnEditarRendimento.setBounds(333, 162, 100, 23);
		panelRendimento.add(btnEditarRendimento);

		btnExcluirRendimento = new JButton("Excluir");
		btnExcluirRendimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnExcluirRendimentoAction();
			}
		});
		btnExcluirRendimento.setBounds(438, 162, 100, 23);
		panelRendimento.add(btnExcluirRendimento);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 14, 526, 136);
		panelRendimento.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tableMouseClickedAction();
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Categoria", "Rendimento", "Data Inicial", "Mensal", "Ocasional", "Total Anual" }));
		scrollPane.setViewportView(table);
	}

	public void criarMascaraData() {
		try {
			this.mascaraData = new MaskFormatter("##/####");
		} catch (ParseException e) {
			System.out.println("ERRO: " + e.getMessage());
		}
	}

	private void btnExcluirRendimentoAction() {
		int coluna = table.getSelectedRow();

		if (coluna == -1) {
			JOptionPane.showMessageDialog(this, "Selecione um rendimento para excluir.", "Aviso",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		Rendimento rendimento = rendimentos.get(coluna);

		int resposta = JOptionPane.showConfirmDialog(this,
				"Deseja realmente excluir o rendimento: " + rendimento.getRendimento() + "?", "Confirmação",
				JOptionPane.YES_NO_OPTION);
		if (resposta == JOptionPane.YES_OPTION) {
			try {
				new RendimentoService().excluir(rendimento);
				JOptionPane.showMessageDialog(this, "Rendimento excluido.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				carregaTabelaRendimento();
			} catch (SQLException | IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Erro ao excluir Rendimento.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void btnEditarRendimentoAction() {

		int coluna = table.getSelectedRow();

		if (coluna == -1) {
			JOptionPane.showMessageDialog(this, "Selecione um rendimento para editar.", "Aviso",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		Rendimento rendimento = rendimentos.get(coluna);

		int resposta = JOptionPane.showConfirmDialog(this,
				"Deseja realmente editar o rendimento: " + rendimento.getRendimento() + "?", "Confirmação",
				JOptionPane.YES_NO_OPTION);
		if (resposta == JOptionPane.YES_OPTION) {
			Categoria categoria = new Categoria();
			categoria = (Categoria) cbCategoria.getSelectedItem();

			if (categoria instanceof Categoria) {
				if (!(txtRendimento.getText().toString().equals("") || txtValor.getText().toString().equals(""))) {
					if (isDateValid(txtData.getText())) {
						Rendimento rendimentoEditado = new Rendimento();
						rendimentoEditado.setId(rendimento.getId());
						rendimentoEditado.setCategoria(categoria);
						rendimentoEditado.setRendimento(txtRendimento.getText());
						rendimentoEditado.setData(txtData.getText());
						try {

							if (rdbtnMensal.isSelected()) {
								rendimentoEditado.setMensal(Double.parseDouble(txtValor.getText()));
								rendimentoEditado.setOcasional(0.0);
								rendimentoEditado.setTotalAno(Double.parseDouble(txtValor.getText()) * 12);
							}
							if (rdbtnOcasional.isSelected()) {
								rendimentoEditado.setOcasional(Double.parseDouble(txtValor.getText()));
								rendimentoEditado.setMensal(0.0);
								rendimentoEditado.setTotalAno(Double.parseDouble(txtValor.getText()));
							}

							new RendimentoService().atualizar(rendimentoEditado);
							JOptionPane.showMessageDialog(this, "Rendimento editado.", "Sucesso",
									JOptionPane.INFORMATION_MESSAGE);
							carregaTabelaRendimento();
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

	private void btnCadastrarRendimentoAction() {
		Categoria categoria = new Categoria();
		categoria = (Categoria) cbCategoria.getSelectedItem();

		if (categoria instanceof Categoria) {
			if (!(txtRendimento.getText().toString().equals("") || txtValor.getText().toString().equals(""))) {
				if (isDateValid(txtData.getText())) {
					Rendimento rendimento = new Rendimento();
					rendimento.setCategoria(categoria);
					rendimento.setRendimento(txtRendimento.getText());
					rendimento.setData(txtData.getText());
					try {
						if (rdbtnMensal.isSelected()) {
							rendimento.setMensal(Double.parseDouble(txtValor.getText()));
							rendimento.setOcasional(0.0);
							rendimento.setTotalAno(Double.parseDouble(txtValor.getText()) * 12);
						}
						if (rdbtnOcasional.isSelected()) {
							rendimento.setOcasional(Double.parseDouble(txtValor.getText()));
							rendimento.setMensal(0.0);
							rendimento.setTotalAno(Double.parseDouble(txtValor.getText()));
						}

						new RendimentoService().cadastrar(rendimento);

						// JOptionPane.showMessageDialog(this, "Rendimento cadastrado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
						carregaTabelaRendimento();
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

	private void btnLimparCamposAction() {
		txtRendimento.setText("");
		txtValor.setText("");
		txtData.setText("");
	}

	private void btnAddCategoriaAction() {
		CategoriaWindow categoriaWindow = new CategoriaWindow();
		categoriaWindow.setVisible(true);

		categoriaWindow.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				carregarComboBox();
			}
		});
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

	private void carregaTabelaRendimento() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.fireTableDataChanged();
		model.setRowCount(0);

		rendimentos = new ArrayList<Rendimento>();
		try {
			rendimentos = new RendimentoService().buscarTodos();
			for (Rendimento rendimento : rendimentos) {
				model.addRow(new Object[] { rendimento.getCategoria().getNome(), rendimento.getRendimento(),
						rendimento.getData(), rendimento.getMensal(), rendimento.getOcasional(),
						rendimento.getTotalAno() });
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

		txtRendimento.setText(model.getValueAt(coluna, 1).toString());

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
}