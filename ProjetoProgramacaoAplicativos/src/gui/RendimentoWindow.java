package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import entities.Categoria;
import entities.Rendimento;
import service.CategoriaService;
import service.RendimentoService;

import javax.swing.border.TitledBorder;
import java.awt.Font;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class  RendimentoWindow{

    private JFrame frame;
    private JTable table;
    private JTextField txtRendimento;
    private JTextField txtValor;
    private JScrollPane scrollPane;
    private JButton btnExcluirRendimento;
    private JButton btnEditarRendimento;
    private JPanel panelRendimento;
    private JPanel panelCadastro;
    private JLabel lblRendimento;
    private JButton btnCadastrarRendimento;
    private JButton btnLimparCampos;
    private JLabel lblCategoria;
    private JComboBox<Categoria> cbCategoria;
    private JButton btnAddCategoria;
    private JLabel lblValor;
    private JPanel panelTipoRendimento;
    private JRadioButton rdbtnOcasional;
    private JRadioButton rdbtnMensal;
    
    private List<Categoria> categorias;

	public static void main(String[] args) { EventQueue.invokeLater(() -> { try {
		RendimentoWindow window = new RendimentoWindow();
		window.frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); }
	}); }
	
	/**
	 * Create the frame.
	 * @wbp.parser.entryPoint
	 */
    public RendimentoWindow() {
        this.initComponents();
        this.carregarComboBox();
        this.carregaTabelaRendimento();
    }
    

    private void initComponents() {
        frame = new JFrame();
        frame.setBounds(100, 100, 570, 433);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        panelCadastro = new JPanel();
        panelCadastro.setBorder(new TitledBorder(null, "Cadastrar Rendimento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelCadastro.setLayout(null);
        panelCadastro.setBounds(10, 12, 548, 170);
        frame.getContentPane().add(panelCadastro);
        
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
        
        panelTipoRendimento.setBorder(new TitledBorder(null, "Tipo de rendimento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelTipoRendimento.setBounds(12, 108, 247, 50);
        panelCadastro.add(panelTipoRendimento);
        panelTipoRendimento.setLayout(null);
        
        rdbtnOcasional = new JRadioButton("Ocasional");
        rdbtnOcasional.setBounds(8, 19, 95, 23);
        panelTipoRendimento.add(rdbtnOcasional);
        
        rdbtnMensal = new JRadioButton("Mensal");
        rdbtnMensal.setBounds(136, 19, 89, 23);
        panelTipoRendimento.add(rdbtnMensal);

        panelRendimento = new JPanel();
        panelRendimento.setBorder(new TitledBorder(null, "Rendimentos cadastrados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelRendimento.setBounds(10, 188, 548, 197);
        frame.getContentPane().add(panelRendimento);
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
        table.setModel(new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
                "Categoria", "Rendimento","Mensal", "Ocasional", "Total Anual"
            }
        ));
        scrollPane.setViewportView(table);
    }
    
    
    
    

	private void btnExcluirRendimentoAction() {
		
	}

	private void btnEditarRendimentoAction() {
		
		
	}


	private void btnCadastrarRendimentoAction() {
		Categoria categoria = new Categoria();
		categoria = (Categoria) cbCategoria.getSelectedItem();
		
		Rendimento rendimento = new Rendimento();
		rendimento.setCategoria(categoria);
		rendimento.setRendimento(txtRendimento.getText());
		
		if(rdbtnMensal.isSelected()){
			rdbtnOcasional.setSelected(false);
			rendimento.setMensal(Double.parseDouble(txtValor.getText()));
			rendimento.setOcasional(0.0);
			rendimento.setTotalAno(Double.parseDouble(txtValor.getText())*12);
		}
		if(rdbtnOcasional.isSelected()){
			rdbtnMensal.setSelected(false);
			rendimento.setOcasional(Double.parseDouble(txtValor.getText()));
			rendimento.setMensal(0.0);
			rendimento.setTotalAno(Double.parseDouble(txtValor.getText()));
		}
		
		try {
			new RendimentoService().cadastrar(rendimento);
			
			carregaTabelaRendimento();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void btnLimparCamposAction() {
		
	}

	private void btnAddCategoriaAction() {
		new CategoriaWindow().setVisible(true);
	}
	
	private void carregarComboBox() {
		try {
			
			this.categorias = new CategoriaService().buscarTodos();
			
			this.cbCategoria.removeAllItems();
			for(Categoria categoria : categorias) {
				this.cbCategoria.addItem(categoria);
			}
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
    }
	
	private void carregaTabelaRendimento() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.fireTableDataChanged();
		model.setRowCount(0);
		
		List<Rendimento> rendimentos = new ArrayList<Rendimento>();
		try {
			rendimentos = new RendimentoService().buscarTodos();
			for(Rendimento rendimento: rendimentos) {
				model.addRow(new Object[] {
						rendimento.getCategoria().getNome(),
						rendimento.getRendimento(),
						rendimento.getMensal(),
						rendimento.getOcasional(),
						rendimento.getTotalAno()
				});
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void tableMouseClickedAction() {
    	int coluna = table.getSelectedRow();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		Categoria categoria = new Categoria();
		for(Categoria categoriaItem: categorias) {
			if(categoriaItem.getNome().equals(model.getValueAt(coluna, 0).toString()))
				categoria = categoriaItem;
		}
		
		cbCategoria.setSelectedItem(categoria);
		
		txtRendimento.setText(model.getValueAt(coluna, 1).toString());
		
		Boolean isMensal = (Double.parseDouble(model.getValueAt(coluna, 2).toString()) > Double.parseDouble(model.getValueAt(coluna, 3).toString()));
		
		String valor =  isMensal ? model.getValueAt(coluna, 2).toString() : model.getValueAt(coluna, 3).toString();
		txtValor.setText(valor);
		
		if(isMensal) {
			rdbtnMensal.setSelected(true);
			rdbtnOcasional.setSelected(false);
		}else {
			rdbtnMensal.setSelected(false);
			rdbtnOcasional.setSelected(true);
		}
	}
}