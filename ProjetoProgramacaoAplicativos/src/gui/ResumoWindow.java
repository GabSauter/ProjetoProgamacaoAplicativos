package gui;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ResumoWindow extends JFrame {

	private JPanel contentPane;
	private JTable tableMensal;
	private JTable tableAnual;
	private JScrollPane scrollPaneAnual;
	private JLabel lblResumoAnual;
	private JComboBox comboBox;
	private JLabel lblSelecioneOMs;
	private JScrollPane scrollPaneMensal;
	private JLabel lblResumoMensal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResumoWindow frame = new ResumoWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ResumoWindow() {
		this.initComponents();
	}
	private void initComponents() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 820, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblResumoMensal = new JLabel("Resumo Mensal");
		lblResumoMensal.setBounds(12, 12, 128, 15);
		contentPane.add(lblResumoMensal);
		
		scrollPaneMensal = new JScrollPane();
		scrollPaneMensal.setBounds(12, 39, 796, 143);
		contentPane.add(scrollPaneMensal);
		
		tableMensal = new JTable();
		tableMensal.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Descrição", "Total Mensal"
			}
		));
		scrollPaneMensal.setViewportView(tableMensal);
		
		lblSelecioneOMs = new JLabel("Selecione o mês desejado");
		lblSelecioneOMs.setBounds(448, 12, 185, 15);
		contentPane.add(lblSelecioneOMs);
		
		comboBox = new JComboBox();
		comboBox.setBounds(641, 9, 167, 20);
		contentPane.add(comboBox);
		
		lblResumoAnual = new JLabel("Resumo Anual");
		lblResumoAnual.setBounds(12, 194, 111, 15);
		contentPane.add(lblResumoAnual);
		
		scrollPaneAnual = new JScrollPane();
		scrollPaneAnual.setBounds(12, 221, 796, 207);
		contentPane.add(scrollPaneAnual);
		
		tableAnual = new JTable();
		tableAnual.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Descrição","Mensal(x12)", "Ocasional","Total Anual"
			}
		));
		scrollPaneAnual.setViewportView(tableAnual);
	}
}
