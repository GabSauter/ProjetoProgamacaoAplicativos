
package ui;
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
import javax.swing.JSeparator;

public class  ModuloRendimento{

    private JFrame frame;
    private JTextField txtCategoria;
    private JComboBox<String> comboBoxCategoria;
    private JTable table;
    private JTextField textField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
            	ModuloRendimento window = new ModuloRendimento();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * @wbp.parser.entryPoint
     */
    public ModuloRendimento() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 521, 433);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panelCategoria = new JPanel();
        panelCategoria.setBounds(10, 11, 487, 70);
        frame.getContentPane().add(panelCategoria);
        panelCategoria.setLayout(null);

        JLabel lblCategoria = new JLabel("Categoria:");
        lblCategoria.setBounds(21, 11, 60, 14);
        panelCategoria.add(lblCategoria);

        txtCategoria = new JTextField();
        txtCategoria.setBounds(107, 9, 150, 20);
        panelCategoria.add(txtCategoria);
        txtCategoria.setColumns(10);

        JButton btnCadastrarCategoria = new JButton("Cadastrar");
        btnCadastrarCategoria.setBounds(267, 7, 100, 23);
        panelCategoria.add(btnCadastrarCategoria);

        JButton btnEditarCategoria = new JButton("Editar");
        btnEditarCategoria.setBounds(267, 36, 100, 23);
        panelCategoria.add(btnEditarCategoria);

        JButton btnExcluirCategoria = new JButton("Excluir");
        btnExcluirCategoria.setBounds(377, 7, 100, 23);
        panelCategoria.add(btnExcluirCategoria);

        comboBoxCategoria = new JComboBox<String>();
        comboBoxCategoria.setBounds(107, 37, 150, 20);
        panelCategoria.add(comboBoxCategoria);

        JPanel panelRendimento = new JPanel();
        panelRendimento.setBounds(10, 202, 487, 183);
        frame.getContentPane().add(panelRendimento);
        panelRendimento.setLayout(null);

        JButton btnCadastrarRendimento = new JButton("Cadastrar");
        btnCadastrarRendimento.setBounds(10, 150, 100, 23);
        panelRendimento.add(btnCadastrarRendimento);

        JButton btnEditarRendimento = new JButton("Editar");
        btnEditarRendimento.setBounds(120, 150, 100, 23);
        panelRendimento.add(btnEditarRendimento);

        JButton btnExcluirRendimento = new JButton("Excluir");
        btnExcluirRendimento.setBounds(230, 150, 100, 23);
        panelRendimento.add(btnExcluirRendimento);
        
                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setBounds(10, 4, 467, 136);
                panelRendimento.add(scrollPane);
                
                        table = new JTable();
                        table.setModel(new DefaultTableModel(
                            new Object[][] {
                            },
                            new String[] {
                                "Categoria", "Rendimento Mensal", "Rendimento Ocasional", "Total", "Ano"
                            }
                        ));
                        scrollPane.setViewportView(table);
        
        JPanel panelCategoria_1 = new JPanel();
        panelCategoria_1.setLayout(null);
        panelCategoria_1.setBounds(10, 112, 487, 70);
        frame.getContentPane().add(panelCategoria_1);
        
        JLabel lblCategoria_1 = new JLabel("Rendimento:");
        lblCategoria_1.setBounds(10, 11, 87, 14);
        panelCategoria_1.add(lblCategoria_1);
        
        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(107, 9, 150, 20);
        panelCategoria_1.add(textField);
        
        JButton btnCadastrarRendimento_1 = new JButton("Cadastrar");
        btnCadastrarRendimento_1.setBounds(267, 7, 100, 23);
        panelCategoria_1.add(btnCadastrarRendimento_1);
        
        JButton btnEditarRendimento_1 = new JButton("Editar");
        btnEditarRendimento_1.setBounds(267, 36, 100, 23);
        panelCategoria_1.add(btnEditarRendimento_1);
        
        JButton btnExcluirRendimento_1 = new JButton("Excluir");
        btnExcluirRendimento_1.setBounds(377, 7, 100, 23);
        panelCategoria_1.add(btnExcluirRendimento_1);
        
        JComboBox<String> comboBoxRendimento_1 = new JComboBox<String>();
        comboBoxRendimento_1.setBounds(107, 37, 150, 20);
        panelCategoria_1.add(comboBoxRendimento_1);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(10, 202, 487, 9);
        frame.getContentPane().add(separator);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(10, 91, 487, 9);
        frame.getContentPane().add(separator_1);
    }
}