package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class CategoriaWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnExcluir;
	private JButton btnEditar;
	private JButton btnCadastrar;
	private JLabel lblNome;
	private JLabel lblCategoriasCadastradas;
	private JComboBox comboBox;
	private JLabel lblCadastrarOuEditar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CategoriaWindow frame = new CategoriaWindow();
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
	public CategoriaWindow() {
		this.initComponents();
	}
	
	public void initComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 428, 217);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCadastrarOuEditar = new JLabel("Cadastrar ou editar Categorias");
		lblCadastrarOuEditar.setBounds(12, 20, 255, 15);
		contentPane.add(lblCadastrarOuEditar);
		
		comboBox = new JComboBox();
		comboBox.setBounds(195, 47, 209, 19);
		contentPane.add(comboBox);
		
		lblCategoriasCadastradas = new JLabel("Categorias cadastradas");
		lblCategoriasCadastradas.setBounds(12, 47, 177, 15);
		contentPane.add(lblCategoriasCadastradas);
		
		textField = new JTextField();
		textField.setBounds(195, 78, 209, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblNome = new JLabel("Nome:");
		lblNome.setBounds(137, 80, 52, 15);
		contentPane.add(lblNome);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(12, 135, 127, 25);
		contentPane.add(btnCadastrar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(151, 135, 124, 25);
		contentPane.add(btnEditar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(287, 135, 117, 25);
		contentPane.add(btnExcluir);
	}
}
