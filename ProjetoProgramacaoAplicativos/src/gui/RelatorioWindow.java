package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RelatorioWindow {
    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	RelatorioWindow window = new RelatorioWindow();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public RelatorioWindow() {
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

        JCheckBox chkRelatorioAnual = new JCheckBox("Relatório Anual");
        chkRelatorioAnual.setBounds(10, 11, 150, 23);
        panel.add(chkRelatorioAnual);

        JCheckBox chkRelatorioMensal = new JCheckBox("Relatório Mensal");
        chkRelatorioMensal.setBounds(10, 45, 150, 23);
        panel.add(chkRelatorioMensal);

        JCheckBox chkOrganizacao = new JCheckBox("Organização");
        chkOrganizacao.setBounds(10, 79, 150, 23);
        panel.add(chkOrganizacao);

        JButton btnGerarRelatorio = new JButton("Gerar Relatório");
        btnGerarRelatorio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                boolean relatorioAnual = chkRelatorioAnual.isSelected();
                boolean relatorioMensal = chkRelatorioMensal.isSelected();
                boolean organizacao = chkOrganizacao.isSelected();

               
                if (relatorioAnual) {
                    
                }

                if (relatorioMensal) {
                   
                }

                if (organizacao) {
                  
                }
            }
        });
        btnGerarRelatorio.setBounds(10, 113, 150, 23);
        panel.add(btnGerarRelatorio);
    }
}
