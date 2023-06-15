package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InvestimentoLongoPrazoWindow {
    private JFrame frame;
    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	InvestimentoLongoPrazoWindow window = new InvestimentoLongoPrazoWindow();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public InvestimentoLongoPrazoWindow() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 434, 261);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JButton btnCadastrar = new JButton("Cadastrar Investimento");
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        btnCadastrar.setBounds(10, 11, 197, 23);
        panel.add(btnCadastrar);

        JButton btnEditar = new JButton("Editar Investimento");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                   
                    String investimento = (String) table.getValueAt(selectedRow, 0);
                    double mensal = (double) table.getValueAt(selectedRow, 1);
                    double ocasional = (double) table.getValueAt(selectedRow, 2);
                    double totalAnual = (double) table.getValueAt(selectedRow, 3);

                  
                }
            }
        });
        btnEditar.setBounds(10, 45, 197, 23);
        panel.add(btnEditar);

        JButton btnExcluir = new JButton("Excluir Investimento");
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
            
                }
            }
        });
        btnExcluir.setBounds(217, 45, 197, 23);
        panel.add(btnExcluir);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 79, 404, 171);
        panel.add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {
                { "Investimento X", 100.0, 50.0, 150.0 },
                { "Investimento Y", 200.0, 0.0, 200.0 },
                { "Investimento Z", 50.0, 25.0, 75.0 },
            },
            new String[] { "Investimento", "Mensal", "Ocasional", "Total Anual" }
        ));
        scrollPane.setViewportView(table);
    }
}
