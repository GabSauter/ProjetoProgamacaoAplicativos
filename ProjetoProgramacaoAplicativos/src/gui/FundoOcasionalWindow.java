
package gui;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import entities.FundoOcasional;
import service.FundoOcasionalService;

public class  FundoOcasionalWindow extends JFrame {

 
    private JTable table;
    private JTextField txtFundo;
    private JTextField txtValor;
    private JPanel panelCadastro;
    private JLabel lblDespesa;
    private JButton btnCadastrarFundo;
    private JButton btnLimparCampos;
    private JLabel lblValor;
    private JPanel panelTipoFundo;
    private JRadioButton rdbtnOcasional;
    private JRadioButton rdbtnMensal;
    private JPanel panelFundos;
    private JButton btnEditarFundo;
    private JButton btnExcluirFundo;
    private JScrollPane scrollPane;
    private List<FundoOcasional> fundos;

	/**
	 * Launch the application.
	 */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FundoOcasionalWindow window = new FundoOcasionalWindow();
                window.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
	 
/**
 * Create the frame.
 * @wbp.parser.entryPoint
 */
    public FundoOcasionalWindow() {
        this.initComponents();
        this.carregaTabelaFundo();
    }

    private void initComponents() {
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 570, 390);
        getContentPane().setLayout(null);
        
        panelCadastro = new JPanel();
        panelCadastro.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Cadastrar  fundo de despesas ocasionais", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
        panelCadastro.setLayout(null);
        panelCadastro.setBounds(10, 12, 548, 145);
        getContentPane().add(panelCadastro);
        
        lblDespesa = new JLabel("Despesa:");
        lblDespesa.setBounds(12, 28, 99, 14);
        panelCadastro.add(lblDespesa);
        
        txtFundo = new JTextField();
        txtFundo.setColumns(10);
        txtFundo.setBounds(109, 26, 150, 20);
        panelCadastro.add(txtFundo);
        
        btnCadastrarFundo = new JButton("Cadastrar");
        btnCadastrarFundo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnCadastrarActionPerformed();
        	}
        });
        btnCadastrarFundo.setBounds(269, 110, 105, 23);
        panelCadastro.add(btnCadastrarFundo);
        
        btnLimparCampos = new JButton("Limpar campos");
        btnLimparCampos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnLimparCamposActionPerformed();
        	}
        });
        btnLimparCampos.setBounds(386, 110, 150, 23);
        panelCadastro.add(btnLimparCampos);
        
        lblValor = new JLabel("Valor:");
        lblValor.setBounds(12, 54, 70, 15);
        panelCadastro.add(lblValor);
        
        
        txtValor = new JTextField();
        txtValor.setColumns(10);
        txtValor.setBounds(109, 52, 150, 20);
        panelCadastro.add(txtValor);
        
        panelTipoFundo = new JPanel();
        
                panelTipoFundo.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Tipo de despesa", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
                panelTipoFundo.setBounds(12, 83, 247, 50);
                panelCadastro.add(panelTipoFundo);
                panelTipoFundo.setLayout(null);
                
                rdbtnOcasional = new JRadioButton("Ocasional");
                rdbtnOcasional.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		rdbtnMensal.setSelected(false);
                	}
                });
                rdbtnOcasional.setBounds(8, 19, 95, 23);
                panelTipoFundo.add(rdbtnOcasional);
                
                rdbtnMensal = new JRadioButton("Mensal");
                rdbtnMensal.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		rdbtnOcasional.setSelected(false);
                	}
                });
                rdbtnMensal.setBounds(136, 19, 89, 23);
                panelTipoFundo.add(rdbtnMensal);

        panelFundos = new JPanel();
        panelFundos.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Fundos Cadastrados", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
        panelFundos.setBounds(10, 169, 548, 186);
        getContentPane().add(panelFundos);
        panelFundos.setLayout(null);

        btnEditarFundo = new JButton("Editar");
        btnEditarFundo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnEditarActionPerformed();
        	}
        });
        btnEditarFundo.setBounds(333, 162, 100, 23);
        panelFundos.add(btnEditarFundo);

        btnExcluirFundo = new JButton("Excluir");
        btnExcluirFundo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnExcluirFundoActionPerformed();
        	}
        });
        btnExcluirFundo.setBounds(438, 162, 100, 23);
        panelFundos.add(btnExcluirFundo);
        
                scrollPane = new JScrollPane();
                scrollPane.setBounds(12, 14, 526, 136);
                panelFundos.add(scrollPane);
                
                        table = new JTable();
                        table.addMouseListener(new MouseAdapter() {
                			@Override
                			public void mouseClicked(MouseEvent e) {
                				tableMouseClickedAction();
                			}
                		});
                        table.setModel(new DefaultTableModel(
                            new Object[][] {
                            },
                            new String[] {
                                "Fundo Ocasional", "Mensal", "Ocasional", "Total Anual"
                            }
                        ));
                        scrollPane.setViewportView(table);
    }

	protected void btnExcluirFundoActionPerformed() {
		 int coluna = table.getSelectedRow();

	        if (coluna == -1) {
	            JOptionPane.showMessageDialog(this, "Selecione um fundo para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
	            return;
	        }
	        
	        FundoOcasional fundo = fundos.get(coluna);

	        int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o fundo: " + fundo.getFundoOcasional() + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
	        if (resposta == JOptionPane.YES_OPTION) {
	            try {
	                new FundoOcasionalService().excluir(fundo);
	                JOptionPane.showMessageDialog(this, "Fundo Ocasional excluido.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
	                carregaTabelaFundo();
	            } catch (SQLException | IOException e) {
	                e.printStackTrace();
	                JOptionPane.showMessageDialog(this, "Erro ao excluir Fundo Ocasional.", "Erro", JOptionPane.ERROR_MESSAGE);
	            }
	        }
		
	}

	protected void btnEditarActionPerformed() {
		int coluna = table.getSelectedRow();

        if (coluna == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um fundo para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        FundoOcasional fundo = fundos.get(coluna);
        
        int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente editar o fundo: " + fundo.getFundoOcasional() + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
    			if(!(txtFundo.getText().toString().equals("") || txtValor.getText().toString().equals(""))) {
    				
    				FundoOcasional fundoEditado = new FundoOcasional();
    				fundoEditado.setId(fundo.getId());
    				fundoEditado.setFundoOcasional(txtFundo.getText());
    				try {
    					if(rdbtnMensal.isSelected()){
    						fundoEditado.setMensal(Double.parseDouble(txtValor.getText()));
    						fundoEditado.setOcasional(0.0);
    						fundoEditado.setTotalAno(Double.parseDouble(txtValor.getText())*12);
    					}
    					if(rdbtnOcasional.isSelected()){
    						fundoEditado.setOcasional(Double.parseDouble(txtValor.getText()));
    						fundoEditado.setMensal(0.0);
    						fundoEditado.setTotalAno(Double.parseDouble(txtValor.getText()));
    					}
    					
    					new FundoOcasionalService().atualizar(fundoEditado);
    					JOptionPane.showMessageDialog(this, "Fundo editado.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    					carregaTabelaFundo();
    				} catch (NumberFormatException e) {
    				    JOptionPane.showMessageDialog(this, "Valor inválido. Insira um valor numérico.", "Erro", JOptionPane.ERROR_MESSAGE);
    				} catch (SQLException | IOException e) {
    					e.printStackTrace();
    					JOptionPane.showMessageDialog(this, "Erro ao editar Fundo.", "Erro", JOptionPane.ERROR_MESSAGE);
    				}
    			}else {
    				JOptionPane.showMessageDialog(this, "Por favor insira valores em todos os campos para editar um fundo.", "Aviso", JOptionPane.WARNING_MESSAGE);
    			}
        		
        }
	}

	protected void btnLimparCamposActionPerformed() {
		txtFundo.setText("");
		txtValor.setText("");
	}

	protected void tableMouseClickedAction() {
	   	int coluna = table.getSelectedRow();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		txtFundo.setText(model.getValueAt(coluna, 0).toString());
		
		Boolean isMensal = (Double.parseDouble(model.getValueAt(coluna, 1).toString()) > Double.parseDouble(model.getValueAt(coluna, 2).toString()));
		String valor =  isMensal ? model.getValueAt(coluna, 1).toString() : model.getValueAt(coluna, 2).toString();
		txtValor.setText(valor);
		
		if(isMensal) {
			rdbtnMensal.setSelected(true);
			rdbtnOcasional.setSelected(false);
		}else {
			rdbtnMensal.setSelected(false);
			rdbtnOcasional.setSelected(true);
		}
		
	}

	protected void btnCadastrarActionPerformed() {
		FundoOcasional fundo = new FundoOcasional();
		fundo.setFundoOcasional(this.txtFundo.getText());
		if(!(txtFundo.getText().toString().equals("") || txtValor.getText().toString().equals(""))) {
			try {
				if(this.rdbtnMensal.isSelected()) {
					fundo.setMensal(Double.parseDouble(txtValor.getText()));
					fundo.setOcasional(0.0);
					fundo.setTotalAno(Double.parseDouble(txtValor.getText())*12);
				}
				if(this.rdbtnOcasional.isSelected()) {
					fundo.setOcasional(Double.parseDouble(txtValor.getText()));
					fundo.setMensal(0.0);
					fundo.setTotalAno(Double.parseDouble(txtValor.getText()));
				}
					new FundoOcasionalService().cadastrar(fundo);
					this.carregaTabelaFundo();
			}  catch (NumberFormatException e) {
			    JOptionPane.showMessageDialog(this, "Valor inválido. Insira um valor numérico.", "Erro", JOptionPane.ERROR_MESSAGE);
			} catch (SQLException | IOException e) {
				JOptionPane.showMessageDialog(this, "Erro ao cadastrar Fundo.", "Erro", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(this, "Por favor insira valores em todos os campos para cadastrar um Fundo.", "Aviso", JOptionPane.WARNING_MESSAGE);
		}
		
	}
	private void carregaTabelaFundo() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.fireTableDataChanged();
		model.setRowCount(0);
		
		fundos = new ArrayList<FundoOcasional>();
		try {
			fundos = new FundoOcasionalService().buscarTodos();
			for(FundoOcasional fundo: fundos) {
				model.addRow(new Object[] {
						fundo.getFundoOcasional(),
						fundo.getMensal(),
						fundo.getOcasional(),
						fundo.getTotalAno()
				});
			}
		} catch (SQLException | IOException e) {
			JOptionPane.showMessageDialog(this, "Erro ao carregar Fundos Ocasionais.", "Erro", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}