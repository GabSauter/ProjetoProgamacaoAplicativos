
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

public class  FundoOcasionalWindow{

    private JFrame frame;
    private JTable table;
    private JTextField txtRendimento;
    private JTextField txtValor;
    private JPanel panelCadastro;
    private JLabel lblDespesa;
    private JButton btnCadastrarRendimento;
    private JButton btnLimparCampos;
    private JLabel lblValor;
    private JPanel panelTipoRendimento;
    private JRadioButton rdbtnOcasional;
    private JRadioButton rdbtnMensal;
    private JPanel panelFundos;
    private JButton btnEditarRendimento;
    private JButton btnExcluirRendimento;
    private JScrollPane scrollPane;
    private JSeparator separator;

	/**
	 * Launch the application.
	 */
	  public static void main(String[] args) { EventQueue.invokeLater(() -> { try {
	  FundoOcasionalWindow window = new FundoOcasionalWindow();
	  window.frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); }
	  }); }
	 
/**
 * Create the frame.
 * @wbp.parser.entryPoint
 */
    public FundoOcasionalWindow() {
        initComponents();
    }

    private void initComponents() {
        frame = new JFrame();
        frame.setBounds(100, 100, 570, 390);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        panelCadastro = new JPanel();
        panelCadastro.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Cadastrar  fundo de despesas ocasionais", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
        panelCadastro.setLayout(null);
        panelCadastro.setBounds(10, 12, 548, 145);
        frame.getContentPane().add(panelCadastro);
        
        lblDespesa = new JLabel("Despesa:");
        lblDespesa.setBounds(12, 28, 99, 14);
        panelCadastro.add(lblDespesa);
        
        txtRendimento = new JTextField();
        txtRendimento.setColumns(10);
        txtRendimento.setBounds(109, 26, 150, 20);
        panelCadastro.add(txtRendimento);
        
        btnCadastrarRendimento = new JButton("Cadastrar");
        btnCadastrarRendimento.setBounds(269, 110, 105, 23);
        panelCadastro.add(btnCadastrarRendimento);
        
        btnLimparCampos = new JButton("Limpar campos");
        btnLimparCampos.setBounds(386, 110, 150, 23);
        panelCadastro.add(btnLimparCampos);
        
        lblValor = new JLabel("Valor:");
        lblValor.setBounds(12, 54, 70, 15);
        panelCadastro.add(lblValor);
        
        
        txtValor = new JTextField();
        txtValor.setColumns(10);
        txtValor.setBounds(109, 52, 150, 20);
        panelCadastro.add(txtValor);
        
        panelTipoRendimento = new JPanel();
        
                panelTipoRendimento.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Tipo de despesa", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
                panelTipoRendimento.setBounds(12, 83, 247, 50);
                panelCadastro.add(panelTipoRendimento);
                panelTipoRendimento.setLayout(null);
                
                rdbtnOcasional = new JRadioButton("Ocasional");
                rdbtnOcasional.setBounds(8, 19, 95, 23);
                panelTipoRendimento.add(rdbtnOcasional);
                
                rdbtnMensal = new JRadioButton("Mensal");
                rdbtnMensal.setBounds(136, 19, 89, 23);
                panelTipoRendimento.add(rdbtnMensal);
        
        separator = new JSeparator();
        separator.setBounds(10, 165, 548, 2);
        frame.getContentPane().add(separator);

        panelFundos = new JPanel();
        panelFundos.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Fundos Cadastrados", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
        panelFundos.setBounds(10, 169, 548, 186);
        frame.getContentPane().add(panelFundos);
        panelFundos.setLayout(null);

        btnEditarRendimento = new JButton("Editar");
        btnEditarRendimento.setBounds(333, 162, 100, 23);
        panelFundos.add(btnEditarRendimento);

        btnExcluirRendimento = new JButton("Excluir");
        btnExcluirRendimento.setBounds(438, 162, 100, 23);
        panelFundos.add(btnExcluirRendimento);
        
                scrollPane = new JScrollPane();
                scrollPane.setBounds(12, 14, 526, 136);
                panelFundos.add(scrollPane);
                
                        table = new JTable();
                        table.setModel(new DefaultTableModel(
                            new Object[][] {
                            },
                            new String[] {
                                "Fundo Ocasional", "Mensal", "Ocasional", "Total Anual"
                            }
                        ));
                        scrollPane.setViewportView(table);
    }
}