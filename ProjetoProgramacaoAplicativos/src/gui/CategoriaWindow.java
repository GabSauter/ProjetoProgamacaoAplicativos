package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.Categoria;
import service.CategoriaService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;

public class CategoriaWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnExcluir;
	private JButton btnEditar;
	private JButton btnCadastrar;
	private JLabel lblNome;
	private JLabel lblCategoriasCadastradas;
	private JComboBox<String> comboBox;
	private JLabel lblCadastrarOuEditar;
	
	private List<Categoria> categorias;

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
	
	public CategoriaWindow() {
		this.initComponents();
		this.carregarComboBox();
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
		
		comboBox = new JComboBox<String>();
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
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCadastrarAction();
			}
		});
		btnCadastrar.setBounds(12, 135, 127, 25);
		contentPane.add(btnCadastrar);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEditarAction();
			}
		});
		btnEditar.setBounds(151, 135, 124, 25);
		contentPane.add(btnEditar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnExcluirAction();
			}
		});
		btnExcluir.setBounds(287, 135, 117, 25);
		contentPane.add(btnExcluir);
	}

	private void btnExcluirAction() {
		Categoria categoria = new Categoria();
		categoria.setNome((String) comboBox.getSelectedItem());
		
		int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir a categoria: " + categoria.getNome() + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
		
		if (resposta == JOptionPane.YES_OPTION) {
			try {
		        new CategoriaService().excluir(categoria);
		        JOptionPane.showMessageDialog(this, "Categoria excluida", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		        carregarComboBox();
		    } catch (SQLException | IOException e) {
		        e.printStackTrace();
		        JOptionPane.showMessageDialog(this, "Erro ao excluir categoria.", "Erro", JOptionPane.ERROR_MESSAGE);
		    }
		}
	}

	private void btnEditarAction() {
		Categoria categoria = new Categoria();
		categoria.setNome((String) comboBox.getSelectedItem());
		String novoNomeCategoria = textField.getText();
		
		int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente editar o nome da categoria: " + categoria.getNome() + " para " + novoNomeCategoria + "?", "Confirmação", JOptionPane.YES_NO_OPTION);

		if (resposta == JOptionPane.YES_OPTION) {
			try {
		        new CategoriaService().atualizar(novoNomeCategoria, categoria);
		        JOptionPane.showMessageDialog(this, "Categoria editada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		        carregarComboBox();
		    } catch (SQLException | IOException e) {
		        e.printStackTrace();
		        JOptionPane.showMessageDialog(this, "Erro ao editar categoria.", "Erro", JOptionPane.ERROR_MESSAGE);
		    }
		}
	}

	private void btnCadastrarAction() {
		Categoria categoria = new Categoria();
		categoria.setNome(textField.getText());
		
		try {
	        new CategoriaService().cadastrar(categoria);
	        JOptionPane.showMessageDialog(this, "Categoria cadastrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
	        carregarComboBox();
	    } catch (SQLException | IOException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Erro ao cadastrar categoria.", "Erro", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	private void carregarComboBox() {
		
		try {
			
			this.categorias = new CategoriaService().buscarTodos();
			
			this.comboBox.removeAllItems();
			for(Categoria categoria : categorias) {
				this.comboBox.addItem(categoria.getNome());
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
}
