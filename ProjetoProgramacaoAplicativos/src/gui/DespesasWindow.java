
package gui;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import javax.swing.JSeparator;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class  DespesasWindow extends JFrame{ 

  
    private JTable table;
    private JTextField txtRendimento;
    private JTextField txtValor;
    private JPanel panelCadastro;
    private JLabel lblDespesa;
    private JButton btnCadastrarRendimento;
    private JButton btnLimparCampos;
    private JLabel lblCategoria;
    private JComboBox<String> cbCategoria;
    private JButton btnAddCategoria;
    private JLabel lblValor;
    private JPanel panelTipoRendimento;
    private JRadioButton rdbtnOcasional;
    private JRadioButton rdbtnMensal;
    private JPanel panelDespesas;
    private JButton btnEditarRendimento;
    private JButton btnExcluirRendimento;
    private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	  public static void main(String[] args) { EventQueue.invokeLater(() -> { try {
	  DespesasWindow window = new DespesasWindow();
	  window.setVisible(true); } catch (Exception e) { e.printStackTrace(); }
	  }); }
	 
/**
 * Create the frame.
 * @wbp.parser.entryPoint
 */
    public DespesasWindow() {
        this.initComponents();
    }

    private void initComponents() {
  
        setBounds(100, 100, 570, 433);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        
        panelCadastro = new JPanel();
        panelCadastro.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Cadastrar despesas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
        panelCadastro.setLayout(null);
        panelCadastro.setBounds(10, 12, 548, 170);
        getContentPane().add(panelCadastro);
        
        lblDespesa = new JLabel("Despesa:");
        lblDespesa.setBounds(12, 53, 99, 14);
        panelCadastro.add(lblDespesa);
        
        txtRendimento = new JTextField();
        txtRendimento.setColumns(10);
        txtRendimento.setBounds(109, 51, 150, 20);
        panelCadastro.add(txtRendimento);
        
        btnCadastrarRendimento = new JButton("Cadastrar");
        btnCadastrarRendimento.setBounds(269, 135, 105, 23);
        panelCadastro.add(btnCadastrarRendimento);
        
        btnLimparCampos = new JButton("Limpar campos");
        btnLimparCampos.setBounds(386, 135, 150, 23);
        panelCadastro.add(btnLimparCampos);
        
        lblCategoria = new JLabel("Categoria");
        lblCategoria.setBounds(12, 26, 87, 15);
        panelCadastro.add(lblCategoria);
        
        cbCategoria = new JComboBox<String>();
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
        
                panelTipoRendimento.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Tipo de despesa", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
                panelTipoRendimento.setBounds(12, 108, 247, 50);
                panelCadastro.add(panelTipoRendimento);
                panelTipoRendimento.setLayout(null);
                
                rdbtnOcasional = new JRadioButton("Ocasional");
                rdbtnOcasional.setBounds(8, 19, 95, 23);
                panelTipoRendimento.add(rdbtnOcasional);
                
                rdbtnMensal = new JRadioButton("Mensal");
                rdbtnMensal.setBounds(136, 19, 89, 23);
                panelTipoRendimento.add(rdbtnMensal);

        panelDespesas = new JPanel();
        panelDespesas.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Despesas cadastradas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
        panelDespesas.setBounds(10, 188, 548, 197);
        getContentPane().add(panelDespesas);
        panelDespesas.setLayout(null);

        btnEditarRendimento = new JButton("Editar");
        btnEditarRendimento.setBounds(333, 162, 100, 23);
        panelDespesas.add(btnEditarRendimento);

        btnExcluirRendimento = new JButton("Excluir");
        btnExcluirRendimento.setBounds(438, 162, 100, 23);
        panelDespesas.add(btnExcluirRendimento);
        
                scrollPane = new JScrollPane();
                scrollPane.setBounds(12, 14, 526, 136);
                panelDespesas.add(scrollPane);
                
                        table = new JTable();
                        table.setModel(new DefaultTableModel(
                            new Object[][] {
                            },
                            new String[] {
                                "Categoria", "Despesa", "Mensal", "Ocasional", "Total Anual"
                            }
                        ));
                        scrollPane.setViewportView(table);
    }

	private void btnAddCategoriaAction() {
			new CategoriaWindow().setVisible(true);
	}
}