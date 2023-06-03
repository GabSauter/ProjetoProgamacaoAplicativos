
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

public class  RendimentoWindow{

    private JFrame frame;
    private JTable table;
    private JTextField txtRendimento;
    private JTextField txtValor;

	/**
	 * Launch the application.
	 */
	  public static void main(String[] args) { EventQueue.invokeLater(() -> { try {
	  RendimentoWindow window = new RendimentoWindow();
	  window.frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); }
	  }); }
	 
/**
 * Create the frame.
 * @wbp.parser.entryPoint
 */
    public RendimentoWindow() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 570, 433);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panelRendimento = new JPanel();
        panelRendimento.setBorder(new TitledBorder(null, "Rendimentos cadastrados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelRendimento.setBounds(10, 188, 548, 197);
        frame.getContentPane().add(panelRendimento);
        panelRendimento.setLayout(null);

        JButton btnEditarRendimento = new JButton("Editar");
        btnEditarRendimento.setBounds(333, 162, 100, 23);
        panelRendimento.add(btnEditarRendimento);

        JButton btnExcluirRendimento = new JButton("Excluir");
        btnExcluirRendimento.setBounds(438, 162, 100, 23);
        panelRendimento.add(btnExcluirRendimento);
        
                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setBounds(12, 14, 526, 136);
                panelRendimento.add(scrollPane);
                
                        table = new JTable();
                        table.setModel(new DefaultTableModel(
                            new Object[][] {
                            },
                            new String[] {
                                "Categoria", "Rendimento","Mensal", "Ocasional", "Ano"
                            }
                        ));
                        scrollPane.setViewportView(table);
        
        JPanel panelCategoria_1 = new JPanel();
        panelCategoria_1.setBorder(new TitledBorder(null, "Cadastrar Rendimento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelCategoria_1.setLayout(null);
        panelCategoria_1.setBounds(10, 12, 548, 170);
        frame.getContentPane().add(panelCategoria_1);
        
        JLabel lblRendimento = new JLabel("Rendimento:");
        lblRendimento.setBounds(12, 53, 99, 14);
        panelCategoria_1.add(lblRendimento);
        
        txtRendimento = new JTextField();
        txtRendimento.setColumns(10);
        txtRendimento.setBounds(109, 51, 150, 20);
        panelCategoria_1.add(txtRendimento);
        
        JButton btnCadastrarRendimento = new JButton("Cadastrar");
        btnCadastrarRendimento.setBounds(269, 135, 105, 23);
        panelCategoria_1.add(btnCadastrarRendimento);
        
        JButton btnLimparCampos = new JButton("Limpar campos");
        btnLimparCampos.setBounds(386, 135, 150, 23);
        panelCategoria_1.add(btnLimparCampos);
        
        JLabel lblCategoria = new JLabel("Categoria");
        lblCategoria.setBounds(12, 26, 87, 15);
        panelCategoria_1.add(lblCategoria);
        
        JComboBox<String> cbCategoria = new JComboBox<String>();
        cbCategoria.setBounds(109, 21, 150, 20);
        panelCategoria_1.add(cbCategoria);
        
        JButton btnAddCategoria = new JButton("✎");
        btnAddCategoria.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnAddCategoriaAction();
        	}
        });
        btnAddCategoria.setFont(new Font("Dialog", Font.BOLD, 12));
        btnAddCategoria.setBounds(269, 21, 44, 20);
        panelCategoria_1.add(btnAddCategoria);
        
        JLabel lblValor = new JLabel("Valor:");
        lblValor.setBounds(12, 79, 70, 15);
        panelCategoria_1.add(lblValor);
        
        
        txtValor = new JTextField();
        txtValor.setColumns(10);
        txtValor.setBounds(109, 77, 150, 20);
        panelCategoria_1.add(txtValor);
        
        JPanel panelTipoRendimento = new JPanel();

        panelTipoRendimento.setBorder(new TitledBorder(null, "Tipo de rendimento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelTipoRendimento.setBounds(12, 108, 247, 50);
        panelCategoria_1.add(panelTipoRendimento);
        panelTipoRendimento.setLayout(null);
        
        JRadioButton rdbtnOcasional = new JRadioButton("Ocasional");
        rdbtnOcasional.setBounds(8, 19, 95, 23);
        panelTipoRendimento.add(rdbtnOcasional);
        
        JRadioButton rdbtnMensal = new JRadioButton("Mensal");
        rdbtnMensal.setBounds(136, 19, 89, 23);
        panelTipoRendimento.add(rdbtnMensal);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(10, 202, 487, 9);
        frame.getContentPane().add(separator);
    }

	protected void btnAddCategoriaAction() {
			new CategoriaWindow().setVisible(true);
	}
}