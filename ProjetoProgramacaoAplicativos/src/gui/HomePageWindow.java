package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class HomePageWindow {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HomePageWindow window = new HomePageWindow();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public HomePageWindow() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 414, 467);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblTitle = new JLabel("GGE Finances");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitle.setBounds(149, 27, 152, 20);
        frame.getContentPane().add(lblTitle);

        JButton btnRendimentos = new JButton("Módulo de Rendimentos");
        btnRendimentos.setBounds(60, 149, 286, 30);
        frame.getContentPane().add(btnRendimentos);

        JButton btnDespesas = new JButton("Módulo de Despesas");
        btnDespesas.setBounds(60, 261, 286, 30);
        frame.getContentPane().add(btnDespesas);

        JButton btnInvestimentos = new JButton("Módulo de Investimentos a Longo Prazo");
        btnInvestimentos.setBounds(60, 314, 286, 30);
        frame.getContentPane().add(btnInvestimentos);

        JButton btnFundoDespesas = new JButton("Módulo Fundo de Despesas Ocasionais");
        btnFundoDespesas.setBounds(60, 365, 286, 30);
        frame.getContentPane().add(btnFundoDespesas);

        JButton btnResumo = new JButton("Módulo Resumo");
        btnResumo.setBounds(60, 92, 286, 30);
        frame.getContentPane().add(btnResumo);

        JButton btnRelatorio = new JButton("Módulo Relatório");
        btnRelatorio.setBounds(60, 207, 286, 30);
        frame.getContentPane().add(btnRelatorio);
    }
}
